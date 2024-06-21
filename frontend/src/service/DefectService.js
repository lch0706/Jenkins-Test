import request from '@/utils/request'

export function getDefectList (param) {
  return request({
    url: '/defect/list',
    method: 'post',
    data: param
  })
}

export function getCompleteChart (param) {
  return request({
    url: '/defect/status/complete',
    method: 'post',
    data: param
  })
}

export function getDailyChart (param) {
  return request({
    url: '/defect/status/daily',
    method: 'post',
    data: param
  })
}

export function getModuleChart (param) {
  return request({
    url: '/defect/status/module',
    method: 'post',
    data: param
  })
}

export function getDetailList (param) {
  return request({
    url: '/defect/detail',
    method: 'POST',
    data: param
  })
}

export function getTestDegree (param) {
  return request({
    url: '/common/degree',
    method: 'POST',
    data: param
  })
}

export function getAssigneeChart (param) {
  return request({
    url: '/defect/status/assignee',
    method: 'POST',
    data: param
  })
}
