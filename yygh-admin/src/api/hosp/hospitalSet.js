import request from '@/utils/request'

const api_name = '/admin/hosp/hospitalSet'

export default {

    getPageList(page, limit, searchObj) {
        return request({
            url: `${api_name}/findPageHospSet/${page}/${limit}`,
            method: 'post',
            params: searchObj
        })
    }
}