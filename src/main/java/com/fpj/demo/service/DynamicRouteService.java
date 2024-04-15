package com.fpj.demo.service;

import com.fpj.demo.service.dto.CustomRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangpengjun
 * @date 2024/4/11
 */
@Service
@Slf4j
public class DynamicRouteService implements ApplicationEventPublisherAware, ApplicationRunner {

    private final RouteDefinitionWriter routeDefinitionWriter;

    @Resource
    private RouteService routeService;


    //通过构造方法进行注入，此处通过跟踪代码，RouteDefinitionWriter的实现类是基于内存的，非redis的
    private ApplicationEventPublisher publisher;

    public DynamicRouteService(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("----------load route info---------");

        RouteDefinition routedefinition = null;
        routedefinition.setId("test1");
        routedefinition.setOrder(1);
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName("StripPrefix");
        Map<String, String> fileterMap = new LinkedHashMap<>();
        fileterMap.put("parts", "1");
        filterDefinition.setArgs(fileterMap);
        routedefinition.setFilters(Arrays.asList(filterDefinition));

        PredicateDefinition preDef = new PredicateDefinition();
        preDef.setName("path");
        Map<String, String> preArgsMap = new LinkedHashMap<>();
        preArgsMap.put("pattern", "/api/**");
        preDef.setArgs(preArgsMap);
        routedefinition.setPredicates(Arrays.asList(preDef));

        routeDefinitionWriter.save(Mono.just(routedefinition)).subscribe();

/*        List<CustomRoute> list = routeService.list();
        if(list != null && !list.isEmpty()){
            for (CustomRoute info:list) {
                RouteDefinition routedefinition = null;
                routeDefinitionWriter.save(Mono.just(routedefinition)).subscribe();
            }
        }*/
        log.info("----------load rote info end---------");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = publisher;
    }
}