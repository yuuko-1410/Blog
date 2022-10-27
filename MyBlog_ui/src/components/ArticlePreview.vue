<template>
    <div id="preview_div" @click="handleArticleClick">
        <div id="image_div">
            <img :src="data.imgUrl" />
        </div>
        <div id="context_div">
            <div id="title">
                {{data.title}}
            </div>
            <div id="text" v-line-clamp="3">
                {{data.context}}
            </div>
            <div id="more_item">
                {{timestampToTime(data.updateTime)}} / {{data.watchNum}}阅读 / {{data.likeNum}}点赞
            </div>
        </div>

    </div>
</template>

<script setup>
import {reactive } from 'vue';
import {timestampToTime} from '../common/dataUtils'
import { useRouter } from 'vue-router'
import {articleInfo} from '../store/articeInfo'
const router = useRouter()
const props  = defineProps({
    data: {
        type: Object
    }
})
function handleArticleClick(){
    
    router.push({
        name: 'details',
        query: {
            tid: props.data.tid
        }
    })
}
</script>

<style scoped>
#preview_div {
    display: flex;
    padding-right: 20px;
}

#image_div img {
    width: 220px;
    height: 140px;
    border-radius: 8px;
}

#context_div{
    display: flex;
    flex-direction: column;
    margin-left: 18px;
}

#title {
    font-weight: 480;
    font-size: 20px;
    height: 32px;
    
}

#text {
    margin-top: 6px;
    height: 66px;
    font-size: 14px;
    color: #a1a4a9;
}

#more_item{
    margin-top: 18px;
    width: 100%;
    height: 26px;
    color: #909399;
    font-size: 12px;
}
</style>