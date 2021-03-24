package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @author wangjie
 * @Title: HospitalService
 * @Description: 上传医院的接口
 * @company: 西安石文软件有限公司
 * @date 2021/3/2414:24
 */
public interface HospitalService {

    /**
     * 上传医院
     * @param switchMap
     */
    void saveHospital(Map<String, Object> switchMap);

    /**
     * 查询医院
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);
}
