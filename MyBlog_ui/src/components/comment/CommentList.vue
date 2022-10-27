<template>
    <div>
        <div style="display: flex;margin-bottom: 20px;">
            <Input style="flex:1" v-model="commentListData.comment.context" type="textarea" :rows="4" maxlength="200"
                show-word-limit placeholder="请输入内容" />
            <Button @click="handleCommentClick" style="align-self:flex-end;margin-left: 8px;" type="primary">评论</Button>
        </div>
        <List item-layout="vertical" style="padding-bottom: 20px;">
            <ListItem v-for="item in commentListData.listData" :key="item.title" style="padding-left: 10px;">
                <ListItemMeta :avatar="item.avatarUrl" :title="item.username" />
                {{ item.context }}
                <div style="display:flex;">
                    <li id="item_div">
                        <Icon type="ios-thumbs-up-outline" /> 0
                    </li>
                    <li @click="handleShowHuifuClick(item)" id="item_div" style="color:#909399">
                        <Icon type="ios-chatbubbles-outline" />回复
                    </li>
                    <li v-show="userInfo.username == item.username" id="item_div">
                        <Poptip confirm title="确认删除该条评论" @on-ok="handledelComment(item)"
                            @on-cancel="cancel">
                            <a>删除评论</a>
                        </Poptip>
                        <!-- <a @click="">删除评论</a> -->
                    </li>
                </div>
                <div v-show="item.showHuifu" style="width:100%;background-color: #f9fafb;border-radius: 10px;">
                    <div style="display: flex;margin-bottom: 20px;">
                        <Input style="flex:1" v-model="commentListData.comment.huifu" type="textarea" :rows="3"
                            maxlength="200" show-word-limit placeholder="请输入内容" />
                        <Button @click="handleAnswerClick(item)" style="align-self:flex-end;margin-left: 8px;"
                            type="primary">回复</Button>
                    </div>
                </div>
            </ListItem>
        </List>
        <div id="page_div">
            <Page :total="commentListData.size" :page-size="commentListData.page" @on-change="handleChangePage"
                size="small" show-total />
        </div>
    </div>
</template>

<script setup>
import { defineProps, onMounted, reactive } from 'vue'
import { apiGetCommonListById, apiSetComment, apiDelCommonByCid } from '../../api/comment'
import { userInfo } from '@/store/UserInfo'
import { Message } from 'view-ui-plus'
import Comment from './Comment.vue';

const props = defineProps({
    tid: {
        type: Object
    }
})

onMounted(() => {
    initList()
})
function handledelComment(index) {
    console.log(index)
    apiDelCommonByCid({
        cid: index.cid
    }).then(res => {
        console.log(res)
        Message.success('删除成功')
        initList()
    })
}
function handleAnswerClick(item) {
    const msg = '回复@' + item.username + ': ' + commentListData.comment.huifu
    apiSetComment({
        'tid': item.tid,
        'context': msg,
        'uid': userInfo.uid,
        'fromCid': item.cid
    }).then(res => {
        commentListData.comment.context = ''
        Message.success('评论成功')
        initList()
    })
    console.log(item)
}
function handleShowHuifuClick(item) {
    item.showHuifu = !item.showHuifu
    console.log(item)
}
function handleChangePage(page) {
    commentListData.current = page
    initList()
}
function initList() {
    apiGetCommonListById({
        current: commentListData.current,
        page: 6,
        tid: props.tid,
        fromCid: '',
        uid: ''
    }).then(res => {
        commentListData.listData = res.data.commentInfo
        commentListData.current = res.data.current
        commentListData.size = res.data.size
        commentListData.page = res.data.page

        commentListData.listData.forEach(element => {
            element.showHuifu = false
        });
        console.log(res)
    })
}
function handleCommentClick() {
    if (commentListData.comment.context == '') {
        return
    }
    apiSetComment({
        'tid': props.tid,
        'context': commentListData.comment.context,
        'uid': userInfo.uid,
        'fromCid': ''
    }).then(res => {
        commentListData.comment.context = ''
        Message.success('评论成功')
        initList()
    })
}

const commentListData = reactive({
    listData: [
        {
            username: 'This is title 1',
            avatarUrl: 'https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar',
            context: 'This is the content, this is the content, this is the content, this is the content.',
            showHuifu: false
        }
    ],
    showHuifu: true,
    fromCid: null,
    size: 0,
    current: 1,
    page: '',
    comment: {
        context: '',
        huifu: ''
    }
})
</script>

<style scoped>
#page_div {
    position: absolute;
    bottom: 20px;
    width: 800px;
    left: 0;
    right: 0;
    margin: 0 auto;
    text-align: center;
}

#item_div {
    margin: 4px;
}
</style>