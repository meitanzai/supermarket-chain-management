package com.cqupt.th.supermarket.utils;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.th.supermarket.entity.Permission;
import com.cqupt.th.supermarket.vo.PermissionVo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建登录用户左侧菜单数据
 * </p>
 *
 * @author qy
 * @since 2019-11-11
 */
public class MemuHelper {

    /**
     * 构建菜单
     *
     * @param treeNodes
     * @return
     */
    public static List<JSONObject> bulid(List<PermissionVo> treeNodes) {
        List<JSONObject> menus = new ArrayList<>();
        for (PermissionVo one : treeNodes) {
            JSONObject oneMeun = new JSONObject();
            oneMeun.put("path", one.getPath());
            oneMeun.put("component", one.getComponent());
            if (one.getChildren() != null && one.getChildren().size() > 0) {
                oneMeun.put("redirect", one.getPath() + "/" + one.getChildren().get(0).getPath());
            }
            oneMeun.put("name", one.getName());
            oneMeun.put("hidden", false);
            JSONObject oneMeta = new JSONObject();
            oneMeta.put("title", one.getName());
            oneMeta.put("icon", one.getIcon());
            oneMeun.put("meta", oneMeta);
            ArrayList<JSONObject> twoMenus = new ArrayList<>();
            for (PermissionVo two : one.getChildren()) {
                JSONObject twoMeun = new JSONObject();
                twoMeun.put("path", two.getPath());
                twoMeun.put("component", two.getComponent());
                twoMeun.put("name", two.getName());
                twoMeun.put("hidden", false);
                JSONObject twoMeta = new JSONObject();
                twoMeta.put("title", two.getName());
                twoMeta.put("icon", two.getIcon());
                twoMeun.put("meta", twoMeta);
                twoMenus.add(twoMeun);
            }
            oneMeun.put("children", twoMenus);
            menus.add(oneMeun);
        }
        return menus;
    }
}
