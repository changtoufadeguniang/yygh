package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.hosp.repository.HospitalRepository;
import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * @author wangjie
 * @Title: HospitalServiceImpl
 * @Description: 上传医院接口实现类
 * @company: 西安石文软件有限公司
 * @date 2021/3/2414:25
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void saveHospital(Map<String, Object> switchMap) {
        //将map转换为json字符传
        String jsonString= JSONObject.toJSONString(switchMap);
        //将json字符串转为对象
        Hospital hospital = JSONObject.parseObject(jsonString, Hospital.class);
        Hospital targetHospital = hospitalRepository.findHospitalByHoscode(hospital.getHoscode());
        if(targetHospital!=null){
            hospital.setStatus(targetHospital.getStatus());
            hospital.setCreateTime(targetHospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else {
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }
}
