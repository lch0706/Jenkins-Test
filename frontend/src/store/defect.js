import {
  getDefectList,
  getCompleteChart,
  getDailyChart,
  getModuleChart,
  getDetailList,
  getTestDegree,
  getAssigneeChart
} from '@/service/DefectService'
import dayjs from 'dayjs'

export default {
  namespaced: true,
  state () {
    return {
      defectList: [],
      completeChart: [],
      completeRatio: '',
      dailyChart: [],
      dailyCategories: [],
      moduleChart: [],
      moduleCategories: [],
      assigneeCategories: [],
      assigneeChart: [],
      detailTableVisible: false,
      detailList: [],
      accDate: dayjs(new Date()).format('YYYY-MM-DD'),
      startDate: dayjs(new Date(Date.now() - 6 * 24 * 60 * 60 * 1000)).format('YYYY-MM-DD'),
      endDate: dayjs(new Date()).format('YYYY-MM-DD'),
      testDegree: []
    }
  },
  mutations: {
    refresh (state) {
      state.defectList = []
      state.completeChart = []
      state.completeRatio = ''
      state.dailyChart = []
      state.dailyCategories = []
      state.moduleChart = []
      state.moduleCategories = []
      state.assigneeChart = []
      state.assigneeCategories = []
      state.testDegree = []
      state.detailTableVisible = false
    },
    setAccDate (state, accDate) {
      state.accDate = accDate
    },
    setWeekDate (state, data) {
      state.startDate = data.startDate
      state.endDate = data.endDate
    },
    pushDefectList (state, lists) {
      state.defectList.push(...lists)
    },
    pushCompleteChart (state, data) {
      state.completeChart.push(...data)
    },
    pushCompleteRatio (state, ratio) {
      state.completeRatio = ratio
    },
    pushDailyChart (state, data) {
      state.dailyChart.push(...data)
    },
    pushDailyCategories (state, data) {
      state.dailyCategories.push(...data)
    },
    pushModuleChart (state, data) {
      state.moduleChart.push(...data)
    },
    pushModuleCategories (state, data) {
      state.moduleCategories.push(...data)
    },
    pushAssigneeChart (state, data) {
      state.assigneeChart.push(...data)
    },
    pushAssigneeCategories (state, data) {
      state.assigneeCategories.push(...data)
    },
    pushDetailList (state, lists) {
      state.detailList.push(...lists)
    },
    pushDetailTableVisible (state, value) {
      state.detailTableVisible = value
    },
    closeDialog (state) {
      state.detailList = []
    },
    pushTestDegree (state, lists) {
      state.testDegree.push(...lists)
    }
  },
  actions: {
    getCompleteChart ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = (await getCompleteChart(payload)).data.data
        const data = []
        let completeRatio = ''

        if (res.length > 0) {
          for (let i = 0; i < res.length; i++) {
            data.push({
              label: res.map((x) => x.status)[i],
              data: res.map((x) => Number(x.cnt))[i],
              ratio: res.map((x) => Number(x.ratio))[i],
              idList: res.map((x) => x.id)[i]
            })
          }
          completeRatio = res.filter((x) => x.status === '완료').map((x) => Number(x.ratio) + '%')[0]
          commit('pushCompleteRatio', completeRatio)
        } else {
          data.push({
            label: '완료'
          })
          commit('pushCompleteRatio', '0%')
        }
        commit('pushCompleteChart', data)
      })
    },
    getDailyChart ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = (await getDailyChart(payload)).data.data
        const data = []

        if (res.length > 0) {
          data.push({
            name: '계획',
            data: res.map((x) => Number(x.planCnt)),
            idList: res.map((x) => x.planList)
          })
          data.push({
            name: '완료',
            data: res.map((x) => Number(x.doneCnt)),
            idList: res.map((x) => x.doneList)
          })
          commit('pushDailyCategories', res.map((x) => x.baseDate))
        }
        commit('pushDailyChart', data)
      })
    },
    getModuleChart ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = (await getModuleChart(payload)).data.data
        const data = []
        let statusList = []

        if (res.length > 0) {
          statusList = new Set(res.map(x => x.status))
          for (const status of statusList) {
            data.push({
              name: status,
              data: res.filter(x => x.status === status).map(x => Number(x.cnt)),
              idList: res.filter(x => x.status === status).map(x => x.ids)
            })
          }
          commit('pushModuleCategories', new Set(res.map(x => x.mdlNm)))
        }
        commit('pushModuleChart', data)
      })
    },
    getDefectList ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = await getDefectList(payload)
        commit('pushDefectList', res.data.data)
      })
    },
    getDetailList ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = await getDetailList(payload)
        commit('pushDetailList', res.data.data)
      })
    },
    getTestDegree ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = await getTestDegree(payload)
        commit('pushTestDegree', res.data.data)
      })
    },
    getAssigneeChart ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = (await getAssigneeChart(payload)).data.data
        const data = []

        if (res.length > 0) {
          data.push({
            name: '조치',
            data: res.map((x) => Number(x.completionCount)),
            idList: res.map((x) => x.doneids)
          })
          data.push({
            name: '잔여',
            data: res.map((x) => Number(x.remainCount)),
            idList: res.map((x) => x.remainids)
          })
          commit('pushAssigneeCategories', res.map((x) => x.assigned))
        }
        commit('pushAssigneeChart', data)
      })
    }
  }
}
