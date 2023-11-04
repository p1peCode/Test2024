public class Main {
    public static void main(String[] args) {
        int startYear = CheckYear.checkYear();

        Calc calc = new Calc();

        System.out.println(calc.calc(startYear));
    }
}