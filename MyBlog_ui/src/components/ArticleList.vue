<template>
    <div id="article_div">
        <Card :bordered="false" style="height: 100%">
            <!-- <template #title>
                
            </template> -->
            <Tabs @on-click="handleTabClick">
                <TabPane label="热门文章" name="name1"></TabPane>
                <TabPane label="最新文章" name="name2"></TabPane>
                <TabPane label="点赞最多" name="name3"></TabPane>
            </Tabs>
            <List id="list_item">
                <ListItem v-for="item in articleInfo.data.blogArticles" :key="item">
                    <li>
                        <ArticlePreview :data="item"/>
                    </li>
                </ListItem>
            </List>
            <div id="page_div">
                <Page :total="articleInfo.data.size" page-size="6" @on-change="handlePageClick" show-total />
            </div>
        </Card>
    </div>
</template>

<script setup>
import ArticlePreview from "./ArticlePreview.vue";
import { onMounted, reactive } from 'vue';
import { apiGetArticleDetailsList } from '../api/article'
import {articleInfo} from '../store/articeInfo'

function initList(){
    apiGetArticleDetailsList({
        //分页
        current: articleInfo.pages,
        page: 6,
        type: '',
        pram: ''
    }).then(res=>{
        articleInfo.data = res.data
    })
}
onMounted(() => {
    initList()
})
function handlePageClick(page){
    articleInfo.pages = page
    initList()
}
function handleTabClick(name) {

}
</script>

<style scoped>
#list_item {
    margin-bottom: 28px;
}

#article_div {
    background: #f8f8f9;
    padding: 20px;
}

#page_div {
    position: absolute;
    bottom: 20px;
    width: 800px;
    left: 0;
    right: 0;
    margin: 0 auto;
    text-align: center;
}
</style>