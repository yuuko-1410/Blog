<template>
    <div id="login_view">
        <Card id="login_div">
            <img src="https://dev-file.iviewui.com/stJXDnKhL5qEBD0dHSDDTKbdnptK6mV5/small" />
            <Login @on-submit="handleSubmit">
                <UserName name="username" />
                <Password name="password" />
                <Submit />
            </Login>
        </Card>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { apiLogin,apiGetUserInfo } from '../api/user'
import {Message} from 'view-ui-plus'
import {userInfo,saveUserInfo} from '../store/UserInfo'
const router = useRouter()

function handleSubmit(valid, { username, password }) {
    if (valid) {
        apiLogin({
            'username': username,
            'password': password
        }).then(res => {
            //保存Token到本地
            localStorage.setItem('satoken',res.data.tokenValue)
            //保存用户信息到本地
            getUserInfo(res.data.loginId)
            Message.success('登陆成功')
            //登陆成功返回主页
            router.back()
        })
    }
}
function getUserInfo(id){
    //获取
    apiGetUserInfo({
        'id': id
    }).then(res =>{
        console.log(res)
        //保存
        userInfo.uid = res.data.uid
        userInfo.avatarUrl = res.data.avatarUrl
        userInfo.emil = res.data.emil
        userInfo.username = res.data.username
        userInfo.phoneNumber = res.data.phoneNumber
        saveUserInfo()
    })
}
</script>

<style>
#login_view {
    height: 100%;
    width: 100%;
    background: #2d8cf0;
    display: flex;
    align-items: center;
    justify-content: center;
}

#login_div {
    height: 400px;
    width: 480px;
    text-align: center;
}

#login_from {
    background-color: #f8f8f9;
    display: table-cell;
    vertical-align: middle;
}
</style>