<template>
  <el-dialog
    :show-close="false"
    :visible="visible"
    custom-class="el-dialog__total">
    <div slot="title" class="popTit">
      <h3>업무구분별 {{ this.pageParam.koType }} 현황
        - {{ this.pageParam.module }} / {{ this.pageParam.assignee }} /
        {{ this.pageParam.koStatus }} {{ this.pageParam.count }}건</h3>
      <a class="pop_close" @click="closeDialogTable">닫기</a>
    </div>
    <div class="popInner">
      <h4 class="tbTit">전체 {{ this.tableList.length }}건</h4>
      <div class="tb_board">
        <el-table
          :data="tableList"
          stripe
          border
          fit>
          <el-table-column label="No" prop="no"></el-table-column>
          <el-table-column label="일감ID" prop="id"></el-table-column>
          <el-table-column label="테스트차수" prop="trkNm" v-if="['test', 'defect'].includes(this.pageParam.enType)"></el-table-column>
          <el-table-column label="테스트케이스ID" prop="caseId" v-if="['test', 'defect'].includes(this.pageParam.enType)"></el-table-column>
          <el-table-column label="상태" prop="status"></el-table-column>
          <el-table-column label="제목" prop="subject"></el-table-column>
          <el-table-column label="담당자" prop="assignee"></el-table-column>
          <el-table-column label="계획시작일" prop="startDate"></el-table-column>
          <el-table-column label="계획종료일" prop="endDate"></el-table-column>
          <el-table-column label="실제종료일" prop="closedDate"></el-table-column>
          <el-table-column label="완료율" prop="doneRatio" v-if="this.pageParam.enType === 'dev'">
            <template slot-scope="scope">
              <span>{{ scope.row.doneRatio + '%' }}</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </el-dialog>
</template>

<script>
export default {
  props: ['pageParam'],
  computed: {
    tableList () {
      let tableList = []
      switch (this.pageParam.tableType) {
        case 'progress':
        case 'defect':
          tableList = this.$store.state[this.pageParam.enType].detailList
          break
        case 'result':
          tableList = this.$store.state[this.pageParam.enType].resultDetailList
          break
      }
      return tableList
    },
    visible () {
      let visible
      switch (this.pageParam.tableType) {
        case 'progress':
        case 'defect':
          visible = this.$store.state[this.pageParam.enType].detailTableVisible
          break
        case 'result':
          visible = this.$store.state[this.pageParam.enType].resultTableVisible
          break
      }
      return visible
    }
  },
  methods: {
    closeDialogTable () {
      switch (this.pageParam.tableType) {
        case 'progress':
        case 'defect':
          this.$store.commit(this.pageParam.enType + '/pushDetailTableVisible', false)
          this.$store.commit(this.pageParam.enType + '/closeDialog')
          break
        case 'result':
          this.$store.commit(this.pageParam.enType + '/pushResultTableVisible', false)
          this.$store.commit(this.pageParam.enType + '/closeDialog')
          break
      }
    }
  }
}
</script>

<style lang="scss">
.el-dialog__total {
  .el-dialog__header {
    padding: 0;
  }
}
</style>
