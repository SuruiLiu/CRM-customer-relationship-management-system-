<template>
    <div style="height: 100vh; display:flex; align-items: center; justify-content: center; background-color:#669fef;">
        <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden;">
            <div style="flex:1">
                <img src="@/assets/register.png" alt="" style="width: 100%;">
            </div>
            <div style="flex:1; display: flex; align-items: center; justify-content: center;">
                <el-form :model="user" style="width: 80%;" :rules="rules" ref="registerRef">
                    <div style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 20px;">
                        欢迎注册CRM管理系统
                    </div>
                    <el-form-item prop="username">
                    <el-input prefix-icon="el-icon-user" size="medium" placeholder="请输入账号" v-model="user.username"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                    <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="请输入密码" v-model="user.password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="info" style="width: 100%; background-color: #6e77f2;" @click="register">注 册</el-button>
                    </el-form-item>
                    <div style="display: flex;">
                        <div style="flex: 1">已有账号?请<span style="color: #6e77f2; cursor: pointer;" @click="$router.push('/login')">登录</span></div>
                    </div>
                </el-form>
            </div>

        </div>
    </div>
</template>

<script>
export default {
    name: "Register",
    data(){
        return {
            user: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    { required: true, message: '请输入账号', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
            }
        }
    },
    created(){

    },
    methods: {
        register(){
            this.$refs['registerRef'].validate((valid) => {
                if (valid) {
                    //验证通过，再发请求
                    this.$request.post('/register', this.user).then(res => {
                        if(res.code === '200'){
                            this.$router.push('/login')
                            this.$message.success('注册成功')
                        } else{
                            this.$message.error(res.msg)
                        }
                    })
                }
            })
            
        }
    }
}
</script>

<style>

</style>