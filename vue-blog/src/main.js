// Vue
import Vue from 'vue'
import i18n from './i18n'
import App from './App'
import httpProxy from '@/plugin/axios'
// 核心插件
import d2Admin from '@/plugin/d2admin'
import waterfall from 'vue-waterfall2'
import preview from 'vue-photo-preview'
import 'vue-photo-preview/dist/skin.css'
import VCharts from 'v-charts'
import D2Crud from '@d2-projects/d2-crud'
import VueQuillEditor from 'vue-quill-editor'
import Quill from 'quill'
import Router from 'vue-router'
// require styles
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
// 新增下面代码：
import resizeImage from 'quill-image-resize-module' // 调整大小组件。
import { ImageDrop } from 'quill-image-drop-module' // 拖动加载图片组件。

// store
import store from '@/store/index'


// 菜单和路由设置
import router from './router'
import menuHeader from '@/menu/header'
import menuAside from '@/menu/aside'
import { frameInRoutes } from '@/router/routes'

Quill.register('modules/imageDrop', ImageDrop)
Quill.register('modules/resizeImage ', resizeImage)

// 核心插件
Vue.use(d2Admin)
Vue.use(waterfall)
Vue.use(preview)
Vue.use(VCharts)
Vue.use(D2Crud)
Vue.use(VueQuillEditor)

Vue.prototype.httpProxy = httpProxy

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

const originalPush = Router.prototype.push // 解决报错问题
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

new Vue({
  router,
  store,
  i18n,
  render: h => h(App),
  created () {
    // 处理路由 得到每一级的路由设置
    this.$store.commit('d2admin/page/init', frameInRoutes)
    // 设置顶栏菜单
    this.$store.commit('d2admin/menu/headerSet', menuHeader)
    // 设置侧边栏菜单
    this.$store.commit('d2admin/menu/asideSet', menuAside)
    // 初始化菜单搜索功能
    this.$store.commit('d2admin/search/init', menuHeader)
  },
  mounted () {
    // 展示系统信息
    this.$store.commit('d2admin/releases/versionShow')
    // 用户登录后从数据库加载一系列的设置
    this.$store.dispatch('d2admin/account/load')
    // 获取并记录用户 UA
    this.$store.commit('d2admin/ua/get')
    // 初始化全屏监听
    this.$store.dispatch('d2admin/fullscreen/listen')
  }
}).$mount('#app')
