package com.wpc.common.base.service;

import java.util.List;

import com.wpc.common.base.entity.DataEntity;
import com.wpc.common.datatables.DataTablesRequest;
import com.wpc.common.datatables.DataTablesResponse;

/**
 * Created by 
 */
public interface BaseService<T extends DataEntity<T>> {

    void save(T t);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    void update(T t);

    T findById(Long id);

    List<T> queryAll();
    
    List<T> search(T query);
    
    List<T> query(T query);
    
    DataTablesResponse<T> searchPage(DataTablesRequest query);

    Integer count();

    Integer count(T t);

}
