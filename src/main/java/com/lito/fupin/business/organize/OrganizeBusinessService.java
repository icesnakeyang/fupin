package com.lito.fupin.business.organize;

import com.lito.fupin.common.GGF;
import com.lito.fupin.meta.organize.entity.Organize;
import com.lito.fupin.meta.organize.service.IOrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrganizeBusinessService implements IOrganizeBusinessService {
    private final IOrganizeService iOrganizeService;

    @Autowired
    public OrganizeBusinessService(IOrganizeService iOrganizeService) {
        this.iOrganizeService = iOrganizeService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map createOrganize(Map in) throws Exception {
        String organizeName = in.get("organizeName").toString();
        String pid = (String) in.get("pid");

        Organize organize = new Organize();
        organize.setOrganizeId(GGF.UUID().toString());
        organize.setOrganizeName(organizeName);
        organize.setPid(pid);
        iOrganizeService.createOrganize(organize);

        Map out = new HashMap();
        out.put("admin/organize", organize);
        return out;
    }

    @Override
    public Map listOrganize() throws Exception {
        ArrayList<Organize> organizes = iOrganizeService.listOrganize();
        Map out = new HashMap();
        out.put("organizeList", organizes);
        return out;
    }
}
