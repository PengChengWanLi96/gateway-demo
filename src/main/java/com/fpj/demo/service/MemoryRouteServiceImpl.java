package com.fpj.demo.service;

import com.fpj.demo.service.dto.CustomRoute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fangpengjun
 * @date 2024/4/11
 */
@Service
public class MemoryRouteServiceImpl implements RouteService{

    List<CustomRoute> customRouteList = new ArrayList<>();

    @Override
    public List<CustomRoute> list() {
        return null;
    }

    @Override
    public CustomRoute add(Map<String, Object> mapData) {
        return null;
    }

    @Override
    public CustomRoute get(String routeId) {
        return null;
    }

    @Override
    public CustomRoute delete(String routeId) {
        return null;
    }

    @Override
    public CustomRoute update(Map<String, Object> mapData) {
        return null;
    }
}