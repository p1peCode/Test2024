public class Calculator {
    static double currentCapital;
    static double spendingOnYear;
    static int year;
    static double firstWithdraw = 0.5;
    static double capital = 100;

    public static void profirCalculation() {
        currentCapital -= spendingOnYear;
        double indexMoexInThisYear = Constants.MOEX_RATE[year - 2000];
        double indexMoexInFutureYear = Constants.MOEX_RATE[year - 1999];
        currentCapital += (currentCapital / 100) * ((indexMoexInFutureYear / indexMoexInThisYear - 1) * 100);
    }

    public static void inflationCalculation() {
        double inflation = Constants.INFLATION_RATE[year - 2000] / 100;
        spendingOnYear *= (1 + inflation);
        year++;
    }

    public static void checkCapital() {
        if (currentCapital > 0) {
            firstWithdraw += 0.5;
        }
    }

    public static void mainLoop() {
        while (year <= 2021 && currentCapital > 0) {
            if (currentCapital <= spendingOnYear) {
                firstWithdraw -= 0.5;
                System.out.println(firstWithdraw);
                System.exit(0);
            }
            profirCalculation();
            inflationCalculation();
        }
    }

    public static void calc(int startYear) {
        while (firstWithdraw <= 100.0) {
            year = startYear;
            currentCapital = capital;
            spendingOnYear = currentCapital * (firstWithdraw / 100);
            mainLoop();
            checkCapital();
        }
    }
}
