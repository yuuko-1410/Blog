<template>
    <div id="article_div">
        <Card :bordered="false" style="height: 100%">
            <template #title>标签</template>
            <Tag color="default">default</Tag>
            <Tag color="default">default</Tag>
            <Tag color="default">default</Tag>
            <Tag color="default">default</Tag>
            <Tag color="default">default</Tag>
        </Card>
    </div>
</template>

<script>

</script>

<style scoped>
#article_div{
    background:#f8f8f9;
    padding-top: 20px;
    padding-right: 20px;
    height: 200px;
}
</style>