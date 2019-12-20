package com.java.tian.utils;


import com.java.tian.model.TMenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 权限数据处理
 *
 * @author ruoyi
 */
public class TreeUtils {
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<TMenu> getChildPerms(List<TMenu> list, int parentId) {
        List<TMenu> returnList = new ArrayList<TMenu>();
        for (Iterator<TMenu> iterator = list.iterator(); iterator.hasNext(); ) {
            TMenu t = (TMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static void recursionFn(List<TMenu> list, TMenu t) {
        // 得到子节点列表
        List<TMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (TMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<TMenu> it = childList.iterator();
                while (it.hasNext()) {
                    TMenu n = (TMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<TMenu> getChildList(List<TMenu> list, TMenu t) {

        List<TMenu> tlist = new ArrayList<TMenu>();
        Iterator<TMenu> it = list.iterator();
        while (it.hasNext()) {
            TMenu n = (TMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    List<TMenu> returnList = new ArrayList<TMenu>();

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list   分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<TMenu> getChildPerms(List<TMenu> list, int typeId, String prefix) {
        if (list == null) {
            return null;
        }
        for (Iterator<TMenu> iterator = list.iterator(); iterator.hasNext(); ) {
            TMenu node = (TMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId) {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<TMenu> list, TMenu node, String p) {
        // 得到子节点列表
        List<TMenu> childList = getChildList(list, node);
        if (hasChild(list, node)) {
            // 判断是否有子节点
            returnList.add(node);
            Iterator<TMenu> it = childList.iterator();
            while (it.hasNext()) {
                TMenu n = (TMenu) it.next();
                n.setMenuName(p + n.getMenuName());
                recursionFn(list, n, p + p);
            }
        } else {
            returnList.add(node);
        }
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<TMenu> list, TMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
