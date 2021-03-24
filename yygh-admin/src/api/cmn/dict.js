import request from '@/utils/request'

const api_name = '/admin/cmn/dict'

export default {
      dictList(id) {//数据字典列表
        return request ({
          url: `${api_name}/findChildData/${id}`,
          method: 'get'
        })
      },
      
      exportData(){
          window.location.href= process.env.BASE_API+'/admin/cmn/dict/exportData'
      }

      
    }