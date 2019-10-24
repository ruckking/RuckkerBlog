<template>
  <div>
    <el-container class="aboutcontainer">
      <el-card class="box-card" :style="{'margin-left':''+mymargin+'px'}" shadow="always">
        <el-main>

          <el-row>
            <el-col>
              <h3>关于我们</h3>
            </el-col>
            <el-col>
              <el-image
                style="width: 100px; height: 123px;border-radius: 50%"
                :src="url"
              ></el-image>
            </el-col>
            <el-col>
              <h3>朱珩</h3>
              <p>
                来自绍兴，现居温州，就职温商。<br>

                涉及方向有：网络爬虫、机器学习、Web 开发等。<br>

                爱生活、爱艺术、爱书画、爱音乐、爱设计、爱编程。<br>

                人生百态，笑口常开，秉承自我，谨慎独行。<br>

                静觅，静静寻觅生活的美好。
              </p>
            </el-col>
            <h3>我们的技能树</h3>
            <el-col :span="16" :offset="6">
              <el-row>
                <el-col :span="6">
                  <div class="describe">
                    前端
                  </div>
                  <div class="describe">
                    PYTHON
                  </div>
                  <div class="describe">
                    PHP
                  </div>
                  <div class="describe">
                    JAVA
                  </div>
                </el-col>
                <el-col :span="12" class="progress">
                  <el-progress :text-inside="true" :stroke-width="26" :percentage="70"></el-progress>
                  <el-progress :text-inside="true" :stroke-width="24" :percentage="100" status="success"></el-progress>
                  <el-progress :text-inside="true" :stroke-width="22" :percentage="80" status="warning"></el-progress>
                  <el-progress :text-inside="true" :stroke-width="20" :percentage="50" status="exception"></el-progress>
                </el-col>
              </el-row>
            </el-col>
            <el-col>
              <h3>联系我们</h3>
              <p>
                个人微信号<br>
                扫码或搜索： <a style="color:mediumvioletred">Mark</a><br>
                联系博主、技术交流、商务合作，可在微信聊天获取
              </p>
              <el-image
                style="width: 300px; height: 300px;"
                :src="url2"
              ></el-image>
            </el-col>
          </el-row>

        </el-main>
      </el-card>

    </el-container>
  </div>
</template>
<script>
export default {
  data () {
    return {
      url: require('@/assets/05.jpg'),
      url2: require('@/assets/06.jpg'),
      mymargin: '200'
    }
  },
  methods: {
    setBackgroundMargin () {
      if (document.documentElement.clientWidth > 800) {
        this.mymargin = (document.documentElement.clientWidth - 800) / 2
      }
    }
  },
  mounted () {
    this.setBackgroundMargin()
    window.addEventListener('resize', () => {
      this.setBackgroundMargin()
    }, false)
  }
}
</script>
<style scoped>
  .aboutcontainer{
    text-align: center;
    color:lightslategrey;
  }
  .describe{
    padding-top:40px;
  }
  .el-progress{
    padding-top:45px;
  }
  .el-main{
    line-height: 30px;
  }
  .box-card{
    width:800px;
    margin:20px 0 20px 0;
    filter:alpha(Opacity=85);
    -moz-opacity:0.85;
    opacity: 0.85;
  }
</style>
