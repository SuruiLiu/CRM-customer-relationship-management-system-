<template>
    <div>
      <div>
          <el-input style="width: 200px;" placeholder="查询联系人" v-model="name"></el-input>
          <el-button type="primary" @click="load(1)">查询</el-button>
          <el-button type="info" style="background-color: #6e77f2;" @click="reset">重置</el-button>
      </div>
      <div style="margin: 10px 0px;">
          <el-button type="primary" @click="add" plain>新增联系人</el-button>
          <el-dialog title='新增联系人' center :visible.sync='addVisible' >
                  <el-form :model='addlinkman' label-width='80px' :rules="rules" ref="addRef">
                      <el-form-item label='联系姓名' prop="lname">
                          <el-input v-model='addlinkman.lname'></el-input>
                      </el-form-item>
                      <el-form-item label='性别' prop="lgender">
                          <el-input v-model='addlinkman.lgender'></el-input>
                      </el-form-item>
                      <el-form-item label='客户' prop="lcname">
                          <el-input v-model='addlinkman.lcname'></el-input>
                      </el-form-item>
                      <el-form-item label='部门' prop="lapart">
                          <el-input v-model='addlinkman.lapart'></el-input>
                      </el-form-item>
                      <el-form-item label='负责人' prop="lman">
                          <el-input v-model='addlinkman.lman'></el-input>
                      </el-form-item>
                      <el-form-item label='手机' prop="lphone">
                          <el-input v-model='addlinkman.lphone'></el-input>
                      </el-form-item>
                      <el-form-item label='备注' prop="ldescr">
                          <el-input v-model='addlinkman.ldescr'></el-input>
                      </el-form-item>
                      <el-form-item>
                          <div style="text-align: center; margin-right: 60px; " >
                              <el-button type="info" style="background-color: #6e77f2;" @click="addcurrent">添 加</el-button>
                          </div>
                      </el-form-item>
                  </el-form>
          </el-dialog>
          <el-button type="danger" @click="delBatch" plain>批量删除</el-button>
      </div>
      <el-table :data="tableData" stripe :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666'}" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" algin="center"></el-table-column>
          <el-table-column prop="lid" label="序号"></el-table-column>
          <el-table-column prop="lname" label="联系人"></el-table-column>
          <el-table-column prop="lgender" label="性别"></el-table-column>
          <el-table-column prop="lcname" label="客户"></el-table-column>
          <el-table-column prop="lapart" label="所属部门"></el-table-column>
          <el-table-column prop="lman" label="负责人"></el-table-column>
          <el-table-column prop="lphone" label="联系方式"></el-table-column>
          <el-table-column label="操作" align="center" width="180">
              <template slot-scope="scope" >
                  <el-button type="primary"  @click='handleEdit(scope.row)' plain>编辑</el-button>
                  <el-dialog title='编辑联系人信息' center :visible.sync='innerVisible' >
                  <el-form :model='linkman' label-width='80px'>
                      <el-form-item label='联系姓名' prop="lname">
                          <el-input v-model='linkman.lname'></el-input>
                      </el-form-item>
                      <el-form-item label='性别' prop="lgender">
                          <el-input v-model='linkman.lgender'></el-input>
                      </el-form-item>
                      <el-form-item label='客户' prop="lcname">
                          <el-input v-model='linkman.lcname'></el-input>
                      </el-form-item>
                      <el-form-item label='部门' prop="lapart">
                          <el-input v-model='linkman.lapart'></el-input>
                      </el-form-item>
                      <el-form-item label='负责人' prop="lman">
                          <el-input v-model='linkman.lman'></el-input>
                      </el-form-item>
                      <el-form-item label='手机' prop="lphone">
                          <el-input v-model='linkman.lphone'></el-input>
                      </el-form-item>
                      <el-form-item label='备注' prop="ldescr">
                          <el-input v-model='linkman.ldescr'></el-input>
                      </el-form-item>
                      <el-form-item>
                          <div style="text-align: center; margin-right: 60px; " >
                              <el-button type="info" style="background-color: #6e77f2;" @click="updatecurrent">保 存</el-button>
                          </div>
                      </el-form-item>
                  </el-form>
                  </el-dialog>
                  <el-button type="danger" @click="del(scope.row.lid)" plain>删除</el-button>
              </template>
          </el-table-column>
      </el-table>
      <div style="margin: 10px 0; display: flex; align-items: center; justify-content: flex-end;">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="pageSize"
        layout="total, prev, pager, next"
        :total="total">
      </el-pagination>
    </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'linkman',
    data () {
      return {
          tableData: [],
          lids: [],
          addVisible: false,
          innerVisible: false,
          linkman: { lman: '', lgender: '', lcname: '', lapart: '', lman: '', lphone:'', ldescr:'' },
          addlinkman: { lman: '', lgender: '', lcname: '', lapart: '', lman: '', lphone:'', ldescr:'' },
          pageNum: 1, //当前页码
          pageSize: 5, //每页显示的个数
          name: '',
          total: 0,
          rules: {
                  lname: [
                      { required: true, message: '请输入联系人姓名', trigger: 'blur' }
                  ],
                  lgender: [
                      { required: true, message: '请输入性别', trigger: 'blur' }
                  ],
                  lcname: [
                      { required: true, message: '请输入客户', trigger: 'blur' }
                  ],
                  lapart: [
                      { required: true, message: '请输入部门', trigger: 'blur' }
                  ],
                  lman: [
                      { required: true, message: '请输入负责人', trigger: 'blur' }
                  ],
                  lphone: [
                      { required: true, message: '请输入手机', trigger: 'blur' }
                  ],
                  ldescr: [
                      { required: true, message: '请输入备注', trigger: 'blur' }
                  ],
              }
      }
    },
    created () {
      this.load()
    },
    methods: {
      del(lid){
          this.$confirm('确认删除吗？','确认删除',{type: "warning"}).then(response =>{
              this.$request.delete('/linkman/delete/' + lid).then(res =>{
                  if(res.code === '200'){
                  this.$message.success('删除成功')   
                  this.load(1)
                  } else {
                      this.$message.error(res.msg)
                  }
              })
          }).catch(()=>{})
      },
      reset(){
          this.name = ''
          this.load(1)
      },
      load(pageNum){
          if(pageNum){
              this.pageNum = pageNum
          }
          this.$request.get('/linkman/selectByPage' ,{
              params: {
                  pageNum: this.pageNum,
                  pageSize: this.pageSize,
                  lname: this.name,
              }
          }).then(res => {
              this.tableData = res.data.records
              this.total = res.data.total
          })
      },
      handleCurrentChange(pageNum){
          this.load(pageNum)
      },
      add(){
          this.addlinkman.lcname = ''
          this.addlinkman.lgender = ''
          this.addlinkman.lcname = ''
          this.addlinkman.lapart = ''
          this.addlinkman.lman = ''
          this.addlinkman.lphone = ''
          this.addlinkman.ldescr = ''
          this.addVisible = true
      },
      addcurrent(){
          this.$refs['addRef'].validate((valid) => {
                  if (valid) {
                      //验证通过，再发请求
                      this.$request.post('/linkman/add', this.addlinkman).then(res => {
                          if(res.code === '200'){
                              this.addVisible=false
                              this.$message.success('添加成功')
                              this.load(1)
                          } else{
                              this.$message.error(res.msg)
                          }
                      })
                  }
              })
      },
      updatecurrent(){
          this.$request.put('linkman/update',this.linkman).then(res =>{
              if(res.code === '200'){
                  this.innerVisible=false
                  this.$message.success('保存成功')
                  this.load(1)
              } else {
                  this.$message.error(res.msg)
              }
          })
      },
      handleEdit(row) {
                  this.linkman = row
                  this.innerVisible = true
              },
      handleSelectionChange(rows){
          this.lids = rows.map(v => v.lid)
      },
      delBatch(){
          if(!this.lids.length){
                  this.$message.warning('请选择数据')
                  return
              }
          this.$confirm('确认批量删除吗？','确认删除',{type: "warning"}).then(response =>{
              this.$request.delete('/customer/delete/batch', {data: this.lids}).then(res =>{
                  if(res.code === '200'){
                  this.$message.success('删除成功')   
                  this.load(1)
                  } else {
                      this.$message.error(res.msg)
                  }
              })
          }).catch(()=>{})
      }
    },
    mounted() {},
    computed: {}
  }
  
  </script>
  <style scoped>
  </style>
  