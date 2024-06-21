import {
  getProgressList,
  getCompleteChart,
  getDailyChart,
  getModuleChart,
  getResultChart,
  getDetailList,
  getResultList,
  getResultDetailList,
  getTestDegree
} from '@/service/TestService'
import dayjs from 'dayjs'

export default {
  namespaced: true,
  state () {
    return {
      progressList: [],
      completeChart: [],
      dailyChart: [],
      dailyCategories: [],
      moduleChart: [],
      moduleCategories: [],
      resultCategories: [],
      resultChart: [],
      completeRatio: '',
      detailTableVisible: false,
      resultTableVisible: false,
      detailList: [],
      resultList: [],
      resultDetailList: [],
      accDate: dayjs(new Date()).format('YYYY-MM-DD'),
      startDate: dayjs(new Date(Date.now() - 6 * 24 * 60 * 60 * 1000)).format('YYYY-MM-DD'),
      endDate: dayjs(new Date()).format('YYYY-MM-DD'),
      testDegree: []
    }
  },
  getters: {
  },
  mutations: {
    refresh (state) {
      state.progressList = []
      state.completeChart = []
      state.dailyChart = []
      state.dailyCategories = []
      state.moduleChart = []
      state.moduleCategories = []
      state.resultCategories = []
      state.resultChart = []
      state.completeRatio = ''
      state.detailTableVisible = false
      state.resultTableVisible = false
      state.resultList = []
      state.resultDetailList = []
      state.testDegree = []
    },
    setAccDate (state, accDate) {
      state.accDate = accDate
    },
    setWeekDate (state, data) {
      state.startDate = data.startDate
      state.endDate = data.endDate
    },
    pushProgressList (state, lists) {
      state.progressList.push(...lists)
    },
    pushCompleteChart (state, data) {
      state.completeChart.push(...data)
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
    pushResultCategories (state, data) {
      state.resultCategories.push(...data)
    },
    pushResultChart (state, data) {
      state.resultChart.push(...data)
    },
    pushCompleteRatio (state, ratio) {
      state.completeRatio = ratio
    },
    pushDetailTableVisible (state, value) {
      state.detailTableVisible = value
    },
    pushResultTableVisible (state, value) {
      state.resultTableVisible = value
    },
    pushDetailList (state, lists) {
      state.detailList.push(...lists)
    },
    closeDialog (state) {
      state.detailList = []
      state.resultDetailList = []
    },
    pushResultList (state, lists) {
      state.resultList.push(...lists)
    },
    pushResultDetailList (state, lists) {
      state.resultDetailList.push(...lists)
    },
    pushTestDegree (state, lists) {
      state.testDegree.push(...lists)
    }
  },
  actions: {
    getProgressList ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = await getProgressList(payload)
        commit('pushProgressList', res.data.data)
      })
    },
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
    getResultChart ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = (await getResultChart(payload)).data.data
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
          commit('pushResultCategories', new Set(res.map(x => x.mdlNm)))
        }
        commit('pushResultChart', data)
      })
    },
    getDetailList ({ commit }, payload) {
      return new Promise(async resolve => {
        const res = await getDetailList(payload)
        commit('pushDetailList', res.data.data)
      })
    },
    getResultList ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = await getResultList(payload)
        commit('pushResultList', res.data.data)
      })
    },
    getResultDetailList ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = await getResultDetailList(payload)
        commit('pushResultDetailList', res.data.data)
      })
    },
    getTestDegree ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = await getTestDegree(payload)
        commit('pushTestDegree', res.data.data)
      })
    }
  }
}
