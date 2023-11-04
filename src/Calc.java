public class Calc {
    public double calc(int startYear) {
        double capital = 45000000;
        //для начала минимальный процент
        double firstWithdraw = 0.5;
        // <= 100.0 для того чтобы узнать максимальный процент изъятия
        while (firstWithdraw <= 100.0) {
            int year = startYear;
            double currentCapital = capital;
            double spendingOnYear = currentCapital * (firstWithdraw / 100);
            while (year <= 2021 && currentCapital > 0) {
                // это проверка на то, хватит ли денег на расходы в текущем году
                if (currentCapital <= spendingOnYear) {
                    firstWithdraw -= 0.5;// 2022 году нужно избежать этой проверки
                    System.out.println(firstWithdraw);
                    System.exit(0);
                }
                /*здесь происходят рассчеты - прирост/убыток капитала, поправка на инфляцию и вычитание из текущего
                капитала, суммы расходов на этот год*/
                currentCapital -= spendingOnYear;
                double indexMoexInThisYear = Constants.MOEX_RATE[year - 2000];
                double indexMoexInFutureYear = Constants.MOEX_RATE[year - 1999];
                currentCapital += (currentCapital / 100) * ((indexMoexInFutureYear / indexMoexInThisYear - 1) * 100);
                double inflation = Constants.INFLATION_RATE[year - 2000] / 100;
                spendingOnYear *= (1 + inflation);
                year++;
            }

            //если прожили до 2022 года и капитал остался > 0, то увеличиваем процент изъятия
            if (currentCapital > 0) {
                firstWithdraw += 0.5;
            }
        }
        return firstWithdraw;
    }
}
