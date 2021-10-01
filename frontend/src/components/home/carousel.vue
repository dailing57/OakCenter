<template>
  <div class="block">
    <span class="demonstration"
    >Switch when indicator is hovered (default)</span
    >
    <el-carousel height="150px">
      <el-carousel-item v-for="item in images" :key="item">
        <h3 class="small">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: "carousel",
  data(){
    return {
      images: [],
    }
  }
}
</script>

<style scoped>

</style>
