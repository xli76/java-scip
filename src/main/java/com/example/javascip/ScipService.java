package com.example.javascip;

import com.example.javascip.strategy.StrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ScipService {
    private static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    @Autowired
    private StrategyFactory strategyFactory;

    public String solve() {
        return runnable();
    }

    private String runnable() {
        Future<String> future = executor.submit(() -> doSolve());
        String result = "";
        try {
            result = future.get(1/** second */, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            future.cancel(true);
        }
        return result;
    }

    private String doSolve() {
        Solver solver = strategyFactory.getSolver("scipSolver", "deafult");
        solver.solve();
        return "Hello Scip";
    }
}
