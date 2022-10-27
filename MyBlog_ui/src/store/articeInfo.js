import { reactive } from "vue";

export const articleInfo = reactive({
  data: {
    current: 1,
    page: 5,
    size: 0,
    blogArticles: [
      {
        tid: "",
        uid: "",
        title: "",
        context: "",
        likeNum: "",
        watchNum: "",
        createTime: 0,
        updateTime: 0,
      },
    ],
  },
  pages: 1,
  sousuo: '',
});
