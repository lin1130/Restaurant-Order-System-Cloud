<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="order id" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="Please input order id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="item id" prop="itemId">
        <el-input
          v-model="queryParams.itemId"
          placeholder="Please input item id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="quantity" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="Please input quantity"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">search</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">reset</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="detailsList" @selection-change="handleSelectionChange">
      <el-table-column label="detail id" align="center" prop="detailId" />
      <el-table-column label="order id" align="center" prop="orderId" />
      <el-table-column label="item id" align="center" prop="itemId" />
      <el-table-column label="quantity" align="center" prop="quantity" />
    </el-table>

  </div>
</template>

<script>
import { listDetails, getDetails, delDetails, addDetails, updateDetails } from "@/api/details/details";

export default {
  name: "Details",
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
      // details表格数据
      detailsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 1000,
        orderId: null,
        itemId: null,
        quantity: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询details列表 */
    getList() {
      this.loading = true;
      listDetails(this.queryParams).then(response => {
        this.detailsList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        detailId: null,
        orderId: null,
        itemId: null,
        quantity: null
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
      this.ids = selection.map(item => item.detailId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
  }
};
</script>
