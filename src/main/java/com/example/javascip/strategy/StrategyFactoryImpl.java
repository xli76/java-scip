package com.example.javascip.strategy;

import com.example.javascip.Solver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StrategyFactoryImpl implements StrategyFactory {
    @Autowired
    private final Map<String, Solver> map = new ConcurrentHashMap<>();

    @Override
    public Solver getSolver(String type, String defaultType) {
        Solver solver = map.get(type);
        if (solver == null) {
            return map.get(defaultType);
        }
        return solver;
    }
}
