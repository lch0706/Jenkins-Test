import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import HighchartsVue from 'highcharts-vue'
import 'highcharts/modules/no-data-to-display.js'
import Highcharts from 'highcharts'
import VueFlashMessage from 'vue-flash-message'
import Configuration from '@/configuration'
Vue.use(VueFlashMessage)

Highcharts.setOptions({
  // options here
  plotOptions: {
    series: {
      cursor: 'pointer',
      point: {
        events: {
          click: function () {
            // console.log(`${process.env.VUE_APP_ORCHESTRA_URL}`)
            if (this.series.initialType === 'pie') {
              window.open(Configuration.value('orchestraHost') + 'issues?set_filter=1&f[]=issue_id&op[issue_id]==&v[issue_id][]=' + this.idList, '_blank')
            } else {
              window.open(Configuration.value('orchestraHost') + 'issues?set_filter=1&f[]=issue_id&op[issue_id]==&v[issue_id][]=' + this.series.options.idList[this.index], '_blank')
            }
          }
        }
      }
    }
  }
})

Vue.config.productionTip = false
Vue.use(HighchartsVue)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
