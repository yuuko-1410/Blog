<template>
    <div id="topbar_div">
        <Menu mode="horizontal" style="display:flex;" active-name="1" @on-select="handleMenuClick">
            <div id="left_div">
                <h2 @click="toIndex">小冰的个人博客</h2>
            </div>
            <div id="serch">
                <Input v-model="articleInfo.sousuo" placeholder="搜索您感兴趣的内容吧" style="width: 200px" />
                <Button @click="handleInputClick" style="margin-left: 4px;" type="primary">搜索</Button>
            </div>

            <div id="right_items">
                <MenuItem name="index">
                <Icon type="ios-paper" />
                首页
                </MenuItem>
                <MenuItem name="wenzhang">
                <Icon type="ios-paper" />
                文章
                </MenuItem>
                <Submenu>
                    <template #title >
                        <Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
                        {{userInfo.username == '' ? '未登录' : userInfo.username}}
                    </template>
                    <MenuItem v-show="userInfo.uid" name="admin">个人中心</MenuItem>
                    <MenuItem v-show="userInfo.uid" name="logout">退出登录</MenuItem>
                    <MenuItem v-show="!userInfo.uid" name="login">登录</MenuItem>
                    <MenuItem v-show="!userInfo.uid" name="register">注册</MenuItem>
                </Submenu>
            </div>
        </Menu>
    </div>
</template>

<script setup>
import { apiLogout } from '../api/user'
import { Message } from 'view-ui-plus'
import { useRouter } from 'vue-router'
import { userInfo, removeUserInfo,initUserInfo } from '../store/UserInfo'
import {articleInfo} from '../store/articeInfo'
import { apiGetArticleDetailsList } from '../api/article'
import {onMounted} from 'vue'
const router = useRouter()
onMounted(()=>{
    initUserInfo()
})
function handleInputClick(){
    apiGetArticleDetailsList({
        //分页
        current: articleInfo.pages,
        page: 5,
        type: '',
        pram: articleInfo.sousuo
    }).then(res=>{
        router.replace('/')
        articleInfo.data = res.data
    })
}
function logout() {
    apiLogout().then(res => {
        Message.success('退出登陆成功')
    })
    //清除本地数据
    removeUserInfo()
    //清除Token
    localStorage.setItem('satoken', '')
}
function toIndex(){
    router.replace('/')
}
function showLog() {
    console.log(userInfo)
}


function handleMenuClick(name) {
    switch (name) {
        case 'admin':
            if (userInfo.uid){
                router.push('/admin/state')
            }else{
                Message.warning('请先登录')
            }
            break
        case 'login':
            router.push('/login')
            break
        case 'logout':
            logout()
            break
        case 'register':
            router.push('/register')
            break
        case 'wenzhang':
            showLog()
            break
    }
}
</script>

<style scoped>
#topbar_div {
    height: 58px;
    width: 100%;
    background-color: #fff;
}

#serch {
    margin-right: 20px;

}

#left_div {
    flex: 1;
    margin-left: 40px;
}

#right_items {
    margin-right: 20px;
}
</style>