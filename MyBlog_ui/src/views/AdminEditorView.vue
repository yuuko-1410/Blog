<template>
  <div id="edit_div">
    
    <div id="edit_item_div">
      <label style="position: relative;top: 4px;">文章标题：</label>
      <Input v-model="artcleData.title" placeholder="文章标题" style="width: 200px;margin-right: 20px;" />
      <label style="position: relative;top: 4px;">标签：</label>
      <Select v-model="artcleData.tag" filterable multiple allow-create style="width: 200px">
        <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
      <Button @click="handleUploadContext" type="primary" style="margin-left: 8px;" >保存</Button>
    </div>
    <v-md-editor v-model="artcleData.text" height="100%" @save="handleSaveEditor" @upload-image="handleUploadImage"
      :disabled-menus="[]"></v-md-editor>
  </div>
  <Modal
        v-model="isShow"
        title="〒▽〒"
        @on-ok="handleEditTemp(true)"
        @on-cancel="handleEditTemp(false)">
        <p>您有一个编辑未保存，是否继续编辑？</p>
    </Modal>
</template>

<script setup>
import { Message } from 'view-ui-plus'
import { onMounted, reactive,ref } from 'vue'
import {userInfo} from '../store/UserInfo'
import {apiUploadFile} from '../api/article'
import {useRouter} from 'vue-router'
const router = useRouter()
const isShow = ref(false)
const artcleData = reactive({
  title: '',
  text: '',
  tag: [],
  tid: ''
})
const cityList = reactive([{
  value: '你好',
  label: '你好'
}])
function handleUploadContext(){
  
  if (artcleData.title == ''){
    Message.warning('请设置标题')
    return
  }
  if (artcleData.text == ''){
    Message.warning('内容为空')
    return
  }

  apiUploadFile(artcleData).then(res=>{
    Message.success('保存成功')
    router.replace("/admin/state")
  })
}
onMounted(() => {
  //判断是否有未保存的文章
  const temp = JSON.parse(localStorage.getItem('temp_artcleData'),artcleData)
  if (temp && temp.id == userInfo.uid){
    isShow.value = true
  }
})
function handleEditTemp(isEdit){
  const temp = JSON.parse(localStorage.getItem('temp_artcleData'),artcleData)
  if (isEdit){
    artcleData.text = temp.text
    artcleData.tag = temp.tag
    artcleData.title = temp.title
  }else{
    localStorage.setItem('temp_artcleData', null)
  }
}
function handleSaveEditor() {
  console.log(artcleData)
  //点击保存图标临时保存到本地
  localStorage.setItem('temp_artcleData', JSON.stringify(artcleData))
}
function handleUploadImage(event, insertImage, files) {
  // 拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
  console.log(files)
  // 此处只做示例
  insertImage({
    url:
      'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1269952892,3525182336&fm=26&gp=0.jpg',
    desc: '图片名称',
    // width: 'auto',
    // height: 'auto',
  });
}

</script>

<style>
#edit_div {
  display: flex;
  position: relative;
  flex-direction: column;
  margin: 10px;
  height: 90%;
}

#edit_item_div {
  margin: 10px;
  display: flex;
  flex-direction: row;

}
</style>