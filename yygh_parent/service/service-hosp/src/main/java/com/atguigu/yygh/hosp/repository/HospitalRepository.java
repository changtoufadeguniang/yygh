package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangjie
 * @Title: HospitalRepository
 * @Description: 上传医院数据到mongodb的数据层接口
 * @company: 西安石文软件有限公司
 * @date 2021/3/2414:07
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

    Hospital findHospitalByHoscode(String hoscode);

    Hospital getHospitalByHoscode(String hoscode);
}
