import XLSX from 'xlsx'
import dayjs from 'dayjs'
import { getWBSList } from '@/service/WBSService'

export default {
  namespaced: true,
  state: {
    WBSList: [],
    accDate: dayjs(new Date()).format('YYYY-MM-DD')
  },
  getters: {
  },
  mutations: {
    pushWBSList (state, wbslist) {
      state.WBSList.push(...wbslist)
    },
    setAccDate (state, accDate) {
      state.accDate = accDate
    },
    refresh (state) {
      state.WBSList = []
    }
  },
  actions: {
    excelDownload () {
      const excelData1 = XLSX.utils.table_to_sheet(document.getElementById('table__wbs1'))
      const excelData2 = XLSX.utils.table_to_sheet(document.getElementById('table__wbs2'))
      const workBook = XLSX.utils.book_new()
      XLSX.utils.book_append_sheet(workBook, excelData1, 'Sheet1')
      XLSX.utils.book_append_sheet(workBook, excelData2, 'Sheet2')
      XLSX.writeFile(workBook, 'wbs.xlsx')
    },
    getWBSList ({ commit }, payload) {
      // eslint-disable-next-line no-async-promise-executor
      return new Promise(async resolve => {
        commit('refresh')
        payload = !payload ? dayjs(new Date()).format('YYYY-MM-DD') : payload
        console.log('payload', payload)
        const res = await getWBSList(payload)
        console.log('res', res)
        commit('pushWBSList', res.data.data)
        console.log(res.data.data)
        resolve(res.data.data)
      })
    }
  }
}
