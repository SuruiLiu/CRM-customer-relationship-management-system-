<template>
    <div>
      <div>
          <el-input style="width: 200px;" placeholder="查询客户" v-model="name"></el-input>
          <el-button type="primary" @click="load(1)">查询</el-button>
          <el-button type="info" style="background-color: #6e77f2;" @click="reset">重置</el-button>
      </div>
      <div style="margin: 10px 0px;">
          <el-button type="primary" @click="add" plain>新增客户</el-button>
          <el-dialog title='新增客户' center :visible.sync='addVisible' >
                  <el-form :model='addcustomer' label-width='80px' :rules="rules" ref="addRef">
                      <el-form-item label='客户名' prop="cname">
                          <el-input v-model='addcustomer.cname'></el-input>
                      </el-form-item>
                      <el-form-item label='客户类型' prop="ctype">
                          <el-input v-model='addcustomer.ctype'></el-input>
                      </el-form-item>
                      <el-form-item label='客户等级' prop="clevel">
                          <el-input v-model='addcustomer.clevel'></el-input>
                      </el-form-item>
                      <el-form-item label='客户行业' prop="cbusiness">
                          <el-input v-model='addcustomer.cbusiness'></el-input>
                      </el-form-item>
                      <el-form-item label='客户来源' prop="corigin">
                          <el-input v-model='addcustomer.corigin'></el-input>
                      </el-form-item>
                      <el-form-item label='负责人' prop="cman">
                          <el-input v-model='addcustomer.cman'></el-input>
                      </el-form-item>
                      <el-form-item label='客户地址' prop="caddress">
                          <el-input v-model='addcustomer.caddress'></el-input>
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
          <el-table-column prop="cid" label="序号"></el-table-column>
          <el-table-column prop="cname" label="客户名"></el-table-column>
          <el-table-column prop="ctype" label="客户类型"></el-table-column>
          <el-table-column prop="clevel" label="客户等级"></el-table-column>
          <el-table-column prop="cbusiness" label="客户行业"></el-table-column>
          <el-table-column prop="cman" label="负责人"></el-table-column>
          <el-table-column label="操作" align="center" width="180">
              <template slot-scope="scope" >
                  <el-button type="primary"  @click='handleEdit(scope.row)' plain>编辑</el-button>
                  <el-dialog title='编辑客户信息' center :visible.sync='innerVisible' >
                  <el-form :model='customer' label-width='80px'>
                      <el-form-item label='客户名' prop="cname">
                          <el-input v-model='customer.cname'></el-input>
                      </el-form-item>
                      <el-form-item label='客户类型' prop="ctype">
                          <el-input v-model='customer.ctype'></el-input>
                      </el-form-item>
                      <el-form-item label='客户等级' prop="clevel">
                          <el-input v-model='customer.clevel'></el-input>
                      </el-form-item>
                      <el-form-item label='客户行业' prop="cbusiness">
                          <el-input v-model='customer.cbusiness'></el-input>
                      </el-form-item>
                      <el-form-item label='客户来源' prop="corigin">
                          <el-input v-model='customer.corigin'></el-input>
                      </el-form-item>
                      <el-form-item label='负责人' prop="cman">
                          <el-input v-model='customer.cman'></el-input>
                      </el-form-item>
                      <el-form-item label='客户地址' prop="caddress">
                          <el-input v-model='customer.caddress'></el-input>
                      </el-form-item>
                      <el-form-item>
                          <div style="text-align: center; margin-right: 60px; " >
                              <el-button type="info" style="background-color: #6e77f2;" @click="updatecurrent">保 存</el-button>
                          </div>
                      </el-form-item>
                  </el-form>
                  </el-dialog>
                  <el-button type="danger" @click="del(scope.row.cid)" plain>删除</el-button>
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
    name: 'customer',
    data () {
      return {
          tableData: [],
          cids: [],
          addVisible: false,
          innerVisible: false,
          customer: { cname: '', ctype: '', clevel: '', cbusiness: '', corigin: '', cman:'', caddress:'' },
          addcustomer: { cname: '', ctype: '', clevel: '', cbusiness: '', corigin: '', cman:'', caddress:'' },
          pageNum: 1, //当前页码
          pageSize: 5, //每页显示的个数
          name: '',
          total: 0,
          rules: {
                  cname: [
                      { required: true, message: '请输入客户名', trigger: 'blur' }
                  ],
                  ctype: [
                      { required: true, message: '请输入客户类型', trigger: 'blur' }
                  ],
                  clevel: [
                      { required: true, message: '请输入客户等级', trigger: 'blur' }
                  ],
                  cbusiness: [
                      { required: true, message: '请输入客户行业', trigger: 'blur' }
                  ],
                  corigin: [
                      { required: true, message: '请输入客户来源', trigger: 'blur' }
                  ],
                  cman: [
                      { required: true, message: '请输入负责人', trigger: 'blur' }
                  ],
                  caddress: [
                      { required: true, message: '请输入客户地址', trigger: 'blur' }
                  ],
              }
      }
    },
    created () {
      this.load()
    },
    methods: {
      del(cid){
          this.$confirm('确认删除吗？','确认删除',{type: "warning"}).then(response =>{
              this.$request.delete('/customer/delete/' + cid).then(res =>{
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
          this.$request.get('/customer/selectByPage' ,{
              params: {
                  pageNum: this.pageNum,
                  pageSize: this.pageSize,
                  cname: this.name,
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
          this.addcustomer.cname = ''
          this.addcustomer.ctype = ''
          this.addcustomer.clevel = ''
          this.addcustomer.cbusiness = ''
          this.addcustomer.corigin = ''
          this.addcustomer.cman = ''
          this.addcustomer.caddress = ''
          this.addVisible = true
      },
      addcurrent(){
          this.$refs['addRef'].validate((valid) => {
                  if (valid) {
                      //验证通过，再发请求
                      this.$request.post('/customer/add', this.addcustomer).then(res => {
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
          this.$request.put('/customer/update',this.customer).then(res =>{
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
                  this.customer = row
                  this.innerVisible = true
              },
      handleSelectionChange(rows){
          this.cids = rows.map(v => v.cid)
      },
      delBatch(){
          if(!this.cids.length){
                  this.$message.warning('请选择数据')
                  return
              }
          this.$confirm('确认批量删除吗？','确认删除',{type: "warning"}).then(response =>{
              this.$request.delete('/customer/delete/batch', {data: this.cids}).then(res =>{
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
  