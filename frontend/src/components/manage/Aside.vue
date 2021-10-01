<template>
      <el-menu
          :uniqueOpened="true"
          default-active="1"
          class="el-menu-vertical-demo"
          background-color="#67C23A"
          text-color="#fff"
          active-text-color="#BCF1ED"
          router
      >
        <el-sub-menu index="1">
          <template #title>
            <i class="el-icon-user"></i>
            <span>用户管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/userList">用户列表</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <i class="el-icon-document"></i>
            <span>动态管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/articleList">文章一览</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title>
            <i class="el-icon-house"></i>
            <span>首页管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/homeInfo">首页信息</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="4">
          <template #title>
            <i class="el-icon-s-data"></i>
            <span>数据库管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/databaseList">数据库一览</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
      </el-menu>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>
</style>
