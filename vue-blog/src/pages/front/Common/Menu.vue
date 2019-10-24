<template>
    <div  class="myclass"  :class=" navBarFixed == true ? 'navBarWrap' :''">
      <div class="menu1">
        <el-menu
          :default-active="defaultActive"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b" >
<!--          <el-menu-item index="7"><img width="24" src="../../assets/logo.png"/></el-menu-item>-->
          <el-menu-item index="1"><router-link tag="span" exact  to="/IndexContent">首页</router-link></el-menu-item>
          <el-menu-item index="6"><router-link tag="span"  to="/ViewBlog">浏览博客</router-link></el-menu-item>
          <el-menu-item index="2"><router-link tag="span" to="/ViewPhoto">浏览相册</router-link></el-menu-item>
          <el-menu-item index="3"><router-link tag="span" to="/About">关于</router-link></el-menu-item>
          <el-menu-item index="5" class="login" v-if="!username" style="float:right">
              <router-link to="/Login" tag="span">未登录</router-link>
          </el-menu-item>
            <el-menu-item class="login" v-else style="float:right">
                <el-dropdown style="color:whitesmoke">
                <span>你好,{{username}}</span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item @click.native="logOff">注销</el-dropdown-item>
                </el-dropdown-menu>
                </el-dropdown>
            </el-menu-item>
          <el-menu-item class="input" index="4" style="float:right" v-if="enabled">
            <el-input  placeholder="请输入内容" v-model="search" class="inline-input"  clearable>
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-menu-item>

        </el-menu>
      </div>
      <div class="menu2">
        <el-menu
          :default-active="defaultActive"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b" >
<!--          <el-menu-item index="7"><img width="24" src="../../assets/logo.png"/></el-menu-item>-->
          <el-menu-item index="1"><router-link tag="span" exact  to="/IndexContent">首页</router-link></el-menu-item>
          <el-menu-item index="6"><router-link tag="span"  to="/ViewBlog">浏览博客</router-link></el-menu-item>
          <el-menu-item index="2"><router-link tag="span" to="/ViewPhoto">浏览相册</router-link></el-menu-item>
          <el-menu-item index="3"><router-link tag="span" to="/About">关于</router-link></el-menu-item>
          <el-menu-item class="login"  style="float:right">
                <router-link to="/Login" tag="span">未登录</router-link>
            </el-menu-item>
          <el-menu-item class="input" index="4" style="float:right;display: none">
            <el-input   placeholder="请输入内容" v-model="search" class="inline-input"  clearable>
              <el-button   slot="append" icon="el-icon-search"></el-button>
            </el-input>

          </el-menu-item>

        </el-menu>
      </div>

    </div>

</template>
<script>
import { mapState, mapActions } from 'vuex'
import cookies from '../../../libs/util.cookies'
export default {
  name: 'Menu',
  data () {
    return {
      search: '',
      select: '',
      timeout: null,
      nowRouter: '',
      navBarFixed: false,
      isCollapse: true,
      enabled: false
    }
  },
  computed: {
    username: function () {
      return cookies.get('username')
    },
    defaultActive () {
      var a
      if (this.$route.path === '/ViewBlog') {
        a = '6'
      } else if (this.$route.path === '/ViewPhoto') {
        a = '2'
      } else if (this.$route.path === '/IndexContent') {
        a = '1'
      } else if (this.$route.path === '/About') {
        a = '3'
      } else if (this.$route.path.params !== null) {
        a = '6'
      } else {
        a = '1'
      }
      return a
    }
  },
  methods: {
    ...mapActions('d2admin/account', [
      'logout'
    ]),
    /**
       * @description 登出
       */
    logOff () {
      this.logout({
        confirm: true
      })
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    handleSelect (key, keyPath) {
      var nowRouter = this.$route.path
      switch (key) {
        case '6' :
          if (nowRouter !== '/ViewBlog') this.$router.push({ path: '/ViewBlog' })
          break
        case '2' :
          if (nowRouter !== '/ViewPhoto') this.$router.push({ path: '/ViewPhoto' })
          break
        case '3' :
          if (nowRouter !== '/About') this.$router.push({ path: '/About' })
          break
        case '5' :
          this.$router.push({ path: '/Login' })
          break
        case '1' :
          if (nowRouter !== '/IndexContent') this.$router.push({ path: '/IndexContent' })
          break
        default:
          break
      }
    },
    watchScroll () {
      var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      //  当滚动超过 50 时，实现吸顶效果
      if (scrollTop > 209 && document.body.clientWidth > '672') {
        this.navBarFixed = true
      } else {
        this.navBarFixed = false
      }
    }
  },
  watch: {
    search (curval, oldval) {
      if (this.$route.path === '/ViewBlog') {
        clearTimeout(this.timeout)
        this.timeout = setTimeout(() => {
          this.$store.commit('setSearch', this.search)
        }, 300)
      }
    },
    $route (to, from) {
      if (to.path === '/ViewBlog') {
        this.enabled = true
      } else {
        this.enabled = false
      }
    }
  },
  created () {
    window.addEventListener('scroll', this.watchScroll)
  }
}
</script>
<style scoped>
  .menu2{
    display: none;
  }
@media  screen and (max-width: 692px) {
    .el-menu-item{

    }
  .input{
    display: none;
  }
  .menu1{
    display: none;
  }
  .menu2{
    display: block;
  }
    .input{
      clear: both;
    }
    .login{
      display: none;
    }
    .myclass{
      position: relative;
    }
}
.navBarWrap {
  position:fixed;
  top:0;
  z-index:999;
  width: 100%;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

</style>
