<template>
  <div id="admin_div">
    <AdminTopBar />
    <div id="admin_context_div">
      <router-view/>
    </div>
  </div>

</template>

<script setup>
import AdminTopBar from '@/components/AdminTopBar.vue';

</script>

<style>
#admin_div{
  position: relative;
  overflow: hidden;
  height: 100%;
}
#admin_context_div {
  width: 100%;
  height: 100%;
  position: absolute;
  background-color: #f8f8f9;

}
</style>