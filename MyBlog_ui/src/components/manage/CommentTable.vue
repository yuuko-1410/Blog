<template>

    <div id="table_item_div">
        <RadioGroup @on-change="handleChangeRadioClick" v-model="tableData.select" type="button" button-style="solid">
            <Radio label="全部"></Radio>
            <Radio label="待审核"></Radio>
            <Radio label="已删除"></Radio>
        </RadioGroup>

        <Button @click="handleRecClick()" v-show="tableData.removeAll" style="margin-left: 10px;"
            type="primary">一键恢复</Button>
        <Button @click="handleDelClick()" v-show="tableData.removeAll" style="margin-left: 10px;"
            type="error">一键删除</Button>
        <div style="flex: 1"></div>
        <Input v-model="tableData.pram" placeholder="请输入内容" style="width: 200px" />
        <Button @click="loadList()" style="margin-left: 4px;" type="primary">搜索</Button>
    </div>
    <Table border ref="tableData.selection" :columns="tableData.columns" :data="tableData.data"
        @on-selection-change="select">
        <template #action="{index}">
            <Button type="primary" size="small" style="margin-right: 5px"
                @click="gb(index)">{{tableData.btn.gb}}</Button>
            <Button type="error" size="small" @click="rb(index)">{{tableData.btn.rb}}</Button>
        </template>
    </Table>
    <div id="page_div">
        <Page :total="tableData.size" page-size="6" @on-change="handlePageClick" show-total />
    </div>
</template>

<script setup>
import { onMounted, reactive } from 'vue'
import { apiGetCommonListById, apiSetComment, apiDelCommonByCid } from '@/api/comment'
import { timestampToTime } from '@/common/dataUtils'
import { useRouter } from 'vue-router'
import { Message } from 'view-ui-plus'
const router = useRouter()
onMounted(() => {
    loadList()
})
function handleChangeRadioClick() {
    switch (tableData.select) {
        case '全部':
            tableData.state = ''
            tableData.btn = {
                'gb': '查看',
                'rb': '封禁'
            }
            break
        case '待审核':
            tableData.state = 'state_ban'
            tableData.btn = {
                'gb': '解封',
                'rb': '删除'
            }
            break
        case '已删除':
            tableData.state = 'state_none'
            tableData.btn = {
                'gb': '恢复',
                'rb': '移除'
            }
            break
    }
    loadList()
}

function loadList() {
    apiGetCommonListById({
        //分页
        current: tableData.pages,
        page: 10,
        type: tableData.state,
        pram: tableData.pram
    }).then(res => {
        console.log(res)
        const dataList = res.data.userinfo
        tableData.size = res.data.size
        tableData.data = []
        dataList.forEach(element => {
            tableData.data.push({
                phoneNumber: element.phoneNumber,
                username: element.username,
                emil: element.emil,
                uid: element.uid,
                createTime: timestampToTime(element.createTime),
            })
        });
    })
}
const tableData = reactive({
    columns: [
        {
            type: 'selection',
            width: 60,
            align: 'center'
        }
        ,
        {
            title: '评论内容',
            slot: 'avatar',
            align: 'center'
        },
        {
            title: '用户名',
            key: 'username',
            align: 'center'
        },
        {
            title: '来源',
            key: 'emil',
            align: 'center'
        },
        {
            title: '状态',
            key: 'phoneNumber',
            align: 'center'
        },
        {
            title: '发表时间',
            key: 'createTime',
            align: 'center'
        },
        {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
        }
    ],
    data: [],
    select: '全部',
    pram: '',
    pages: 1,
    size: 0,
    state: '',
    btn: {//按钮相关
        gb: '查看',
        rb: '封禁'
    },
    edit: {
        uid: '',
        isShow: false,
        username: '',
        emil: '',
        phoneNumber: ''
    },
    removeAll: false
})
function handleSaveEditClick(){
    const edit = tableData.edit
    if (edit.emil == ''||edit.phoneNumber == ''||edit.username == ''){
        Message.warning('请输入内容')
        return 
    }
    //编辑用户
    apiSetUserInfo({
        'uid': edit.uid,
        'username': edit.username,
        'phoneNumber': edit.phoneNumber,
        'emil': edit.emil
    }).then(res => {
        Message.success('操作成功')
        loadList()
    })
}

function gb(index) {
    const uid = tableData.data[index].uid
    switch (tableData.btn.gb) {
        case '编辑':
            tableData.edit = {
                isShow: true,
                username: tableData.data[index].username,
                phoneNumber: tableData.data[index].phoneNumber,
                emil: tableData.data[index].emil,
                uid: uid
            }
            break
        case '解封':
            changeDisable(uid, 1)
            break
        case '恢复':
            changeDisable(uid, 1)
            break
    }
}
function rb(index) {
    const uid = tableData.data[index].uid
    switch (tableData.btn.rb) {
        case '封禁':
            changeDisable(uid, 2)
            break
        case '注销':
            changeDisable(uid, 3)
            break
        case '移除':
            changeDisable(uid, 0)
            break
    }

}
function changeDisable(uid, code) {
    apiSetUserInfo({
        'uid': uid,
        'disable': code
    }).then(res => {
        Message.success('操作成功')
        loadList()
    })
}
//勾选事件
function select(selection) {
    if (selection.length == 0) {
        tableData.removeAll = false
    } else {
        tableData.removeAll = true
    }
}
</script>

<style>
#table_item_div {
    margin-bottom: 10px;
    display: flex;
}

#page_div {
    margin: 10px;
    text-align: center;
}
</style>