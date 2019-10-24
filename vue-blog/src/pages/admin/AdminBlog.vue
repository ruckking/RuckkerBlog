<template>
    <d2-container>
        <el-main>
            <el-button @click="AddBlog">新增博文</el-button>
            <el-divider></el-divider>
            <el-table
                    :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                    border
                    style="width: 100%">
                <el-table-column
                        fixed
                        prop="blogdate"
                        label="日期"
                        >
                </el-table-column>
                <el-table-column
                        prop="blogpasser"
                        label="姓名"
                        >
                </el-table-column>
                <el-table-column
                        prop="blogtitle"
                        label="博文标题"
                >
                </el-table-column>

                <el-table-column
                        :show-overflow-tooltip="true"
                        prop="blogcontent"
                        label="博文内容浏览"
                        >
                </el-table-column>
                <el-table-column
                        prop="enabled"
                        label="可用性"
                       >
                </el-table-column>
                <el-table-column
                        prop="type"
                        label="博文类型"
                >
                </el-table-column>
                <el-table-column
                        prop="star"
                        label="点赞数"
                >
                </el-table-column>

                <el-table-column
                        fixed="right"
                        label="操作"
                        width="180">
                    <template slot-scope="scope">
                        <el-button @click="EditBlog(scope.$index, tableData)" size="small"><i class="el-icon-edit"></i>编辑</el-button>
                        <el-button
                                type="danger"
                                @click.native.prevent="deleteRow(scope.$index, tableData)"
                                size="small">
                            <i class="el-icon-delete"></i>移除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="block" style="text-align: center">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[3, 5, 10, 40]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
                </el-pagination>
            </div>
        </el-main>
<!--        <template slot="footer">footer</template>-->
    </d2-container>
</template>

<script>
import httpProxy from '../../plugin/axios'
export default {
  data () {
    return {
      loading: false,
      pagesize: 3,
      currentPage: 1,
      tableData: [
        {
          blogid: '',
          star: 10,
          blogcontent: '博文测试 博文测试',
          blogdate: '2016-05-02',
          blogpasser: '王小虎',
          enabled: '可用',
          blogtitle: 's23',
          showRemoveButton: true,
          type: ''
        }
      ]
    }
  },
  created () {
    httpProxy.get('/getBlogList').then((response) => {
      this.tableData = response.data
      this.tableData.forEach((value, index, array) => {
        if (this.tableData[index].enabled === false) {
          this.tableData[index].enabled = '不可用'
        } else {
          this.tableData[index].enabled = '可用'
        }
      })
    })
  },
  methods: {
    handleSizeChange: function (size) {
      this.pagesize = size
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    EditBlog (index, rows) {
      this.$router.push({
        name: 'EditBlog',
        params: {
          blogid: rows[index].blogid,
          blogcontent: rows[index].blogcontent,
          blogdate: rows[index].blogdate,
          blogpasser: rows[index].blogpasser,
          blogtitle: rows[index].blogtitle,
          enabled: rows[index].enabled,
          star: rows[index].star,
          type: rows[index].type
        }
      })
    },
    AddBlog () {
      this.$router.push({ name: 'EditBlog' })
    },
    deleteRow (index, rows) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var blogid = this.tableData[index].blogid
        httpProxy.delete('deleteBlog?blogid=' + blogid).then(response => {
          this.$message.success('删除成功!')
          this.tableData.splice(index, 1)
        }).catch(() => {
          this.$message.error('出错了')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }

  }
}
</script>

<style scoped>

</style>
