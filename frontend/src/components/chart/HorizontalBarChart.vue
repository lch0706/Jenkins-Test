<template>
<highcharts :options="chartData"></highcharts>
</template>

<script>
import { Chart } from 'highcharts-vue'
import _ from 'lodash'

export default {
  data () {
    return {
      options: {
        series: [],
        chart: {
          type: 'bar',
          style: {
            fontFamily: 'Noto Sans'
          },
          height: 300
        },
        title: {
          text: '프로젝트 계획/실적',
          style: {
            fontFamily: 'Noto Sans',
            fontSize: '20px'
          }
        },
        xAxis: {
          visible: false,
          categories: [],
          labels: {
            style: {
              fontFamily: 'Noto Sans',
              fontSize: '16px'
            }
          }
        },
        yAxis: {
          labels: {
            enabled: false
          },
          title: {
            text: undefined
          }
        },
        credits: {
          enabled: false
        },
        colors: ['#73D8D8', '#7B87FF'],
        legend: {
          align: 'left',
          verticalAlign: 'middle',
          layout: 'vertical',
          itemStyle: {
            // color: '#495057',
            fontWeight: 100,
            // fontFamily: 'Montserrat',
            fontFamily: 'Noto Sans'
          }
        },
        tooltip: {
        }
      }
    }
  },
  computed: {
    chartData () {
      const chart = this.options
      const list = _.filter(this.$store.state.wbs.WBSList, item => item.lv === 0)
      const data = []

      if (list.length > 0) {
        data.push({
          name: '계획',
          data: [Number(list[0].plan_weight_progress.toFixed(2))]
        })
        data.push({
          name: '실적',
          data: [Number(list[0].result_weight_progress.toFixed(2))]
        })
      }
      chart.series = data
      chart.tooltip.headerFormat = '<span>{list[0].subjectlist}</span>'
      return chart
    }
  },
  components: {
    highcharts: Chart
  }
}
</script>

<style>

</style>
