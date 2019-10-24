<template>
  <d2-container>

    <el-main>
      <el-row>
        <el-col :span="6">
          <!--          <v-chart :options="polar"/>-->
          <el-card class="box-card" style="background-color:#5ecd8a">
            <h2 style="display: inline">{{DateData.RecentlyBlog}}</h2>
            <i style="float: right" class="fa fa-commenting-o fa-3x"></i>
            <div class="card-content">
              新文章
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <!--          <v-chart :options="polar"/>-->
          <el-card class="box-card" style="background-color: #f68584">
            <h2 style="display: inline">{{DateData.RecentlyRetell}}</h2>
            <i style="float: right" class="fa fa-comments-o fa-3x"></i>
            <div class="card-content">
              新留言
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <!--          <v-chart :options="polar"/>-->
          <el-card class="box-card" style="background-color: #8574a5">
            <h2 style="display: inline">{{DateData.BlogStar}}</h2>
            <i style="float: right" class="fa fa-star-o fa-3x"></i>
            <div class="card-content">
              文章总赞数
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <!--          <v-chart :options="polar"/>-->
          <el-card class="box-card" style="background-color: #92cfc2">
            <h2 style="display: inline">{{HoverData.BlogHover}}</h2>
            <i style="float: right" class="fa fa-eye fa-3x"></i>
            <div class="card-content">
              文章浏览数
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12 " style="margin-top:20px;">
          <el-card style="margin-top:20px;padding-left:20px;margin-right:40px;" shadow="always">
                <h3 style="text-align: center;color:cadetblue;font-size: 30px;margin-bottom:0">网站板块访问占比</h3>
                <el-divider></el-divider>
                <ve-pie :data="chartData2" :settings="chartSettings" :legend-position="'bottom'"></ve-pie>

          </el-card>

        </el-col>
        <el-col :span="12 " style="margin-top:20px;">
          <el-card style="margin-top:20px;padding-left:20px;margin-right:40px;" shadow="always">
            <h3 style="text-align: center;color:cadetblue;font-size: 30px;margin-bottom:0">管理员照片</h3>
            <el-divider></el-divider>
            <el-carousel height="400px" direction="vertical" :autoplay="false">
              <el-carousel-item v-for="item in imgList" :key="item.id">
                <el-image style="height: 100%;width: 100%"
                        :src="item.idView"
                        fit="cover"></el-image>
              </el-carousel-item>
            </el-carousel>
          </el-card>

        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <h2 style="text-align: center;color:cadetblue">网站访问数据量实时折线图</h2>
          <ve-line  :legend-position="'bottom'" :data="chartData"></ve-line>
        </el-col>
      </el-row>
    </el-main>
    <template slot="footer">
      <div  style="text-align: center;font-family: Hiragino Sans GB;color:#909399">
        当前时间是:{{date}}
      </div>
    </template>
  </d2-container>
</template>

