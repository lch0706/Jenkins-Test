<template>
  <div>
    <flash-message></flash-message>
    <div class="dev-status-page">
      <!--h2 class="pjTitle">Orchestra 고도화 프로젝트</h2-->
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="전체 프로젝트 설정" name="global">
          <article class="searchWrap">
            <div class="search">
              <input type="button" class="save_btn" style="cursor:pointer" autocomplete="on" value="저장"  @click="onSave"/>
            </div>
          </article>
        </el-tab-pane>
        <el-tab-pane label="개별 프로젝트 설정" name="project">
          <!-- SEARCH -->
          <article class="searchWrap">
            <div class="search">
              <select v-model="projectId" placeholder="프로젝트 선택" style="min-width: 500px;">
                <option value="" selected>프로젝트 선택</option>
                <option v-for="item in projectList" :value="item.id" :key="item.id">{{item.name}}</option>
              </select>
              <input type="submit" class="search_btn" style="cursor:pointer" autocomplete="on" value="검색" @click="onSearch"/>
              <input type="button" class="save_btn" style="cursor:pointer" autocomplete="on" value="저장"  @click="onSave"/>
            </div>
          </article>
          <!-- //END : SEARCH -->
        </el-tab-pane>
      </el-tabs>
      <!-- LIST BOARD -->
      <article class="listBoard">
        <div class="tb_board block">
          <table>
            <colgroup>
              <col />
              <col/>
              <col/>
              <col/>
            </colgroup>
            <thead>
              <tr>
                <th>No</th>
                <th>유형구분</th>
                <th>설정항목명</th>
                <th>설정항목코드</th>
                <th>항목설명</th>
                <th>설정값</th>
              </tr>
            </thead>
            <tbody v-if="gridData.length > 0"  :fetchingData="fetching.list">
              <tr v-for="(list) in gridData" :key="list.id" style="height:100%;">
                <td>{{ list.rownum }}</td>
                <td>{{ list.type }}</td>
                <td>{{ list.kor }}</td>
                <td>{{ list.name }}</td>
                <td>{{ list.description }}</td>
                <td><input type="text" v-model="list.value" class="settingBox" v-on:change.prevent="handleChange(list)"/></td>
              </tr>
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="4">선택한 프로젝트 내 조회된 데이터가 없습니다.</td>
              </tr>
            </tbody>
          </table>
        </div>
      </article>
      <!-- // END : LIST BOARD -->
    </div>
  </div>
</template>

<script>
import {
  getProjectList,
  getSettingList,
  setSettingList
} from '@/service/SettingService'
require('vue-flash-message/dist/vue-flash-message.min.css')
export default {
  components: {
  },
  data () {
    return {
      gridData: [],
      projectList: [],
      projectId: '0',
      pageSize: 20,
      currentPage: 1,
      fetching: {
        list: false
      },
      activeName: 'global'
    }
  },
  methods: {
    async onSearch () {
      this.fetching.list = true
      this.currentPage = 1
      try {
        this.gridData = (await getSettingList(this.projectId)).data
      } catch (error) {
        this.gridData = []
        console.log(error)
      }
      this.fetching.list = false
    },
    // onSave() {
    //   console.log("onSave",this.gridData);
    // },
    async onSave () {
      let result = null
      if (this.projectId === '') {
        this.flash('프로젝트를 선택해주세요.', 'error', {
          timeout: 2000
        })
      } else {
        try {
          result = (await setSettingList(this.projectId, this.gridData)).status
          if (result === 200) {
            this.flash('성공적으로 저장되었습니다.', 'success', {
              timeout: 2000
            })
          } else {
            this.flash('저장에 실패하였습니다.', 'error', {
              timeout: 2000
            })
          }
        } catch (error) {
          this.flash('저장에 실패하였습니다.', 'error', {
            timeout: 2000
          })
          console.log(error)
        }
      }
    },
    async fetchData () {
      try {
        this.projectList = (await getProjectList()).data
      } catch (error) {
        console.log(error)
      }
    },
    handleClick (tab, event) {
      if (this.activeName === 'global') {
        this.projectId = '0'
        this.onSearch()
      } else {
        this.projectId = ''
        this.gridData = []
      }
    }
  },
  created () {
    this.fetchData()
  },
  mounted () {
    this.onSearch()
  }
}
</script>

<style>
.search .save_btn {
  width: 70px;
  height: 38px;
  margin-left: 8px;
  color: #fff;
  font-weight: 600;
  border-radius: 19px;
  background: #9e59e8;
}
.el-select .el-input.is-focus .el-input__inner,
.el-pagination__sizes .el-input .el-input__inner:hover,
.el-select .el-input__inner:focus {
  border-color: #2fbcbd;
}
.el-pager li.active {
  color: white;
}
.el-pager li:hover {
  color: #9e59e8;
}
.el-pagination button:hover {
  color: #9e59e8;
}
li.el-select-dropdown__item.selected {
  color: #2fbcbd;
}
.statusfont {
  padding: 10px;
  font-size: 16px;
  text-align: center;
  color: #fff;
}
.modulefont {
  padding: 10px;
  font-size: 16px;
  text-align: center;
  color: black;
}
.totalBlock {
  border-radius: 4px;
  background-color: #fff;
  box-shadow: 0px 0px 4px 1px rgba(0, 0, 0, 0.1);
  -webkit-box-shadow: 0px 0px 4px 1px rgba(0, 0, 0, 0.1);
  flex: 1;
  padding: 0;
  margin-right: 20px;
}
.mouserover {
  cursor: pointer;
}
.settingBox {
  width: 300px;
  padding: 8px 14px;
  border: 1px solid #eaeaed;
  border-radius: 19px;
  background: #fff;
  box-shadow: inset 1px 1px 4px 0 rgba(0, 0, 0, 0.1);
  vertical-align: middle;
}
</style>
