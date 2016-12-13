package com.yxj.service;

import com.yxj.entity.security.Right;

import java.util.List;

/**
 * Created by 95 on 2016/12/1.
 */
public interface RightService extends BaseService<Right> {
    //保存或更新权限
    void saveOrUpdateRight(Right model);

    //按照url追加权限
    void appendRightByUrl(String url);

    //批量修改权限
    void batchUpdateRights(List<Right> allRights);

    //查找id在指定范围内的权限
    List<Right> findRightsInRange(Integer[] ids);

    //查询最大权限位
    int getMaxRightPos();
}
