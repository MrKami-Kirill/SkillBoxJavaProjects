import java.util.*;

/**
 * Класс, который содержит методы компании
 * @author Kirill Galeev
 * @version 1.0
 */
public class Company implements Comparator<Employee> {
    /**
     * Статическая переменная income (доход компании)
     */
    public static long income;
    /**
     * Список сотрудников в компании
     */
    List<Employee> employees = new ArrayList<>();

    /**
     * Конструктор компании, в который подается переменная income
     * @param income
     */
    public Company(long income) {
        this.income = income;
    }


    /**
     * Метод, отвечающий за набор ОДНОГО сотрудника в компанию с указанием его должности (position) и табельного номера (ID)
     * @param employee
     * @param position
     * @param id
     * @return
     */
    public int hire(Employee employee, String position, int id) {
        System.out.println("В компанию нанят сотрудник" + " на должность " + position + " с идентификатором " + id);
        if (employee instanceof Manager) {
            //Employee manager = new Manager(position, id);
            employees.add(new Manager(position, id));
        } else if (employee instanceof TopManager) {
            //Employee topManager = new TopManager(position, id);
            employees.add(new TopManager(position, id));
        } else if (employee instanceof Operator) {
            //Employee operator = new Operator(position, id);
            employees.add(new Operator(position, id));
        }
        return employees.size();
    }

    /**
     * Метод, отвечающий за МНОЖЕСТВЕННЫЙ набор сотрудников, с указанием должности (position) и кол-ва сотрудников (countWorkers)
     * @param employee
     * @param position
     * @param countWorkers
     * @return
     */
    public int hireAll(Employee employee, String position, int countWorkers) {
        System.out.println("В компанию нанято " + countWorkers + " сотрудников на должность " + position);
        for (int i = 1; i < countWorkers + 1; i++) {
            hire(employee, position, i);
        }
        return employees.size();
    }

    /**
     * Метод, устанавливающий доход компании
     * @param income
     */
    public void setIncome (long income) {
        this.income = income;
    }

    /**
     * Метод, возвращающих доход компанииф
     * @return
     */
    public long getIncome() {
        return income;
    }


    /**
     * Метод, твечающий за увольнение ОДНОГО сотрудника с указанием его должности и идентификатора
     * @param position
     * @param id
     */
    public void fire(String position, int id) {
        System.out.println("Уволили " + position + "-а" + ", ID которого " + id);
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            Employee nextEmployees = employeeIterator.next();
            if (nextEmployees.getId() == id && nextEmployees.getPosition().equalsIgnoreCase(position)) {
                employeeIterator.remove();
            }
        }
    }

    /**
     * Метод, отвечающий за увольнение МНОЖЕСТВА сотрудников с их должности (postionManager, beginIdManager, postionTopManager)
     и интервала идентификаторов с (beginIdManager, beginIdTopManager, beginIdOperator)
     по (endIdManager, endIdTopManager, endIdOperator)
     * @param postionManager
     * @param beginIdManager
     * @param endIdManager
     * @param postionTopManager
     * @param beginIdTopManager
     * @param endIdTopManager
     * @param postionOperator
     * @param beginIdOperator
     * @param endIdOperator
     */
    public void fireALot (
            String postionManager, int beginIdManager, int endIdManager,
            String postionTopManager, int beginIdTopManager, int endIdTopManager,
            String postionOperator, int beginIdOperator, int endIdOperator)
    {
        System.out.println(
                "Уволили " + postionManager + "-ов" + ", ID которых с " + beginIdManager + " по " + endIdManager + "\n" +
                "Уволили " + postionTopManager + "-ов" + ", ID которых с " + beginIdTopManager + " по " + endIdTopManager + "\n" +
                "Уволили " + postionOperator + "-ов" + ", ID которых с " + beginIdOperator + " по " + endIdOperator
        );
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            Employee nextEmployees = employeeIterator.next();
            for (int i = beginIdManager; i <= endIdManager; i++) {
                if (nextEmployees.getId() == i && nextEmployees.getPosition().equalsIgnoreCase(postionManager)) {
                    employeeIterator.remove();
                }
            }
            for (int i = beginIdTopManager; i <= endIdTopManager; i++) {
                if (nextEmployees.getId() == i && nextEmployees.getPosition().equalsIgnoreCase(postionTopManager)) {
                    employeeIterator.remove();
                }
            }
            for (int i = beginIdOperator; i <= endIdOperator; i++) {
                if (nextEmployees.getId() == i && nextEmployees.getPosition().equalsIgnoreCase(postionOperator)) {
                    employeeIterator.remove();
                }
            }
        }
        System.out.println("Сотрудников в компании после увольнения: " + employees.size() + " человек");
    }


    /**
     * Метод, который выводит в консоль список (count) самых высоких зарплат в компании
     * @param count
     */
    public void getTopSalaryStaff (int count) {
        employees.sort((o1, o2) -> compare1(o2, o1));
        System.out.println("\nСписок " + count + " самых ВЫСОКИХ зарплат в компании по УБЫВАНИЮ:");
        for (int i = 0; i < count; i++) {
            System.out.println((i+1) + ". " + employees.get(i).getMonthSalary());
        }
    }

    /**
     * Метод, который выводит в консоль список (count) самых низких зарплат в компании
     * @param count
     */
    public void getLowestSalaryStaff (int count) {
        employees.sort((o1, o2) -> compare2(o2, o1));
        System.out.println("\nСписок " + count + " самых НИЗКИХ зарплат в компании по ВОЗРАСТАНИЮ:");
        for (int i = 0; i < count; i++) {
            //System.out.printf("%.2f", employees.get(i).getMonthSalary());
            System.out.println((i+1) + ". " + employees.get(i).getMonthSalary());
        }
    }

    /**
     * Метод, который вывод список сотрудников компании в консоль
     * @return
     */
    public void getEmployees() {
        employees.stream().sorted(Comparator.comparing(Employee::getId)).forEach(System.out::print);
    }

    /**
     * Метод, который выводит в консоль кол-во человек в компании
     */
    public void getCompanySize() {
        System.out.println("Сотрудников в компании: " + employees.size() + " человек");
    }


    /** Метод, наследованный от интерфейса Comparator
     * @param o1
     * @param o2
     * @return
     */
    @Override

    public int compare(Employee o1, Employee o2) {
        return 0;
    }

    /**
     * Метод, наследованный от интерфейса Comparator (см. метод getTopSalaryStaff)
     * @param o1
     * @param o2
     * @return
     */
    public int compare1(Employee o1, Employee o2) {
        if (o1.getMonthSalary() > o2.getMonthSalary()) {
            return 1; }
        if (o1.getMonthSalary() < o2.getMonthSalary()) {
            return -1; }
        return 0;
    }

    /**
     * Метод, наследованный от интерфейса Comparator (см. метод getLowestSalaryStaff)
     * @param o1
     * @param o2
     * @return
     */
    public int compare2(Employee o1, Employee o2) {
        if (o1.getMonthSalary() > o2.getMonthSalary()) {
            return -1; }
        if (o1.getMonthSalary() < o2.getMonthSalary()) {
            return 1; }
        return 0;
    }
}
