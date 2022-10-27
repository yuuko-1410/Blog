<template>
    <div id="commentItem_div">
        
    </div>

</template>

<script setup>
import { defineProps, onMounted, reactive } from 'vue'
const props = defineProps({
    commentData: {
        type: Object
    }
})
function handleCommentClick(){
    console.log(props.commentData)
}
</script>

<style scoped>
#commentItem_div{
    padding: 10px;
}
</style>