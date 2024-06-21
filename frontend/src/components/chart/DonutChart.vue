<template>
  <highcharts :options="donutData"></highcharts>
</template>

<script>
import { Chart } from 'highcharts-vue'

export default {
  props: ['renderParam'],
  data () {
    return {
      options: {
        chart: {
          type: 'pie',
          height: 200,
          width: 390,
          backgroundColor: 'rgba(255, 255, 255, 0)'
        },
        credits: {
          enabled: false
        },
        title: {
          // 완료율
          text: '완료율',
          style: {
            fontFamily: 'Noto Sans',
            color: '#FFFFFF',
            fontSize: '16px'
          },
          verticalAlign: 'middle',
          x: -40,
          y: -5,
          floating: true
        },
        subtitle: {
          text: '',
          style: {
            fontFamily: 'Noto Sans',
            color: '#FFFFFF',
            fontSize: '30px'
          },
          verticalAlign: 'middle',
          x: -40,
          y: 38,
          floating: true
        },
        plotOptions: {
          pie: {
            dataLabels: {
              enabled: false
              // crop: false,
              // overflow: 'none',
            },
            borderColor: null,
            showInLegend: true,
            innerSize: 120,
            size: 170,
            states: {
              hover: {
                halo: {
                  size: 1
                }
              }
            }
          }
        },
        colors: ['#B8BAF1', '#7B87FF', '#393A80', '#D4DAFC', '#94A0FF', '#FFFFFF'],
        legend: {
          align: 'right',
          verticalAlign: 'middle',
          layout: 'vertical',
          itemStyle: {
            // color: '#495057',
            color: '#FFFFFF',
            fontWeight: 100,
            // fontFamily: 'Montserrat',
            fontFamily: 'Noto Sans'
          },
          itemMarginBottom: 5,
          symbolRadius: 0
        },
        exporting: {
          enabled: false
        },
        series: []
      }
    }
  },
  computed: {
    donutData () {
      const chart = this.options
      const data = [{
        name: '',
        data: this.$store.state[this.renderParam.type].completeChart.map((s) => {
          return {
            name: s.label,
            y: s.data,
            idList: s.idList
          }
        })
      }]
      chart.series = data
      chart.subtitle.text = (this.$store.state[this.renderParam.type].completeRatio === undefined ? '0%' : this.$store.state[this.renderParam.type].completeRatio)
      return chart
    }
  },
  components: {
    highcharts: Chart
  }
}
</script>

<style>
.statusfont {
  padding: 10px;
  font-size: 16px;
  text-align: center;
  color: #fff;
}
</style>
