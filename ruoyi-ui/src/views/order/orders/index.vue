<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
<!--      <el-form-item label="user id" prop="userId">-->
<!--        <el-input-->
<!--          v-model="queryParams.userId"-->
<!--          placeholder="Please input user id"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="order state" prop="orderState">
        <el-input
          v-model="queryParams.orderState"
          placeholder="Please input order state"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">search</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">reset</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="ordersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="order id" align="center" prop="orderId" />
      <el-table-column label="user id" align="center" prop="userId" />
      <el-table-column label="order time" align="center" prop="orderTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="order state" align="center" prop="orderState" />
      <el-table-column label="operation" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['orders:orders:edit']"
            :disabled="scope.row.orderState === 'completed'"
            v-if="user.userId === 1"
          >edit state</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="viewDetails(scope.row)"
          >view details</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改orders对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <span>Are you sure you want to change the order status to Completed?</span>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">yes</el-button>
        <el-button @click="cancel">cancel</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="title" :visible.sync="openView" width="500px" append-to-body>
      <el-table v-loading="loading" :data="detailsList">
        <el-table-column label="detail id" align="center" prop="detailId" />
        <el-table-column label="order id" align="center" prop="orderId" />
        <el-table-column label="item id" align="center" prop="itemId" />
        <el-table-column label="quantity" align="center" prop="quantity" />
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { listOrders, getOrders, delOrders, addOrders, updateOrders } from "@/api/order/orders";
import {getUserProfile} from "@/api/system/user";
import { listDetails} from "@/api/details/details";


export default {
  name: "Orders",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // orders表格数据
      ordersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openView: false,
      // order state时间范围
      daterangeOrderTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100000,
        orderId: null,
        userId: null,
        orderTime: null,
        orderState: null
      },
      queryDetails: {
        orderId: null,
        itemId: null,
        quantity: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      user: {},
      detailsList: []
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    /** 查询orders列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if(this.user.userId != 1){
        this.queryParams.userId = this.user.userId;
      }

      if (null != this.daterangeOrderTime && '' != this.daterangeOrderTime) {
        this.queryParams.params["beginOrderTime"] = this.daterangeOrderTime[0];
        this.queryParams.params["endOrderTime"] = this.daterangeOrderTime[1];
      }
      listOrders(this.queryParams).then(response => {
        this.ordersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.getList();
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        orderId: null,
        userId: null,
        orderTime: null,
        orderState: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeOrderTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrders(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "edit orders";
      });
    },
    // 查看订单详情
    viewDetails(row){
      this.reset();
      const orderId = row.orderId || this.ids;
      this.queryDetails.orderId = orderId;
      listDetails(this.queryDetails).then(response => {
        this.detailsList = response.rows;
        this.openView = true;
        this.title = "view details";
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.form.orderState = "completed";
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrders(this.form).then(response => {
              this.$modal.msgSuccess("Modified successfully");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('Do you want to delete the data item whose orders number is"' + orderIds + '"').then(function() {
        return delOrders(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("Delete Successfully");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('orders/orders/export', {
        ...this.queryParams
      }, `orders_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
