package com.lito.fupin.meta.organize.service;

import com.lito.fupin.meta.organize.dao.OrganizeDao;
import com.lito.fupin.meta.organize.entity.Organize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrganizeService implements IOrganizeService {
    private final OrganizeDao organizeDao;

    @Autowired
    public OrganizeService(OrganizeDao organizeDao) {
        this.organizeDao = organizeDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrganize(Organize organize) throws Exception {
        organizeDao.createOrganize(organize);
    }

    /**
     * 根据机构名称模糊查询机构列表，支持分页
     *
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<Organize> listOrganize(String name, Integer pageIndex, Integer pageSize) throws Exception {
        Map qIn = new HashMap();
        qIn.put("name", name);
        Integer indexStart = pageIndex * pageSize;
        qIn.put("offset", indexStart);
        qIn.put("size", pageSize);
        ArrayList<Organize> organizes = organizeDao.listOrganize(qIn);
        return organizes;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrganize(Organize organize) throws Exception {
        organizeDao.updateOrganize(organize);
    }

    @Override
    public Organize getOrganizeByName(String organizeName) {
        Organize organize = organizeDao.getOrganizeByName(organizeName);
        return organize;
    }

    @Override
    public Organize getOrganizeById(String organizeId) throws Exception {
        Organize organize=organizeDao.getOrganizeById(organizeId);
        return organize;
    }

    @Override
    public void deleteOrganize(String organizeId) throws Exception {
        organizeDao.deleteOrganize(organizeId);
    }
}
