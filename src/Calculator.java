public class Calculator {
    public static double profitCalculation(double currentCapital, double spendingOnYear, int year) {
        currentCapital -= spendingOnYear;
        double indexMoexInThisYear = Constants.MOEX_RATE[year - 2000];
        double indexMoexInFutureYear = Constants.MOEX_RATE[year - 1999];
        currentCapital += (currentCapital / 100) * ((indexMoexInFutureYear / indexMoexInThisYear - 1) * 100);
        return currentCapital;
    }

    public static double inflationCalculation(double spendingOnYear, int year) {
        double inflation = Constants.INFLATION_RATE[year - 2000] / 100;
        return spendingOnYear * (1 + inflation);
    }

    public static double checkCapital(double currentCapital, double firstWithdraw) {
        if (currentCapital > 0) {
            return firstWithdraw + 0.5;
        } else {
            return firstWithdraw;
        }
    }

    public static void mainLoop(double capital, double firstWithdraw, int startYear) {
        int year = startYear;
        double currentCapital = capital;
        double spendingOnYear = currentCapital * (firstWithdraw / 100);

        while (year <= 2021 && currentCapital > 0) {
            if (currentCapital <= spendingOnYear) {
                firstWithdraw -= 0.5;
                System.out.println(firstWithdraw);
                System.exit(0);
            }
            currentCapital = profitCalculation(currentCapital, spendingOnYear, year);
            spendingOnYear = inflationCalculation(spendingOnYear, year);
            year++;
        }
    }

    public static void calc(double capital, double firstWithdraw, int startYear) {
        while (firstWithdraw <= 100.0) {
            mainLoop(capital, firstWithdraw, startYear);
            firstWithdraw = checkCapital(capital, firstWithdraw);
        }
    }
}