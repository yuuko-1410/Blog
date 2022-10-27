<template>
    <div id="manage_div">
        <Card>
            <Tabs>
                <TabPane label="文章管理">
                    <ArticleTable/>
                </TabPane>
                <TabPane label="用户管理">
                    <UserTable/>
                </TabPane>
                <TabPane label="评论管理">
                    <CommentTable/>
                </TabPane>
                    
            </Tabs>
        </Card>
    </div>
</template>

<script setup>
import { reactive } from 'vue'
import ArticleTable from '@/components/manage/ArticleTable.vue'
import UserTable from '@/components/manage/UserTable.vue';
import CommentTable from '@/components/manage/CommentTable.vue'


</script>

<style scoped>
#manage_div {
    height: 100%;
    width: 100%;
    padding: 40px;
}


</style>