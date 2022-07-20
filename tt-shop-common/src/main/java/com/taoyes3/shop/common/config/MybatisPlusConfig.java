package com.taoyes3.shop.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.taoyes3.shop.**.dao"})
public class MybatisPlusConfig {
}
