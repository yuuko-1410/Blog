import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "../views/HomeView";
import HomeContextView from "../views/HomeContextView"
import ArticleDetailsView from "../views/ArticleDetailsView"
import LoginView from "../views/LoginView";
import RegisterView from "../views/RegisterView";
import AdminView from "../views/AdminView";
import AdminManageView from "../views/AdminManageView"
import AdminEditorView from "../views/AdminEditorView";
import AdminStateView from "../views/AdminStateView";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
    children: [{
      path: "",
      component: HomeContextView,
    },
    {
      path: "details",
      name: "details",
      component: ArticleDetailsView,
    }]
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/register",

    component: RegisterView,
  },
  {
    path: "/admin",
    name: "admin",
    component: AdminView,
    children: [
      {
        path: "edit",
        component: AdminEditorView,
      },
      {
        path: "state",
        component: AdminStateView,
      },
      {
        path: "manage",
        component: AdminManageView,
      }
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
