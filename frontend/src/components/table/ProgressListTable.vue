<template>
  <article class="listBoard">
    <div class="tbTit">
      <h4>{{ tableLabel }} / 전체 {{ paginatedLength }} 건</h4>
      <div align="right">
        <button class="btn_B" @click="excelDownload">Download</button>&emsp;
        <label>페이지당 개수</label>
        <div class="slt">
          <el-pagination
            background
            @size-change="handleSizeChange"
            :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30, 40]"
            :page-size="pageSize"
            layout="sizes"
            :total="paginatedLength">
          </el-pagination>
        </div>
      </div>
    </div>
    <div class="tb_board block">
      <el-table
        id="listTable_top"
        :data="paginatedData"
        stripe
        border
        fit
        sum-text="합계"
        :show-summary="showSum"
        :summary-method="getSummary"
        @cell-click="popDialogTable"
        style="width: 100%">
        <el-table-column
          fixed
          prop="no"
          label="No">
        </el-table-column>
        <el-table-column
          v-if="this.renderParam.type === 'test'"
          fixed
          prop="trkNm"
          label="테스트차수"
          :filters="trkFilters"
          :filter-method="filterHandler">
        </el-table-column>
        <el-table-column
          fixed
          prop="mdlNm"
          label="업무구분"
          :filters="mdlFilters"
          :filter-method="filterHandler">
        </el-table-column>
        <el-table-column
          fixed
          prop="assigned"
          label="담당자"
          :filters="assignFilters"
          :filter-method="filterHandler">
        </el-table-column>
        <el-table-column
          :label="accLabel">
          <el-table-column
            class-name="clickable"
            prop="accPlan"
            label="계획(A=B+C+D)">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="accPer"
            label="실적(B)">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="accProg"
            label="진행중(C)">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="accDly"
            label="지연(D=A-(B+C))">
          </el-table-column>
          <el-table-column
            prop="accCmpRate"
            label="완료율(B/A*100)%">
            <template slot-scope="scope">
              <span>{{ scope.row.accCmpRate + '%' }}</span>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column
          :label="weekLabel">
          <el-table-column
            class-name="clickable"
            prop="weekPlan"
            label="계획(A=B+C+D)">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="weekPer"
            label="실적(B)">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="weekProg"
            label="진행중(C)">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="weekDly"
            label="지연(D=A-(B+C))">
          </el-table-column>
          <el-table-column
            prop="weekCmpRate"
            label="완료율(B/A*100)%">
            <template slot-scope="scope">
              <span>{{ scope.row.weekCmpRate + '%' }}</span>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column
            label="단계">
          <el-table-column
            class-name="clickable"
            prop="allRes"
            label="잔여">
          </el-table-column>
          <el-table-column
            prop="allTotal"
            label="전체">
          </el-table-column>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination">
        <div class="pg_move">
          <div align="center">
            <el-pagination
              @size-change="handleSizeChange"
              :current-page.sync="currentPage"
              :page-sizes="[5, 10, 20, 30, 40]"
              :page-size="pageSize"
              layout="prev, pager, next"
              :total="paginatedLength"
            ></el-pagination>
          </div>
        </div>
    </div>
    <detail-list-table :page-param="this.params" v-if="this.$store.state[this.renderParam.type].detailTableVisible"/>
  </article>
</template>

<script>
import _ from 'lodash'
import XLSX from 'xlsx'
import DetailListTable from '@/components/table/DetailListTable'

