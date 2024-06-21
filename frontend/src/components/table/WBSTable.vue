<template>
  <div>
    <!-- <h5>
      달성율(계획/실적): [72%/72%]
    </h5> -->
    <table id="table__wbs2">
      <thead>
        <tr>
          <th rowspan="2">No</th>
          <!-- <th rowspan="2">WBS ID</th> -->
          <th rowspan="2">Task</th>
          <th rowspan="2">담당자</th>
          <th rowspan="2">가중치</th>
          <th colspan="5">계획</th>
          <th colspan="2">실적</th>
          <th rowspan="2">달성율</th>
        </tr>
        <tr>
          <th class="sticky-top">시작일</th>
          <th class="sticky-top">종료일</th>
          <th class="sticky-top">소요기간</th>
          <th class="sticky-top"><div>진척률</div><div>(100%기준)</div></th>
          <th class="sticky-top"><div>진척률</div><div>(가중치기준)</div></th>
          <th class="sticky-top"><div>진척률</div><div>(100%기준)</div></th>
          <th class="sticky-top"><div>진척률</div><div>(가중치기준)</div></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(wbs, index) in sortedWbsList" :key="wbs.id">
        <!-- <tr v-for="(wbs, index) in this.$store.state.wbs.WBSList" :key="wbs.id"> -->
          <td>{{index+1}}</td>  <!-- No -->
          <!-- <td style="text-align: left;">{{wbs.wbs_id}}</td> -->
          <td style="text-align: left; white-space: pre-wrap;">{{wbs.subject}}</td> <!-- Task -->
          <td>{{wbs.assignee}}</td> <!-- 담당자 -->
          <td>{{wbs.weight}}</td>   <!-- 가중치 -->
          <td>{{todate(wbs.plan_start_date)}}</td>  <!-- 계획시작일 -->
          <td>{{todate(wbs.plan_end_date)}}</td>    <!-- 계획종료일 -->
          <td>{{wbs.plan_day}}일</td>       <!-- 소요기간 -->
          <td>{{fixed(wbs.plan_progress)}}%</td>  <!-- 계획진척율(100%) -->
          <td>{{fixed(wbs.plan_weight_progress)}}%</td> <!-- 계획진척율(가중치) -->
          <td>{{fixed(wbs.result_progress)}}%</td>  <!-- 실적진척율(100%) -->
          <!-- <td>{{fixed(wbs.ratio)}}%</td> -->
          <td>{{fixed(wbs.result_weight_progress)}}%</td> <!-- 실적진척율(가중치) -->
          <td>{{fixed(wbs.achievement_rate)}}%</td>   <!-- 달성율 -->
          <!-- <td>
            {{
              archivement(wbs.plan_progress, wbs.ratio)
            }}%
          </td> -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import _ from 'lodash'
import dayjs from 'dayjs'

export default {
  mounted () {
    // this.$store.dispatch('wbs/getWBSList')
    // const test = this.$store.dispatch('wbs/getWBSList')
    // console.log('>>>>>',test)
  },
  computed: {
    sortedWbsList () {
      const data = _.sortBy(this.$store.state.wbs.WBSList, 'wbs_id')
      const sort = {
        asc: function (a, b) {
          let l = 0
          const m = Math.min(a.value.length, b.value.length)
          while (l < m && a.value[l] === b.value[l]) {
            l++
          }
          return l === m ? a.value.length - b.value.length : a.value[l] - b.value[l]
        },
        desc: function (a, b) {
          return sort.asc(b, a)
        }
      }

      const mapped = data.map(function (el, i) {
        return {
          index: i,
          value: el.wbs_id.split('.').map(Number)
        }
      })

      mapped.sort(sort.asc)

      return mapped.map(function (el) {
        return data[el.index]
      })
    }
  },
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
    },
    todate (date) {
      return dayjs(date).format('YYYY-MM-DD')
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
table#table__wbs2 th {
  position: sticky;
  top: 60px;
  background-color: #4749a0 !important;
}
table#table__wbs2 th.sticky-top {
  position: sticky;
  top: 96px;
  background-color: #4749a0 !important;
}
</style>
