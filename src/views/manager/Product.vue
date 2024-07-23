<template>
  <div>
    <div>
        <el-input style="width: 200px;" placeholder="查询产品" v-model="name"></el-input>
        <el-button type="primary" @click="load(1)">查询</el-button>
        <el-button type="info" style="background-color: #6e77f2;" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0px;">
        <el-button type="primary" @click="add" plain>新增产品</el-button>
        <el-dialog title='新增产品' center :visible.sync='addVisible' >
                <el-form :model='addproduct' label-width='80px' :rules="rules" ref="addRef">
                    <el-form-item label='产品名' prop="pname">
                        <el-input v-model='addproduct.pname'></el-input>
                    </el-form-item>
                    <el-form-item label='产品类型' prop="ptype">
                        <el-input v-model='addproduct.ptype'></el-input>
                    </el-form-item>
                    <el-form-item label='产品价格' prop="pprice">
                        <el-input v-model='addproduct.pprice'></el-input>
                    </el-form-item>
                    <el-form-item label='产品简介' prop="pintro">
                        <el-input v-model='addproduct.pintro'></el-input>
                    </el-form-item>
                    <el-form-item label='产品图片' prop="ppicture">
                        <el-input v-model='addproduct.ppicture'></el-input>
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
        <el-table-column prop="pid" label="序号"></el-table-column>
        <el-table-column prop="pname" label="产品名"></el-table-column>
        <el-table-column prop="ptype" label="产品类型"></el-table-column>
        <el-table-column prop="pprice" label="产品价格"></el-table-column>
        <el-table-column prop="pintro" label="产品简介"></el-table-column>
        <el-table-column prop="ppicture" label="产品图片"></el-table-column>
        <el-table-column label="操作" align="center" width="180">
            <template slot-scope="scope" >
                <el-button type="primary"  @click='handleEdit(scope.row)' plain>编辑</el-button>
                <el-dialog title='编辑产品信息' center :visible.sync='innerVisible' >
                <el-form :model='product' label-width='80px'>
                    <el-form-item label='产品名' prop="pname">
                        <el-input v-model='product.pname'></el-input>
                    </el-form-item>
                    <el-form-item label='产品类型' prop="ptype">
                        <el-input v-model='product.ptype'></el-input>
                    </el-form-item>
                    <el-form-item label='产品价格' prop="pprice">
                        <el-input v-model='product.pprice'></el-input>
                    </el-form-item>
                    <el-form-item label='产品简介' prop="pintro">
                        <el-input v-model='product.pintro'></el-input>
                    </el-form-item>
                    <el-form-item label='产品图片' prop="ppicture">
                        <el-input v-model='product.ppicture'></el-input>
                    </el-form-item>
                    <el-form-item>
                        <div style="text-align: center; margin-right: 60px; " >
                            <el-button type="info" style="background-color: #6e77f2;" @click="updatecurrent">保 存</el-button>
                        </div>
                    </el-form-item>
                </el-form>
                </el-dialog>
                <el-button type="danger" @click="del(scope.row.pid)" plain>删除</el-button>
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
  name: 'product',
  data () {
    return {
        tableData: [],
        pids: [],
        addVisible: false,
        innerVisible: false,
        product: { pname: '', ptype: '', pprice: '', pintro: '', ppicture: '' },
        addproduct: { pname: '', ptype: '', pprice: '', pintro: '', ppicture: '' },
        pageNum: 1, //当前页码
        pageSize: 5, //每页显示的个数
        name: '',
        total: 0,
        rules: {
                pname: [
                    { required: true, message: '请输入产品名', trigger: 'blur' }
                ],
                ptype: [
                    { required: true, message: '请输入产品类型', trigger: 'blur' }
                ],
                pprice: [
                    { required: true, message: '请输入产品价格', trigger: 'blur' }
                ],
                pintro: [
                    { required: true, message: '请输入产品简介', trigger: 'blur' }
                ],
            }
    }
  },
  created () {
    this.load()
  },
  methods: {
    del(pid){
        this.$confirm('确认删除吗？','确认删除',{type: "warning"}).then(response =>{
            this.$request.delete('/product/delete/' + pid).then(res =>{
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
        this.$request.get('/product/selectByPage' ,{
            params: {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                pname: this.name,
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
        this.addproduct.pname = ''
        this.addproduct.ptype = ''
        this.addproduct.pprice = ''
        this.addproduct.pintro = ''
        this.addproduct.ppicture = ''
        this.addVisible = true
    },
    addcurrent(){
        this.$refs['addRef'].validate((valid) => {
                if (valid) {
                    //验证通过，再发请求
                    this.$request.post('/product/add', this.addproduct).then(res => {
                        if(res.code === '200'){
                            this.addVisible=false
                            this.$message.success('添加成功')
                            this.addproduct.pname = ''
                            this.addproduct.ptype = ''
                            this.addproduct.pprice = ''
                            this.addproduct.pintro = ''
                            this.addproduct.ppicture = ''
                            this.load(1)
                        } else{
                            this.$message.error(res.msg)
                        }
                    })
                }
            })
    },
    updatecurrent(){
        this.$request.put('product/update',this.product).then(res =>{
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
                this.product = row
                this.innerVisible = true
            },
    handleSelectionChange(rows){
        this.pids = rows.map(v => v.pid)
    },
    delBatch(){
        if(!this.pids.length){
                this.$message.warning('请选择数据')
                return
            }
        this.$confirm('确认批量删除吗？','确认删除',{type: "warning"}).then(response =>{
            this.$request.delete('/product/delete/batch', {data: this.pids}).then(res =>{
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
