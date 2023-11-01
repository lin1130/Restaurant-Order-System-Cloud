<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="item name" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="Please input item name"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="item description" prop="itemDescription">
        <el-input
          v-model="queryParams.itemDescription"
          placeholder="Please input item description"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">search</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">reset</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['item:item:add']"
        >new item</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
      <el-table-column label="item id" align="center" prop="itemId" />
      <el-table-column label="item picture" align="center" prop="itemPic" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.itemPic" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="item name" align="center" prop="itemName" />
      <el-table-column label="item price" align="center" prop="itemPrice" />
      <el-table-column label="item description" align="center" prop="itemDescription" />
      <el-table-column label="counter" align="center" v-if="user.userId != 1">
        <template slot-scope="scope">
          <el-input
            v-model="scope.row.counter"
            size="mini"
            type="number"
            :min="0"
            @change="handleCounterChange(scope.row)"
          ></el-input>
        </template>
      </el-table-column>
      <el-table-column label="operation" align="center" class-name="small-padding fixed-width" v-if="user.userId === 1">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['item:item:edit']"
          >modify</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['item:item:remove']"
          >delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row style="text-align: right; margin-top: 50px;" v-if="user.userId != 1">
      <el-button type="primary" @click="submitCounters">Submit Order</el-button>
    </el-row>

    <!-- 添加或修改item对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" >
        <el-form-item label="item picture" prop="itemPic">
          <image-upload v-model="form.itemPic"/>
        </el-form-item>
        <el-form-item label="item name" prop="itemName">
          <el-input v-model="form.itemName" placeholder="Please input item name" />
        </el-form-item>
        <el-form-item label="item price" prop="itemPrice">
          <el-input v-model="form.itemPrice" placeholder="Please input item price" />
        </el-form-item>
        <el-form-item label="item description" prop="itemDescription">
          <el-input v-model="form.itemDescription" placeholder="Please input item description" />
        </el-form-item>
        <el-form-item label="order number" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="Please input order number" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">yes</el-button>
        <el-button @click="cancel">cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/item/item";
import { getUserProfile } from "@/api/system/user";
import { addOrders } from "@/api/order/orders";
import {addDetails} from "@/api/details/details";

export default {
  name: "Item",
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
      // item表格数据
      itemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 1000,
        itemName: null,
        itemPrice: null,
        itemDescription: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      counters: {}, // 用于存储计数器值的对象
      submissionData: [], // 用于存储提交内容的数组
      currentTime: null,
      user: {},
      newOrderId: null
    };
  },
  created() {
    this.getList();
    this.getUser();
  },
  computed: {
    computedItemList() {
      return this.itemList.map(item => {
        return {
          ...item,
          counter: this.counters[item.itemId] || 0 // 使用 counters 对象中的值，如果不存在则默认为0
        };
      });
    }
  },
  methods: {
    /** 查询item列表 */
    getList() {
      this.loading = true;
      listItem(this.queryParams).then(response => {
        this.itemList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
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
        itemId: null,
        itemPic: null,
        itemName: null,
        itemPrice: null,
        itemDescription: null,
        orderNum: null
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.itemId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "add item";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const itemId = row.itemId || this.ids
      getItem(itemId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "edit item";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.itemId != null) {
            updateItem(this.form).then(response => {
              this.$modal.msgSuccess("Modified successfully");
              this.open = false;
              this.getList();
            });
          } else {
            addItem(this.form).then(response => {
              this.$modal.msgSuccess("Added successfully");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const itemIds = row.itemId || this.ids;
      this.$modal.confirm('Do you want to delete the data item whose item number is"' + itemIds + '"').then(function() {
        return delItem(itemIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("Delete successfully");
      }).catch(() => {});
    },
    handleCounterChange(item) {
    // 更新计数器的值到 counters 对象中
    this.$set(this.counters, item.itemId, item.counter);
    },
    getCurrentTime() {
      const currentDate = new Date();
      const year = currentDate.getFullYear();
      const month = String(currentDate.getMonth() + 1).padStart(2, '0');
      const day = String(currentDate.getDate()).padStart(2, '0');
      const hours = String(currentDate.getHours()).padStart(2, '0');
      const minutes = String(currentDate.getMinutes()).padStart(2, '0');
      const seconds = String(currentDate.getSeconds()).padStart(2, '0');
      this.currentTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    },
    // 当前登录用户ID及时间
    getOrderInfo(){
      this.orderInfo = {
        userId: this.user.userId,
        orderTime: this.currentTime
      }
    },
    async submitCounters() {
      this.getCurrentTime();
      this.getOrderInfo();

      const nonZeroCounters = Object.values(this.counters).filter(counter => counter > 0);

      if (nonZeroCounters.length === 0) {
        this.$modal.msgWarning("Item unadded");
      } else {
        try {
          // 执行提交操作
          const response = await addOrders(this.orderInfo);
          this.$modal.msgSuccess("Order creation success");
          console.log(response);
          this.newOrderId = response.orderId;
          console.log("New Order ID:", this.newOrderId);

          // 构建提交数据
          this.submissionData = this.computedItemList
            .filter(item => item.counter > 0)
            .map(item => ({
              itemId: item.itemId,
              counter: item.counter
            }));
          console.log(this.submissionData);

          // 分条加入details表
          for (let i = 0; i < this.submissionData.length; i++) {
            const item = this.submissionData[i];
            const detailInfo = {
              orderId: this.newOrderId,
              itemId: item.itemId,
              quantity: item.counter
            };
            const detailResponse = await addDetails(detailInfo);
            // this.$modal.msgSuccess("Details creation success");
            console.log(detailResponse);
            console.log('Counter:', item.counter);
            console.log('Item Name:', item.itemId);
          }
        } catch (error) {
          // 处理错误
          console.error("An error occurred:", error);
        }
      }
    }
  }
};
</script>
