import request from '@/utils/request'

// 개발진척현황
export function getProgressList (param) {
  return request({
    url: '/test/list/progress',
    method: 'post',
    data: param
  })
}

export function getCompleteChart (param) {
  return request({
    url: '/test/status/complete',
    method: 'post',
    data: param
  })
}

export function getDailyChart (param) {
  return request({
    url: '/test/status/daily',
    method: 'post',
    data: param
  })
}

export function getModuleChart (param) {
  return request({
    url: '/test/status/module',
    method: 'post',
    data: param
  })
}

export function getResultChart (param) {
  return request({
    url: '/test/status/result',
    method: 'POST',
    data: param
  })
}

export function getDetailList (param) {
  return request({
    url: '/test/detail/progress',
    method: 'POST',
    data: param
  })
}

export function getResultList (param) {
  return request({
    url: '/test/list/result',
    method: 'POST',
    data: param
  })
}

export function getResultDetailList (param) {
  return request({
    url: '/test/detail/result',
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
