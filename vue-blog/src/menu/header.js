// 菜单 顶栏
export default [
  { path: '/home/index', title: '首页', icon: 'home' },
  {
    title: '页面',
    icon: 'folder-o',
    children: [
      { path: '/home/AdminBlog', title: '博文管理' },
      { path: '/home/AdminPhoto', title: '相册管理' },
      { path: '/home/AdminRetell', title: '留言管理' },
      { path: '/home/AdminUser', title: '用户管理' }
    ]
  }
]
