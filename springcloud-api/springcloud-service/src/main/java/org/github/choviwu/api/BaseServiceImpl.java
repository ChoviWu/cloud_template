package org.github..api;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by fuxx on 15/10/9.
 */
@RestController
public  abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
    
    @Override
    public T selectOne(T entity){
    	return mapper.selectOne(entity);
    }

    @Transactional
    @Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Transactional
    @Override
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }
    @Transactional
    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }
    @Transactional
    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }

    @Override
    public List<T> selectPage(int pageNum, int pageSize, T entity) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.select(entity);
    }

}
