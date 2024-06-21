<template>
  <article class="searchWrap">
    <div class="acc_date" v-if="renderParam.accDate">
        <h4>누적 진척</h4>
        <div class="multiple">
            <el-date-picker
                v-model="accDaterange"
                placeholder="종료일"
            ></el-date-picker>
        </div>
    </div>
    <div class="week_date" v-if="renderParam.weekDate">
        <h4>주간 진척</h4>
        <div class="multiple">
            <el-date-picker
                v-model="weekDaterange"
                type="daterange"
                start-placeholder="시작일"
                end-placeholder="종료일"
                :picker-options="pickerOptions"
            ></el-date-picker>
        </div>
    </div>
    <div class="search">
      <select v-if="['test', 'defect'].includes(renderParam.type)" v-model="testDgr">
        <option value="all" selected>전체</option>
        <option v-for="option in this.$store.state[this.renderParam.type].testDegree" :value="option.trackerId" :key="option.trackerId">{{ option.testDgr }}</option>
      </select>
      <select v-if="renderParam.searchBar && renderParam.type !== 'matrix'" v-model="region">
          <option value="all" selected>전체</option>
          <option value="module">업무구분명</option>
          <option value="assigned">담당자</option>
      </select>
      <select v-if="renderParam.type === 'matrix'" v-model="region2">
          <option>검색조건</option>
          <option value="subject" selected>제목</option>
          <option value="reqId">요구사항ID</option>
          <option value="pgrId">프로그램ID</option>
          <option value="tcId">테스트케이스ID</option>
      </select>
      <span class="txt" v-if="renderParam.searchBar">
          <input
            type="text"
            v-model="keyword"
            placeholder="검색어를 입력해 주세요">
      </span>
      <input
          type="submit"
          class="search_btn"
          autocomplete="on"
          value="검색"
          @click="getAllData"
      >
      <slot></slot>
    </div>
  </article>
</template>

<script>
import dayjs from 'dayjs'
import _ from 'lodash'

export default {
  props: [
    'renderParam'
  ],
  data () {
    return {
      params: {},
      region: 'all',
      region2: 'subject',
      keyword: '',
      testDgr: 'all'
    }
  },
  computed: {
    accDaterange: {
      get () {
        const accDate = this.$store.state[this.renderParam.type].accDate
        return accDate
      },
      set (date) {
        this.$store.commit(this.renderParam.type + '/setAccDate', dayjs(date).format('YYYY-MM-DD'))
      }
    },
    weekDaterange: {
      get () {
        const weekDate = [
          this.$store.state[this.renderParam.type].startDate,
          this.$store.state[this.renderParam.type].endDate
        ]
        return weekDate
      },
      set (date) {
        const dates = {
          startDate: dayjs(date[0]).format('YYYY-MM-DD'),
          endDate: dayjs(date[1]).format('YYYY-MM-DD')
        }
        this.$store.commit(this.renderParam.type + '/setWeekDate', dates)
      }
    },
    pickerOptions () {
      return {
        shortcuts: [
          {
            text: '1주',
            onClick (picker) {
              const end = _.cloneDeep(picker.$data.value[1])
              const start = _.cloneDeep(picker.$data.value[1])
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '1개월',
            onClick (picker) {
              const end = _.cloneDeep(picker.$data.value[1])
              const start = _.cloneDeep(picker.$data.value[1])
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '3개월',
            onClick (picker) {
              const end = _.cloneDeep(picker.$data.value[1])
              const start = _.cloneDeep(picker.$data.value[1])
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
              picker.$emit('pick', [start, end])
            }
          }
        ]
      }
    }
  },
  methods: {
    getAllData () {
      this.params = {
        accDate: this.accDaterange,
        startDate: this.weekDaterange[0],
        endDate: this.weekDaterange[1]
      }

      if (this.testDgr !== 'all') {
        this.params.testDgr = this.testDgr
      }

      if (this.keyword !== '') {
        this.params.keyword = this.keyword
        this.params.region = this.region
        this.params.region2 = this.region2
      }

      switch (this.renderParam.type) {
        case 'wbs':
          this.$store.dispatch('wbs/getWBSList', this.params.accDate)
          break
        case 'dev':
          this.$store.commit('dev/refresh')
          // 개발진척테이블
          this.$store.dispatch('dev/getProgressList', this.params)
          // 누적개발완료현황(도넛차트)
          this.$store.dispatch('dev/getCompleteChart', this.params)
          // 주간실적현황(라인차트)
          this.$store.dispatch('dev/getDailyChart', this.params)
          // 주간담당자별실적(바차트)
          this.$store.dispatch('dev/getAssigneeChart', this.params)
          // 주간업무구분별실적(바차트)
          this.$store.dispatch('dev/getModuleChart', this.params)
          break
        case 'test':
          this.$store.commit('test/refresh')
          // 테스트 차수 selectbox
          this.$store.dispatch('test/getTestDegree', this.params)
          // 테스트진척테이블
          this.$store.dispatch('test/getProgressList', this.params)
          // 누적테스트완료현황(도넛차트)
          this.$store.dispatch('test/getCompleteChart', this.params)
          // 주간실적현황(라인차트)
          this.$store.dispatch('test/getDailyChart', this.params)
          // 주간업무구분별실적(바차트)
          this.$store.dispatch('test/getModuleChart', this.params)
          // 주간담당자별실적(바차트)
          this.$store.dispatch('test/getResultChart', this.params)
          // 테스트결과테이블
          this.$store.dispatch('test/getResultList', this.params)
          break
        case 'defect':
          this.$store.commit('defect/refresh')
          this.$store.dispatch('defect/getTestDegree', this.params)
          this.$store.dispatch('defect/getDefectList', this.params)
          this.$store.dispatch('defect/getCompleteChart', this.params)
          this.$store.dispatch('defect/getDailyChart', this.params)
          this.$store.dispatch('defect/getModuleChart', this.params)
          this.$store.dispatch('defect/getAssigneeChart', this.params)
          break
        case 'matrix':
          this.$store.commit('matrix/refresh')
          this.$store.dispatch('matrix/getMatrixList', this.params)
      }
    }
  },
  mounted () {
    this.getAllData()
  }
}
</script>

<style>
</style>
