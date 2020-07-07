/**
 * Интерфейс сотрудника
 */
public interface Employee {

    /**
     * Метод, возвращающих ЗП сотрудника
     * @return
     */
    public double getMonthSalary();

    /**
     * Метод, возвращающих идентификатор сотрудника
     * @return
     */
    public int getId();

    /**
     * Метод, возвращающих должность сотрудника
     * @return
     */
    public String getPosition();
    /**
     * Метод, возвращающих доход компании (наверное, не нужен)
     * @return
     */
    public long getIncome();

}
