/*
 * Copyright (c) 2018, TP-Link Co.,Ltd.
 * Author:  zhangyuxiang <zhangyuxiang@tp-link.com.cn>
 * Created: 2018-07-26
 */
package zyx.practise.dubbospring;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ConsumerTest {
    private DemoService demoService;

    @Test
    public void start() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        context.start();

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-test-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://172.31.1.151:2181");

        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(DemoService.class);
        demoService = referenceConfig.get();

        System.out.println(demoService.sayHello("world."));
    }

}
