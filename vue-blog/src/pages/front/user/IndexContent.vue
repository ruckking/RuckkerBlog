<template>
    <div>
      <div class="pull-height animated" style="padding-bottom: 20px">
        <div class="index">
      <el-container class="outerContainer">

        <el-container class="innerContainer">
          <el-main>
            <el-row>
              <el-col>
                <el-carousel style="z-index: 0" class="carousel"  :height="bannerH +'px'" :interval="5000" arrow="always">
                  <el-carousel-item class="carousel-item" v-for="item in imgList" :key="item.id">
                    <el-row>
                      <el-col :span="24">
                        <img  style="max-width: 100%; height-width: 100%" :src="item.idView" class="banner_img"/>
                      </el-col>
                    </el-row>
                  </el-carousel-item>
                </el-carousel>
              </el-col>
              <el-col class="toOpcity"  style="height: 100%" v-for="(item,value) in blogList" :key="value">
                <el-col  :span="6" style="margin:0;height: 234px">
                  <img style="width: 80%" height="234" :src="url[value]"/>
                </el-col>
                <el-card body-style="padding:0px" shadow="hover"  class="mycard">
                  <el-col style="margin:0px;height: 234px;position: relative;padding-left: 15px" :span="24" >
                    <h1 style="line-height: 0;text-align: left;margin-bottom: 30px">{{item.blogtitle}}</h1>
                    <article v-if="item.blogcontent.length<=200" v-html="item.blogcontent" style="line-height: 2;text-align: left;"></article>
                    <article v-else  style="line-height: 2;text-align: left"><p style="text-overflow: ellipsis;width: 100%;overflow: hidden">{{item.blogcontent.substring(0,200)}} <router-link  :to="'/BlogContent:'+item.blogid" style="">...详情</router-link></p>
                     </article>
                    <div style="line-height: 2;text-align: left;width: 100%;position: absolute;bottom: 0">
                      <b><i class="el-icon-document"></i> 作者:{{item.blogpasser}}</b>
                      <b><i class="el-icon-date"></i>发布日期:{{item.blogdate.split(" ")[0]}}</b>
                      <b style="margin-left: 46%  "><i class="el-icon-star-on">点赞数</i>:{{item.star}}</b>
                    </div>
                  </el-col>
                </el-card>

              </el-col>
            </el-row>
          </el-main>
        </el-container>
        <el-aside class="aside toOpcity" style="width: 20%;height: 100%">
          <el-row>
            <el-col class="aside-title">
              <h1 >关于我们</h1>
            </el-col>
            <el-col class="aside-content" style="line-height: 30px;">
              <el-row>
                <el-col :span="12" >
                  <el-image style="width: 120px;height: 120px;padding:60px 60px 40px 40px" :previewSrcList="previewList" :src="url2"></el-image>
                </el-col>
                <el-col :span="12">
                  <h3 style="text-align: left;margin-top:50px;margin-bottom: 0;">微信号</h3>
                  <ul style="padding:15px;margin-top: 0;">
                    <li>
                      • 技术干货推荐
                    </li>
                   <li>
                     • 免费资料领取
                   </li>
                   <li style="font-weight: bolder">
                     • 扫码领取更多惊喜
                   </li>
                   </ul>
                </el-col>
              </el-row>
              <el-Divider></el-Divider>
              <el-row>
                <el-col style="font-size: 24px">
                  <el-popover
                          placement="top-start"
                          title="电话"
                          width="100"
                          trigger="hover"
                          content="13246076856">
                    <i slot="reference" class="el-icon-phone icon-demo indexI"></i>
                  </el-popover>
                  <a style="text-decoration: none;color:#333333"  href="https://github.com/ruckking">
                    <i class="github fa icon-demo fa-github indexI"></i>
                  </a>
                  <a style="text-decoration: none" href="tencent://AddContact/?fromId=45&fromSubId=1&subcmd=all&uin=840679158&website=www.ruck.com">
                    <i class="fa fa-qq icon-demo  indexI"></i>
                  </a>
                  <el-popover
                          placement="top-start"
                          title="联系邮箱"
                          width="100"
                          trigger="hover"
                          content="840679158@qq.com">
                    <i slot="reference" class="fa fa-envelope-o icon-demo indexI"></i>
                  </el-popover>
                </el-col>
              </el-row>
            </el-col>
            <el-col class="aside-title">
              <h1 >热门文章</h1>
            </el-col>
            <el-col class="aside-content"  style="line-height: 20px">
              <el-card v-for="(item,index) in blogList" :key="index" class="box-card">
                <router-link  v-rainbow style="cursor: pointer" :to="'/BlogContent:'+item.blogid">
                 {{item.blogtitle}}   <i style="float: right" class="el-icon-star-on">{{item.star}}</i>
                </router-link>

              </el-card>

            </el-col>
            <el-col class="aside-title">
              <h1 >热门标签</h1>
            </el-col>
            <el-col class="aside-content" style="line-height: 35px;padding-top:118px">
              <el-tag style="margin:10px" v-for="(item,index) in popList" :key="index" :type="type1[Math.floor((Math.random()*type1.length))]">
                  <router-link :to="{path:'/ViewBlog',query:{type:item.type}}">
                {{item.type}}
                  </router-link>
              </el-tag>
            </el-col>
