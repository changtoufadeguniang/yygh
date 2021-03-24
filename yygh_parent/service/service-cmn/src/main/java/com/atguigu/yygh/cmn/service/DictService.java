package com.atguigu.yygh.cmn.service;


import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 组织架构表 服务类
 * </p>
 *
 * @author wangjie
 * @since 2021-03-23
 */
public interface DictService extends IService<Dict> {
    //根据数据id查询子数据列表
    List<Dict> findChlidData(Long id);

    /**
     * 导出excel
     * @param response
     */
    void exportData(HttpServletResponse response);

    /**
     * 导入数据
     * @param file
     */
    void importData(MultipartFile file);
}
