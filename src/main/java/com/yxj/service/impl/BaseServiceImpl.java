package com.yxj.service.impl;

import com.yxj.dao.BaseDao;
import com.yxj.service.BaseService;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by 95 on 2016/11/13.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> dao;

    private Class<T> clazz;

    public BaseServiceImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Resource
    public void setDao(BaseDao<T> dao) {
        this.dao = dao;
    }

    @Override
    public void saveEntity(T t) {
        dao.saveEntity(t);
    }

    @Override
    public void saveOrUpdateEntity(T t) {
        dao.saveOrUpdateEntity(t);
    }

    @Override
    public void updateEntity(T t) {
        dao.updateEntity(t);
    }

    @Override
    public void deleteEntity(T t) {
        dao.deleteEntity(t);
    }

    @Override
    public void batchEntityByHQL(String hql, Object... objects) {
        dao.batchEntityByHQL(hql,objects);
    }

    @Override
    public T loadEntity(Integer id) {
        return dao.loadEntity(id);
    }

    @Override
    public T getEntity(Integer id) {
        return dao.getEntity(id);
    }

    @Override
    public List<T> findEntityByHQL(String hql, Object... objects) {
        return dao.findEntityByHQL(hql,objects);
    }

    @Override
    public Object uniqueResult(String hql, Object... objects) {
        return dao.uniqueResult(hql,objects);
    }

    @Override
    public List<T> findAllEntities() {
        String hql = "from "+clazz.getSimpleName();
        return this.findEntityByHQL(hql);
    }
}
