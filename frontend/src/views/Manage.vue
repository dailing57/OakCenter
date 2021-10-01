<template>
  <el-row>
    <el-col :span="3">
      <Aside/>
    </el-col>
    <el-col :span="21">
      <router-view></router-view>
    </el-col>
  </el-row>

</template>

<script>
import Aside from "@/components/manage/Aside";
export default {
  name: "Manage",
  components:{
    Aside
  }
}
</script>

<style scoped>

</style>
