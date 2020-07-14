package com.oit.oitcloud;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@NacosPropertySource(dataId = "oitcloudums.yaml", autoRefreshed = true)
public class OitUMS_Start_9310 {
    public static void main(String[] args) {
        SpringApplication.run(OitUMS_Start_9310.class, args);
    }
}
