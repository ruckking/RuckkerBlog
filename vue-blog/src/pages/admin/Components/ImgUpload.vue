<template>
    <div style="cursor: pointer">
        <el-upload
                ref="upload"
                class="upload-demo"
                action=""
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="fileList"
                :on-success="hadnleOnsuccess"
                :http-request="uploadFile"
                list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
    </div>
</template>
<script>
import axios from 'axios'
export default {
  data () {
    return {
      fileList: [],
      filename: this.value
    }
  },
  props: {
    value: {
      type: String,
      require: true
    }
  },
  methods: {
    handleRemove (file, fileList) {
      // console.log(file, fileList)
    },
    handlePreview (file) {
      // console.log(file)
    },
    handleExceed (files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    hadnleOnsuccess (response, files, fileList) {
      this.filename = files.name
      this.$emit('input', this.filename)
    },
    uploadFile (param) {
      let uploadData = new FormData()
      uploadData.append('file', param.file)
      let config = {
        header: {
          'Content-Type': 'multipart/form-data'
        }
      }
      axios.post('/api/uploadImg', uploadData, config).then(res => {
        if (res.data.data === '102') {
          this.$message.error('图片已存在！')
          // eslint-disable-next-line no-unused-expressions
          this.$refs['upload'].clearFiles()
        } else if (res.data.data === '103') {
          this.$message.error('请选择图片！')
        } else {
          this.$store.commit('setPhotoId', res.data.data)
        }
      })
    }
  }
}
</script>
<style scoped>

</style>
