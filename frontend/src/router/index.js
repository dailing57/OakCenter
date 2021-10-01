import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/Layout";
const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/home",
    children:[
      {
        path: '/register',
        name: 'register',
        component: () => import('@/views/Register')
      },
      {
        path: '/home',
        name: 'home',
        component: () => import('@/views/Home')
      },
      {
        path: '/activity',
        name: 'activity',
        component: () => import('@/views/Activity')
      },
      {
        path: '/database',
        name: 'database',
        component: () => import('@/views/DataBase')
      },
      {
        path: '/jbrowse',
        name: 'jbrowse',
        component: () => import('@/views/JBrowse')
      },
      {
        path: '/manage',
        name: 'manage',
        component: () => import('@/views/Manage'),
        redirect: "/userList",
        children: [
          {
            path: '/userList',
            name: 'userList',
            component: () => import('@/components/manage/userList')
          },
          {
            path: '/articleList',
            name: 'articleList',
            component: () => import('@/components/manage/articleList')
          },
          {
            path: '/homeInfo',
            name: 'homeInfo',
            component: () => import('@/components/manage/homeInfo')
          },
          {
            path: '/databaseList',
            name: 'databaseList',
            component: () => import('@/components/manage/databaseList')
          },
        ]
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 限制某些页面禁止未登录访问
let limitPagePath = ["/manage","/userList","/databaseList","/homeInfo","/articleList"]

router.beforeEach((to, from, next) => {
  if (limitPagePath.includes(to.path)) {
    // 判断sessionStorage是否保存了用户信息
    let userStr = sessionStorage.getItem("user") || "{}"
    let user = JSON.parse(userStr)
    if (!user.id || user.role!=='admin') {
      next({path: "/"})
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
