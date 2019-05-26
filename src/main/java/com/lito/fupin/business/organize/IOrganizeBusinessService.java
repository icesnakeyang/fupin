package com.lito.fupin.business.organize;

import java.util.Map;

public interface IOrganizeBusinessService {
    Map createOrganize(Map in) throws Exception;

    /**
     * 根据机构名称模糊查询机构列表，支持分页
     * @param in
     * @return
     * @throws Exception
     */
    Map listOrganize(Map in) throws Exception;

    void updateOrganize(Map in) throws Exception;

    void deleteOrganize(Map in) throws Exception;

    Map listOrganizeByToken(Map in) throws Exception;
}
