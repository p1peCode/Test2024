import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите год начала жизни на проценты: ");
        int startYear = scanner.nextInt();

        if (startYear < 2002 || startYear > 2021) {
            throw new IllegalArgumentException("Год начала жизни должен быть от 2002 до 2021");
        }
        double capital = 45000000;
        //для начала минимальный процент
        double firstWithdraw = 0.5;
        while (firstWithdraw <= 100.0) {
            int year = startYear;
            double currentCapital = capital;
            double spendingOnYear = currentCapital * (firstWithdraw / 100);
            while (year <= 2021 && currentCapital > 0) {
                double indexMoexInThisYear = Constants.MOEX_RATE[year - 2000];
                double indexMoexInFutureYear = Constants.MOEX_RATE[year - 1999];
                    currentCapital += (currentCapital / 100) * ((indexMoexInFutureYear / indexMoexInThisYear - 1) * 100);
                double inflation = Constants.INFLATION_RATE[year - 2000] / 100;
                spendingOnYear *= (1 + inflation);
                year++;
                currentCapital -= spendingOnYear;
            }
            if (currentCapital < spendingOnYear) {
                System.out.println(firstWithdraw);
                break;
            }
            if (currentCapital > 0) {
                firstWithdraw += 0.5;
            }
        }
    }
}