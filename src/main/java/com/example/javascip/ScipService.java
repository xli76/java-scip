package com.example.javascip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScipService {
    @Autowired
    private ScipSolver solver;

    public String solve() {
        solver.solve();
        return "Hello Scip";
    }
}
