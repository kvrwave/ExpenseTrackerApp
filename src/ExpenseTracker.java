import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseTracker {
    private final List<Expense> expenses = new ArrayList<>();
    private final Set<String> categories = new HashSet<>();

    public ExpenseTracker() {
        categories.addAll(Arrays.asList("Еда", "Здоровье", "Транспорт", "Покупки", "Развлечения",
                "Коммунальные услуги", "Образование", "Путешествия", "Сбережения", "Подарки"));
    }

    public void addExpense(double amount, String category, LocalDate date) throws IllegalArgumentException {
        if (!categories.contains(category)) {
            throw new IllegalArgumentException("Неправильная категория. Доступные: " + categories);
        }
        expenses.add(new Expense(amount, category, date));
    }

    public Map<String, Double> getCategoryStatistics() {
        return expenses.stream()
                .collect(Collectors.groupingBy(Expense::category, Collectors.summingDouble(Expense::amount)));
    }

    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::amount).sum();
    }

    public Map<String, Double> getCategoryPercentages() {
        double total = getTotalExpenses();
        return getCategoryStatistics().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (e.getValue() / total) * 100));
    }

    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }

}



