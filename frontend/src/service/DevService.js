import request from '@/utils/request'

// 개발진척현황
export function getProgressList (param) {
  return request({
    url: '/dev/list',
    method: 'post',
    data: param
  })
}

export function getCompleteChart (param) {
  return request({
    url: '/dev/status/complete',
    method: 'post',
    data: param
  })
}

export function getDailyChart (param) {
  return request({
    url: '/dev/status/daily',
    method: 'post',
    data: param
  })
}

export function getModuleChart (param) {
  return request({
    url: '/dev/status/module',
    method: 'post',
    data: param
  })
}

export function getAssigneeChart (param) {
  return request({
    url: '/dev/status/assignee',
    method: 'POST',
    data: param
  })
}

export function getDetailList (param) {
  return request({
    url: '/dev/detail',
    method: 'POST',
    data: param
  })
}
