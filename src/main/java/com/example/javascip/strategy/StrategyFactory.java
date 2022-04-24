package com.example.javascip.strategy;

import com.example.javascip.Solver;

public interface StrategyFactory {
    Solver getSolver(String type, String defaultType);
}
