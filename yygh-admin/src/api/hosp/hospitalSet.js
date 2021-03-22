import request from '@/utils/request'

const api_name = '/admin/hosp/hospitalSet'

export default {
    getPageList(page, limit, searchObj) {
        return request({
            url: `${api_name}/findPageHospSet/${page}/${limit}`,
            method: 'post',
            data: searchObj
        })
    },
    //删除医院设置
    deleteHospSet(id) {
        return request({
            url: `/admin/hosp/hospitalSet/${id}`,
            method: 'delete'
        })
    },
    //批量删除
    batchRemoveHospSet(idList) {
        return request({
            url: `/admin/hosp/hospitalSet/batchRemove`,
            method: 'delete',
            data: idList
        })
    },
    //锁定和取消锁定
    lockHospSet(id, status) {
        return request({
            url: `/admin/hosp/hospitalSet/lockHospitalSet/${id}/${status}`,
            method: 'put'
        })
    },
    //添加医院设置
    saveHospSet(hospitalSet) {
        return request({
            url: `/admin/hosp/hospitalSet/saveHospitalSet`,
            method: 'post',
            data: hospitalSet
        })
    },
    //院设置id查询
    getHospSet(id) {
        return request({
            url: `/admin/hosp/hospitalSet/getHospSet/${id}`,
            method: 'get'
        })
    },
    //修改医院设置
    updateHospSet(hospitalSet) {
        return request({
            url: `/admin/hosp/hospitalSet/updateHospitalSet`,
            method: 'post',
            data: hospitalSet
        })
    }
}