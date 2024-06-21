<template>
  <div>
    <search-box :render-param="renderParam"></search-box>
    <article class="listBoard">
      <div class="tbTit">
        <h4>요구사항추적매트릭스 / 전체 {{ paginatedLength }} 건</h4>
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
          style="width: 100%">
          <el-table-column
            prop="no"
            label="no">
          </el-table-column>
          <el-table-column
            prop="requirement"
            label="요구사항">
          </el-table-column>
          <el-table-column
            prop="program"
            label="프로그램">
          </el-table-column>
          <el-table-column
            prop="testcase"
            label="테스트케이스">
          </el-table-column>
          <el-table-column
            prop="defect"
            label="결함">
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
    </article>
  </div>
</template>

<script>
import XLSX from 'xlsx'
import SearchBox from '@/components/SearchBox'

export default {
  data () {
    return {
      renderParam: {
        accDate: false,
        weekDate: false,
        type: 'matrix',
        searchBar: true
      },
      currentPage: 1,
      pageSize: 5,
      showSum: true,
      params: {}
    }
  },
  components: {
    SearchBox
  },
  computed: {
    tableList () {
      return this.$store.state[this.renderParam.type].matrixList
    },
    paginatedData () {
      const start = (this.currentPage - 1) * this.pageSize
      const end = start + this.pageSize
      return this.tableList.slice(start, end)
    },
    paginatedLength () {
      return this.$store.state[this.renderParam.type].matrixList.length
    }
  },
  methods: {
    handleSizeChange (val) {
      this.pageSize = val
      this.currentPage = 1
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
  }
}
</script>

<style>

</style>
