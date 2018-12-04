package org.github..auth.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.github..common.model.BasUser;
import org.github..common.model.BasUserT;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2018-09-03
 */
public interface BasUserTMapper extends BaseMapper<BasUserT> {
    /**
     * get List
     *
     * @param map
     * @return
     */
    List<BasUser> getList(Page<BasUser> page, Map map);

    /**
     * get user
     *
     * @param userName
     * @return
     */
    BasUser getOneByUserName(String userName);

    /**
     * check user login status
     *
     * @param userName
     * @param password
     * @return
     */
    BasUser getOneByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
