package com.yxj.service;

import com.yxj.entity.security.Right;

import java.util.List;

/**
 * Created by 95 on 2016/11/13.
 */
public interface BaseService<T> {
    //写操作
    public void saveEntity(T t);
    public void saveOrUpdateEntity(T t);
    public void updateEntity(T t);
    public void deleteEntity(T t);
    public void batchEntityByHQL(String hql, Object... objects);

    //读操作
    public T loadEntity(Integer id);
    public T getEntity(Integer id);
    public List<T> findEntityByHQL(String hql, Object... objects);
    //单值检索，确保查询结果有且只有一条结果
    public Object uniqueResult(String hql, Object...objects);

    //查询所有实体
    List<T> findAllEntities();
    //执行原生的SQL语句
    public void executeSQL(String sql,Object...objects);
    //执行原生的SQL查询
    public List executeSQLQuery(Class clazz,String sql,Object...objects);
}
