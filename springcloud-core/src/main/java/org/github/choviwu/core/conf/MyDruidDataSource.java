package org.github..core.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidStatProperties;
import lombok.Data;

/**
 * @author
 * @date 2018/8/29
 * Description :
 */
@Data
public class MyDruidDataSource extends DruidDataSource {

    private DruidStatProperties druidStatProperties;

}
