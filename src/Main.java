public class Main {
    public static void main(String[] args) {
        int startYear = CheckYear.checkYear();

        double capital = 100.0;
        double firstWithdraw = 0.5;

        Calculator.calc(capital, firstWithdraw, startYear);
    }
}