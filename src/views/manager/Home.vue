<template>
    <div>
        <div style="box-shadow: 0 0 10px rgba(0,0,0,.1); padding: 10px 20px; border-radius: 5px; margin-bottom: 10px;">
              目前已有商机 10 个, 预期成交个数为 5, 成交金额期望值为 20万
        </div>
        <div style="display: flex;">
          <div style="flex: 0.7">
            <el-card style="width: 500px;">
              <div slot="header" class="clearfix">
                <span>目前已有线索13个</span>
              </div>
              <div class="echart" id="chart" style="width:450px; height:457px; margin-left:10px;"></div>
            </el-card>
          </div>
          <div style="flex: 1; box-shadow: 0 0 10px rgba(0,0,0,.1); border-radius: 5px; margin-bottom: 10px;">
            <div style="margin-top: 20px; margin-bottom: 10px;margin-left: 50px;">
              <span style="font-size: large; color: #409EFF;">商机影响因素重要性排序</span>
            </div>
            <div class="echart" id="mychart" :style="myChartStyle"></div>
          </div>
        </div>
        
    </div>
</template>


<script>
import * as echarts from "echarts";

export default {
    name : "home",
    data(){
        return{
          xData: ["商务谈判利润值", "客户预期投入", "过往成交单最高金额", "负责人成交单数", "初步接洽满意度", "客户需求产品符合度", "技术交流","客户等级" , "客户公司盈利状况", "负责人等级"], //横坐标
          yData: [2633, 804, 550, 397, 245, 188, 85, 77, 68, 44], //数据
          myChartStyle: { float: "left", width: "100%", height: "500px" } //图表样式
        }
    },
    mounted() {
      this.initEcharts();
      this.initEcharts2();
    },
    created(){

    },
    methods: {
      initEcharts2(){
        const option1 = {
          legend: {
            orient: 'vertical',
            left: '70%',  //图例距离左的距离
            y: 'center',  //图例上下居中
            data: ['发现商机', '初步接洽', '技术交流', '商务谈判', '赢单']
          },
          title: {
          text: '',
          subtext: '',
          x: 'center'
        },
          tooltip: {
            trigger: 'item',
            formatter: "{b}({d}%)"
          },
          series: [
            {
              type: 'pie',
              radius: '60%',  //图的大小
              center: ['35%', '50%'], //图的位置，距离左跟上的位置
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              labelLine: {
                show: true
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '30',
                  fontWeight: 'bold'
                }
              },
              data: [
                { value: 335, name: '发现商机' },
                { value: 310, name: '初步接洽' },
                { value: 234, name: '技术交流' },
                { value: 135, name: '商务谈判' },
                { value: 1548, name: '赢单' }
              ]
            }
          ]
        };
      const mychart = echarts.init(document.getElementById("chart"));
      mychart.setOption(option1);
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        mychart.resize();
      });
      },
    initEcharts() {
      // 基本柱状图
      const option = {
        grid: {
          top: '2%',
          left: '18%',  // grid布局设置适当调整避免X轴文字只能部分显示
          right: '10%', // grid布局设置适当调整避免X轴文字只能部分显示
          bottom: '10%',
        },

        xAxis: {
          
        },
        yAxis: {
          data: this.xData,
          inverse: true,
          animationDuration: 300,
          animationDurationUpdate: 300
        },
        series: [
          {
            type: "bar", //形状为柱状图
            data: this.yData,
            colorBy: 'data',
            label: {
            show: true,
            position: 'right',
          }
          }
        ]
      };
      const myChart = echarts.init(document.getElementById("mychart"));
      myChart.setOption(option);
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    }
  }
}
</script>

<style scoped>

</style>