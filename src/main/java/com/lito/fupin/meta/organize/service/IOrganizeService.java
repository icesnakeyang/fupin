package com.lito.fupin.meta.organize.service;

import com.lito.fupin.meta.organize.entity.Organize;

import java.util.ArrayList;
import java.util.Map;

public interface IOrganizeService {
    void createOrganize(Organize organize) throws Exception;

    /**
     * 根据机构名称模糊查询机构列表，支持分页
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    ArrayList<Organize> listOrganize(String name, Integer pageIndex, Integer pageSize) throws Exception;

    void updateOrganize(Organize organize) throws Exception;

    Organize getOrganizeByName(String organizeName) throws Exception;

    Organize getOrganizeById(String organizeId) throws Exception;

    void deleteOrganize(String organizeId) throws Exception;

    /**
     * 查询指定父分类id的所有子分类
     *
     * @param pid
     * @return
     */
    ArrayList<Organize> listOrganizeByPid(String pid);
}
