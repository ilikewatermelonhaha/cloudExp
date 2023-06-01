package org.exp.loadbalancer;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class CustomLoadBalanceConfiguration {
    @Bean
    ReactorLoadBalancer randomLoadBalance(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory){
        String name=environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        ReactorLoadBalancer reactorLoadBalancer=new RandomLoadBalancer(
                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class),name
        );
        return reactorLoadBalancer;
    }
}
