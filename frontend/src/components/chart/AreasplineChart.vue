<template>
    <highcharts :options="lineData"></highcharts>
</template>

<script>
import { Chart } from 'highcharts-vue'

export default {
  props: ['renderParam'],
  data () {
    return {
      options: {
        chart: {
          type: 'areaspline',
          height: 200,
          // height: '14%',
          marginTop: 50,
          style: {
            fontFamily: 'Noto Sans'
          }
        },
        credits: {
          enabled: false
        },
        title: false,
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
        // '#73D8D8-to-do,'#FF6F8F-doing','#7B87FF-complete',#6A6CC2-done,'#AA74E9-issue''
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
            // fontWeight: 100,
          }
        },
        tooltip: {
          // headerFormat: "<b>{point.x}</b><br/>",
          // pointFormat: "{series.name}: {point.y}",
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
  components: {
    highcharts: Chart
  },
  computed: {
    lineData () {
      const chart = this.options
      chart.series = this.$store.state[this.renderParam.type].dailyChart
      chart.xAxis.categories = this.$store.state[this.renderParam.type].dailyCategories
      return chart
    }
  }
}
</script>

<style>

</style>
