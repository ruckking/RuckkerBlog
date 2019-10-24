<template>
    <div :style="{backgroundImage:'url('+url+')'}" style="background-attachment:fixed;background-size: 100% 100%;">

      <Menu/>

      <div id="v-content" v-bind:style="{minHeight: Height+'px'}">  <router-view></router-view></div>
      <Footer :style="enableFooer"/>
    </div>

</template>
<script>
import Footer from './Common/Footer'
import Menu from './Common/Menu'
import '../../components/d2-icon/font-awesome-4.7.0/css/font-awesome.min.css'
export default {
  data () {
    return {
      Height: 0,
      url: require('@/assets/03.jpg')
    }
  },
  components: {
    Footer, Menu

  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    }
  },
  computed: {
    enableFooer: function () {
      if (this.$route.path === '/ViewPhoto') {
        return 'display:none'
      } else {
        return 'display:block'
      }
    }
  },
  mounted () {
    // 动态设置内容高度 让footer始终居底   header+footer的高度是100
    this.Height = document.documentElement.clientHeight - 100
    // 监听浏览器窗口变化
    window.onresize = () => { this.Height = document.documentElement.clientHeight - 100 }
  }
}
</script>
<style>
body {
  margin: 0;
}
</style>
