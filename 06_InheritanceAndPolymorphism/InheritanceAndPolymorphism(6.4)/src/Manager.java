/**
 * Класс сотрудника - Менеджера
 * @author Kirill Galeev
 * @version 1.0
 */
public class Manager //extends Company
        implements Employee {
    /**
     * Переменная типа double, отвечающая за кол-во продаж Менеджера
     */
    double sales = 50000 + (Math.random() * 100000); //продажи менеджера от 50000 до 150000
    /**
     * Переменная типа double, отвечающая за фиксированную часть ЗП Менеджера
     */
    double fixPartSalary = 40000 + (Math.random() * 20000); // фиксированная часть менеджера от 40000 до 60000
    /**
     * Переменнаыя типа String, отвечающая за должность Менеджера
     */
    String position;
    /**
     * Переменнаыя типа int, отвечающая за идентификатор Менеджера
     */
    int id;

    /**
     * Конструктор Менеджера, в который подается должность и идентификатор
     * @param position
     * @param id
     */
    public Manager (String position, int id) {
        this.id = id;
        this.position = position;
    }

    public Manager() {

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
     * Имплементированный метод из интерфейса Employee, возвращающий ЗП Менеджера
     * @return
     */
    @Override
    public double getMonthSalary() {
        double managerSalary = fixPartSalary + (0.05 * sales);
        managerSalary = managerSalary * 100;
        int i = (int) Math.round(managerSalary);
        managerSalary = (double) i / 100;
        return managerSalary;
    }

    /**
     * Имплементированный метод из интерфейса Employee, возвращающий идентификатор Менеджера
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Имплементированный метод из интерфейса Employee, возвращающий должность Менеджера
     * @return
     */
    @Override
    public String getPosition() {
        return position;
    }


}