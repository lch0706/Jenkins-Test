import {
  getProgressList,
  getCompleteChart,
  getDailyChart,
  getModuleChart,
  getAssigneeChart,
  getDetailList
} from '@/service/DevService'
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
      assigneeCategories: [],
      assigneeChart: [],
      completeRatio: '',
      detailTableVisible: false,
      detailList: [],
      accDate: dayjs(new Date()).format('YYYY-MM-DD'),
      startDate: dayjs(new Date(Date.now() - 6 * 24 * 60 * 60 * 1000)).format('YYYY-MM-DD'),
      endDate: dayjs(new Date()).format('YYYY-MM-DD')
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
      state.assigneeCategories = []
      state.assigneeChart = []
      state.completeRatio = ''
      state.detailTableVisible = false
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
    pushAssigneeCategories (state, data) {
      state.assigneeCategories.push(...data)
    },
    pushAssigneeChart (state, data) {
      state.assigneeChart.push(...data)
    },
    pushCompleteRatio (state, ratio) {
      state.completeRatio = ratio
    },
    pushDetailTableVisible (state, value) {
      state.detailTableVisible = value
    },
    pushDetailList (state, lists) {
      state.detailList.push(...lists)
    },
    closeDialog (state) {
      state.detailList = []
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

        if (res.length > 0) {
          data.push({
            name: '계획',
            data: res.map((x) => Number(x.weekPlan)),
            idList: res.map((x) => x.weekPlanIds)
          })
          data.push({
            name: '실적',
            data: res.map((x) => Number(x.weekPer)),
            idList: res.map((x) => x.weekPlanIds)
          })
          data.push({
            name: '진행중',
            data: res.map((x) => Number(x.weekProg)),
            idList: res.map((x) => x.weekProgIds)
          })
          data.push({
            name: '지연',
            data: res.map((x) => Number(x.weekDly)),
            idList: res.map((x) => x.weekDlyIds)
          })
          commit('pushModuleCategories', res.map((x) => x.mdlNm))
        }
        commit('pushModuleChart', data)
      })
    },
    getAssigneeChart ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = (await getAssigneeChart(payload)).data.data
        const data = []

        if (res.length > 0) {
          data.push({
            name: '계획',
            data: res.map((x) => Number(x.weekPlan)),
            idList: res.map((x) => x.weekPlanIds)
          })
          data.push({
            name: '실적',
            data: res.map((x) => Number(x.weekPer)),
            idList: res.map((x) => x.weekPlanIds)
          })
          data.push({
            name: '진행중',
            data: res.map((x) => Number(x.weekProg)),
            idList: res.map((x) => x.weekProgIds)
          })
          data.push({
            name: '지연',
            data: res.map((x) => Number(x.weekDly)),
            idList: res.map((x) => x.weekDlyIds)
          })
          commit('pushAssigneeCategories', res.map((x) => x.assigned))
        }
        commit('pushAssigneeChart', data)
      })
    },
    getDetailList ({ commit }, payload) {
      return new Promise(async resolve => {
        const res = await getDetailList(payload)
        commit('pushDetailList', res.data.data)
      })
    }
  }
}
