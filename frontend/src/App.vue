<template>
  <main>
    <top-bar></top-bar>
    <section class="container">
      <side-bar></side-bar>
      <section class="contents">
        <router-view></router-view>
      </section>
    </section>
  </main>
</template>

<script>
import TopBar from '@/components/nav/TopBar'
import SideBar from '@/components/nav/SideBar'

export default {
  name: 'app',
  components: {
    TopBar,
    SideBar
  }
}
</script>

<style lang="scss">
  @import '~@/assets/css/common.css';
  @import '~@/assets/css/flatpickr.min.css';
</style>
