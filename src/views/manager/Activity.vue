<template>
  <div>
    <div>
        <el-input style="width: 200px;" placeholder="查询活动" v-model="name"></el-input>
        <el-button type="primary" @click="load(1)">查询</el-button>
        <el-button type="info" style="background-color: #6e77f2;" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0px;">
        <el-button type="primary" @click="add" plain>新增市场活动</el-button>
        <el-dialog title='新增市场活动' center :visible.sync='addVisible' >
                <el-form :model='addactivity' label-width='80px' :rules="rules" ref="addRef">
                    <el-form-item label='活动名' prop="actname">
                        <el-input v-model='addactivity.actname'></el-input>
                    </el-form-item>
                    <el-form-item label='活动状态' prop="actstate">
                        <el-input placeholder="未开始或进行中" v-model='addactivity.actstate'></el-input>
                    </el-form-item>
                    <el-form-item label='活动类型' prop="acttype">
                        <el-input v-model='addactivity.acttype'></el-input>
                    </el-form-item>
                    <el-form-item label='活动简介' prop="actintro">
                        <el-input v-model='addactivity.actintro'></el-input>
                    </el-form-item>
                    <el-form-item label='活动描述' prop="actdescrip">
                        <el-input v-model='addactivity.actdescrip'></el-input>
                    </el-form-item>
                    <el-form-item label='开始时间'  prop="start">
                        <el-input placeholder="格式：YY-MM-DD" v-model='addactivity.start'></el-input>
                    </el-form-item>
                    <el-form-item label='结束时间'  prop="end">
                        <el-input placeholder="格式：YY-MM-DD" v-model='addactivity.end'></el-input>
                    </el-form-item>
                    <el-form-item label='活动地址' prop="actaddress">
                        <el-input v-model='addactivity.actaddress'></el-input>
                    </el-form-item>
                    <el-form-item>
                        <div style="text-align: center; margin-right: 60px; " >
                            <el-button type="info" style="background-color: #6e77f2;" @click="addcurrent">添 加</el-button>
                        </div>
                    </el-form-item>
                </el-form>
        </el-dialog>
    </div>
    <el-table :data="tableData" stripe :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666'}">
        <el-table-column prop="actid" label="序号"></el-table-column>
        <el-table-column prop="actname" label="市场活动">
            <template slot-scope="scope">
                <a style="color: #409EFF; cursor: pointer;" @click="openclue(scope.row.actname)">{{ scope.row.actname }}</a>
                <el-drawer
                title="活动线索"
                :visible.sync="drawer"
                :size="drawerWidth"
                :direction="rtl">
                <div style="margin: 10px 0px;">
                    <div style="margin-left: 10px;">
                        <el-button type="primary" @click="addcluefunc" plain>添加线索</el-button>
                        <el-dialog title='新增线索' center :visible.sync='addclueVisible' append-to-body="ap">
                <el-form :model='addclue' label-width='80px' :rules="cluerules" ref="addclueRef">
                    <el-form-item label='线索' prop="xname">
                        <el-input v-model='addclue.xname'></el-input>
                    </el-form-item>
                    <el-form-item label='客户' prop="cname">
                        <el-input v-model='addclue.cname'></el-input>
                    </el-form-item>
                    <el-form-item label='线索来源' prop="xorigin">
                        <el-input v-model='addclue.xorigin' disabled></el-input>
                    </el-form-item>
                    <el-form-item label='意向产品' prop="xproduct">
                        <el-input v-model='addclue.xproduct'></el-input>
                    </el-form-item>
                    <el-form-item label='线索级别' prop="xlevel">
                        <el-input v-model='addclue.xlevel'></el-input>
                    </el-form-item>
                    <el-form-item label='创建时间' prop="xtime">
                        <el-input placeholder="格式：YY-MM-DD" v-model='addclue.xtime'></el-input>
                    </el-form-item>
                    <el-form-item label='状态'  prop="xstate">
                        <el-input  placeholder="未转换" v-model='addclue.xstate'></el-input>
                    </el-form-item>
                    <el-form-item label='负责人'  prop="xman">
                        <el-input  v-model='addclue.xman'></el-input>
                    </el-form-item>
                    <el-form-item label='客户需求'  prop="cneed">
                        <el-input v-model='addclue.cneed'></el-input>
                    </el-form-item>
                    <el-form-item>
                        <div style="text-align: center; margin-right: 60px; " >
                            <el-button type="info" style="background-color: #6e77f2;" @click="addcurrentclue">添 加</el-button>
                        </div>
                    </el-form-item>
                </el-form>
                        </el-dialog>
                    </div>
                    <el-table :data="clueData" style="margin-left: 10px; margin-top: 20px;" stripe :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666'}">
                        <el-table-column prop="xname" label="线索"></el-table-column>
                        <el-table-column prop="cname" label="客户"></el-table-column>
                        <el-table-column prop="xproduct" label="意向产品"></el-table-column>
                        <el-table-column prop="xman" label="负责人"></el-table-column>
                    </el-table>
                </div>
                
                </el-drawer>
            </template>
        </el-table-column>
        <el-table-column prop="actstate" label="活动状态">
            <template slot-scope="scope">
              <div :style="{ color: scope.row.actstate ==='进行中'?'#67C23A':'black'}">
                {{ scope.row.actstate }}
              </div>
            </template>
        </el-table-column>
        <el-table-column prop="acttype" label="活动类型"></el-table-column>
        <el-table-column prop="actintro" label="活动简介"></el-table-column>
        <el-table-column prop="start" label="开始时间"></el-table-column>
        <el-table-column prop="end" label="结束时间"></el-table-column>
        <el-table-column label="操作" align="center" width="180">
            <template slot-scope="scope" >
                <el-button type="primary"  @click='handleEdit(scope.row)' plain>编辑</el-button>
                <el-dialog title='编辑活动信息' center :visible.sync='innerVisible' >
                <el-form :model='activity' label-width='80px'>
                    <el-form-item label='活动名称' prop="actname">
                        <el-input v-model='activity.actname'></el-input>
                    </el-form-item>
                    <el-form-item label='活动状态' prop="actstate">
                        <el-input v-model='activity.actstate' disabled></el-input>
                    </el-form-item>
                    <el-form-item label='活动类型' prop="acttype">
                        <el-input v-model='activity.acttype'></el-input>
                    </el-form-item>
                    <el-form-item label='活动简介' prop="actintro">
                        <el-input v-model='activity.actintro'></el-input>
                    </el-form-item>
                    <el-form-item label='活动描述' prop="actdescrip">
                        <el-input v-model='activity.actdescrip'></el-input>
                    </el-form-item>
                    <el-form-item label='开始时间' prop="start">
                        <el-input v-model='activity.start'></el-input>
                    </el-form-item>
                    <el-form-item label='结束时间' prop="end">
                        <el-input v-model='activity.end'></el-input>
                    </el-form-item>
                    <el-form-item label='活动地址' prop="actaddress">
                        <el-input v-model='activity.actaddress'></el-input>
                    </el-form-item>
                    <el-form-item>
                        <div style="text-align: center; margin-right: 60px; " >
                            <el-button type="info" style="background-color: #6e77f2;" @click="updatecurrent">保 存</el-button>
                        </div>
                    </el-form-item>
                </el-form>
                </el-dialog>
                <el-button type="danger" @click="del(scope.row.actid)" plain>删除</el-button>
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
  name: 'activity',
  data () {
    return {
        drawer: false,
        ap: true,
        tableData: [],
        clueData: [],
        drawerWidth: '40%',
        pageNum: 1, //当前页码
        pageSize: 5, //每页显示的个数
        name: '',
        addname: '',
        total: 0,
        innerVisible: false,
        addVisible: false,
        addclueVisible: false,
        activity: { actname: '', actstate: '', acttype: '', actintro: '', actdescrip: '', start: '', end: '', actaddress: ''},
        addactivity: { actname: '', actstate: '', acttype: '', actintro: '', actdescrip: '', start: '', end: '', actaddress: ''},
        addclue:{xname:'', cname: '', xorigin: '', xproduct: '', xlevel: '', xtime: '', xstate: '', xman: '', cneed:'' },
        rules: {
                actname: [
                    { required: true, message: '请输入活动名', trigger: 'blur' }
                ],
                actstate: [
                    { required: true, message: '请输入活动状态', trigger: 'blur' }
                ],
                acttype: [
                    { required: true, message: '请输入活动类型', trigger: 'blur' }
                ],
                actintro: [
                    { required: true, message: '请输入活动简介', trigger: 'blur' }
                ],
                actdescrip: [
                    { required: true, message: '请输入活动描述', trigger: 'blur' }
                ],
                start: [
                    { required: true, message: '请输入活动开始时间', trigger: 'blur' }
                ],
                end: [
                    { required: true, message: '请输入活动结束时间', trigger: 'blur' }
                ],
                actaddress: [
                    { required: true, message: '请输入活动地址', trigger: 'blur' }
                ],
            },
            cluerules: {
                xname: [
                    { required: true, message: '请输入线索名', trigger: 'blur' }
                ]
            }
    }
  },
  created () {
    this.load()
  },
  methods: {
    openclue(aname){
        this.drawer = true
        this.addname = aname
        this.$request.get('/activity/selectByxorigin' ,{
            params: {
                actname: aname,
            }
        }).then(res => {
            this.clueData = res.data
        })
    },
    addcurrent(){
        this.$refs['addRef'].validate((valid) => {
                if (valid) {
                    //验证通过，再发请求
                    this.$request.post('/activity/add', this.addactivity).then(res => {
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
    addcurrentclue(){
        this.$refs['addclueRef'].validate((valid) => {
                if (valid) {
                    //验证通过，再发请求
                    this.$request.post('/clue/add', this.addclue).then(res => {
                        if(res.code === '200'){
                            this.addclueVisible=false
                            this.drawer = false
                            this.$message.success('添加成功')
                            this.load(1)
                        } else{
                            this.$message.error(res.msg)
                        }
                    })
                }
            })
    },
    add(){
        this.addactivity.actname = ''
        this.addactivity.actstate = ''
        this.addactivity.acttype = ''
        this.addactivity.actintro = ''
        this.addactivity.actdescrip = ''
        this.addactivity.start = ''
        this.addactivity.end = ''
        this.addactivity.actaddress = ''
        this.addVisible = true
    },
    addcluefunc(){
        this.addclue.xname = ''
        this.addclue.cname = ''
        this.addclue.xorigin = this.addname
        this.addclue.xproduct = ''
        this.addclue.xlevel = ''
        this.addclue.xtime = ''
        this.addclue.xstate = ''
        this.addclue.xman = ''
        this.addclue.cneed = ''
        this.addclueVisible = true
    },
    updatecurrent(){
        this.$request.put('activity/update',this.activity).then(res =>{
            if(res.code === '200'){
                this.innerVisible=false
                this.$message.success('保存成功')
                this.load(1)
            } else {
                this.$message.error(res.msg)
            }
        })
    },
    reset(){
        this.name = ''
        this.load(1)
    },
    handleCurrentChange(pageNum){
        this.load(pageNum)
    },
    load(pageNum){
        if(pageNum){
            this.pageNum = pageNum
        }
        this.$request.get('/activity/selectByPage' ,{
            params: {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                actname: this.name,
            }
        }).then(res => {
            this.tableData = res.data.records
            this.total = res.data.total
        })
    },
    handleEdit(row) {
                this.activity = row
                this.innerVisible = true
            },
    del(actid){
        this.$confirm('确认删除吗？','确认删除',{type: "warning"}).then(response =>{
            this.$request.delete('/activity/delete/' + actid).then(res =>{
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
