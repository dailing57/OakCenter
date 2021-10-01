<template>
<div>
  <el-container>
    <el-header><Header/></el-header>
    <el-main>
      <router-view/>
    </el-main>
    <el-footer><Footer/></el-footer>
  </el-container>
</div>
</template>

<script>
import Header from "@/components/layout/Header";
import Footer from "@/components/layout/Footer";
export default {
  name: "Layout",
  components: {
    Header,
    Footer
  }
}
</script>

<style scoped>

</style>
