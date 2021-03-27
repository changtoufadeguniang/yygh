package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hosp.service.DepartmentService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjie
 * @Title: DepartmentController
 * @Description: 科室controller层
 * @company: 西安石文软件有限公司
 * @date 2021/3/2515:33
 */
@Api(tags = "科室信息")
@RestController
@RequestMapping("/admin/hosp/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "根据医院编号获取科室信息")
    @GetMapping("/list/{hospCode}")
    public Result  getDepartment(@PathVariable String hospCode){
        return Result.ok(departmentService.getDepartmentList(hospCode));
    }

}
