package com.example.javascip;

import com.example.javascip.strategy.StrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScipService {
//    @Autowired
//    private ScipSolver solver;
    @Autowired
    private StrategyFactory strategyFactory;

    public String solve() {
        Solver solver = strategyFactory.getSolver("scipSolver", "deafult");
        solver.solve();
        return "Hello Scip";
    }
}
