<template>
  <highcharts :options="chartData"></highcharts>
</template>

<script>
import { Chart } from 'highcharts-vue'

export default {
  props: ['renderParam', 'chartType'],
  data () {
    return {
      options: {
        chart: {
          type: 'column',
          height: '25%',
          marginTop: 50,
          style: {
            fontFamily: 'Noto Sans'
          }
        },
        credits: {
          enabled: false
        },
        title: false,
        subtitle: {
          text: ''
        },
        xAxis: {
          categories: [],
          crosshair: true
        },
        yAxis: {
          min: 0,
          tickInterval: 1,
          title: {
            text: ''
          },
          labels: {
            format: '{value}건'
          }
        },
        colors: ['#73D8D8', '#7B87FF', '#FF6F8F', '#6A6CC2', '#AA74E9', '#4054FF', '#2FBCBD', '#F9CA0C'],
        legend: {
          align: 'right',
          x: -25,
          verticalAlign: 'top',
          y: 0,
          floating: true,
          backgroundColor: 'white',
          borderColor: '#CCC',
          symbolHeight: 10,
          symbolWidth: 10,
          symbolRadius: 0,
          shadow: false,
          itemStyle: {
            fontFamily: 'Noto Sans'
          }
        },
        tooltip: {
          headerFormat:
            '<span style="font-size:12px"><b>{point.x}</b></span><br><table>',
          pointFormat:
            '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y}건</b></td></tr>',
          footerFormat: '</table>'
        },
        plotOptions: {
          column: {
            dataLabels: {
              enabled: true,
              style: {
                fontSize: '12px',
                fontWeight: 150
              }
            }
          }
        },
        series: []
      }
    }
  },
  computed: {
    chartData () {
      const chart = this.options
      if (this.chartType === 'module') {
        chart.series = this.$store.state[this.renderParam.type].moduleChart
        chart.xAxis.categories = this.$store.state[this.renderParam.type].moduleCategories
      } else if (this.chartType === 'assignee') {
        chart.series = this.$store.state[this.renderParam.type].assigneeChart
        chart.xAxis.categories = this.$store.state[this.renderParam.type].assigneeCategories
      } else if (this.chartType === 'result') {
        chart.series = this.$store.state[this.renderParam.type].resultChart
        chart.xAxis.categories = this.$store.state[this.renderParam.type].resultCategories
      }

      if (chart.series.length === 0) {
        chart.subtitle = {
          text: '기간 내에 조회된 데이터가 없습니다.',
          verticalAlign: 'middle',
          style: {
            fontFamily: 'Noto Sans',
            fontSize: '15px',
            color: '#303030'
          }
        }
      } else {
        chart.subtitle = {
          text: ''
        }
      }
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
