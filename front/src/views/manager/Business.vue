<template>
    <div>
      <div>
          <el-input style="width: 200px;" placeholder="查询商机" v-model="name"></el-input>
          <el-button type="primary" @click="load(1)">查询</el-button>
          <el-button type="info" style="background-color: #6e77f2;" @click="reset">重置</el-button>
      </div>
      
      <el-table :data="tableData" stripe :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666'}" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" algin="center"></el-table-column>
          <el-table-column prop="bid" label="序号"></el-table-column>
          <el-table-column prop="bname" label="商机">
            <template slot-scope="scope">
                <a style="color: #409EFF; cursor: pointer;" @click="openb(scope.row)">{{ scope.row.bname }}</a>
                <el-drawer
                title="商机阶段-跟进"
                :visible.sync="drawer"
                :size="drawerWidth"
                :direction="rtl">
                <div style="margin: 10px 0px;">
                    <div style="margin-left: 10px;">
                        <el-steps :active="active" finish-status="success">
                            <el-step title="发现商机"></el-step>
                            <el-step title="初步接洽"></el-step>
                            <el-step title="技术交流"></el-step>
                            <el-step title="商务谈判"></el-step>
                            <el-step title="投标阶段"></el-step>
                            <el-step title="开工结束"></el-step>
                        </el-steps>
                        <el-button style="margin-top: 12px;" @click="next(scope.row)">下一步</el-button>
                    </div>
                    <div>
                        <div style="margin-left: 10px;">
                        <el-button type="primary" @click="addtrackfunc" plain>新增跟进信息</el-button>
                        <el-dialog title='新增跟进' center :visible.sync='addclueVisible' append-to-body="ap">
                <el-form :model='addtrack' label-width='80px' :rules="trackrules" ref="addtrackRef">
                    <el-form-item label='跟进' prop="tname">
                        <el-input v-model='addtrack.tname'></el-input>
                    </el-form-item>
                    <el-form-item label='商机' prop="name">
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
          <el-table-column prop="bstate" label="状态"></el-table-column>
          <el-table-column prop="bsum" label="预期成交金额"></el-table-column>
          <el-table-column prop="bpdate" label="预期成交时间"></el-table-column>
          <el-table-column prop="bphase" label="当前阶段"></el-table-column>
          <el-table-column label="操作" align="center" width="220">
              <template slot-scope="scope" >
                  <el-button type="success"  @click='prehandleEdit(scope.row)' plain>预测</el-button>
                  <el-dialog title='编辑商机影响因素信息' center :visible.sync='preinnerVisible' >
                  <el-form :model='business' label-width='80px'>
                      <el-form-item label='客户等级' prop="clevel" label-width="160px">
                          <el-input v-model='business.clevel'></el-input>
                      </el-form-item>
                      <el-form-item label='负责人等级' prop="bmanlevel" label-width="160px">
                          <el-input v-model='business.bmanlevel'></el-input>
                      </el-form-item>
                      <el-form-item label='负责人商机成交数' prop="bmannum" label-width="160px">
                          <el-input v-model='business.bmannum'></el-input>
                      </el-form-item>
                      <el-form-item label='产品匹配度' prop="suit" label-width="160px">
                          <el-input v-model='business.suit'></el-input>
                      </el-form-item>
                      <el-form-item label='初步接洽满意度' prop="satisfaction" label-width="160px">
                          <el-input v-model='business.satisfaction' ></el-input>
                      </el-form-item>
                      <el-form-item label='技术交流' prop="tech" label-width="160px">
                          <el-input placeholder="需要新技术请填1" v-model='business.tech'></el-input>
                      </el-form-item>
                      <el-form-item label='商务谈判利润大小' prop="profit" label-width="160px">
                          <el-input v-model='business.profit'></el-input>
                      </el-form-item>
                      <el-form-item label='客户公司是否盈利' prop="state" label-width="160px">
                          <el-input placeholder="盈利请填1" v-model='business.state'></el-input>
                      </el-form-item>
                      <el-form-item label='客户预期投入' prop="input" label-width="160px">
                          <el-input v-model='business.input'></el-input>
                      </el-form-item>
                      <el-form-item label='客户过往成交最高金额' prop="maxsum" label-width="160px">
                          <el-input v-model='business.maxsum'></el-input>
                      </el-form-item>
                      <el-form-item>
                          <div style="text-align: center; margin-right: 60px; " >
                              <el-button type="info" style="background-color: #6e77f2;" @click="predict">预 测</el-button>
                          </div>
                      </el-form-item>
                  </el-form>
                  </el-dialog>
                  <el-button type="primary"  style="margin-left: 0px;" @click='handleEdit(scope.row)' plain>编辑</el-button>
                  <el-dialog title='编辑商机信息' center :visible.sync='innerVisible' >
                  <el-form :model='business' label-width='80px'>
                      <el-form-item label='商机' prop="bname">
                          <el-input v-model='business.bname'></el-input>
                      </el-form-item>
                      <el-form-item label='状态' prop="bstate">
                          <el-input v-model='business.bstate'></el-input>
                      </el-form-item>
                      <el-form-item label='预期金额' prop="bsum">
                          <el-input v-model='business.bsum'></el-input>
                      </el-form-item>
                      <el-form-item label='预期成交时间' prop="bpdate">
                          <el-input v-model='business.bpdate'></el-input>
                      </el-form-item>
                      <el-form-item label='当前阶段' prop="bphase" >
                          <el-input v-model='business.bphase' disabled></el-input>
                      </el-form-item>
                      <el-form-item label='联系人' prop="lname">
                          <el-input v-model='business.lname'></el-input>
                      </el-form-item>
                      <el-form-item label='负责人' prop="bman">
                          <el-input v-model='business.bman'></el-input>
                      </el-form-item>
                      <el-form-item>
                          <div style="text-align: center; margin-right: 60px; " >
                              <el-button type="info" style="background-color: #6e77f2;" @click="updatecurrent">保 存</el-button>
                          </div>
                      </el-form-item>
                  </el-form>
                  </el-dialog>
                  <el-button type="danger" @click="del(scope.row.bid)" plain>删除</el-button>
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
    <div style="display: flex;">
        <div style="flex: 1;">
            <el-card style="width: 500px; margin-top: 10px; margin-bottom: 10px;">
              <div slot="header" class="clearfix">
                <span>当前预测可成交商机</span>
              </div>
              <div>
                <div v-for="business in filteredTableData" :key="business.bid">
                    <div>{{ business.bname }}的成交概率为{{ business.rate }}</div>
                </div>
              </div>
            </el-card>
        </div>
        <div style="flex: 1;">
            <el-card style="width: 500px; margin-top: 10px; margin-bottom: 10px;">
              <div slot="header" class="clearfix">
                <span>其他商机推荐下一步</span>
              </div>
              <div>
                <div v-for="business in filteredTableData" :key="business.bid">
                    <div>{{ business.bname }}的下一步应该：详谈投标</div>
                </div>
              </div>
            </el-card>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'business',
    data () {
      return {
          active: 0,
          rate: 0.001,
          drawer: false,
          drawerWidth: '60%',
          tableData: [],
          trackData: [],
          bids: [],
          addname: '',
          addVisible: false,
          addclueVisible: false,
          innerVisible: false,
          preinnerVisible: false,
          business: { bid: '',bname: '', bstate: '', bsum: '', bpdate: '', bphase: '', lname:'', bman:'', clevel: '', bmanlevel: '', bmannum: '', suit: '', satisfaction: '', tech: '', profit: '', state: '', input: '', maxsum: '', win: '', rate: '' },
          addbusiness: { bname: '', bstate: '', bsum: '', bpdate: '', bphase: '', lname:'', bman:'' },
          addtrack: { tname: '', name: '', tway: '', tinfo: '', tman:'', linkman:'' },
          pageNum: 1, //当前页码
          pageSize: 5, //每页显示的个数
          name: '',
          total: 0,
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
        predict(){
            var data = {
                "clevel": this.business.clevel,
                "bmanlevel": this.business.bmanlevel,
                "bmannum": this.business.bmannum,
                "suit": this.business.suit,
                "satisfaction": this.business.satisfaction,
                "tech": this.business.tech,
                "profit": this.business.profit,
                "state": this.business.state,
                "input": this.business.input,
                "maxsum": this.business.maxsum
            }
            this.$request.post('/home/predict', data).then(res => {
                        if(res.code === '200'){
                            this.preinnerVisible=false
                            let responseData = JSON.parse(res.data);
                            let predictedRate;
                            this.$request.post('/home/predictrate', data).then(res2 => {
                                let responseDatax = JSON.parse(res2.data);
                                predictedRate = responseDatax.prediction[0];
                                this.business.bsum = Math.round(predictedRate * this.business.input);
                                if (responseData.prediction[0] === 1) {
                                console.log(predictedRate)
                                this.$message.success('预测成功！该商机最后能够成交！成交概率为' + predictedRate);
                                this.business.win = 1
                                this.business.rate = predictedRate
                                this.$request.put('/business/update',this.business)
                            } else {
                                this.$message.info('预测成功！但该商机最后不能成交！成交概率只有' + predictedRate);
                                this.business.win = 0
                                this.business.rate = predictedRate
                                this.$request.put('/business/update',predictedRate)
                            }
                            })
                        } else{
                            this.$message.error(res.msg)
                        }
                    })
        },
        next(row) {
            this.active = this.active + 1
            console.log(this.active)
            if (this.active == 1) row.bphase = '初步接洽'
            if (this.active == 2) row.bphase = '技术交流'
            if (this.active == 3) row.bphase = '商务谈判'
            if (this.active == 4) row.bphase = '投标阶段'
            if (this.active == 5) row.bphase = '开工结束'
            console.log(row.bphase)
            this.business.bid = row.bid
            this.business.bname = row.bname
            this.business.bstate = row.bstate
            this.business.bsum = row.bsum
            this.business.bpdate = row.bpdate
            this.business.bphase = row.bphase
            this.business.lname = row.lname
            this.business.bman = row.bman
            console.log(this.business.bphase)
            this.$request.put('/business/update',this.business).then(res =>{
              if(res.code === '200'){
                  this.drawer = false
                  this.$message.success('推进成功')
                  this.load()
              } else {
                  this.$message.error(res.msg)
              }
          })
        },
        addcurrenttrack(){
        this.$refs['addtrackRef'].validate((valid) => {
                if (valid) {
                    //验证通过，再发请求
                    this.$request.post('/track/add', this.addtrack).then(res => {
                        if(res.code === '200'){
                            this.addVisible=false
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
        openb(row){
        this.drawer = true
        this.addname = row.bname
        if (row.bphase === '发现商机') this.active = 0;
        if (row.bphase === '初步接洽') this.active = 1;
        if (row.bphase === '技术交流') this.active = 2;
        if (row.bphase === '商务谈判') this.active = 3;
        if (row.bphase === '投标阶段') this.active = 4;
        if (row.bphase === '开工结束') this.active = 5;
        this.$request.get('/track/selectByclue' ,{
            params: {
                name: row.bname,
            }
        }).then(res => {
            this.trackData = res.data
        })
    },
      del(bid){
          this.$confirm('确认删除吗？','确认删除',{type: "warning"}).then(response =>{
              this.$request.delete('/business/delete/' + bid).then(res =>{
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
          this.$request.get('/business/selectByPage' ,{
              params: {
                  pageNum: this.pageNum,
                  pageSize: this.pageSize,
                  bname: this.name,
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
          this.$request.put('/business/update',this.business).then(res =>{
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
                  this.business = row
                  this.innerVisible = true
              },
      prehandleEdit(row) {
                  this.business = row
                  this.preinnerVisible = true
              },
      handleSelectionChange(rows){
          this.bids = rows.map(v => v.bid)
      }
    },
    mounted() {},
    computed: {
        filteredTableData() {
            return this.tableData.filter(business => business.win === 1);
        }
    }
  }
  
  </script>
  <style scoped>
  </style>
  