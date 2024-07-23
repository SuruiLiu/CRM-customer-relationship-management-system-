<template>
    <div>
      <div>
          <el-input style="width: 200px;" placeholder="查询线索" v-model="name"></el-input>
          <el-button type="primary" @click="load(1)">查询</el-button>
          <el-button type="info" style="background-color: #6e77f2;" @click="reset">重置</el-button>
      </div>
      <el-table :data="tableData" stripe :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666'}" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" algin="center"></el-table-column>
          <el-table-column prop="xid" label="序号"></el-table-column>
          <el-table-column prop="xname" label="线索">
            <template slot-scope="scope">
                <a style="color: #409EFF; cursor: pointer;" @click="openclue(scope.row.xname)">{{ scope.row.xname }}</a>
                <el-drawer
                title="线索跟进-转换商机"
                :visible.sync="drawer"
                :size="drawerWidth"
                :direction="rtl">
                <div style="margin: 10px 0px;">
                    <div style="margin-left: 10px;">
                        <el-button type="primary" @click="addtrackfunc" plain>新增跟进信息</el-button>
                        <el-button type="danger" @click="turn(scope.row)" plain>转换成商机</el-button>
                        <el-dialog title='新增跟进' center :visible.sync='addclueVisible' append-to-body="ap">
                <el-form :model='addtrack' label-width='80px' :rules="trackrules" ref="addtrackRef">
                    <el-form-item label='跟进' prop="tname">
                        <el-input v-model='addtrack.tname'></el-input>
                    </el-form-item>
                    <el-form-item label='线索' prop="name">
                        <el-input v-model='addtrack.name' disabled></el-input>
                    </el-form-item>
                    <el-form-item label='方式（电话号）' prop="tway">
                        <el-input v-model='addtrack.tway'></el-input>
                    </el-form-item>
                    <el-form-item label='跟进详情' prop="tinfo">
                        <el-input v-model='addtrack.tinfo'></el-input>
                    </el-form-item>
                    <el-form-item label='负责人' prop="tman">
                        <el-input v-model='addtrack.tman'></el-input>
                    </el-form-item>
                    <el-form-item label='联系人' prop="linkman">
                        <el-input v-model='addtrack.linkman'></el-input>
                    </el-form-item>
                    <el-form-item>
                        <div style="text-align: center; margin-right: 60px; " >
                            <el-button type="info" style="background-color: #6e77f2;" @click="addcurrenttrack">添 加</el-button>
                        </div>
                    </el-form-item>
                </el-form>
                        </el-dialog>
                    </div>
                    <el-table :data="trackData" style="margin-left: 10px; margin-top: 20px;" stripe :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666'}">
                        <el-table-column prop="tname" label="跟进"></el-table-column>
                        <el-table-column prop="tman" label="负责人"></el-table-column>
                        <el-table-column prop="linkman" label="联系人"></el-table-column>
                        <el-table-column prop="tinfo" label="跟进详情"></el-table-column>
                        <el-table-column prop="tway" label="联系方式"></el-table-column>
                    </el-table>
                </div>
                
                </el-drawer>
            </template>
          </el-table-column>
          <el-table-column prop="cname" label="客户"></el-table-column>
          <el-table-column prop="xorigin" label="线索来源"></el-table-column>
          <el-table-column prop="xproduct" label="意向产品"></el-table-column>
          <el-table-column prop="xstate" label="线索状态">
          <template slot-scope="scope">
          <div  :style="{'color':scope.row.xstate == '未转换' ? '#E6A23C':'#67C23A'}">
            {{scope.row.xstate}}
          </div>
          </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="180">
              <template slot-scope="scope" >
                  <el-button type="primary"  @click='handleEdit(scope.row)' plain>修改</el-button>
                  <el-dialog title='修改线索信息' center :visible.sync='innerVisible' >
                  <el-form :model='clue' label-width='80px'>
                      <el-form-item label='线索' prop="xname">
                          <el-input v-model='clue.xname'></el-input>
                      </el-form-item>
                      <el-form-item label='客户' prop="cname">
                          <el-input v-model='clue.cname'></el-input>
                      </el-form-item>
                      <el-form-item label='线索来源' prop="xorigin">
                          <el-input v-model='clue.xorigin'></el-input>
                      </el-form-item>
                      <el-form-item label='意向产品' prop="xproduct">
                          <el-input v-model='clue.xproduct'></el-input>
                      </el-form-item>
                      <el-form-item label='线索等级' prop="xlevel">
                          <el-input v-model='clue.xlevel'></el-input>
                      </el-form-item>
                      <el-form-item label='负责人' prop="xman">
                          <el-input v-model='clue.xman'></el-input>
                      </el-form-item>
                      <el-form-item label='客户需求' prop="cneed">
                          <el-input v-model='clue.cneed'></el-input>
                      </el-form-item>
                      <el-form-item>
                          <div style="text-align: center; margin-right: 60px; " >
                              <el-button type="info" style="background-color: #6e77f2;" @click="updatecurrent">保 存</el-button>
                          </div>
                      </el-form-item>
                  </el-form>
                  </el-dialog>
                  <el-button type="danger" @click="del(scope.row.xid)" plain>删除</el-button>
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
    name: 'clue',
    data () {
      return {
          tableData: [],
          trackData: [],
          xids: [],
          addVisible: false,
          innerVisible: false,
          clue: { xname: '', cname: '', xorigin: '', xproduct: '', xlevel: '', xtime:'', xstate:'', xman:'', cneed:'' },
          addclue: { xname: '', cname: '', xorigin: '', xproduct: '', xlevel: '', xtime:'', xstate:'', xman:'', cneed:'' },
          addbusiness: { bname: '', bstate: '', bsum: '', bpdate: '', bphase: '', lname:'', bman:'' },
          addtrack: { tname: '', name: '', tway: '', tinfo: '', tman:'', linkman:'' },
          addcustomer: { cname: '', ctype: '', clevel: '', cbusiness: '', corigin: '', cman:'', caddress:'' },
          pageNum: 1, //当前页码
          pageSize: 5, //每页显示的个数
          name: '',
          addname: '',
          total: 0,
          drawer: false,
          drawerWidth: '60%',
          addclueVisible: false,
          trackrules: {
                tname: [
                    { required: true, message: '请输入跟进', trigger: 'blur' }
                ]
            }
      }
    },
    created () {
      this.load()
    },
    methods: {
        turn(row){
            this.$confirm('确认转换吗？','确认转换',{type: "warning"}).then(response =>{
                this.addbusiness.bname = row.xname
                this.addbusiness.bstate = '未成交'
                this.addbusiness.bsum = '0'
                this.addbusiness.bphase = '发现商机'
                this.addbusiness.bman = row.xman
                this.addbusiness.bpdate = '未定'
                this.addbusiness.lname = row.cname
                row.xstate = '已转换'
                this.addcustomer.cname = row.cname
                this.addcustomer.ctype = '合作商'
                this.addcustomer.clevel = '普通'
                this.addcustomer.cbusiness = '软件'
                this.addcustomer.corigin = row.xorigin
                this.addcustomer.cman = '张三'
                this.$request.post('/customer/add', this.addcustomer).then(res => {
                          if(res.code === '200'){
                              this.$message.success('客户添加成功')
                          } else{
                              this.$message.error(res.msg)
                          }
                      })
                this.$request.post('/business/add', this.addbusiness).then(res => {
                        if(res.code === '200'){
                            this.$message.success('添加成功')
                            this.$request.put('/clue/update', row)
                            this.$router.push('/business')
                        } else{
                            this.$message.error(res.msg)
                        }
                    })
          }).catch(()=>{})

        },
        addcurrenttrack(){
        this.$refs['addtrackRef'].validate((valid) => {
                if (valid) {
                    //验证通过，再发请求
                    this.$request.post('/track/add', this.addtrack).then(res => {
                        if(res.code === '200'){
                            this.addclueVisible=false
                            this.drawer=false
                            this.$message.success('添加成功')
                        } else{
                            this.$message.error(res.msg)
                        }
                    })
                }
            })
        },
        addtrackfunc(){
        this.addtrack.tname = ''
        this.addtrack.name = this.addname
        this.addtrack.tway = ''
        this.addtrack.tinfo = ''
        this.addtrack.tman = ''
        this.addtrack.linkman = ''
        this.addclueVisible = true
    },
        openclue(xname){
        this.drawer = true
        this.addname = xname
        this.$request.get('/track/selectByclue' ,{
            params: {
                name: xname,
            }
        }).then(res => {
            this.trackData = res.data
        })
    },
      del(xid){
          this.$confirm('确认删除吗？','确认删除',{type: "warning"}).then(response =>{
              this.$request.delete('/clue/delete/' + xid).then(res =>{
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
          this.$request.get('/clue/selectByPage' ,{
              params: {
                  pageNum: this.pageNum,
                  pageSize: this.pageSize,
                  xname: this.name,
              }
          }).then(res => {
              this.tableData = res.data.records
              this.total = res.data.total
          })
      },
      handleCurrentChange(pageNum){
          this.load(pageNum)
      },
      updatecurrent(){
          this.$request.put('/clue/update',this.clue).then(res =>{
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
                  this.clue = row
                  this.innerVisible = true
              },
      handleSelectionChange(rows){
          this.xids = rows.map(v => v.xid)
      },
      delBatch(){
          if(!this.xids.length){
                  this.$message.warning('请选择数据')
                  return
              }
          this.$confirm('确认批量删除吗？','确认删除',{type: "warning"}).then(response =>{
              this.$request.delete('/clue/delete/batch', {data: this.xids}).then(res =>{
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
  