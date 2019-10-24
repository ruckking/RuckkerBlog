<template>
    <d2-container>
        <el-main>
            <d2-crud
                    ref="d2Crud"
                    :columns="columns"
                    :data="data.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                    :rowHandle="rowHandle"
                    :add-template="addTemplate"
                    :form-options="formOptions"
                    :loading="loading"
                    @dialog-open="handleDialogOpen"
                    @row-add="handleRowAdd"
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
import ImgUpload from './Components/ImgUpload'
import httpProxy from '../../plugin/axios'
export default {
  components: {
    // eslint-disable-next-line vue/no-unused-components
    ImgUpload
  },
  data () {
    return {
      pagesize: 3,
      currentPage: 1,
      loading: false,
      columns: [
        {
          title: '照片日期',
          key: 'phototime'
        },
        {
          title: '照片说明',
          key: 'photoabout'
        },
        {
          title: '照片地址',
          key: 'photourl'
        }
      ],
      data: [
        {
          date: '2016-05-02',
          content: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          forbidEdit: false,
          tag: '上海市'
        }
      ],
      addTemplate: {
        phototime: {
          title: '日期',
          value: '2016-05-05',
          component: {
            name: 'el-date-picker'
          }
        },
        photoabout: {
          title: '照片说明',
          value: '王小虎'
        },
        photourl: {
          title: '上传照片',
          value: '',
          component: {
            name: ImgUpload
          }
        }
      },
      rowHandle: {
        columnHeader: '编辑表格',
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
  mounted () {
    this.httpProxy.get('/getAllPohtoList').then(res => {
      this.data = res.data
      this.data.forEach((value, index, array) => {
        this.data[index].showEditButton = true
        this.data[index].showRemoveButton = true
      })
    }).catch(err => {
      this.$message.error('获取列表出错!' + err.data)
    })
  },
  methods: {
    handleSizeChange: function (size) {
      this.pagesize = size
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    handleDialogOpen ({ mode }) {
      this.$message({
        message: '打开模态框，模式为：' + mode,
        type: 'success'
      })
    },
    // 普通的新增
    addRow () {
      this.$refs.d2Crud.showDialog({
        mode: 'add'
      })
    },
    formatDateTime: function (date) {
      var y = date.getFullYear()
      var m = date.getMonth() + 1
      m = m < 10 ? ('0' + m) : m
      var d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      var h = date.getHours()
      var minute = date.getMinutes()
      minute = minute < 10 ? ('0' + minute) : minute
      return y + '-' + m + '-' + d + ' ' + h + ':' + minute
    },
    handleRowAdd (row, done) {
      this.formOptions.saveLoading = true
      setTimeout(() => {
        this.httpProxy.post('/addPhotoInfo', {
          photoid: this.$store.state.PhotoId,
          phototime: this.formatDateTime(row.phototime),
          photoabout: row.photoabout
        }).then(res => {
        })
        this.$message({
          message: '上传成功',
          type: 'success'
        })
        // done可以传入一个对象来修改提交的某个字段
        done()
        this.formOptions.saveLoading = false
      }, 300)
    },
    handleRowRemove ({ index, row }) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.httpProxy.delete('deletePhotoById?id=' + row.photoid).then(response => {
          this.$message.success('删除成功!')
          this.data.splice(index, 1)
        }).catch((e) => {
          console.log(e)
          this.$message.error('出错了')
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
