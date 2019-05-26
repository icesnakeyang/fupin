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
        String token = in.get("token").toString();
        String organizeName = in.get("organizeName").toString();
        String pid = (String) in.get("pid");

        User user = iUserService.getUserByToken(token);
        if (user == null) {
            throw new Exception("10003");
        }

        Organize organize = new Organize();
        organize.setOrganizeId(GGF.UUID().toString());
        organize.setOrganizeName(organizeName);
        if (pid == null) {
            /**
             * 如果pid为空，则赋值为当前用户的organizeid
             */
            pid = user.getOrganizeId();
        } else {
            /**
             * 检查pid是否存在
             */
            Organize porganize = iOrganizeService.getOrganizeById(pid);
            if (porganize == null) {
                throw new Exception("10004");
            }
        }
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
        String token=in.get("token").toString();
        String organizeId = in.get("organizeId").toString();
        String organizeName = (String) in.get("organizeName");
        String pOrgName = (String) in.get("pOrgName");
        String pid=(String)in.get("pid");

        /**
         * 检查用户登录
         */
        User user=iUserService.getUserByToken(token);
        if(user==null){
            throw new Exception("10003");
        }

        /**
         * 读取要修改的机构
         */
        Organize organize = iOrganizeService.getOrganizeById(organizeId);
        int pp = 0;
        if (!organize.getOrganizeName().equals(organizeName)) {
            //当前机构名称与要修改的名称不一致，修改
            organize.setOrganizeName(organizeName);
            pp++;
        }
        if (pOrgName != null) {
            //检查上级机构是否存在
            if(!organize.getPname().equals(pOrgName)) {
                Organize pOrganize = iOrganizeService.getOrganizeByName(pOrgName);
                organize.setPid(pOrganize.getOrganizeId());
                pp++;
            }
        }else{
            if(pid!=null){
                //检查上级机构
                if(!organize.getPid().equals(pid)) {
                    Organize pOrganize = iOrganizeService.getOrganizeById(pid);
                    organize.setPid(pid);
                    pp++;
                }
            }
        }

        //当pp>0，则存在可修改的行为
        if (pp > 0) {
            iOrganizeService.updateOrganize(organize);
        }
    }

    @Override
    public void deleteOrganize(Map in) throws Exception {
        String token=in.get("token").toString();
        String organizeId = in.get("organizeId").toString();

        User user=iUserService.getUserByToken(token);
        if(user==null){
            throw new Exception("10003");
        }

        Organize organize=iOrganizeService.getOrganizeById(organizeId);
        if(organize==null){
            throw new Exception("10004");
        }

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

        ArrayList<Organize> organizeList = new ArrayList<>();
        organizeList.add(organize);
        organizeList.addAll(listSubOrganize(organize));

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
