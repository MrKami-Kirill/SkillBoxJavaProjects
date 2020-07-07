import java.util.*;

public class Main {
    public static void main(String[] args) {
/*        //ДЗ 5.1 (задание 1)
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] massone = text.split(",?\\s+");
        for (int i = 0; i < massone.length; i++) {
            System.out.print("Элемент #" + i + " = " + massone[i] + ";  ");
        }
        System.out.println("\nПосле переворачивания:");

        for (int i = massone.length - 1, j = 0; i >= 0; i--, j++) {
            System.out.print("Элемент #" + j + " = " + massone[i] + ";  ");
        }
        System.out.println();

        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] massone = text.split(",?\\s+");
        int n = massone.length;
        for (int i = 0; i < massone.length; i++){
            System.out.print(massone[i] + " ");
        }
        System.out.println();
        String a;
        for (int i = 0; i < (n / 2); i++) {
            a = massone[n-i-1];
            massone[n - i - 1] = massone[i];
            massone[i] = a;
        }
        for (int i = 0; i < massone.length; i++) {
            System.out.print(massone[i]+ " ");
        }

        //ДЗ 5.1 (задание 2)
        double[] patient = new double[30];
        double sum = 0;
        long countOfHealthPeople = 0;
        for (int patientNumber = 0; patientNumber < patient.length; patientNumber++)
        {
            patient[patientNumber] = (32 + (Math.random() * (40 - 32)));
            System.out.print("Температура пациента №" + patientNumber + " = " + patient[patientNumber] + ", ");
            //Средняя температура по больнице
            sum += patient[patientNumber];
            //Кол-во здоровых пациентов
            countOfHealthPeople = Arrays.stream(patient)
                    .filter(t -> t >= 36.2 && t <= 36.9)
                    .count();
        }
        System.out.println();
        System.out.println("Средняя температура по больнице = " + sum / patient.length);
        System.out.println("Кол-во здоровых пациентов = " + countOfHealthPeople);

        ДЗ 5.1 (задание 3)
        String[][] massive =
                {
                        {"X", " ", " ", " ", " ", " ", "X"},
                        {" ", " ", " ", " ", " ", " ", " "},
                        {" ", "X", " ", " ", " ", "X", " "},
                        {" ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", "X", " ", "X", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", " ", "X", " ", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " "},
                        {" ", " ", "X", " ", "X", " ", " "},
                        {" ", " ", " ", " ", " ", " ", " "},
                        {" ", "X", " ", " ", " ", "X", " "},
                        {" ", " ", " ", " ", " ", " ", " "},
                        {"X", " ", " ", " ", " ", " ", "X"}
                };
        for (int i = 0; i < massive.length; i++) {
            for (int j = 0; j < massive[i].length; j++) {
                System.out.print(massive[i][j] + "\t");
            }
            System.out.println();
            System.out.println();
        }

        // создаем двумерный массив X на X
        String[][] multiplyTab  = new String[7][7];

        // цикл по первой размерности
        for (int i = 0; i < multiplyTab.length; i++) {
            // цикл по второй размерности
            for (int j = 0; j < multiplyTab[i].length; j++) {
                //инициализация элементов массива (главной и побочной диагонали)
                if (i == j || i + j == multiplyTab.length - 1)
                    {                //вывод элементов массива
                        multiplyTab[i][j] = "X";
                    }
                else {
                   multiplyTab[i][j] = " ";
                }
                System.out.print(multiplyTab[i][j] + "\t");
            }
            System.out.println();
        }*/
/*      //ДЗ 5.2
        System.out.println("LIST - выводит список дел с их порядковыми номерами.");
        System.out.println("ADD - добавляет дело в конец списка или на определённое место, сдвигая остальные дела вперёд, если указать номер.");
        System.out.println("EDIT - заменяет дело с указанным номером.");
        System.out.println("DELETE - удаляет дело.");
        List<String> todoList = new ArrayList<>() {{
            add(0, "0-е дело");
            add(1, "1-е дело");
            add(2, "2-е дело");
            add(3, "3-е дело");
            add(4, "4-е дело");
            add(5, "5-е дело");
            add(6, "6-е дело");
            add(7, "7-е дело");
            add(8, "8-е дело");
            add(9, "9-е дело");
            add(10, "10-е дело");
        }};
        System.out.println("Кол-во дел в списке todoList = " + todoList.size());
        System.out.println("Вызовите команду : ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String[] massive = command.split("\\s+");
        System.out.println();
        String errorText = "Команда введена неверно!";
        String nullText = "Сделать какое-то дело!";


        //Реализация команды LIST
        if (massive[0].equalsIgnoreCase("LIST")) {
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println(todoList.get(i)); // расечатываем список дел по команде LIST
            }
        }


        //Реализация команды DELETE
        else if (massive[0].equalsIgnoreCase("DELETE")) {
            if (command.length() <= 7) {
                System.out.println(errorText);
            } else {
                if (massive[1].matches("\\d+")) {
                    int numberList = Integer.parseInt(massive[1]);
                    boolean result = (numberList <= (todoList.size() - 1));
                    if (result = false) {
                        todoList.remove(numberList);
                        System.out.println("Обновленный список дел: ");
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println(i + " - " + todoList.get(i));
                        }
                    } else {
                        System.out.println("Невозможно удалить дело, которого не сущетсвует!");
                    }
                }
            }
        }


        //Реализация команды ADD
        else if (massive[0].equalsIgnoreCase("ADD")) {
            if (command.length() <= 4) {
                System.out.println(errorText);
            } else {
                if (massive[1].matches("\\d+")) {
                    int numberList = Integer.parseInt(massive[1]); // объявляем переменную = номеру дела
                    String textWork = command.substring(4); // текст строки из консоли, который находится после команды ADD
                    int spaceIndex = textWork.indexOf(' ');
                    String textWork2 = textWork.substring(spaceIndex + 1);
                    if (numberList > (todoList.size() - 1)) {
                        numberList = (todoList.size() - 1);
                        todoList.add(numberList, textWork2);
                        System.out.println("Обновленный список дел: ");
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println(i + " - " + todoList.get(i));
                        }
                    } else {
                        todoList.add(numberList, textWork2);
                        System.out.println("Обновленный список дел: ");
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println(i + " - " + todoList.get(i));
                        }
                    }
                } else {
                    String textWork = command.substring(4);
                    {
                        todoList.add(textWork); // добавляем в список дело, название которого начинается после команды ADD
                        System.out.println("Обновленный список дел: ");
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println(i + " - " + todoList.get(i));
                        }
                    }
                }
            }
        }


        //Реализация команды EDIT
        else if (massive[0].equalsIgnoreCase("EDIT")) {
            //System.out.println(command.length());
            if (command.length() <= 5) {
                System.out.println(errorText);
            } else {
                if (massive[1].matches("\\d+")) {
                    int numberList = Integer.parseInt(massive[1]); //объявляем переменную = номеру дела
                    String textWork = command.substring(5); // текст строки из консоли, который находится после команды ADD
                    System.out.println(textWork);
                    int spaceIndex = textWork.indexOf(' '); // пробел между номером дела и его текстом
                    String textWork2 = textWork.substring(spaceIndex + 1); // вычислили текст дела
                    System.out.println(textWork2);
                    if (numberList <= (todoList.size() - 1)) {
                        todoList.remove(numberList);
                        todoList.add(numberList, textWork2);
                        System.out.println("Обновленный список дел: ");
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.println(i + " - " + todoList.get(i));
                        }
                    } else {
                        System.out.println("Невозможно редактировать дело, которого не существует!");
                    }
                }
                else {
                        System.out.println(errorText);
                }
            }
        }


        //Ошибка (не введена команда или введена неверная команда)
        else {
            System.out.println(errorText);
        }*/
/*        //ДЗ 5.3
        HashSet<String> mail = new HashSet<>();
        mail.add("mrkami19940@gmail.com");
        mail.add("xxxomyakk@mail.ru");
        mail.add("enuvoba-4690@yopmail.com");
        mail.add("ukusisy-0814@yopmail.com");
        mail.add("xizitoqadi-3205@yopmail.com");

        String errorText = "Команда введена неверно!";
        System.out.println("Вызовите команду: ");


        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            String[] massive = command.split("\\s+");
            switch (massive[0]) {
                case ("LIST"):
                    //Реализация команды LIST
                    for (String word : mail) {
                        System.out.println(word);// расечатываем список дел по команде LIST
                    }
                    continue;


                case ("ADD"):
                    if (command.length() <= 4) {
                        System.out.println(errorText);
                    } else {
                        int mailSpaceIndex = command.indexOf(' ');
                        String email = command.substring(mailSpaceIndex + 1);
                        // ДЗ 5.3 (со звездочкой)
                        // проверки взял отсюда: https://habr.com/ru/post/267205/
                        if (email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}")) {
                            mail.add(email);
                            System.out.println("Обновленный список дел: ");
                            for (String word : mail) {
                                System.out.println(word);
                            }
                        } else {
                            System.out.println("Имейл введен неправильно");
                        }
                    }
                    continue;

                default:
                    System.out.println(errorText);
                    break;
            }
        }*/
/*        //ДЗ 5.4
        TreeMap<String, String> phoneDirectory = new TreeMap<>();

        phoneDirectory.put("Кирилл","9138022858");
        phoneDirectory.put("Евгения", "9234174910");
        phoneDirectory.put("Владимир", "9999156604");
        phoneDirectory.put("Екатерина", "4957923930");
        phoneDirectory.put("Елена", "9636440107");

        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            String findContact = scanner.nextLine();
            if (findContact.equalsIgnoreCase("LIST")) {
                printAllMap(phoneDirectory);
                continue;
            }
            if (!findContact.matches("\\d+")) {
                if (phoneDirectory.containsKey(findContact)) {
                    System.out.println("Данные контакта:");
                    System.out.println("Имя контакта: " + findContact);
                    System.out.println("Телефон: " + phoneDirectory.get(findContact));
                    continue;
                } else {
                    System.out.println("Имя контакта не найдено. Введите номер телефона нового контакта: ");
                    String phoneNumber = scanner.nextLine();
                    phoneDirectory.put(findContact, phoneNumber);
                    continue;
                }

            } else {
                if (phoneDirectory.containsValue(findContact)) {
                    System.out.println("Данные контакта:");
                    for (Map.Entry entry : phoneDirectory.entrySet()) {
                        if (entry.getValue().equals(findContact)) {
                            System.out.println("Имя контакта: " + entry.getKey());
                            System.out.println("Номер телефона: " + findContact);
                        }
                    }
                    continue;
                } else {
                    System.out.println("Номер телефона не найден. Введите имя нового контакта: ");
                    String nameContact = scanner.nextLine();
                    phoneDirectory.put(nameContact, findContact);
                    continue;
                }
            }
        }*/
        //ДЗ 5.5
        String chars[] = new String[]{"E", "T", "Y", "O", "P", "A", "H", "K", "X", "C", "B", "M"};
        ArrayList<String> governmentNumbers = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i < chars.length; i++) {
            for (int k = 0; k < chars.length; k++) {
                for (int n = 0; n < chars.length; n++) {
                    for (int j = 0; j < 10; j++) {
                        for (int m = 1; m < 200; m++) {
                            String currChar1 = chars[i];
                            String currChar2 = chars[k];
                            String currChar3 = chars[n];
                            if (m < 10) {
                                String number = String.format("%s%s%d%d%d%s0%d", currChar1, currChar2, j, j, j, currChar3, m);
                                //System.out.println(number);
                                governmentNumbers.add(number);
                            } else {
                                String number = String.format("%s%s%d%d%d%s%d", currChar1, currChar2, j, j, j, currChar3, m);
                                //System.out.println(number);
                                governmentNumbers.add(number);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(governmentNumbers.size());
        hashSet.addAll(governmentNumbers);
        treeSet.addAll(governmentNumbers);
        //Collections.addAll(governmentNumbers);
        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            String findNumber = scanner.nextLine();

            //Поиск прямым перебором в ArrayList
            long start = System.nanoTime();
            if (governmentNumbers.contains(findNumber)) {
                System.out.print("Поиск перебором: номер " + findNumber + " найден,");
            }
            else {
                System.out.print("Поиск перебором: номер " + findNumber + " не найден,");
            }
            long duration = System.nanoTime() - start;
            System.out.println(" поиск занял " + duration + " нс");
        }

            //Бинарый поиск в ArrayList
//            Collections.sort(governmentNumbers);
//            long start = System.nanoTime();
//            int result = Collections.binarySearch(governmentNumbers, findNumber);
//            if (result >= 0) {
//                System.out.print("Бинарный поиск: номер " + findNumber + " найден,");
//            }
//            else {
//                System.out.print("Бинарный поиск: номер " + findNumber + " не найден,");
//            }
//            long duration = System.nanoTime() - start;
//            System.out.println(" поиск занял " + duration + " нс");
//        }

            //Поиск в HashSet
//            long start = System.nanoTime();
//            if (hashSet.contains(findNumber)) {
//                System.out.print("Поиск в HashSet: номер " + findNumber + " найден,");
//            }
//            else {
//                System.out.print("Поиск в HashSet: номер " + findNumber + " не найден,");
//            }
//            long duration = System.nanoTime() - start;
//            System.out.println(" поиск занял " + duration + " нс");
//        }

        //Поиск в TreeSet
//        long start = System.nanoTime();
//        if (treeSet.contains(findNumber)) {
//            System.out.print("Поиск в TreeSet: номер " + findNumber + " найден,");
//        }
//        else {
//            System.out.print("Поиск в TreeSet: номер " + findNumber + " не найден,");
//        }
//        long duration = System.nanoTime() - start;
//        System.out.println(" поиск занял " + duration + " нс");
//    }
//        for (String str: governantNumbers) { // Распечатываем ArrayList
//            System.out.println(str);
//        }
//        System.out.println(governantNumbers.size()); //проверяем кол-во номеров. Должно быть 3 438 720 номеров.
    }
    private static void printAllMap(Map<String, String> map) {
        System.out.println("Телефонная книга:");
        for (String key : map.keySet()) {
            System.out.println("Имя контакта: " + key + "\n\t" + " Номер телефона: " + map.get(key));
        }
    }
}
