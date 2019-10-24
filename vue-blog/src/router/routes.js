import layoutHeaderAside from '@/layout/header-aside'

// 由于懒加载页面太多的话会造成webpack热更新太慢，所以开发环境不使用懒加载，只有生产环境使用懒加载
const _import = require('@/libs/util.import.' + process.env.NODE_ENV)

/**
 * 在主框架内显示
 */
const frameIn = [
  {
    path: '/home',
    redirect: { name: 'index' },
    component: layoutHeaderAside,
    meta: {
      title: '后台首页'
    },
    children: [
      // 首页
      {
        path: 'index',
        name: 'index',
        meta: {
          auth: true
        },
        component: _import('system/index/page')
      },
      // 后台页面
      {
        path: 'AdminUser',
        name: 'AdminUser',
        component: () => import('@/pages/admin/AdminUser'),
        meta: {
          auth: true,
          title: '用户管理'
        }
      },
      {
        path: 'AdminBlog',
        name: 'AdminBlog',
        component: () => import('@/pages/admin/AdminBlog'),
        meta: {
          auth: true,
          title: '博文管理'
        }
      },
      {
        path: 'EditBlog',
        name: 'EditBlog',
        component: () => import('@/pages/admin/EditBlog'),
        meta: {
          auth: true,
          title: '增改博文'
        }
      },
      {
        path: 'AdminPhoto',
        name: 'AdminPhoto',
        component: () => import('@/pages/admin/AdminPhoto'),
        meta: {
          auth: true,
          title: '相册管理'
        }
      },
      {
        path: 'AdminRetell',
        name: 'AdminRetell',
        component: () => import('@/pages/admin/AdminRetell'),
        meta: {
          auth: true,
          title: '留言管理'
        }
      },
      // 系统 前端日志
      {
        path: 'log',
        name: 'log',
        meta: {
          title: '前端日志',
          auth: true
        },
        component: _import('system/log')
      },
      // 刷新页面 必须保留
      {
        path: 'refresh',
        name: 'refresh',
        hidden: true,
        component: _import('system/function/refresh')
      },
      // 页面重定向 必须保留
      {
        path: 'redirect/:route*',
        name: 'redirect',
        hidden: true,
        component: _import('system/function/redirect')
      }
    ]
  }

]

const front = [
  {
    path: '/',
    name: 'frontindex',
    redirect: { name: 'IndexContent' },
    component: () => import('@/pages/front/frontindex'),
    children: [
      {
        path: '/ViewBlog',
        name: 'ViewBlog',
        component: () => import('@/pages/front/user/ViewBlog')
      }, {
        path: '/ViewPhoto',
        name: 'ViewPhoto',
        component: () => import('@/pages/front/user/ViewPhoto')
      }, {
        path: '/IndexContent',
        name: 'IndexContent',
        component: () => import('@/pages/front/user/IndexContent')
      }, {
        path: '/About',
        name: 'About',
        component: () => import('@/pages/front/user/About')
      }, {
        path: '/BlogContent:number',
        component: () => import('@/pages/front/user/BlogContent'),
        name: 'BlogContent'
      }
    ]
  }
]
/**
 * 在主框架之外显示
 */
const frameOut = [
  // 登录
  {
    path: '/login',
    name: 'login',
    component: _import('system/login')
  }
]

/**
 * 错误页面
 */
const errorPage = [
  {
    path: '*',
    name: '404',
    component: _import('system/error/404')
  }
]

// 导出需要显示菜单的
export const frameInRoutes = frameIn

// 重新组织后导出
export default [
  ...frameIn,
  ...frameOut,
  ...errorPage,
  ...front
]
