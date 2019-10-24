<template>
    <d2-container>
        <el-main>
            <el-row>
                <h1 style="text-align: center" v-if="haveId">修改博客</h1>
                <h1 style="text-align: center" v-else>新增博客</h1>
                <el-col>
                    <el-input class="editInput" placeholder="请输入内容" v-model="blogtitle">
                        <template slot="prepend">博文标题</template>
                    </el-input>
                </el-col>
                <el-col>
                    <el-input class="editInput" placeholder="请输入内容" v-model="type">
                        <template slot="prepend">博文类型</template>
                    </el-input>
                </el-col>
                <el-col class="quillcol">
                    <quill-editor
                            v-model="content"
                            ref="myQuillEditor"
                            :options="{imageResize: true,imageDrop: true}"
                            @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
                            @change="onEditorChange($event)">
                    </quill-editor>
                </el-col>
            </el-row>
            <el-row>
                <el-col>
                    <label class="editlabel" style="padding-right: 10px">可用性:</label>
                    <el-select class="editselect" v-model="value" placeholder="请选择">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <el-row>
                <el-col>
                    <el-button  style="display:block;margin:15px auto" @click="InsertOrUpdate">
                        <b v-if="haveId">
                            修改
                        </b>
                        <b v-else>
                            新增
                        </b>
                    </el-button>
                </el-col>
            </el-row>
        </el-main>
    </d2-container>
</template>
<script>
import httpProxy from '../../plugin/axios'
import cookies from '../../libs/util.cookies'
export default {
  name: 'EditBlog',
  data () {
    return {
      content: `<p>hello world</p>`,
      haveId: false,
      blogId: 0,
      blogtitle: '',
      type: '',
      options: [
        {
          value: '1',
          label: '可用'
        },
        {
          value: '0',
          label: '不可用'
        }
      ],
      value: 0
    }
  },
  mounted () {
    this.content = this.$route.params.blogcontent
    this.blogtitle = this.$route.params.blogtitle
    this.type = this.$route.params.type
    if (this.$route.params.blogid) {
      this.haveId = true
      this.blogId = this.$route.params.blogid
    } else {
      this.haveId = false
    }
  },
  methods: {
    onEditorBlur () { // 失去焦点事件
    },
    onEditorFocus () { // 获得焦点事件
    },
    onEditorChange () { // 内容改变事件
    },
    InsertOrUpdate () {
      if (this.haveId) {
        httpProxy.post('/updateBlog', {
          blogcontent: this.content,
          blogid: this.blogId,
          blogdate: this.getDate(),
          blogpasser: cookies.get('username'),
          blogtitle: this.blogtitle,
          enabled: this.value,
          star: this.$route.params.star,
          type: this.type
        }).then((response) => {
          this.$message.success('修改成功!')
          this.$router.push('/home/AdminBlog')
        }).catch(err => {
          console.log(err)
        })
      } else {
        httpProxy.post('/updateBlog', {
          blogcontent: this.content,
          blogid: this.blogId,
          blogdate: this.getDate(),
          blogpasser: cookies.get('username'),
          blogtitle: this.blogtitle,
          enabled: this.value,
          star: 0,
          type: this.type
        }).then((response) => {
          this.$message.success('新增成功!')
          this.$router.push('/home/AdminBlog')
        }).catch(err => {
          console.log(err)
        })
      }
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
    }
  }
}
</script>

<style scoped>
.quill-editor{

}
.editInput{
    margin-bottom: 20px;
}
.editselect{
    margin-top: 20px;
}
.editlabel{
    color: #909399;

    padding: 0 20px;
    white-space: nowrap;
    height: 40px;
}
</style>
