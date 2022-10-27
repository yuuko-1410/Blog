<template>
    <div id="topBar_div">
        <div id="topBer_left_div">
            <Row id="row_div">
                <Col span="6" id="item_div" @click="router.replace('/admin/state')">控制台</Col>
                <Col span="6" id="item_div" @click="router.replace('/admin/edit')">撰写文章</Col>
                <Col span="6" id="item_div" @click="router.replace('/admin/manage')">管理</Col>
                <Col span="6" id="item_div">设置</Col>
            </Row>
        </div>
        <div style="flex: 1"></div>
        <div id="topBer_right_div">
            <Row id="row_div">
                <Col span="8" id="item_div">用户设置</Col>
                <Col span="8" id="item_div">登出</Col>
                <Col @click="router.replace('/')" span="8" id="item_div">主页</Col>
            </Row>
        </div>
    </div>
</template>

<script setup>
import {useRouter} from 'vue-router'
const router = useRouter()

</script>

<style scoped>
#topBar_div {
    height: 42px;
    width: 100%;
    background-color: #515a6e;
    display: flex;
}

#topBer_left_div {
    width: 280px;
    height: 100%;
}

#topBer_right_div {
    width: 200px;
}

#item_div {
    height: 100%;
    width: 100%;
    color: white;
    padding-top: 10px;
}
#item_div:hover {
    height: 100%;
    width: 100%;
    color: white;
    padding-top: 10px;
    background-color: #17233d;
}

#row_div {
    height: 100%;
    display: flex;
    align-content: center;
    text-align: center;
}
</style>