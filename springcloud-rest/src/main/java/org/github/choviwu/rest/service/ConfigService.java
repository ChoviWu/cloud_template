package org.github..rest.service;

import org.github..common.constant.FeignConstants;
import org.github..common.model.SysConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = FeignConstants.SYS_SERVICE)
public interface ConfigService {


    @RequestMapping(value = "api/sys/getListForHolder")
    Object getList();

    @RequestMapping(value = "api/sys/getList")
    List<SysConfig> getListForDB();

    @RequestMapping(value = "api/sys/insert")
    Object insert();


}
