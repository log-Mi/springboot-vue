<template>
  <div style="padding: 10px">
    <!-- 功能区 -->
    <div style="margin: 10px 0">
        <el-button type="primary" @click="add">新增</el-button> 
    </div>

    <!-- 搜索区 -->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" @keyup.enter="load" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">搜索</el-button> 
    </div>

    <!-- 展示区 -->
    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%">
      <el-table-column
      prop="flowerId"
      label="编号"
      sortable>

      </el-table-column>
      <el-table-column
      prop="flowerName"
      label="花名">

      </el-table-column>
      <el-table-column
      prop="flowerPrice"
      label="价格">

      </el-table-column>
      <el-table-column
      prop="flowerStock"
      label="库存">

      </el-table-column>
      <el-table-column
      prop="staffId"
      label="操作员工编号">

      </el-table-column>

      <el-table-column
      prop="createTime"
      label="创建时间">

      </el-table-column>
      <el-table-column
      label="操作">

      <template #default="scope">
        <el-button @click="handleEdit(scope.row)" size="small">编辑</el-button>
        <el-popconfirm
          title="这是一段内容确定删除吗？" @confirm="handleDelete(scope.row.flowerId)">
          <template #reference>
            <el-button size="small" type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 20]"
      :page-size="10"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>

    <el-dialog
      title="提示"
      v-model="dialogVisible"
      width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="花名">
          <el-input v-model="form.flowerName" style="width: 80%"></el-input>
        </el-form-item>
        <!-- <el-form-item label="性别">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
          <el-radio v-model="form.sex" label="未知">未知</el-radio>
        </el-form-item> -->
        <el-form-item label="数量">
          <el-input v-model="form.flowerStock" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.flowerPrice" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="更新日期">
          <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width: 80%" clearable></el-date-picker>
        </el-form-item>
        <el-form-item label="供应商编号">
          <el-input v-model="form.provId" style="width: 80%"></el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
    </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request.js';

export default {
  name: 'Flower',
  components: {

  },
  data() {
      return {
        form: {},
        dialogVisible: false,
        search:'',
        CurrentChange: 1,
        currentPage: 1,
        pageSize: 10,
        total: 0,
        tableData: []
      }
    },
    created(){
      this.load()
    },
    methods: {
      handleEdit(row){
        this.form = JSON.parse(JSON.stringify(row))
        this.dialogVisible = true
      },
      handleDelete(flowerId){
        request.delete("http://localhost:8081/flower/" + flowerId).then(res=>{
          if (res.code === '0'){
              this.$message({
                type: "success",
                message: "删除成功"
              })
            }else{
              this.$message({
                type: "error",
                message: res.smg
              })
            }
          this.load()
        })
      },
      handleSizeChange(pageSize){
        this.pageSize = pageSize
        this.load()
      },
      handleCurrentChange(pageNum){
        this.currentPage = pageNum
        this.load()
      },
      add(){
        this.dialogVisible = true
        this.form = {}
      },
      save(){
        if (this.form.flowerId){
            this.form.staffId = JSON.parse(sessionStorage.getItem('staff')).staffId
            request.put("http://localhost:8081/flower", this.form).then(res => {
                // console.log(res);
                if (res.code === '0'){
                this.$message({
                    type: "success",
                    message: "更新成功"
                })
                }else{
                this.$message({
                    type: "error",
                    message: res.msg
                })
                }
            })
            this.load()
            this.dialogVisible = false
        }else{
            this.form.staffId = JSON.parse(sessionStorage.getItem('staff')).staffId
            request.post("http://localhost:8081/flower", this.form).then(res => {
                // console.log(res);
                if (res.code === '0'){
                this.$message({
                    type: "success",
                    message: "新建成功"
                })
                }else{
                this.$message({
                    type: "error",
                    message: res.msg
                })
                }
            })
            this.load()
            this.dialogVisible = false
        }
        
      },
      load(){
        request.get("http://localhost:8081/flower", {
          params:{
            pageNum: this.currentPage, 
            pageSize: this.pageSize, 
            search: this.search}}).then(res => {
              console.log(res);
              this.tableData = res.data.records
              this.total = res.data.total
        })
      }

    },
}
</script>
