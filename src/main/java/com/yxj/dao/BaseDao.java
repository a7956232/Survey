package com.yxj.dao;

import java.util.List;

/**
 * Created by 95 on 2016/11/21.
 */
public interface BaseDao<T> {
    //写操作
    public void saveEntity(T t);
    public void saveOrUpdateEntity(T t);
    public void updateEntity(T t);
    public void deleteEntity(T t);
    public void batchEntityByHQL(String hql,Object...objects);

    //读操作
    public T loadEntity(Integer id);
    public T getEntity(Integer id);
    public List<T> findEntityByHQL(String hql, Object...objects);
    //单值检索，确保查询结果有且只有一条结果
    public Object uniqueResult(String hql, Object...objects);
    //执行原生的SQL语句
    public void executeSQL(String sql,Object...objects);
    //执行原生的SQL查询(可以指定是否封装成实体)
    public List executeSQLQuery(Class clazz,String sql,Object...objects);
    //
}
