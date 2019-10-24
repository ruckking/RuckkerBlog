
<template>
  <div>
    <div class="container-water-fall">
      <waterfall :col='5' :width="itemWidth" :gutterWidth="gutterWidth"   :data="Imgdata"    @scroll="scroll"  >
        <template>
          <div class="cell-item" v-for="(item,index) in Imgdata" :key="index">
            <img  preview="0" :src="item.photourl" alt="加载错误"  />
            <el-image style="display: none"
                    :preview-src-list="Imgdata">
            </el-image>
            <div class="name">{{item.photoabout}}</div>
          </div>
        </template>
      </waterfall>
    </div>
  </div>
</template>
<script>
export default {
  components: {
  },
  data () {
    return {
      Imgdata: [{
      }
      ],
      preview: 0
    }
  },
  computed: {
    itemWidth () {
      return (138 * 0.5 * (document.documentElement.clientWidth / 375))
    },
    gutterWidth () {
      return (9 * 0.5 * (document.documentElement.clientWidth / 375))
    }
  },
  async created () {
    await this.httpProxy.get('/getPhotoList').then(res => {
      this.Imgdata = res.data
    })
    var _this = this
    this.Imgdata.forEach((value, index) => {
      // eslint-disable-next-line use-isnan
      _this.Imgdata[index].photourl = _this.getUrl(_this.Imgdata[index].photourl.split('\\')[5])
    })
  },
  methods: {
    scroll () {

    },
    getUrl (src) {
      return require('file-loader!@/assets/img/' + src)
    }
  }
}

</script>
<style lang="less" scoped="scoped">

  * {
    margin: 0;
  }
  .container-water-fall {
    // padding: 0 28px;
    filter:alpha(Opacity=80);
    -moz-opacity:0.8;
    opacity: 0.8;
    box-sizing: border-box;
    .cell-item {
      margin: 10px 10px 10px 10px;
      width: 100%;
      // margin-bottom: 18px;
      background: #ffffff;
      border: 2px solid #F0F0F0;
      border-radius: 12px 12px 12px 12px;
      overflow: hidden;
      box-sizing: border-box;
      img {
        // border-radius: 12px 12px 0 0;
        width: 100%;
        height: auto;
        display: block;
      }
      .item-body {
        // border: 1px solid #F0F0F0;
        padding: 12px;

        .item-desc {
          font-size: 15px;
          color: #333333;
          line-height: 15px;
          font-weight: bold;
        }

        .item-footer {
          margin-top: 22px;
          position: relative;
          display: flex;
          align-items: center;

          .avatar {
            width: 44px;
            height: 44px;
            border-radius: 50%;
            background-repeat: no-repeat;
            background-size: contain;
          }

          .name {
            max-width: 150px;
            margin-left: 10px;
            font-size: 14px;
            color: #999999;
          }

        }
      }
    }
  }
</style>
