package org.github..api.sys;

import org.github..common.model.SysConfig;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/sys")
public interface SysConfigService {

    @RequestMapping(value = "getListForHolder")
    Object getList();

    @RequestMapping(value = "getList")
    List<SysConfig> getListForDB();

    @RequestMapping(value = "insert")
    Object insert();
}