export default {
  props: ['renderParam', 'tableLabel'],
  data () {
    return {
      currentPage: 1,
      pageSize: 5,
      showSum: true,
      params: {}
    }
  },
  computed: {
    tableList () {
      return this.$store.state[this.renderParam.type].progressList
    },
    paginatedLength () {
      return this.$store.state[this.renderParam.type].progressList.length
    },
    paginatedData () {
      const start = (this.currentPage - 1) * this.pageSize
      const end = start + this.pageSize
      return this.tableList.slice(start, end)
    },
    accLabel () {
      const accDate = this.$store.state[this.renderParam.type].accDate
      return '누적진척 ( ~ ' + accDate + ' )'
    },
    weekLabel () {
      const startDate = this.$store.state[this.renderParam.type].startDate
      const endDate = this.$store.state[this.renderParam.type].endDate
      return '주간진척 ( ' + startDate + ' ~ ' + endDate + ' )'
    },
    trkFilters () {
      const filters = this.tableList.map((x) => {
        return _.assignIn(
          {
            text: x.trkNm, value: x.trkNm
          }
        )
      })
      return _.uniqBy(filters, 'text')
    },
    mdlFilters () {
      const filters = this.tableList.map((x) => {
        return _.assignIn(
          {
            text: x.mdlNm, value: x.mdlNm
          }
        )
      })
      return _.uniqBy(filters, 'text')
    },
    assignFilters () {
      const filters = this.tableList.map((x) => {
        return _.assignIn(
          {
            text: x.assigned, value: x.assigned
          }
        )
      })
      return _.uniqBy(filters, 'text')
    }
  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.currentPage = 1
    },
    filterHandler (value, row, column) {
      this.paginatedData = this.tableList
      const property = column.property
      return row[property] === value
    },
    getSummary ({ columns, data }) {
      const sums = []
      let accCmpRateIndex = 0
      let weekCmpRateIndex = 0
      let accPlanIndex = 0
      let accPerIndex = 0
      let weekPlanIndex = 0
      let weekPerIndex = 0
      columns.forEach((column, index) => {
        if (column.property === 'accCmpRate') {
          accCmpRateIndex = index
        } else if (column.property === 'weekCmpRate') {
          weekCmpRateIndex = index
        } else if (column.property === 'accPlan') {
          accPlanIndex = index
        } else if (column.property === 'accPer') {
          accPerIndex = index
        } else if (column.property === 'weekPlan') {
          weekPlanIndex = index
        } else if (column.property === 'weekPer') {
          weekPerIndex = index
        }
        if (index === 0) {
          sums[index] = '합계'
          return
        }
        const values = this.tableList.map(item => Number(item[column.property]))
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr)
            if (!isNaN(value)) {
              return prev + curr
            } else {
              return prev
            }
          }, 0)
        }
      })

      if (sums[accPerIndex] === 0 && sums[accPlanIndex] === 0) {
        sums[accCmpRateIndex] = '0%'
      } else {
        sums[accCmpRateIndex] = ((sums[accPerIndex] / sums[accPlanIndex]) * 100).toFixed(1) + '%'
      }

      if (sums[weekPerIndex] === 0 && sums[weekPlanIndex] === 0) {
        sums[weekCmpRateIndex] = '0%'
      } else {
        sums[weekCmpRateIndex] = ((sums[weekPerIndex] / sums[weekPlanIndex]) * 100).toFixed(1) + '%'
      }
      return sums
    },
    popDialogTable (row, column, cell, event) {
      this.params = {}
      if (column.className === 'clickable') {
        let status = ''

        if (_.includes(column.property, 'acc')) {
          status = column.property.substring('acc'.length)
          this.params.type = 'acc'
        } else if (_.includes(column.property, 'week')) {
          status = column.property.substring('week'.length)
          this.params.type = 'week'
        } else {
          status = column.property.substring('res'.length)
          this.params.type = 'res'
        }

        switch (status) {
          case 'Plan':
            this.params.status = 'plan'
            this.params.koStatus = '계획'
            break
          case 'Per':
            this.params.status = 'perform'
            this.params.koStatus = '실적'
            break
          case 'Prog':
            this.params.status = 'progress'
            this.params.koStatus = '진행중'
            break
          case 'Dly':
            this.params.status = 'delay'
            this.params.koStatus = '지연'
            break
          case 'Res':
            this.params.status = 'res'
            this.params.koStatus = '잔여'
            break
        }
        this.params.module = row.mdlNm
        this.params.assignee = row.assigned
        this.params.count = cell.innerText

        switch (this.renderParam.type) {
          case 'dev':
            this.params.koType = '개발'
            break
          case 'test':
            this.params.koType = '테스트'
            break
        }
        this.params.enType = this.renderParam.type
        this.params.tableType = 'progress'
        this.params.accDate = this.$store.state[this.renderParam.type].accDate
        this.params.startDate = this.$store.state[this.renderParam.type].startDate
        this.params.endDate = this.$store.state[this.renderParam.type].endDate
        this.params.testDgr = row.trkNm
        this.$store.dispatch(this.renderParam.type + '/getDetailList', this.params)
        this.$store.commit(this.renderParam.type + '/pushDetailTableVisible', true)
      }
    },
    excelDownload () {
      const fix = document.querySelector('#listTable_top>.el-table__fixed')
      const workBook = XLSX.utils.book_new()
      let excelData
      if (fix) {
        excelData = XLSX.utils.table_to_sheet(document.querySelector('#listTable_top').removeChild(fix))
        document.querySelector('#listTable_top').appendChild(fix)
      } else {
        excelData = XLSX.utils.table_to_sheet(document.querySelector('#listTable_top'))
      }
      XLSX.utils.book_append_sheet(workBook, excelData, 'Sheet1')
      XLSX.writeFile(workBook, this.renderParam.type + '.xlsx')
    }
  },
  components: {
    DetailListTable
  }
}
</script>

<style lang="scss">
.clickable {
  cursor: pointer;
}
.el-table__footer {
  font-weight: bold;
}
</style>
