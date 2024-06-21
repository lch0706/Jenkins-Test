<template>
  <div>
    <table id="table__wbs1">
      <thead>
        <tr>
          <!-- <th rowspan="2">업무구분</th> -->
          <th rowspan="2">단계</th>
          <th rowspan="2">계획</th>
          <th rowspan="2">실적</th>
          <th rowspan="2">가중치</th>
          <th colspan="2">진척율(가중치)</th>
          <th rowspan="2">달성율</th>
        </tr>
        <tr>
          <th class="sticky-top">계획</th>
          <th class="sticky-top">실적</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="wbs in sortedWbsList" :key="wbs.id">
          <td style="text-align: left; white-space: pre-wrap;">{{wbs.subject}}</td> <!-- Task -->
          <td>{{fixed(wbs.plan_progress)}}%</td>  <!-- 계획진척율(100%) -->
          <td>{{fixed(wbs.result_progress)}}%</td>  <!-- 실적진척율(100%) -->
          <td>{{wbs.weight}}</td>   <!-- 가중치 -->
          <td>{{fixed(wbs.plan_weight_progress)}}%</td> <!-- 계획진척율(가중치) -->
          <td>{{fixed(wbs.result_weight_progress)}}%</td> <!-- 실적진척율(가중치) -->
          <td>{{fixed(wbs.achievement_rate)}}%</td>   <!-- 달성율 -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import _ from 'lodash'

export default {
  mounted () {
    // this.$store.dispatch('wbs/getWBSList')
    // const test = this.$store.dispatch('wbs/getWBSList')
    // console.log('>>>>>',test)
  },
  computed: {
    sortedWbsList () {
      // const list = this.$store.state.wbs.WBSList;
      // return _.filter( list, item => item.lv == 1 );
      const list = _.filter(this.$store.state.wbs.WBSList, item => item.lv < 2)
      return _.orderBy(list, 'wbs_id')
    }
  },

  // var result = _(data)
  //   .filter(x => _.get(x, 'members.0.lastseen') > 1800000)
  //   .orderBy('newest_message', 'desc')
  //   .value();

  methods: {
    archivement (plan, result) {
      if (plan === 0 && result === 0) {
        return this.fixed(0)
      } else if (plan === 0) {
        return this.fixed(result)
      } else {
        return this.fixed(result / plan)
      }
    },
    fixed (progress) {
      return parseFloat(progress).toFixed(2)
    }
  }
}
</script>

<style scoped>
table {
  width:100%;
  line-height: 1.2;
  text-align: center;
  font-weight: 400;
  text-align: center;
  font-size: 13px;
}
thead {
  background-color: #4749a0;
  color: white !important;
}
thead > tr > th {
  border: 1px solid white;
  vertical-align: middle !important;
  padding: 10px 0;
  position: sticky;
  top: -6px;
  background-color: #4749a0 !important;
}
th.sticky-top {
  position: sticky;
  top: 30px;
  background-color: #4749a0 !important;
}
tr td {
  padding: 12px 8px;
}
td {
  border: 1px solid rgba(0, 0, 0, 0.1);
}
tbody > tr:hover {
  background-color: #d9e5f2;
  color: #0054a6;
}
</style>
