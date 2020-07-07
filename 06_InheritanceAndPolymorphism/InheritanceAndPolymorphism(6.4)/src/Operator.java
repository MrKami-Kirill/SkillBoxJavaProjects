/**
 * Класс сотрудника - Оператора
 * @author Kirill Galeev
 * @version 1.0
 */
public class Operator //extends Company
        implements Employee {
    /**
     * Переменная типа double, отвечающая за фиксированную часть ЗП Оператора
     */
    double fixPartSalary = 20000 + (Math.random() * 25000); // фиксированная часть менеджера от 20000 до 45000
    /**
     * Переменнаыя типа String, отвечающая за должность Оператора
     */
    String position;
    /**
     * Переменнаыя типа String, отвечающая за идентификатор Оператора
     */
    int id;

    /**
     * Конструктор Оператора, в который подается должность и идентификатор
     * @param position
     * @param id
     */
    public Operator (String position, int id) {
        this.id = id;
        this.position = position;
    }

    public Operator() {

    }

    /**
     * Имплементированный метод из интерфейса Employee
     * @return
     */
    @Override
    public long getIncome() {
        return 0;
    }

    /**
     * Имплементированный метод из интерфейса Employee
     * @return
     */
    @Override
    public String toString() {
        return "{" + "Type=" + position + ", " +
                "id=" + id + "}\n";
    }

    /**
     * Имплементированный метод из интерфейса Employee, возвращающий ЗП Оператора
     * @return
     */
    @Override
    public double getMonthSalary() {
        double operatorSalary = fixPartSalary;
        operatorSalary = operatorSalary * 100;
        int i = (int) Math.round(operatorSalary);
        operatorSalary = (double) i / 100;
        return operatorSalary;

    }

    /**
     * Имплементированный метод из интерфейса Employee, возвращающий идентификатор Оператора
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Имплементированный метод из интерфейса Employee, возвращающий должность Оператора
     * @return
     */
    @Override
    public String getPosition() {
        return position;
    }
}
