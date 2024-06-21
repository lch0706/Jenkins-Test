<template>
  <article class="listBoard">
    <div class="tbTit">
      <h4>{{ tableLabel }}(테스트완료 건) / 전체 {{ paginatedLength }} 건</h4>
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
        id="listTable_bottom"
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
            prop="accTotal"
            label="전체">
          </el-table-column>
          <el-table-column
            prop="accPassRate"
            label="Pass율(%)">
            <template slot-scope="scope">
              <span>{{ scope.row.accPassRate + '%' }}</span>
            </template>
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="accPass"
            label="Pass">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="accFail"
            label="Fail">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="accBlock"
            label="Block">
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
            prop="weekPassRate"
            label="Pass율(%)">
            <template slot-scope="scope">
              <span>{{ scope.row.weekPassRate + '%' }}</span>
            </template>
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="weekPass"
            label="Pass">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="weekFail"
            label="Fail">
          </el-table-column>
          <el-table-column
            class-name="clickable"
            prop="weekBlock"
            label="Block">
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
    <detail-list-table :page-param="this.params" v-if="this.$store.state[this.renderParam.type].resultTableVisible"/>
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
      return this.$store.state[this.renderParam.type].resultList
    },
    paginatedLength () {
      return this.$store.state[this.renderParam.type].resultList.length
    },
    paginatedData () {
      const start = (this.currentPage - 1) * this.pageSize
      const end = start + this.pageSize
      return this.tableList.slice(start, end)
    },
    accLabel () {
      const accDate = this.$store.state[this.renderParam.type].accDate
      return '누적결과 ( ~ ' + accDate + ' )'
    },
    weekLabel () {
      const startDate = this.$store.state[this.renderParam.type].startDate
      const endDate = this.$store.state[this.renderParam.type].endDate
      return '주간결과 ( ' + startDate + ' ~ ' + endDate + ' )'
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
      const property = column.property
      return row[property] === value
    },
    getSummary ({ columns, data }) {
      const sums = []
      let accPassRateIndex = 0
      let weekPassRateIndex = 0
      let accPassIndex = 0
      let accTotalIndex = 0
      let weekPassIndex = 0
      let weekTotalIndex = 0

      columns.forEach((column, index) => {
        if (column.property === 'accPassRate') {
          accPassRateIndex = index
        } else if (column.property === 'weekPassRate') {
          weekPassRateIndex = index
        } else if (column.property === 'accTotal') {
          accTotalIndex = index
        } else if (column.property === 'accPass') {
          accPassIndex = index
        } else if (column.property === 'weekTotal') {
          weekTotalIndex = index
        } else if (column.property === 'weekPass') {
          weekPassIndex = index
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

      if (sums[accPassIndex] === 0 && sums[accTotalIndex] === 0) {
        sums[accPassRateIndex] = '0%'
      } else {
        sums[accPassRateIndex] = ((sums[accPassIndex] / sums[accTotalIndex]) * 100).toFixed(1) + '%'
      }
      if (sums[weekPassIndex] === 0 && sums[weekTotalIndex] === 0) {
        sums[weekPassRateIndex] = '0%'
      } else {
        sums[weekPassRateIndex] = ((sums[weekPassIndex] / sums[weekTotalIndex]) * 100).toFixed(1) + '%'
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
        this.params.status = status.toUpperCase()
        this.params.module = row.mdlNm
        this.params.assignee = row.assigned
        this.params.count = cell.innerText

        switch (this.renderParam.type) {
          case 'test':
            this.params.koType = '테스트'
            break
        }

        this.params.testDgr = row.trkNm
        this.params.enType = this.renderParam.type
        this.params.tableType = 'result'
        this.params.accDate = this.$store.state[this.renderParam.type].accDate
        this.params.startDate = this.$store.state[this.renderParam.type].startDate
        this.params.endDate = this.$store.state[this.renderParam.type].endDate
        this.$store.dispatch(this.renderParam.type + '/getResultDetailList', this.params)
        this.$store.commit(this.renderParam.type + '/pushResultTableVisible', true)
      }
    },
    excelDownload () {
      const fix = document.querySelector('#listTable_bottom>.el-table__fixed')
      const workBook = XLSX.utils.book_new()
      let excelData
      if (fix) {
        excelData = XLSX.utils.table_to_sheet(document.querySelector('#listTable_bottom').removeChild(fix))
        document.querySelector('#listTable_bottom').appendChild(fix)
      } else {
        excelData = XLSX.utils.table_to_sheet(document.querySelector('#listTable_bottom'))
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
