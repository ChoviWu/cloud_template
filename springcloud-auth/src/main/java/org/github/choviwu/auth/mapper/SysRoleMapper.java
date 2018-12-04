package org.github..auth.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.github..common.model.SysRole;
import tk.mybatis.mapper.common.Mapper;

public interface SysRoleMapper extends BaseMapper<SysRole> {


    SysRole getOneByUserId(Integer userId);
}