import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseTracker manager = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить траты");
            System.out.println("2. Сколько всего потрачено");
            System.out.println("3. Сколько потрачено на каждую категорию");
            System.out.println("4. Процентное соотношение категорий");
            System.out.println("5. Экспортировать данные в Excel");
            System.out.println("6. Выход");

            int choice = -1;
            while (choice < 1 || choice > 6) {
                System.out.print("Выберите действие: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice < 1 || choice > 6) {
                        printError("Неверный выбор.");
                    }
                } catch (InputMismatchException e) {
                    printError("Неверный выбор.");
                    scanner.nextLine();
                }
            }

            try {
                switch (choice) {
                    case 1:
                        double amount = -1;
                        while (amount <= 0) {
                            System.out.print("Введите сумму: ");
                            try {
                                amount = scanner.nextDouble();
                                scanner.nextLine();
                                if (amount <= 0) {
                                    printError("Сумма должна быть положительным числом.");
                                }
                            } catch (InputMismatchException e) {
                                printError("Сумма должна быть положительным числом.");
                                scanner.nextLine();
                            }
                        }

                        System.out.print("Введите категорию: ");
                        String category = scanner.nextLine();

                        System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
                        LocalDate date = LocalDate.parse(scanner.nextLine());

                        manager.addExpense(amount, category, date);
                        break;
                    case 2:
                        System.out.println("Всего потрачено: " + manager.getTotalExpenses());
                        break;
                    case 3:
                        System.out.println("Статистика по категориям: " + manager.getCategoryStatistics());
                        break;
                    case 4:
                        System.out.println("Процентное соотношение категорий: " + manager.getCategoryPercentages());
                        break;
                    case 5:
                        System.out.print("Введите название файла: ");
                        String fileName = scanner.nextLine();
                        ExcelExport.exportExpensesToExcel(manager.getAllExpenses(), fileName);
                        System.out.println("Данные экспортированы в " + fileName);
                        break;
                    case 6:
                        System.out.println("Удачи.");
                        return;
                    default:
                        printError("Неверный выбор.");
                }
            } catch (Exception e) {
                printError(e.getMessage());
            }
        }
    }

    private static void printError(String message) {
        System.out.println("Ошибка: " + message);
    }
}

