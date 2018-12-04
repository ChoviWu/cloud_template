package org.github..core.conf.properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.proxy.DruidDriver;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceProperties;
import com.alibaba.druid.spring.boot.autoconfigure.DruidStatProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.github..core.conf.MyDruidDataSource;

import java.sql.SQLException;

/**
 * <p>数据库数据源配置</p>
 * <p>说明:这个类中包含了许多默认配置,若这些配置符合您的情况,您可以不用管,若不符合,建议不要修改本类,建议直接在"application.yml"中配置即可</p>
 */
@Data
@EqualsAndHashCode
public class DruidProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private Integer initialSize = 2;

    private Integer minIdle = 1;

    private Integer maxActive = 20;

    private Integer maxWait = 60000;

    private Integer timeBetweenEvictionRunsMillis = 60000;

    private Integer minEvictableIdleTimeMillis = 300000;

    private String validationQuery = "SELECT 'x'";

    private Boolean testWhileIdle = true;

    private Boolean testOnBorrow = false;

    private Boolean testOnReturn = false;

    private Boolean poolPreparedStatements = true;

    private Integer maxPoolPreparedStatementPerConnectionSize = 20;

    private String filters = "stat";

    private Integer validationQueryTimeout = 30000;



    public void config(MyDruidDataSource dataSource) {

        DruidStatProperties druidStatProperties = new DruidStatProperties();
        //druid stat filter
        DruidStatProperties.WebStatFilter statFilter = new DruidStatProperties.WebStatFilter();
        statFilter.setEnabled(true);
        statFilter.setUrlPattern("/*");
        statFilter.setPrincipalSessionName("*.js,*.css,*.font,*.html,*.ico,/druid/*,*.jpeg,*.jpg,*.png,*.gif");
        statFilter.setSessionStatEnable("");
        statFilter.setSessionStatMaxCount("10");
        druidStatProperties.setWebStatFilter(statFilter);
        DruidStatProperties.StatViewServlet statViewServlet = new DruidStatProperties.StatViewServlet();
        statViewServlet.setResetEnable("true");
        statViewServlet.setUrlPattern("/druid/*");
        statViewServlet.setEnabled(true);
        statViewServlet.setLoginPassword("localhost");
        statViewServlet.setLoginUsername("root");
        statViewServlet.setDeny("192.168.2.141");//黑名单
        statViewServlet.setAllow("127.0.0.1");
        druidStatProperties.setStatViewServlet(statViewServlet);
        dataSource.setUrl(url);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDruidStatProperties(druidStatProperties);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setInitialSize(initialSize);     //定义初始连接数
        dataSource.setMinIdle(minIdle);             //最小空闲
        dataSource.setMaxActive(maxActive);         //定义最大连接数
        dataSource.setMaxWait(maxWait);             //最长等待时间

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);

        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
