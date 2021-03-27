package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.vo.hosp.DepartmentQueryVo;
import com.atguigu.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Map;

/**
 * @author wangjie
 * @Title: DepartmentService
 * @Description: 医院科室接口
 * @company: 西安石文软件有限公司
 * @date 2021/3/2416:25
 */

public interface DepartmentService {
    /**
     * 上传科室信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    Page<Department> selectPage(int page, int limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);

    List<DepartmentVo> getDepartmentList(String hospCode);

    //根据科室编号，和医院编号，查询科室名称
    String getDepName(String hoscode, String depcode);
}
