<template>
  <div>
    <el-card style="width: 50%;">
        <el-form :model="user" label-width="80px" style="padding-right: 20px;">
            <el-form-item label="账号" prop="username">
                <el-input v-model="user.username" placeholder="账号" disabled></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="user.password" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item label="用户姓名" prop="emane">
                <el-input v-model="user.ename" placeholder="用户姓名"></el-input>
            </el-form-item>
            <div style="text-align: center; margin-bottom: 20px;">
                <el-button type="primary" @click="update">保存</el-button>
            </div>
        </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'person',
  data () {
    return {
        user: JSON.parse(localStorage.getItem('current-user') || '{}')
    }
  },
  created () {},
  methods: {
    update(){
        this.$request.put('user/update',this.user).then(res =>{
            if(res.code === '200'){
                this.$message.success('保存成功')
                localStorage.setItem('current-user',JSON.stringify(this.user))
            } else {
                this.$message.error(res.msg)
            }
        })
    }
  },
  mounted() {},
  computed: {}
}

</script>
<style scoped>
/deep/.el-form-item__label {
    font-weight: bold;
}
</style>
