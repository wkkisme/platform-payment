package com.platfrom.payment;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
//import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangkai
 */
@SpringBootApplication(scanBasePackages = "com.platfrom.payment")
//@SpringBootApplication
@ImportResource(locations = {"classpath*:platform-*.xml"})
@MapperScan("com.platfrom.payment.dal")
//@ComponentScan(basePackages = {"com.platfrom.payment.**"})
@RestController
@EnableScheduling
@EnableTransactionManagement
@EnableAsync
@EnableDubbo
//@NacosPropertySource(dataId = "bqhealth_cloud_data", groupId = "bqhealth_cloud_group",autoRefreshed = true)
public class PlatformPaymentApplication {
    public static void main(String[] args) {
        try {
            //System.setProperty("org.quartz.properties", "");
            SpringApplication.run(PlatformPaymentApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/healthCheck")
    public String healthCheck(){
        try {
            return "I'm Ok! \nI'm at ";
        } catch (Exception e) {
            return "I'm OK! \nBut getLocalHost failed!";
        }
    }
}
