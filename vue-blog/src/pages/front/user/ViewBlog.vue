
<template>
<div class="content">
  <el-container>
    <el-main>
      <el-row>
        <el-col :span="16" :offset="4">
          <el-card class="box-card">

            <div v-bind:name="item.id" v-for="(item,index) in searchData" :key="index"  class="text item">
              <h2 v-rainbow> {{item.blogtitle}}</h2>

              <div class="content">
                <router-link  v-if="item.blogcontent.length<=20" :to="'/BlogContent:'+item.blogid" v-html="item.blogcontent"></router-link>
                <router-link  v-else :to="'/BlogContent:'+item.blogid" >{{item.blogcontent.substring(0,20)}}...</router-link>
              </div>
              <div class="content-title"><i class="el-icon-user-solid"></i>作者:{{item.blogpasser}}&nbsp;&nbsp;&nbsp;<i class="el-icon-star-on"></i>
                <a style="cursor: pointer" class="starbutton" v-on:click.once="addStar(item.blogid,index)">star数：{{item.star}}</a>&nbsp;</div>
              <el-divider ><i v-rainbow class="fa fa-bug fa-2x"></i></el-divider>
            </div>
          </el-card>
        </el-col>
        <el-col>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pagesize"
            layout="prev, pager, next"
            :total="data.length">
          </el-pagination>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</div>

</template>
<script>
import httpProxy from '../../../plugin/axios'
export default {
  data () {
    return {
      data: [],
      activeNames: ['1'],
      currentPage: 1,
      pagesize: 5,
      search: '',
      olddata: [],
      retellNum: 0
    }
  },
  computed: {
    listdata: function () {
      return this.data.slice((this.currentPage - 1) * this.pagesize, this.currentPage * this.pagesize)
    },
    searchData: function () {
      var search = this.$store.state.search
      if (search) {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.data = this.olddata.filter(function (olddata) {
          return Object.keys(olddata).some(function (key) {
            return String(olddata[key]).toLowerCase().indexOf(search) > -1
          })
        })
        return this.listdata
      }
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      this.data = this.olddata
      return this.listdata
    },
    getSearch () {
      return this.$store.state.search
    }
  },
  created () {
    if (!this.$route.query.type) {
      httpProxy.get('/getBlogListEnabled').then((response) => {
        // console.log(response.data[0]);
        this.olddata = response.data
        this.data = response.data
      })
    } else {
      httpProxy.get('/getBlogListByType?type=' + this.$route.query.type).then(response => {
        this.olddata = response.data
        this.data = response.data
      })
    }
  },
  name: 'AddBlog',
  methods: {
    addStar (blogid, index) {
      httpProxy.post('/addStar', {
        blogid: blogid
      }).then(response => {
        if (response.status !== 'fail') {
          this.$message.success('为' + blogid + '号博文点赞成功!')
          this.searchData[index].star += 1
        } else {
          this.$message.error('服务器异常')
        }
      })
    },
    onSubmit () {
      this.submitted = !this.submitted
    },
    handleChange (val) {
    },
    handleSizeChange: function (size) {
      this.pagesize = size
      console.log(this.pagesize) // 每页下拉显示数据
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
      console.log(this.currentPage) // 点击第几页
    }
  },
  filters: {

  },
  directives: {
    'rainbow': {
      bind (el, binding, vnode) {
        el.style.color = '#' + Math.random().toString(16).slice(2, 8)
      }
    }
  },
  watch: {
    getSearch: {
      handler (newValue, oldValue) {
        this.search = newValue
      }
    }
  }
}
</script>
<style scoped>
.title{
  font-size: 25px;
}
.content-title{
  font-size: 15px;
  color:dimgray;
  text-align: right;
}
.content{
  font-size:18px;
  filter:alpha(Opacity=85);
  -moz-opacity:0.85;
  opacity: 0.85;
  font-style: italic;
  color: #a3a3a3;
}
.content a{
  text-decoration: none;

}
.content a:hover{
  color:deeppink;
}
.content a:visited {
  color:#a3a3a3;
}
.el-pagination{
  text-align: center;
}
</style>
