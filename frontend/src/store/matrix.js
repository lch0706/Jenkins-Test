import {
  getMatrixList
} from '@/service/MatrixService'

export default {
  namespaced: true,
  state () {
    return {
      matrixList: []
    }
  },
  mutations: {
    refresh (state) {
      state.matrixList = []
    },
    pushMatrixList (state, lists) {
      state.matrixList.push(...lists)
    }
  },
  actions: {
    getMatrixList ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        const res = await getMatrixList(payload)
        commit('pushMatrixList', res.data.data)
      })
    }
  }
}
