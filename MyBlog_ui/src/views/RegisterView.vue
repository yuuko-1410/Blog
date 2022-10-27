<template>
    <div id="login_view">
        <Card id="login_div">
            
            <Login @on-submit="handleSubmit">
                <h1>注册</h1>
                <UserName name="username" />
                <Email name="mail" />
                <Captcha name="captcha" :field="['mail']" @on-get-captcha="handleGetCaptcha" />
                <Password name="password" />
                <Submit>注册</Submit>
            </Login>
        </Card>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { apiCaptcha,apiRegister } from '../api/user'
import {Message} from 'view-ui-plus'
const router = useRouter()
const code = ref('')
function handleGetCaptcha(valid, {mail}) {
    apiCaptcha({
        'emil':mail
    }).then(res => {
        console.log(res)
        code.value = res.data
    })
}
function handleSubmit(valid, {captcha, mail,username, password }) {
    if (valid) {
        apiRegister({
            'username':username,
            'password':password,
            'emil':mail,
            'code':code.value,
            'captcha':captcha
        }).then(res=>{
            Message.success('注册成功')
            router.replace("/login")
        })
    }
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