package org.bonn.se.ss20.midterm.util.Analytics;

/**
 * @author Henry Weckermann, Anton Drees
 */

public class Strategy {

    private AnalyticsUtility strategy = null;
    private String[] params;

    public void setStrategy(AnalyticsUtility strat) {
        strategy = strat;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public void analyze(String[] params) {
        if (strategy != null) {
            strategy.analyze(params);
        }
    }

}
