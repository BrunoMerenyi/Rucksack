package org.example;

import java.util.ArrayList;
import java.util.List;

public class OptimalPackStrategie implements PackStrategie {

    @Override
    public PackErgebnis packe(List<Gegenstand> kandidaten, int maxGewicht) {
        if (kandidaten == null || kandidaten.isEmpty() || maxGewicht <= 0) {
            return new PackErgebnis(List.of(), 0);
        }

        int n = kandidaten.size();
        boolean[][] dp = new boolean[n + 1][maxGewicht + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            int w = kandidaten.get(i - 1).getGewicht();
            for (int cap = 0; cap <= maxGewicht; cap++) {
                dp[i][cap] = dp[i - 1][cap];
                if (cap - w >= 0 && dp[i - 1][cap - w]) {
                    dp[i][cap] = true;
                }
            }
        }

        int best = 0;
        for (int cap = maxGewicht; cap >= 0; cap--) {
            if (dp[n][cap]) {
                best = cap;
                break;
            }
        }

        List<Gegenstand> chosen = new ArrayList<>();
        int cap = best;
        for (int i = n; i >= 1; i--) {
            Gegenstand item = kandidaten.get(i - 1);
            int w = item.getGewicht();
            if (cap - w >= 0 && dp[i - 1][cap - w]) {
                chosen.add(item);
                cap -= w;
            }
        }

        return new PackErgebnis(chosen, best);
    }
}
