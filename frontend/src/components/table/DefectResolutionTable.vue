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
        id="listTable"
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
          fixed
          prop="testDgr"
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
            prop="accTotal"
            label="전체">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="accCmp"
            label="완료">
          </el-table-column>
          <el-table-column
            prop="accCmpRate"
            label="조치율(%)">
            <template slot-scope="scope">
              <span>{{ scope.row.accCmpRate + '%' }}</span>
            </template>
          </el-table-column>
        </el-table-column>
        <el-table-column
          :label="weekLabel">
          <el-table-column
            class-name="clickable"
            prop="weekTotal"
            label="전체">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="weekCmp"
            label="완료">
          </el-table-column>
          <el-table-column
            prop="weekCmpRate"
            label="조치율(%)">
            <template slot-scope="scope">
              <span>{{ scope.row.weekCmpRate + '%' }}</span>
            </template>
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
      return this.$store.state[this.renderParam.type].defectList
    },
    paginatedLength () {
      return this.$store.state[this.renderParam.type].defectList.length
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
            text: x.testDgr, value: x.testDgr
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
      let accCmpIndex = 0
      let accTotalIndex = 0
      let weekCmpIndex = 0
      let weekTotalIndex = 0
      columns.forEach((column, index) => {
        if (column.property === 'accCmpRate') {
          accCmpRateIndex = index
        } else if (column.property === 'weekCmpRate') {
          weekCmpRateIndex = index
        } else if (column.property === 'weekCmp') {
          weekCmpIndex = index
        } else if (column.property === 'weekTotal') {
          weekTotalIndex = index
        } else if (column.property === 'accCmp') {
          accCmpIndex = index
        } else if (column.property === 'accTotal') {
          accTotalIndex = index
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

      if (sums[accCmpIndex] === 0 && sums[accTotalIndex] === 0) {
        sums[accCmpRateIndex] = '0%'
      } else {
        sums[accCmpRateIndex] = ((sums[accCmpIndex] / sums[accTotalIndex]) * 100).toFixed(1) + '%'
      }

      if (sums[weekCmpIndex] === 0 && sums[weekTotalIndex] === 0) {
        sums[weekCmpRateIndex] = '0%'
      } else {
        sums[weekCmpRateIndex] = ((sums[weekCmpIndex] / sums[weekTotalIndex]) * 100).toFixed(1) + '%'
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
        }

        switch (status) {
          case 'Total':
            this.params.status = 'total'
            this.params.koStatus = '전체'
            break
          case 'Cmp':
            this.params.status = 'complete'
            this.params.koStatus = '완료'
            break
        }
        this.params.module = row.mdlNm
        this.params.assignee = row.assigned
        this.params.count = cell.innerText
        this.params.koType = '결함'
        this.params.testDgr = row.testDgr
        this.params.enType = this.renderParam.type
        this.params.tableType = 'defect'
        this.params.accDate = this.$store.state[this.renderParam.type].accDate
        this.params.startDate = this.$store.state[this.renderParam.type].startDate
        this.params.endDate = this.$store.state[this.renderParam.type].endDate
        this.$store.dispatch(this.renderParam.type + '/getDetailList', this.params)
        this.$store.commit(this.renderParam.type + '/pushDetailTableVisible', true)
      }
    },
    excelDownload () {
      const fix = document.querySelector('#listTable>.el-table__fixed')
      const workBook = XLSX.utils.book_new()
      let excelData
      if (fix) {
        excelData = XLSX.utils.table_to_sheet(document.querySelector('#listTable').removeChild(fix))
        document.querySelector('#listTable').appendChild(fix)
      } else {
        excelData = XLSX.utils.table_to_sheet(document.querySelector('#listTable'))
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
