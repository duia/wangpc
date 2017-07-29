package com.wpc.common.base.service;

import java.io.Serializable;
import java.util.List;

import com.wpc.common.datatables.DataTablesRequest;
import com.wpc.common.datatables.DataTablesResponse;

/**
 * Created by 
 */
public interface BaseService<T extends Serializable, PK extends Serializable> {

    void save(T t);

    void delete(PK id);

    void deleteByIds(PK[] ids);

    void update(T t);

    T findById(PK id);

    List<T> queryAll();
    
    List<T> search(T query);
    
    List<T> query(T query);
    
    DataTablesResponse<T> searchPage(DataTablesRequest query);

    Integer count();

    Integer count(T t);

}
