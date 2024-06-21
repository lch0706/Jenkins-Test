import Vue from 'vue'
import Vuex from 'vuex'
import wbs from './wbs'
import dev from './dev'
import test from './test'
import defect from './defect'
import matrix from './matrix'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    wbs,
    dev,
    test,
    defect,
    matrix
  }
})
