import request from '@/utils/request'

export function getMatrixList (param) {
  return request({
    url: '/matrix/list',
    method: 'post',
    data: param
  })
}
