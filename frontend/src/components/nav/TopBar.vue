<template>
    <header class="header">
        <a href="/"><h1 class="logo">Dashboard</h1></a>
        <nav class="menu">
            <ul>
                <li v-for="link in menuLink" :key="link" :index="link" :class="getClass(link)">
                    <router-link :to="`/${link}`">{{ getMenuName(link) }}</router-link>
                </li>
            </ul>
        </nav>
    </header>
</template>

<script>
export default {
  data () {
    return {
      menuLink: ['wbs', 'dev', 'test', 'defect', 'matrix', 'setting']
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    getClass (link) {
      return this.$route.path.indexOf(`/${link}`) > -1 ? 'on' : ''
    },
    getMenuName (link) {
      let name = null
      switch (link) {
        case 'wbs':
          name = 'WBS'
          break
        case 'dev':
          name = '개발현황'
          break
        case 'test':
          name = '테스트현황'
          break
        case 'defect':
          name = '결함현황'
          break
        case 'matrix':
          name = '요구사항추적매트릭스'
          break
        case 'setting':
          name = '환경설정'
          break
      }
      return name
    }
  }
}
</script>
