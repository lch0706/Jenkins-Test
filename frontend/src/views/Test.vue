<template>
  <div>
    <search-box :render-param="renderParam"></search-box>
    <article class="projectStatus">
      <div class="total">
        <div class="statusfont">누적 테스트 완료 현황 {{ accLabel }} </div>
        <donut-chart :render-param="renderParam"></donut-chart>
      </div>
      <div class="each">
        <div class="weeklyblock block">
          <div class="modulefont">주간 일자별 현황 {{ weekLabel }} </div>
          <areaspline-chart :render-param="renderParam"></areaspline-chart>
        </div>
      </div>
    </article>
    <span align="left">
      <h5>※ 취소상태, 업무구분명이 없는 경우는 모수에 불포함.</h5>
    </span>
    <help />
    <article class="projectStatus">
      <div class="each">
        <div class="module block">
          <div class="modulefont">주간 업무구분별 테스트 진척 {{ weekLabel }} </div>
          <bar-chart :render-param="renderParam" chart-type="module"></bar-chart>
        </div>
        <div class="developer block">
          <div class="modulefont">주간 업무구분별 테스트 결과 {{ weekLabel }} </div>
          <bar-chart :render-param="renderParam" chart-type="result"></bar-chart>
        </div>
      </div>
    </article>
    <progress-list-table :render-param="renderParam" table-label="테스트 진척 현황"></progress-list-table>
    <result-list-table :render-param="renderParam" table-label="테스트 결과 현황"></result-list-table>
  </div>
</template>

<script>
import SearchBox from '@/components/SearchBox'
import DonutChart from '@/components/chart/DonutChart'
import AreasplineChart from '@/components/chart/AreasplineChart'
import BarChart from '@/components/chart/BarChart'
import ProgressListTable from '@/components/table/ProgressListTable'
import ResultListTable from '@/components/table/ResultListTable'
import Help from '@/components/Help'

export default {
  data () {
    return {
      renderParam: {
        accDate: true,
        weekDate: true,
        searchBar: true,
        type: 'test'
      }
    }
  },
  computed: {
    accLabel () {
      return '( ~ ' + this.$store.state.test.accDate + ' )'
    },
    weekLabel () {
      return '( ' + this.$store.state.test.startDate + ' ~ ' + this.$store.state.test.endDate + ' )'
    }
  },
  components: {
    SearchBox,
    DonutChart,
    AreasplineChart,
    BarChart,
    ProgressListTable,
    ResultListTable,
    Help
  }
}
</script>

<style>

</style>
