package DAO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zky on 2017/1/9.
 */
public interface BaseDAO<T> {

    T get(Class<T> entityClazz,Serializable id);

    Serializable save(T entity);

    void update(T entity);

    void delete(T entity);

    void delete(Class<T> entityClazz,Serializable id);

    List<T> findAll(Class<T> entityClazz);

    long findCount(Class<T> entityClazz);

}