<script>
import httpProxy from '../../../plugin/axios'
export default {
  name: 'AdminUser',
  data () {
    return {
      imgList: [],
      totalHover: 0,
      HoverData: {
        BlogHover: 2,
        IndexHover: 2,
        PhotoHover: 2,
        AboutHover: 2,
        RetellNum: 0
      },
      DateData: {
        RecentlyRetell: 0,
        RecentlyBlog: 0,
        BlogStar: 0
      },
      chartSettings: {
        dimension: '访问模块',
        metrics: '访问用户'
      },
      date: new Date(),
      chartData2: {
        columns: ['访问模块', '访问用户'],
        rows: [
        ]
      },
      chartData: {
        columns: ['日期', '访问用户', '评论用户'],
        rows: [
        ]
      }
    }
  },
  async created () {
    await this.httpProxy.get('/getCarouselPic').then(res => {
      this.imgList = res.data
    })
    this.imgList = JSON.parse(JSON.stringify(this.imgList).replace(/photourl/g, 'idView'))
    this.imgList = JSON.parse(JSON.stringify(this.imgList).replace(/photoid/g, 'id'))
    this.imgList.forEach((value, index) => {
      this.imgList[index].idView = this.getUrl2(this.imgList[index].idView.split('\\')[5])
    })
    httpProxy.get('/getRecentlyRetell').then(response => {
      this.DateData.RecentlyRetell = response.data
    }).catch(err => {
      console.log(err)
    })

    httpProxy.get('/getRecentlyBlog').then(response => {
      this.DateData.RecentlyBlog = response.data
    }).catch(err => {
      console.log(err)
    })
    httpProxy.get('/getBlogStar').then(response => {
      this.DateData.BlogStar = response.data
    }).catch(err => {
      console.log(err)
    })
    // eslint-disable-next-line no-unused-expressions
    await this.getData()
    window.setInterval(this.getData, 1000 * 60 * 0.1)
    this.setrows()
    this.setrows2()
    window.setInterval(this.setrows2, 1000 * 60 * 0.1)
  },
  methods: {
    async getData () {
      await httpProxy.get('/getServerInfo').then(response => {
        this.HoverData.BlogHover = response.data.blogHoverNumber
        this.HoverData.AboutHover = response.data.aboutHoverNumber
        this.HoverData.IndexHover = response.data.indexHoverNumber
        this.HoverData.PhotoHover = response.data.photoHoverNumber
        this.HoverData.RetellNum = response.data.retellNumber
      })
      this.totalHover = this.HoverData.BlogHover + this.HoverData.AboutHover + this.HoverData.IndexHover + this.HoverData.PhotoHover
    },
    getUrl2 (src) {
      return require('@/assets/img/' + src)
    },
    getDate () {
      var myDate = new Date()
      // 获取当前年
      var year = myDate.getFullYear()
      // 获取当前月
      var month = myDate.getMonth() + 1
      // 获取当前日
      var date = myDate.getDate()
      var h = myDate.getHours() // 获取当前小时数(0-23)
      var m = myDate.getMinutes() // 获取当前分钟数(0-59)
      var s = myDate.getSeconds()
      // 获取当前时间
      var now = year + '-' + this.conver(month) + '-' + this.conver(date) + ' ' + this.conver(h) + ':' + this.conver(m) + ':' + this.conver(s)
      return now
    },
    // 日期时间处理
    conver (s) {
      return s < 10 ? '0' + s : s
    },
    setrows () {
      // eslint-disable-next-line standard/object-curly-even-spacing
      let a = { '访问模块': '首页', '访问用户': this.HoverData.IndexHover }
      this.chartData2.rows.push(a)
      // eslint-disable-next-line standard/object-curly-even-spacing
      a = { '访问模块': '博文', '访问用户': this.HoverData.BlogHover }
      this.chartData2.rows.push(a)
      // eslint-disable-next-line standard/object-curly-even-spacing
      a = { '访问模块': '关于我们', '访问用户': this.HoverData.AboutHover }
      this.chartData2.rows.push(a)
      // eslint-disable-next-line standard/object-curly-even-spacing
      a = { '访问模块': '相册', '访问用户': this.HoverData.PhotoHover }
      this.chartData2.rows.push(a)
      // eslint-disable-next-line no-unused-vars
    },
    setrows2 () {
      let b = { '日期': this.getDate(), '访问用户': this.totalHover, '评论用户': this.HoverData.RetellNum }
      this.chartData.rows.push(b)
    }
  },
  mounted () {
    let _this = this // 声明一个变量指向Vue实例this，保证作用域一致
    this.timer = setInterval(() => {
      _this.date = new Date() // 修改数据date
    }, 1000)
  },
  beforeDestroy () {
    if (this.timer) {
      clearInterval(this.timer) // 在Vue实例销毁前，清除我们的定时器
    }
  }
}
</script>

<style scoped>
  .box-card {
    width: 90%;

  }
  h2{
    font-size: 40px;
  }
  i,.card-content,h2{
    color:whitesmoke;
  }
  .card-content{
    margin-top:15px;
    font-size: 20px;

  }
   .el-carousel__item h3 {
     color: #475669;
     font-size: 14px;
     opacity: 0.75;
     line-height: 200px;
     margin: 0;
   }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

</style>
