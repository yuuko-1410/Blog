<template>
    <div id="table_item_div">
        <RadioGroup @on-change="handleChangeRadioClick" v-model="tableData.select" type="button" button-style="solid">
            <Radio label="可用"></Radio>
            <Radio label="待审核"></Radio>
            <Radio label="未通过"></Radio>
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
        <template #action="{index }">
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
import { apiGetArticleDetailsList, apiUpdateArticle } from '@/api/article'
import { articleInfo } from '@/store/articeInfo'
import { timestampToTime } from '@/common/dataUtils'
import { useRouter } from 'vue-router'
import { Message } from 'view-ui-plus'
const router = useRouter()
onMounted(() => {
    loadList()
})
function handleChangeRadioClick() {
    switch (tableData.select) {
        case '可用':
            tableData.state = ''
            tableData.btn = {
                'gb': '查看',
                'rb': '下架'
            }
            break
        case '待审核':
            tableData.state = 'state_check'
            tableData.btn = {
                'gb': '通过',
                'rb': '下架'
            }
            break
        case '未通过':
            tableData.state = 'state_disable'
            tableData.btn = {
                'gb': '通过',
                'rb': '删除'
            }
            break
        case '已删除':
            tableData.state = 'state_del'
            tableData.btn = {
                'gb': '恢复',
                'rb': '移除'
            }
            break
    }
    loadList()
}

function loadList() {
    apiGetArticleDetailsList({
        //分页
        current: articleInfo.pages,
        page: 10,
        type: tableData.state,
        pram: tableData.pram
    }).then(res => {
        const dataList = res.data.blogArticles
        tableData.size = res.data.size
        tableData.data = []
        dataList.forEach(element => {
            tableData.data.push({
                title: element.title,
                address: element.username,
                tid: element.tid,
                updateTime: timestampToTime(element.updateTime),
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
        },
        {
            title: 'id',
            key: 'tid',
            align: 'center'
        },
        {
            title: '标题',
            key: 'title',
            align: 'center'
        },
        {
            title: '作者',
            key: 'address',
            align: 'center'
        },
        {
            title: '更新时间',
            key: 'updateTime',
            align: 'center'
        },
        {
            title: '发布时间',
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
    select: '可用',
    pram: '',
    size: 0,
    state: '',
    btn: {//按钮相关
        gb: '查看',
        rb: '下架'
    },
    removeAll: false
})

function gb(index) {
    const tid = tableData.data[index].tid
    switch (tableData.btn.gb) {
        case '查看':
            router.push("/details?tid=" + tid)
            break
        case '通过':
            changeDisable(tid,1)
            break
        case '恢复':
            changeDisable(tid,1)
            break
    }
}
function rb(index) {
    const tid = tableData.data[index].tid
    switch (tableData.btn.rb) {
        case '下架':
            changeDisable(tid,3)
            break
        case '删除':
            changeDisable(tid,0)
            break
        case '移除':
            changeDisable(tid,-1)
            break
    }

}
function changeDisable(tid,code) {
    apiUpdateArticle({
        'tid': tid,
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