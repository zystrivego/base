package com.example.demo.soap.service;


import com.example.demo.soap.service.rs.UserServiceRS;
import com.example.demo.soap.service.ws.UserServiceWS;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by mabo-pc on 2017/7/9.
 */
@Configuration
public class CXFConfig {

    @Autowired
    private UserServiceWS userServiceWS;

    @Autowired
    private UserServiceRS userServiceRS;

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
//
//    //JAX-WS发布
//    @Bean
//    public Endpoint WSServer() {
//        EndpointImpl endpoint = new EndpointImpl(springBus(), userServiceWS);
//        endpoint.publish("/UserServiceWS");
//        return endpoint;
//    }

    //JAX-RS发布
    @Bean
    public Server restfullServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(springBus());
        endpoint.setAddress("/UserServiceRS");
        endpoint.setServiceBeans(Collections.singletonList(userServiceRS));
        endpoint.setFeatures(Collections.singletonList(new Swagger2Feature()));
        return endpoint.create();
    }
}
