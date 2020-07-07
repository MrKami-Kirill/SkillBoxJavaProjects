/**
 * Класс сотрудника - Топ-Менеджера
 * @author Kirill Galeev
 * @version 1.0
 */
public class TopManager //extends Company
        implements Employee {
    /**
     * Переменная типа double, отвечающая за фиксированную часть ЗП Топ-Менеджера
     */
    double fixPartSalary =  90000 + (Math.random() * 30000); // фиксированная часть топменеджера от 90000 до 120000
    /**
     * Переменнаыя типа String, отвечающая за должность Топ-Менеджера
     */
    String position;
    /**
     * Переменнаыя типа int, отвечающая за идентификатор Топ-менеджера
     */
    int id;

    /**
     * Конструктор Топ-Менеджера, в который подается должность и идентификатор
     * @param position
     * @param id
     */
    public TopManager (String position, int id) {
        super();
        this.id = id;
        this.position = position;
    }

    public TopManager() {

    }

    /**
     * Имплементированный метод из интерфейса Employee, возвращающий доход компании
     * @return
     */
    @Override
    public long getIncome() {
        return Company.income;
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
     * Имплементированный метод из интерфейса Employee, возвращающий ЗП Топ-Менеджера
     * @return
     */
    @Override
    public double getMonthSalary() {
        double topManagerSalary;
        if (getIncome() > 10000000) {
            topManagerSalary = fixPartSalary + (1.5 * fixPartSalary);
        }
        else {
            topManagerSalary = fixPartSalary;
        }
        topManagerSalary = topManagerSalary * 100;
        int i = (int) Math.round(topManagerSalary);
        topManagerSalary = (double) i / 100;
        return topManagerSalary;
    }

    /**
     * Имплементированный метод из интерфейса Employee, возвращающий идентификатор Топ-Менеджера
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Имплементированный метод из интерфейса Employee, возвращающий должность Топ-Менеджера
     * @return
     */
    @Override
    public String getPosition() {
        return position;
    }

}
