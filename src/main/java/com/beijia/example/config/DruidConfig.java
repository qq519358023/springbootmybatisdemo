package com.beijia.example.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.alibaba.druid.filter.config.ConfigTools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
//    引入配置文件中数据库url
    @Value("${spring.datasource.url}")
    private String dbUrl;
//    引入数据库账号
    @Value("${spring.datasource.username}")
    private String username;
//    引入数据库密码
    @Value("${spring.datasource.password}")
    private String password;
    //    引入数据库密码钥匙
    @Value("${spring.datasource.publicKey}")
    private String publicKey;
//    引入数据库驱动
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
//    引入初始化时建立物理连接的个数
    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;
//    最小连接池数量
    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;
//    最大连接池数量
    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;
//    获取连接时最大等待时间，单位毫秒
    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;
//    申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
//    配置一个连接在池中最小生存的时间，单位是毫秒
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
//  用来检测连接是否有效的sql 必须是一个查询语句
    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;
//    申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;
//    申请连接时会执行validationQuery检测连接是否有效,开启会降低性能,默认为true
    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;
//    归还连接时会执行validationQuery检测连接是否有效,开启会降低性能,默认为true
    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;
//    是否缓存preparedStatement,mysql5.5+建议开启
    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
//    当值大于0时poolPreparedStatements会自动修改为true
    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
//    配置扩展插件     配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    @Value("${spring.datasource.druid.filters}")
    private String filters;
//    通过connectProperties属性来打开mergeSql功能；慢SQL记录
    @Value("${spring.datasource.druid.connectionProperties}")
    private String connectionProperties;
//    合并多个DruidDataSource的监控数据
    @Value("${spring.datasource.druid.useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;
//    Druid监控账号
    @Value("${spring.datasource.druid.stat-view-servlet.login-username}")
    private String loginUsername;
//    Druid监控密码
    @Value("${spring.datasource.druid.stat-view-servlet.login-password}")
    private String loginPassword;

    /**
     * 配置Druid Servlet
     * @return reg
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", loginUsername);
        reg.addInitParameter("loginPassword", loginPassword);
//        禁用HTML页面上的“Reset All”功能
        reg.addInitParameter("resetEnable", "false");
        return reg;
    }

    /**
     * 配置Druid Filter
     * @return filterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
//        忽略资源
        filterRegistrationBean.addInitParameter("exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() throws Exception {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(ConfigTools.decrypt(publicKey, password));
        datasource.setDriverClassName(driverClassName);
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: "+ e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }
}
