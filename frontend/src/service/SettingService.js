import request from '@/utils/request'

export function getProjectList () {
  return request({
    url: '/setting/projects',
    method: 'POST'
  })
}

export function getSettingList (projectId) {
  return request({
    url: '/setting/projects/' + projectId,
    method: 'GET'
  })
}

export function setSettingList (projectId, gridData) {
  return request({
    url: '/setting/projects/' + projectId,
    method: 'POST',
    data: gridData
  })
}
