package org.github..sys.service.impl;

import org.github..api.sys.SysConfigService;
import org.github..common.base.GlobalConfig;
import org.github..common.model.SysConfig;
import org.github..core.annotation.Permission;
import org.github..sys.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class SysConfigServiceImpl implements SysConfigService {

    private final SysConfigMapper configMapper;

    @Autowired
    SysConfigServiceImpl(SysConfigMapper configMapper){
        this.configMapper = configMapper;
    }

    @Override
    @Permission
    public Object  getList() {
        return GlobalConfig.Global;
    }

    @Override
    public List<SysConfig> getListForDB() {
        return configMapper.selectAll();
    }

    @Override
    public Object insert() {
        SysConfig config = new SysConfig();
        config.setParam("test");
        config.setAddtime(new Date());
        config.setValue("test");
        configMapper.insert(config);
        return 1;
    }
}
