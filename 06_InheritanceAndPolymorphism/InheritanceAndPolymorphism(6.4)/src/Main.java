public class Main {
    public static void main(String[] args) {
        Company topCompanyName = new Company(1100000);
        topCompanyName.setIncome(12000000);
        topCompanyName.hireAll(new Operator(), "OPERATOR", 180);
        topCompanyName.hireAll(new Manager(), "MANAGER", 80);
        topCompanyName.hireAll(new TopManager(), "TOPMANAGER", 10);
        topCompanyName.getCompanySize();
        topCompanyName.getTopSalaryStaff(15);
        topCompanyName.getLowestSalaryStaff(30);
//        System.out.println(topCompanyName.fire("OPERATOR",9)); //Уволить оператора с ID = 9;
        topCompanyName.fireALot( //Увольняем 50% рабочих (с выбором ID)
                "OPERATOR", 1, 90, //уволить Операторов, ID которых с 1 - 90;
                "MANAGER", 1, 40, //уволить Менеджеров, ID которых с 1 - 40;
                "TOPMANAGER", 1, 5 //уволить Топ Менеджеров, ID которых с 1 - 5;
        );
//        System.out.println(topCompanyName.getEmployees());
        topCompanyName.getTopSalaryStaff(15);
        topCompanyName.getLowestSalaryStaff(30);
//        Employee topManager1 = new TopManager("TOPMANAGER", 100);
        topCompanyName.getEmployees();
    }
}

