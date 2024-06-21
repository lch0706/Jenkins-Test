import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import WBS from '@/views/WBS.vue'
import Dev from '@/views/Dev.vue'
import Test from '@/views/Test.vue'
import Defect from '@/views/Defect.vue'
import Matrix from '@/views/Matrix.vue'
import Setting from '@/views/Setting.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HOME',
    component: Dev
  },
  {
    path: '/wbs',
    name: 'WBS',
    component: WBS
  },
  {
    path: '/dev',
    name: 'Dev',
    component: Dev
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/defect',
    name: 'Defect',
    component: Defect
  },
  {
    path: '/matrix',
    name: 'Matrix',
    component: Matrix
  },
  {
    path: '/setting',
    name: 'Settomg',
    component: Setting
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
