import java.time.LocalDate;

public record Expense(double amount, String category, LocalDate date) {

    public String toString() {
        return String.format("Дата: %s, Категория: %s, Сумма: %.2f", date, category, amount);
    }
}