import {get,post} from './http'

//登录
export const apiLogin = p => post('/user/login', p);
//注销
export const apiLogout = p => post('/user/logout', p);
//获取验证码
export const apiCaptcha = p => get('user/captcha', p);
export const apiRegister = p => post('user/register', p);
//获取用户信息
export const apiGetUserInfo = p => get('user/getUserById',p);
export const apiGetUserListByInfo = p => post('user/getUserListByInfo', p);
//修改用户信息
export const apiSetUserInfo = p => post('user/setUserInfo', p);
