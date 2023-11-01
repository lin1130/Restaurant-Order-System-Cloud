import request from '@/utils/request'

// 查询details列表
export function listDetails(query) {
  return request({
    url: '/details/details/list',
    method: 'get',
    params: query
  })
}

// 查询details详细
export function getDetails(detailId) {
  return request({
    url: '/details/details/' + detailId,
    method: 'get'
  })
}

// 新增details
export function addDetails(data) {
  return request({
    url: '/details/details',
    method: 'post',
    data: data
  })
}

// 修改details
export function updateDetails(data) {
  return request({
    url: '/details/details',
    method: 'put',
    data: data
  })
}

// 删除details
export function delDetails(detailId) {
  return request({
    url: '/details/details/' + detailId,
    method: 'delete'
  })
}
