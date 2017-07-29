package com.wpc.common.base.service.impl;

import java.io.Serializable;
import java.util.List;

import com.wpc.common.base.dao.BaseDao;
import com.wpc.common.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpc.common.datatables.DataTablesRequest;
import com.wpc.common.datatables.DataTablesResponse;

public abstract class BaseServiceImpl<T extends Serializable,PK extends Serializable> implements BaseService<T,PK>{
    
    @Autowired
    protected BaseDao<T, PK> baseDao;

    @Override
    public void save(T t) {
        this.baseDao.save(t);
    }

    @Override
    public void delete(PK id) {
    	this.baseDao.delete(id);
    }
    
    @Override
    public void deleteByIds(PK[] ids) {
    	this.baseDao.deleteByIds(ids);
    }

    @Override
    public void update(T t) {
        this.baseDao.update(t);
    }

    @Override
    public T findById(PK id) {
        return baseDao.findById(id);
    }

    @Override
    public List<T> queryAll() {
        return this.baseDao.search(null);
    }
    
    @Override
    public List<T> search(T query) {
        return this.baseDao.search(query);
    }
    
    @Override
    public List<T> query(T query) {
        return this.baseDao.query(query);
    }
    
    @Override
	public DataTablesResponse<T> searchPage(DataTablesRequest query) {
		// TODO Auto-generated method stub
		query.setOrder();
		DataTablesResponse<T> dtr = new DataTablesResponse<T>();
		dtr.setDraw(query.getDraw());
		dtr.setData(this.baseDao.searchPage(query));
		dtr.setRecordsFiltered(this.baseDao.countPage(query));
		query.setCondition(null);
		dtr.setRecordsTotal(this.baseDao.countPage(query));
		return dtr;
	}

    @Override
    public Integer count() {
        return this.baseDao.count();
    }

    @Override
    public Integer count(T t) {
        return this.baseDao.count(t);
    }

}
