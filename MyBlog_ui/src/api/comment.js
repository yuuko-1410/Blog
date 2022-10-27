import {get,post} from './http'

//发送评论
export const apiSetComment = p => post('/comment/setComment', p);
//获取评论列表
export const apiGetCommonListById = p => post('/comment/getCommonListById', p);
//删除评论
export const apiDelCommonByCid = p => post('/comment/delCommonByCid', p);