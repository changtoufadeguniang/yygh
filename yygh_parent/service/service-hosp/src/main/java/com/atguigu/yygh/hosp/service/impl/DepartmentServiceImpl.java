package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.common.exception.YyghException;
import com.atguigu.yygh.common.helper.HttpRequestHelper;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.result.ResultCodeEnum;
import com.atguigu.yygh.hosp.repository.DepartmentRepository;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.atguigu.yygh.model.hosp.Department;
import com.atguigu.yygh.vo.hosp.DepartmentQueryVo;
import com.atguigu.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangjie
 * @Title: DepartmentServiceImpl
 * @Description: 上传医院科室实现类
 * @company: 西安石文软件有限公司
 * @date 2021/3/2416:26
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        Department department = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Department.class);
        Department targetDepartment = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getDepcode());
        if (null != targetDepartment) {
            //copy不为null的值，该方法为自定义方法
            BeanUtils.copyProperties(department, targetDepartment);
            departmentRepository.save(targetDepartment);
        } else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override
    public Page<Department> selectPage(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);
        department.setIsDeleted(0);
        //创建匹配器
        ExampleMatcher matcher = ExampleMatcher.matching();
        //改变默认字符匹配方式模糊查询
        matcher.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        Example<Department> example = Example.of(department, matcher);
        return departmentRepository.findAll(example, pageable);
    }

    @Override
    public void remove(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (null != department) {
            departmentRepository.deleteById(department.getId());
        }
    }

    /**
     * 根据医院编号获取科室树形结构数据
     *
     * @param hospCode
     * @return
     */
    @Override
    public List<DepartmentVo> getDepartmentList(String hospCode) {
        List<DepartmentVo> list = new ArrayList<>();//用于封装所有层级数据
        List<Department> departmentList = departmentRepository.getDepartmentListByHoscode(hospCode);
        //根据bigcode给数据分个组显示
        Map<String, List<Department>> listMap = departmentList.stream().collect(Collectors.groupingBy(Department::getBigcode));
        for (Map.Entry<String, List<Department>> entry : listMap.entrySet()) {
            //大科室的编号
            String bigCode = entry.getKey();
            //大科室的小科室集合
            List<Department> departments = entry.getValue();
            //封装大科室信息
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setDepcode(bigCode);
            departmentVo.setDepname(departments.get(0).getBigname());
            //封装小科室信息
            List<DepartmentVo> children = new ArrayList<>();
            for (Department department : departments) {
                DepartmentVo departmentVo01 = new DepartmentVo();
                departmentVo01.setDepname(department.getDepname());
                departmentVo01.setDepcode(department.getDepcode());
                children.add(departmentVo01);
            }
            departmentVo.setChildren(children);
            list.add(departmentVo);
        }
        return list;
    }

    @Override
    public String getDepName(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(department != null) {
            return department.getDepname();
        }
        return null;
    }


}
