<template>
    <div class="content">
        <el-container>
          <el-main>
            <el-row>
              <el-col>
                <el-card class="box-card grey">
                       <h1 style="text-align: center">
                         {{title}}
                       </h1>
                        <article v-html='blogcontent'></article>
                        <div style="text-align: right">
                          <p>作者：{{writer}}</p>
                          <p>发布日期：{{date}}</p>
                        </div>
                </el-card>
              </el-col>
              <el-col>
                <div class="block">
                  <el-timeline>
                    <el-timeline-item v-for="(item,index) in retellList" :key="index" :timestamp="item.retelldate.split(' ')[0]" placement="top">
                      <el-card>
                        <h1>留言人:{{item.retellpasser}}</h1>
                        <Article v-html="item.retellcontent"></Article>
                      </el-card>
                    </el-timeline-item>
                  </el-timeline>
                </div>
              </el-col>
            </el-row>
            <el-row v-if="username!='无名氏'">
                <h1 style="text-align: center;color:#C0C4CC">留言版</h1>
                <el-col class="quillcol">
                    <quill-editor
                            style="min-height: 300px"
                            v-model="content"
                            ref="myQuillEditor"
                            :options="{imageResize: true,imageDrop: true}"
                           >
                    </quill-editor>
                </el-col>
                <div style="clear: both"></div>
                <el-button style="display:block;margin:0 auto;margin-top: 20px" @click="open">提交留言</el-button>
             </el-row>
              <el-row v-else>
                  <p style="color:whitesmoke;font-size:30px;text-align: center">登陆后即可在此处留言。。。</p>
              </el-row>
          </el-main>
        </el-container>
    </div>
</template>
<script>

import BaseTitle from '../Common/BaseTitle'
import httpProxy from '../../../plugin/axios'
import cookies from '../../../libs/util.cookies'
export default {
  components: {
  // eslint-disable-next-line vue/no-unused-components
    BaseTitle
  },
  data () {
    return {
      content: '',
      blogid: 0,
      blogcontent: '空的',
      writer: '',
      date: '',
      title: '',
      retellList: []
    }
  },
  computed: {
    paramsNumber () {
      return this.$route.params.number
    },
    username: function () {
      if (cookies.get('username')) {
        return cookies.get('username')
      } else {
        return '无名氏'
      }
    }

  },
  methods: {
    getdate () {
      var day2 = new Date()
      day2.setTime(day2.getTime())
      var s2 = day2.getFullYear() + '-' + (day2.getMonth() + 1) + '-' + day2.getDate()
      return s2
    },
    open () {
      this.$confirm('确认要上传留言？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.content.length >= 20) {
          httpProxy.post('/addRetell', {
            retellpasser: this.username,
            retelldate: this.getdate(),
            retellcontent: this.content,
            blogid: this.blogid,
            retellid: 0
          }).then(response => {
            if (response.status !== 'fail') {
              this.$message.success('留言成功!')
              this.httpProxy.post('/addRetellNum')
              this.httpProxy.get('getRetellByBlogId?blogid=' + this.blogid).then(response => {
                this.retellList = response.data
              })
            } else {
              this.$message.danger('好像出了一些问题。。。')
            }
          })
        } else {
          this.$message.info('你的留言太短了！')
        }
      })
    } },
  mounted () {
    this.blogid = this.$route.params.number.slice(1)
    this.httpProxy.get('/getBlogById?blogid=' + this.blogid).then(response => {
      this.blogcontent = response.data.blogcontent
      this.writer = response.data.blogpasser
      this.date = response.data.blogdate
      this.title = response.data.blogtitle
    })
    this.httpProxy.get('getRetellByBlogId?blogid=' + this.blogid).then(response => {
      this.retellList = response.data
    })
  }
}
</script>
<style scoped>
.quillcol{
    background-color: #f5f5f5;
}
.grey{
    background-color: #f5f5f5;
}
.content{
  filter:alpha(Opacity=85);
  -moz-opacity:0.85;
  opacity: 0.85;
}
.article pre{
  display: block;
  padding: 1em;
  border: 2px solid #ddd;
  border-radius: 4px;
  overflow-x: auto;
  line-height: 1.6;
  background: #f8f8f8;
  }
.breadcrumb{
  height: 30px;
}
.el-timeline{
  padding:0;
  margin-top:30px;
}
.el-timeline-item__timestamp{
  font-size: 40px ;
}
</style>
