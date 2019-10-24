<template>
    <d2-container>
        <el-main>
            <d2-crud
                    ref="d2Crud"
                    :columns="columns"
                    :data="data.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                    add-title="新增留言"
                    :rowHandle="rowHandle"
                    :add-template="addTemplate"
                    edit-title="修改留言"
                    :edit-template="editTemplate"
                    :form-options="formOptions"
                    :loading="loading"
                    @row-add="handleRowAdd"
                    @row-edit="handleRowEdit"
                    @row-remove="handleRowRemove"
                    @dialog-cancel="handleDialogCancel">
                <el-button slot="header" style="margin-bottom: 5px" @click="addRow">新增</el-button>
            </d2-crud>
            <div class="block" style="text-align: center">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[3, 5, 10, 40]"
                        :page-size="pagesize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="data.length">
                </el-pagination>
            </div>
        </el-main>
    </d2-container>
</template>
<script>
import httpProxy from '../../plugin/axios'

export default {
  data () {
    return {
      pagesize: 3,
      currentPage: 1,
      loading: false,
      columns: [
        {
          title: '留言日期',
          key: 'retelldate'
        },
        {
          title: '留言姓名',
          key: 'retellpasser'
        },
        {
          title: '留言内容',
          key: 'retellcontent'
        },
        {
          title: '所属文章',
          key: 'blogid'
        }
      ],
      data: [
        {
          date: '2016-05-02',
          name: '王小虎',
          content: '上海市普陀区金沙江路 1518 弄',
          forbidEdit: false,
          showEditButton: true,
          showRemoveButton: true,
          tag: '上海市'
        }
      ],
      addTemplate: {
        retelldate: {
          title: '留言日期',
          value: '2016-05-05',
          component: {
            name: 'el-date-picker'
          }
        },
        retellpasser: {
          title: '留言姓名',
          value: '王小虎'
        },
        retellcontent: {
          title: '留言内容',
          value: '上海市普陀区金沙江路 1520 弄'
        },
        blogid: {
          title: '所属文章',
          value: '',
          component: {
            name: 'el-select',
            options: [{
              value: '',
              label: ''
            }]
          }
        }

      },
      editTemplate: {
        retelldate: {
          title: '留言日期',
          value: '2016-05-05',
          component: {
            name: 'el-date-picker'
          }
        },
        retellpasser: {
          title: '留言姓名',
          value: '王小虎'
        },
        retellcontent: {
          title: '留言内容',
          value: '上海市普陀区金沙江路 1520 弄'
        },
        blogid: {
          title: '所属文章',
          value: '',
          component: {
            name: 'el-select',
            options: [{
              value: '',
              label: ''
            }]
          }
        },
        forbidEdit: {
          title: '禁用按钮',
          value: false,
          component: {
            show: false
          }
        },
        showEditButton: {
          title: '显示按钮',
          value: true,
          component: {
            show: false
          }
        }
      },
      rowHandle: {
        columnHeader: '编辑表格',
        edit: {
          icon: 'el-icon-edit',
          text: '编辑',
          size: 'small'
        },
        remove: {
          icon: 'el-icon-delete',
          size: 'small',
          fixed: 'right',
          confirm: false
        }
      },
      formOptions: {
        labelWidth: '80px',
        labelPosition: 'left',
        saveLoading: false
      }
    }
  },
  async mounted () {
    let blogidList = []
    // eslint-disable-next-line no-unused-vars
    let blogList = []
    await httpProxy.get('/getRetellList').then(response => {
      this.data = response.data
    })
    await httpProxy.get('/getBlogList').then(response => {
      blogList = response.data
    })
    this.data.forEach((value, index) => {
      blogidList.push(this.data[index].blogid)
    })
    blogidList = this.unique(blogidList)
    blogidList.forEach((value, index) => {
      blogidList.forEach((value1) => {
        if (index !== 0 && value1 !== value) {
          this.$refs.d2Crud.addTemplate.blogid.component.options.push({ value: '', label: '' })
          this.$refs.d2Crud.editTemplate.blogid.component.options.push({ value: '', label: '' })
        }
      })
    })
    this.$refs.d2Crud.addTemplate.blogid.component.options.forEach((value, index) => {
      this.$refs.d2Crud.addTemplate.blogid.component.options[index].value = blogidList[index]
      this.$refs.d2Crud.editTemplate.blogid.component.options[index].value = blogidList[index]
      blogList.forEach((value1, index1) => {
        // eslint-disable-next-line no-cond-assign
        if (blogList[index1].blogid === blogidList[index]) {
          this.$refs.d2Crud.addTemplate.blogid.component.options[index].label = blogList[index1].blogtitle
          this.$refs.d2Crud.editTemplate.blogid.component.options[index].label = blogList[index1].blogtitle
        }
      })
    })
  },
  methods: {
    unique (array) {
      var r = []
      for (var i = 0, l = array.length; i < l; i++) {
        // eslint-disable-next-line no-unused-expressions
        for (var j = i + 1; j < l; j++) { if (array[i] === array[j]) j === ++i }
        r.push(array[i])
      }
      return r
    },
    handleSizeChange: function (size) {
      this.pagesize = size
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    // 普通的新增
    addRow () {
      this.$refs.d2Crud.showDialog({
        mode: 'add'
      })
    },
    handleRowAdd (row, done) {
      this.formOptions.saveLoading = true
      setTimeout(() => {
        httpProxy.post('/addRetell', {
          retellid: 0,
          retellpasser: row.retellpasser,
          retelldate: this.formatDateTime(row.retelldate),
          retellcontent: row.retellcontent,
          blogid: row.blogid
        }).then(response => {
          if (response.status !== 'fail') {
            this.$message({
              message: '保存成功',
              type: 'success'
            })
          }
        })
        // done可以传入一个对象来修改提交的某个字段
        done({
          date: this.formatDateTime(row.retelldate)
        })
        this.formOptions.saveLoading = false
      }, 300)
    },
    formatDateTime: function (date) {
      if (typeof (date) !== 'string') {
        var y = date.getFullYear()
        var m = date.getMonth() + 1
        m = m < 10 ? ('0' + m) : m
        var d = date.getDate()
        d = d < 10 ? ('0' + d) : d
        var h = date.getHours()
        var minute = date.getMinutes()
        minute = minute < 10 ? ('0' + minute) : minute
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute
      } else {
        return date
      }
    },
    handleRowEdit ({ index, row }, done) {
      this.formOptions.saveLoading = true
      setTimeout(() => {
        httpProxy.post('/addRetell', {
          retellid: row.retellid,
          retellpasser: row.retellpasser,
          retelldate: this.formatDateTime(row.retelldate),
          retellcontent: row.retellcontent,
          blogid: row.blogid
        }).then(response => {
          if (response.status !== 'fail') {
            this.$message({
              message: '保存成功',
              type: 'success'
            })
          }
        })
        // done可以传入一个对象来修改提交的某个字段
        done({
          date: this.formatDateTime(row.retelldate)
        })
        this.formOptions.saveLoading = false
      }, 300)
    },
    handleRowRemove ({ index, row }, done) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.httpProxy.delete('/deleteRetell?retellid=' + row.retellid).then(response => {
          if (response.status !== 'fail') {
            this.$message.success('删除成功!')
            this.data.splice(index, 1)
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleDialogCancel (done) {
      this.$message({
        message: '取消保存',
        type: 'warning'
      })
      done()
    }
  }
}
</script>

<style scoped>

</style>
