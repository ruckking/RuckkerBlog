<template>
    <d2-container>
        <el-main>
            <d2-crud
                    ref="d2Crud"
                    :columns="columns"
                    :data="data.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                    add-title="我的新增"
                    :rowHandle="rowHandle"
                    :add-template="addTemplate"
                    edit-title="我的修改"
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
export default {
  data () {
    return {
      pagesize: 3,
      currentPage: 1,
      loading: false,
      columns: [
        {
          title: '用户名',
          key: 'username'
        },
        {
          title: '用户密码',
          key: 'password'
        },
        {
          title: '用户可用性',
          key: 'enabled'
        },
        {
          title: '用户手机',
          key: 'telephone'
        }
      ],
      data: [
        {
          username: '王小虎',
          password: '123213',
          enabled: false,
          forbidEdit: false,
          showEditButton: true,
          showRemoveButton: true,
          telephone: '',
          tag: '上海市'
        }
      ],
      addTemplate: {
        username: {
          title: '用户名',
          value: ''
        },
        password: {
          title: '密码',
          value: ''
        },
        telephone: {
          title: '手机',
          value: ''
        },
        enabled: {
          title: '可用性',
          value: '',
          component: {
            name: 'el-select',
            options: [
              {
                value: false,
                label: '否'
              }, {
                value: true,
                label: '是'
              }

            ]
          }
        }
      },
      editTemplate: {
        username: {
          title: '用户名',
          value: ''
        },
        password: {
          title: '密码',
          value: ''
        },
        telephone: {
          title: '手机',
          value: ''
        },
        enabled: {
          title: '可用性',
          value: '',
          component: {
            name: 'el-select',
            options: [
              {
                value: false,
                label: '否'
              }, {
                value: true,
                label: '是'
              }

            ]
          }
        },
        forbidEdit: {
          title: '禁用按钮',
          value: true,
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
          text: '点我进行编辑',
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
  created () {
    this.httpProxy.get('/getUserListExpectAdmin').then(response => {
      this.data = response.data
      this.data.forEach((value, index, array) => {
        this.data[index].showEditButton = true
        this.data[index].showRemoveButton = true
      })
    })
  },
  methods: {
    // 普通的新增
    addRow () {
      this.$refs.d2Crud.showDialog({
        mode: 'add'
      })
    },
    handleRowAdd (row, done) {
      this.formOptions.saveLoading = true
      setTimeout(() => {
        this.httpProxy.post('/saveOrUpdateUser', {
          username: row.username,
          password: row.password,
          telephone: row.telephone,
          role: 'user',
          enabled: row.enabled,
          userid: 0
        }).then(res => {
          this.$message.success('新增成功!')
          // eslint-disable-next-line no-unused-expressions
          this.$refs.d2Crud.closeDialog()
        }).catch(e => {
        })
        this.formOptions.saveLoading = false
      }, 300)
    },
    handleRowEdit ({ index, row }, done) {
      this.formOptions.saveLoading = true
      setTimeout(() => {
        this.$message({
          message: '编辑成功',
          type: 'success'
        })

        // done可以传入一个对象来修改提交的某个字段
        done({
          address: '我是通过done事件传入的数据！'
        })
        this.formOptions.saveLoading = false
      }, 300)
    },
    handleSizeChange: function (size) {
      this.pagesize = size
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    handleRowRemove ({ index, row }, done) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.httpProxy.delete('/deleteUserById?userid=' + row.userid).then(res => {
          if (res.stauts !== '102') {
            this.$message.success('删除成功!')
            this.data.splice(index, 1)
          } else {
            this.$message.error('出错了...')
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
