package com.fpj.demo.service;

import com.fpj.demo.service.dto.CustomRoute;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author fangpengjun
 * @date 2024/4/11
 */

@Service
public interface RouteService {

    List<CustomRoute> list();

    CustomRoute add(Map<String, Object> mapData);

    CustomRoute get(String routeId);

    CustomRoute delete(String routeId);

    CustomRoute update(Map<String, Object> mapData);



}