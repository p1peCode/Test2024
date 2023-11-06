import java.util.Scanner;

public class CheckYear {
    static int checkYear() throws IllegalArgumentException {
        System.out.print("Год начала жизни на проценты: ");
        Scanner scanner = new Scanner(System.in);
        int startYear = scanner.nextInt();

        if (startYear < 2002 || startYear > 2021) {
            throw new IllegalArgumentException("Год начала жизни должен быть от 2002 до 2021");

        }
        return startYear;
    }
}