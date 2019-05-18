package com.lito.fupin.meta.organize.dao;

import com.lito.fupin.meta.organize.entity.Organize;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface OrganizeDao {
    void createOrganize(Organize organize);

    ArrayList<Organize> listOrganize();

}
