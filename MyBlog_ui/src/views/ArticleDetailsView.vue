<template>
    <div style="padding: 20px">
        <Row justify="center" gutter="16">
            <Col span="15">
            <Card style="width:100%;">
                <div id="title_div">
                    <label>{{ detailsData.title }}</label>
                </div>
                <div id="top_item_div">
                    <div @click="back" style="font-size:28;margin-left: -10px;color:#2d8cf0;flex:1">
                        <Icon type="ios-arrow-back" size="20"/>
                        返回主页
                    </div>
                    <!-- <div @click="toComment" style="font-size:28;margin-left: -10px;">
                        查看评论
                    </div> -->
                </div>
                <v-md-preview ref="drag" :text="detailsData.text"> </v-md-preview>
            </Card>
            <div style="padding-top: 20px">
                <Card style="width:100%;">
                    <template #title>热门评论</template>
                    <CommentList :tid="router.currentRoute.value.query.tid" />
                </Card>
            </div>
            </Col>
            <Col span="5">
            <Card style="width:100%;width: 400px;">
                <div id="addressInfo_div">
                    <div id="touxiang_div">
                        <Avatar shape="circle" :src="detailsData.avatarUrl" size="80" />
                    </div>
                    <label id="username">{{ detailsData.address }}</label>
                    <Row justify="center" align="middle">
                        <Col style="text-align: center;font-size: larger;" span="8">
                        文章:233
                        </Col>
                        <Col style="text-align: center;font-size: larger;" span="8">
                        评论:200
                        </Col>
                    </Row>
                </div>
            </Card>
            </Col>
        </Row>
    </div>
</template>

<script setup>
import { onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { apiGetArticleDetailsById } from '../api/article'
import { apiSetComment } from '../api/comment'
import CommentList from '../components/comment/CommentList.vue'
const router = useRouter()
const detailsData = reactive({
    text: '',
    title: '',
    titles: [],
    address: '',
    avatarUrl: ''
})
onMounted(() => {
    //获取tid
    const tid = router.currentRoute.value.query.tid
    apiGetArticleDetailsById({
        'tid': tid
    }).then(res => {
        console.log(res)
        detailsData.text = res.data.context
        detailsData.title = res.data.title
        detailsData.address = res.data.address
        detailsData.avatarUrl = res.data.avatarUrl
    })
})
function back(){
    router.back()
}
//到评论区
function toComment(){

}


</script>

<style scoped>
#title_div {
    text-align: center;
    padding-top: 20px;
    font-size: 24px;
    font-weight: bolder;
}

#top_item_div {
    height: 60px;
    width: 100%;
    /* background-color: aqua; */
    display: flex;
    padding: 36px;
    align-items: center;
}

#addressInfo_div {
    width: 100%;
    height: 400px;
    display: flex;
    flex-direction: column;

}

#username {
    font-size: 20px;
    text-align: center;
}

#touxiang_div {
    margin: 0 auto;
    padding-top: 20px;
}
</style>