package com.lito.fupin.business.organize;

import com.lito.fupin.common.GGF;
import com.lito.fupin.meta.organize.entity.Organize;
import com.lito.fupin.meta.organize.service.IOrganizeService;
import com.lito.fupin.meta.user.entity.User;
import com.lito.fupin.meta.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrganizeBusinessService implements IOrganizeBusinessService {
    private final IOrganizeService iOrganizeService;
    private final IUserService iUserService;

    @Autowired
    public OrganizeBusinessService(IOrganizeService iOrganizeService,
                                   IUserService iUserService) {
        this.iOrganizeService = iOrganizeService;
        this.iUserService = iUserService;
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

    /**
     * 根据机构名称模糊查询机构列表，支持分页
     *
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public Map listOrganize(Map in) throws Exception {
        String token = (String) in.get("token");
        String organizeName = (String) in.get("organizeName");
        Integer pageIndex = (Integer) in.get("pageIndex");
        Integer pageSize = (Integer) in.get("pageSize");

        ArrayList<Organize> organizes = null;
        if (organizeName != null) {
            organizes = iOrganizeService.listOrganize(organizeName, pageIndex, pageSize);
        } else {
            User user = iUserService.getUserByToken(token);
            Organize organize = iOrganizeService.getOrganizeById(user.getOrganizeId());
            organizes = iOrganizeService.listOrganize(organize.getOrganizeName(), pageIndex, pageSize);
        }
        Map out = new HashMap();
        out.put("organizeList", organizes);
        return out;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrganize(Map in) throws Exception {
        String organizeId = in.get("organizeId").toString();
        String organizeName = (String) in.get("organizeName");
        String pOrgName = (String) in.get("pOrgName");

        Organize organize = iOrganizeService.getOrganizeById(organizeId);
        int pp = 0;
        if (!organize.getOrganizeName().equals(organizeName)) {
            organize.setOrganizeName(organizeName);
            pp++;
        }
        if (pOrgName != null) {
            Organize pOrganize = iOrganizeService.getOrganizeByName(pOrgName);
            organize.setPid(pOrganize.getOrganizeId());
            pp++;
        }
        if (pp > 0) {
            iOrganizeService.updateOrganize(organize);
        }
    }

    @Override
    public void deleteOrganize(Map in) throws Exception {
        String organizeId = in.get("organizeId").toString();
        iOrganizeService.deleteOrganize(organizeId);
    }

    @Override
    public Map listOrganizeByToken(Map in) throws Exception {
        String token = in.get("token").toString();

        User user = iUserService.getUserByToken(token);

        if (user == null) {
            throw new Exception("10003");
        }

        Organize organize = iOrganizeService.getOrganizeById(user.getOrganizeId());

        ArrayList<Organize> organizeList=listSubOrganize(organize);

        Map out = new HashMap();
        out.put("organizeList", organizeList);

        return out;
    }

    private ArrayList<Organize> listSubOrganize(Organize organize) throws Exception {
        ArrayList<Organize> organizes = iOrganizeService.listOrganizeByPid(organize.getOrganizeId());
        ArrayList<Organize> outOrganizeList = new ArrayList<>();
        outOrganizeList.addAll(organizes);
        for (int i = 0; i < organizes.size(); i++) {
            ArrayList list = listSubOrganize(organizes.get(i));
            outOrganizeList.addAll(list);
        }
        return outOrganizeList;
    }
}
