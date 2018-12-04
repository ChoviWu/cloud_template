package org.github..auth.mapper;

import org.github..common.model.SysResource;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {


    List<SysResource> getListByRoleId(Integer roleId);

}