<template>
  <div id="home_div">
    <!-- 返回顶端 -->
    <BackTop bottom="100"></BackTop>
    <Affix>
      <TopBar />
    </Affix>
    <router-view />
  </div>
</template>
<script setup>
import TopBar from '@/components/TopBar.vue';


</script>
<style scoped>
#home_div {
  height: 100%;
  width: 100%;
  background-color: #f8f8f9;
}
</style>