<!--            <el-col>-->
<!--              <el-divider style="background-color: red"></el-divider>-->
<!--            </el-col>-->
            <el-col class="aside-title">
              <h1 >友情链接</h1>
            </el-col>
            <el-col class="aside-content" style="line-height: 35px;">
              <ul class="friendlyLink">
                <li v-for="(index,value) in 4" :key="value">
                  <a href="www.baidu.com">百度</a>
                  <a href="www.baidu.com">百度</a>
                </li>
              </ul>

            </el-col>
          </el-row>
        </el-aside>
      </el-container>
        </div>
      </div>
    </div>
</template>
<script>
import { CustomForeach } from '../../../utils/utils'
import httpProxy from '../../../plugin/axios'
export default {
  components: {
  },
  data () {
    return {
      bannerH: 200,
      blogList: [],
      popList: [],
      type1: ['success', 'info', 'warning', 'danger'],
      url: [],
      previewList: [require('@/assets/06.jpg')],
      url2: require('@/assets/06.jpg'),
      imgList: [
      ]
    }
  },
  directives: {
    'rainbow': {
      bind (el, binding, vnode) {
        el.style.color = '#' + Math.random().toString(16).slice(2, 8)
      }
    }
  },
  methods: {
    setBannerH () {
      this.bannerH = 740 / 2560 * document.body.clientWidth
      if (this.bannerH > 740) this.bannerH = 740
      if (this.bannerH < 360) this.bannerH = 200
    },
    getUrl (src) {
      return require('file-loader!@/assets/img/' + src)
    },
    getUrl2 (src) {
      return require('@/assets/img/' + src)
    }

  },
  async mounted () {
    this.setBannerH()
    await this.httpProxy.get('/getCarouselPic').then(res => {
      this.imgList = res.data
    })
    await this.httpProxy.get('/getPopType').then(res => {
      this.popList = res.data
    })
    this.imgList = JSON.parse(JSON.stringify(this.imgList).replace(/photourl/g, 'idView'))
    this.imgList = JSON.parse(JSON.stringify(this.imgList).replace(/photoid/g, 'id'))
    this.imgList.forEach((value, index) => {
      this.imgList[index].idView = this.getUrl2(this.imgList[index].idView.split('\\')[5])
    })
    window.addEventListener('resize', () => {
      this.setBannerH()
    }, false)
    await this.httpProxy.get('/getTopBlog').then(response => {
      this.blogList = response.data
    })
    CustomForeach([1, 2, 3, 4, 5], async (e, i) => {
      let blogid = '首页' + e
      await httpProxy.get('/getPhotoByBlogId?blogid=' + blogid).then(respon => {
        if (respon.status !== 'fail') {
          this.url.push(this.getUrl(respon.data[0].photourl.split('\\')[5]))
        }
      })
    })
  }
}
</script>
<style scoped>
  b{
    margin-right: 10px;
  }
  .mycard,.el-card__body{
  }
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }
  li{
    list-style: none;
    text-align: left;
  }
  .carousel{
    opacity: 1;
  }
  .carousel-item{
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .outerContainer{
    padding-top:30px;
  }
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  .el-menu.el-menu--horizontal{
    border: none;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  .el-divider--horizontal{
    margin:0;
  }
  .el-divider{
    background-color:red;
  }
  .index {
    background-color: transparent;
  }
  .friendlyLink a {
    display: block;
    width: 118px;
    height: 35px;
    float: left;
    border: 1px darkgrey solid;
    padding: 0px !important;
    line-height: 35px;
    text-align: center;
    color: darkgray;
    transition: all 0.5s;
    margin: 0px 12.5px 10px 12.5px;
  }
  .friendlyLink {
    float: left;
    width: 290px;
    min-height: 110px;
    padding-left: 65px;
  }
  .friendlyLink li a:hover {
    color: #fff;
    border-radius: 40px 0px;
    background: darkgrey;
    color: #fff !important;
  }
  .toOpcity{
    opacity: 0.85;
  }
  a:link{
    color: #333;
  }
  .aside{
    margin-right: 10rem;
    margin-left: 5rem;
  }
  .innerContainer{
    margin-left: 5rem;
  }
  .innerContainer .el-col{
    margin-top:15px;
  }
  .aside h1{
    color:whitesmoke;
  }
  .aside-title{
    line-height: 20px;
    background-color: #333333;
  }
  .aside-content{
    height: 300px;
    background-color: white;
    line-height: 300px;
  }
  .indexI{
    font-size: 34px;
    padding-left: 10px;
    padding-top: 10px;
    text-align: left;
  }
  .icon-demo:hover{
    color: #f56c6c;
    -webkit-transform: scale(1.2) rotate(30deg);
    transform: scale(1.2) rotate(30deg);
  }
  @media  screen and (max-width: 692px) {
   .aside{
     display: none;
   }
  .innerContainer{
      margin-left: 0px;
  }
  .carousel{

  }

  }
</style>
