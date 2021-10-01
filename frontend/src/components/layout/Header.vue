<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#67C23A"
      text-color="#fff"
      active-text-color="#BCF1ED"
      router
  >
    <el-menu-item index="/home">首页</el-menu-item>
    <el-menu-item index="/activity">动态</el-menu-item>
    <el-menu-item index="/database">数据库</el-menu-item>
    <el-menu-item index="/jbrowse">JBrowse</el-menu-item>
    <el-menu-item index="/manage" v-if="user !== null && user.role === 'admin'">管理</el-menu-item>
    <div style="flex: 1"></div>
    <el-menu-item index="/register" v-if="user === null">注册</el-menu-item>
    <el-tag type="success" size="large" style="margin-top: 14px" v-if="user !== null">{{user.name}}</el-tag>
    <el-button type="success" style="width: 200px" @click="vis=true" v-if="user === null">登录</el-button>
    <el-button type="success" style="width: 200px" @click="logout" v-if="user !== null">注销</el-button>
  </el-menu>

  <el-dialog v-model='vis' style="text-align: center" width="30%">
    <login v-on:success="loginSuccess"></login>
    <el-button type="text" @click="register">前往注册 >> </el-button>
  </el-dialog>

</template>

<script>
import login from "@/components/login";
export default {
  components: {login},
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
  },
  data() {
    return {
      activeIndex: '/home',
      vis: false,
      user: {},
    }
  },
  methods: {
    register(){
      this.vis = false
      this.$router.push('/register')
    },
    loginSuccess(val){
      this.vis = val
      this.$router.go(0)
    },
    logout(){
      sessionStorage.removeItem("user")
      this.user = null
      this.$router.go(0)
    }
  },
}
</script>

<style scoped>

</style>
