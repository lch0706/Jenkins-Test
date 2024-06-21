<template>
  <div>
    <search-box :render-param="renderParam">
        <button class="btn_B" style="width:90px; height:38px; margin-left: 8px;" @click="excelDownload">내려받기</button>
    </search-box>
    <article class="projectStatus">
      <div class="each">
        <div class="version block">
          <wbs-top-table/>
        </div>
        <!-- <div class="progress block">
          <horizontal-bar-chart/>
        </div> -->
      </div>
    </article>
    <hr>
    <wbs-table :render-param="renderParam"></wbs-table>
  </div>
</template>

<script>
import dayjs from 'dayjs'
import searchBox from '@/components/SearchBox'
import wbsTopTable from '@/components/table/WBSTopTable'
import wbsTable from '@/components/table/WBSTable'
import { mapActions } from 'vuex'
import HorizontalBarChart from '@/components/chart/HorizontalBarChart'

export default {
  data () {
    return {
      renderParam: {
        weekDate: false,
        accLabel: '날짜',
        searchBar: false,
        type: 'wbs',
        accDate: dayjs(new Date()).format('YYYY-MM-DD'),
        startDate: dayjs(new Date(Date.now() - 6 * 24 * 60 * 60 * 1000)).format('YYYY-MM-DD'),
        endDate: dayjs(new Date()).format('YYYY-MM-DD')
      }
    }
  },
  components: {
    searchBox,
    wbsTopTable,
    wbsTable,
    HorizontalBarChart
  },
  methods: {
    ...mapActions('wbs', [
      'excelDownload'
    ])
  }
}
</script>

<style scoped>
.block {
  height: 300px
}
.version.block {
  overflow: auto;
}
</style>
