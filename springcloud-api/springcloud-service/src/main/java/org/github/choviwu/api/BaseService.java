package org.github..api;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @date 2018/8/13
 * Description :
 */
@RequestMapping(value = "{module}")
public interface BaseService<T> {
    @RequestMapping(value = "selectByKey/{key}")
    T selectByKey(@PathVariable("key") Object key);

    @RequestMapping(value = "selectByKey")
    T selectOne(T entity);

    @RequestMapping(value = "save")
    int save(T entity);

    @RequestMapping(value = "delete/{key}")
    int delete(@PathVariable("key") Object key);

    @RequestMapping(value = "updateAll")
    int updateAll(T entity);

    @RequestMapping(value = "updateNotNull")
    int updateNotNull(T entity);

    @RequestMapping(value = "selectPage")
    List<T> selectPage(int pageNum, int pageSize);

    @RequestMapping(value = "selectPage")
    List<T> selectPage(int pageNum, int pageSize, T entity);
}
