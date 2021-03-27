package com.atguigu.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.yygh.cmn.listener.DictListener;
import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.vo.cmn.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 组织架构表 服务实现类
 * </p>
 *
 * @author wangjie
 * @since 2021-03-23
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Cacheable(value = "dict",keyGenerator = "myKeyGenerator")
    @Override
    public List<Dict> findChlidData(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        List<Dict> dicts = baseMapper.selectList(queryWrapper);
        dicts.forEach(dict -> {
            //判断是否有子节点
            Long dictId = dict.getId();
            boolean isChlid = this.hasNotChlidNode(dictId);
            dict.setHasChildren(isChlid);
        });
        return dicts;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("数据字典", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            List<Dict> dictList = baseMapper.selectList(null);
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
            for(Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictVo);
                dictVoList.add(dictVo);
            }
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CacheEvict(value = "dict", allEntries=true)
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getNameByParentDictCodeAndValue(String parentDictCode, String value) {
        if(StringUtils.isEmpty(parentDictCode)){
            Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>().eq("value", value));
            if(dict!=null){
                return dict.getName();
            }
        }else {
            Dict parentDict = this.getByDictsCode(parentDictCode);
            if(null == parentDict) return "";
            Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>().eq("parent_id", parentDict.getId()).eq("value", value));
            if(null != dict) {
                return dict.getName();
            }
        }
        return "";

    }

    @Override
    public List<Dict> findByDictCode(String dictCode) {
        Dict codeDict = this.getByDictsCode(dictCode);
        if(null == codeDict) return null;
        return this.findChlidData(codeDict.getId());
    }

    private Dict getByDictsCode(String parentDictCode) {
        return baseMapper.selectOne(new QueryWrapper<Dict>().eq("dict_code", parentDictCode));
    }


    /**
     * 判断是否有子数据
     * @param id
     * @return
     */
    private boolean hasNotChlidNode(Long id){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer integer = baseMapper.selectCount(queryWrapper);
        return integer>0;
    }
}
