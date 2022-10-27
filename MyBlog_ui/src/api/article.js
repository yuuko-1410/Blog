import {get,post} from './http'

//提交文章
export const apiUploadFile = p => post('/article/uploadFile', p);
//获取文章列表
export const apiGetArticleDetailsList = p => post('/article/getArticleDetailsList', p);
export const apiGetArticleDetailsById = p => get('/article/getArticleDetailsById',p);
//更新文章
export const apiUpdateArticle = p => post('/article/updateArticle',p);