package com.lito.fupin.meta.organize.dao;

import com.lito.fupin.meta.organize.entity.Organize;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface OrganizeDao {
    void createOrganize(Organize organize);

    /**
     * 根据机构名称模糊查询机构列表，支持分页
     * @param qIn
     * @return
     */
    ArrayList<Organize> listOrganize(Map qIn);

    void updateOrganize(Organize organize);

    Organize getOrganizeByName(String organizeName);

    Organize getOrganizeById(String organizeId);

    void deleteOrganize(String organizeId);
}
