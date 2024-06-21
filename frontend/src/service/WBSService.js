import request from '@/utils/request'

export function getWBSList (param) {
  return request({
    url: '/wbs',
    method: 'post',
    data: param
  })
}
