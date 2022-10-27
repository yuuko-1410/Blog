<template>
      <Row id="context_div" justify="center">
        <Col span="16">
          <ArticleList/>
        </Col>
        <Col span="6">
          <TageList/>
          <TageList/>
        </Col>
    </Row>
</template>

<script setup>
import ArticleList from '@/components/ArticleList.vue';
import TageList from '../components/TageList.vue';
</script>

<style>

</style>