package com.wpc.common;

import java.io.Serializable;
import java.util.List;

import com.wpc.common.datatables.DataTablesRequest;

/**
 * Created by 
 */
public interface BaseDao<T extends Serializable,PK extends Serializable> {

    void save(T t);

    void delete(PK id);

    void deleteByIds(PK[] ids);

    void update(T t);

    T findById(PK id);

    List<T> queryAll();
    
    List<T> search(T query);
    
    List<T> query(T query);
    
    List<T> searchPage(DataTablesRequest query);
	
	Integer countPage(DataTablesRequest query);

	Integer count();

	Integer count(T t);

}
