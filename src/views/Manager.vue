<template>
  <div>
    <el-container>

      <el-aside :width="asidewidth" style=" min-height: 100vh; background-color:#001529 " >
          <div style="height: 60px; color: white; display: flex; align-items: center; justify-content: center;">
            <img src="@/assets/logo.png" alt="" style="width: 40px; height: 40px;">
            <Transition name="el-fade-in-linear">
              <span style="margin-left: 10px; font-size: 20px;" v-show="!isCollapse">CRM</span>
            </Transition>
          </div>

          <el-menu :collapse="isCollapse" :collapse-transition="false" router background-color="#001529" text-color="rgba(255,255,255,0.65)" active-text-color="#fff" style="border: none" :default-active="$route.path">
            <el-menu-item index="/home">
              <i class="el-icon-s-home"></i>
              <span slot="title">系统首页</span>
            </el-menu-item>
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>营销管理</span>
              </template>
              <el-menu-item index="/activity">市场活动</el-menu-item>
              <el-menu-item index="/clue">线索</el-menu-item>
            </el-submenu>
            <el-menu-item index="/business">
              <i class="el-icon-setting"></i>
              <span>商机管理</span>
            </el-menu-item>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>客户管理</span>
              </template>
              <el-menu-item index="/customer">客户</el-menu-item>
              <el-menu-item index="/linkman">联系人</el-menu-item>
            </el-submenu>
            <el-menu-item index="/product">
              <i class="el-icon-document"></i>
              <span>产品管理</span>
            </el-menu-item>
            <!-- <el-menu-item index="">
              <i class="el-icon-setting"></i>
              <span>智能分析</span>
            </el-menu-item> -->
          </el-menu>
      </el-aside>

      <el-container>
          <el-header>
            <i :class="collapseIcon" style="font-size: 26px; " @click="handleCollapse"></i>
            <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-left: 20px;">
              <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item :to="{ path: '/' }">CRM管理</el-breadcrumb-item>
            </el-breadcrumb>

            <div style="flex: 1; width: 0; display: flex; align-items: center; justify-content: flex-end;">
              <el-dropdown placement="bottom">
                <div style="display: flex; align-items: center; cursor: default;">
                  <img src="@/assets/user.png" alt="" style="width: 40px; height: 40px;">
                <span style="margin-left: 5px;">{{user.ename}}</span>
                </div>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="$router.push('/person')">个人信息</el-dropdown-item>
                  <el-dropdown-item @click.native="$router.push('/person')">修改密码</el-dropdown-item>
                  <el-dropdown-item @click.native="$router.push('/login')">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>

          </el-header>

          <el-main>
            <router-view />
          </el-main>
      </el-container>

    </el-container>
  </div>
</template>

<script>

export default {
  name: 'HomeView',
  data(){
    return {
      user: JSON.parse(localStorage.getItem('current-user')|| '{}'),
      isCollapse: false, //默认不收缩
      asidewidth: '200px',
      collapseIcon: 'el-icon-s-fold'
    }
  },
  methods: {
    handleCollapse(){
      this.isCollapse = !this.isCollapse
      this.asidewidth = this.isCollapse ? '64px' : '200px'
      this.collapseIcon = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'
    }
  }
}
</script>

<style>
.el-menu--inline .el-menu-item {
  background-color: #000c17 !important;
}
.el-menu-item:hover , .el-submenu__title:hover{
  color: #fff !important;
}
.el-menu-item:hover i{
  color: #fff !important;
}
.el-submenu__title:hover i{
  color: #fff !important;
}
.el-menu-item.is-active {
  background-color: #1890ff !important;
  border-radius: 10px;
  margin: 4px !important;
}
.el-menu-item{
  height: 40px !important;
  line-height: 40px !important;
  margin: 4px !important;
}
.el-submenu__title{
  height: 40px !important;
  line-height: 40px !important;
  margin: 4px !important;
}
.el-aside{
  transition: width .3s;
  box-shadow: 2px 0 6px rgba(0,21,41,.35);
}
.el-header {
  box-shadow: 2px 0 6px rgba(0,21,41,.35);
  display: flex;
  align-items: center;
}
</style>