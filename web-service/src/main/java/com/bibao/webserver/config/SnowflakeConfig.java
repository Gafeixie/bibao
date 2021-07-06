package com.bibao.webserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.downgoon.snowflake.Snowflake;

/**
 * @author: 谢佳辉
 * @date 2021/5/30 10:22 下午
 */
@Configuration
public class SnowflakeConfig {

    @Bean(name = "snow")
    public Snowflake snowflake() {
        return new Snowflake(1, 2);
    }

}
