package com.example.javascip;
import jscip.*;
import org.springframework.stereotype.Component;

@Component
public class ScipSolver {
    static {
        // load generated C-library
        System.loadLibrary("jscip");
    }

    public void solve() {
        Scip scip = new Scip();

        // set up data structures of SCIP
        scip.create("LinearExample");

        // create variables (also adds variables to SCIP)
        Variable x = scip.createVar("x", 2.0, 3.0, 1.0, SCIP_Vartype.SCIP_VARTYPE_CONTINUOUS);
        Variable y = scip.createVar("y", 0.0, scip.infinity(), -3.0, SCIP_Vartype.SCIP_VARTYPE_INTEGER);

        // create a linear constraint
        Variable[] vars = {x, y};
        double[] vals = {1.0, 2.0};
        Constraint lincons = scip.createConsLinear("lincons", vars, vals, -scip.infinity(), 10.0);

        // add constraint to SCIP
        scip.addCons(lincons);

        // release constraint (if not needed anymore)
        scip.releaseCons(lincons);

        // set parameters
        scip.setRealParam("limits/time", 100.0);
        scip.setRealParam("limits/memory", 10000.0);
        scip.setLongintParam("limits/totalnodes", 1000);

        // solve problem
        scip.solve();
        System.out.println("final gap = " + scip.getGap());

        // print all solutions
        Solution[] allsols = scip.getSols();

        for( int s = 0; allsols != null && s < allsols.length; ++s )
            System.out.println("solution (x,y) = (" + scip.getSolVal(allsols[s], x) + ", " + scip.getSolVal(allsols[s], y) + ") with objective value " + scip.getSolOrigObj(allsols[s]));

        // release variables (if not needed anymore)
        scip.releaseVar(y);
        scip.releaseVar(x);

        // free SCIP
        scip.free();
    }
}
