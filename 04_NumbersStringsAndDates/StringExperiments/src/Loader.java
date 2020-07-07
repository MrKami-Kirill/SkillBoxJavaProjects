import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class Loader {
    public static void main(String[] args)
            throws InterruptedException, ParseException {

        //ДЗ 4.6
        Scanner scanner = new Scanner(System.in);
        System.out.print("Сколько вам полных лет?\nВведите число: ");
        String yourAge = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy - EEEE");
        Calendar calendar = new GregorianCalendar(1994, Calendar.SEPTEMBER , 5); //Дата рождения

        for (int i = 0; i < (Integer.parseInt(yourAge)+1); i++) {
            System.out.println(i + " - " + dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.YEAR, 1);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите дату рождения в формате ДД.ММ.ГГГГ - ");
        String dateBirth = scanner.nextLine();
        // creating date format
        SimpleDateFormat dateFormat = new SimpleDateFormat ("d.MM.yyyy");
        Date docDate = dateFormat.parse(dateBirth);
        // creating calendar object
        Calendar calObj1 = Calendar.getInstance();
        Calendar calObj2 = Calendar.getInstance();
        calObj1.setTime(docDate);
        calObj1.set(1994, Calendar.SEPTEMBER, 5); // Дата рождения
        System.out.println("Ваша дата рождения - " + dateFormat.format(calObj1.getTime()));
        for (int i = 0; i < 1000; i++) {
            if (calObj1.before(calObj2)) {
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("d.MM.yyyy - EEEE");
                System.out.println(i + " - " + dateFormat1.format(calObj1.getTime()));
                calObj1.add(Calendar.YEAR, 1);
            }
        }


        String text = "Вася заработал 999999999 рублей, Петя - 7563 рубля, а Маша - 111111112 рублей";
        //ДЗ 4.5 (задание 1)
        String salary = text.replaceAll("[^0-9\\s]", "");
        String salary2 = salary.trim();
        String[] salary3 = salary2.split("\\s+");
        for (int i = 0; i < salary3.length; i++)
        {
            System.out.println(salary3[0]);
        }
        String a3 = salary3[0];
        int a4 = Integer.parseInt(a3);
        String a5 = salary3[1];
        int a6 = Integer.parseInt(a5);
        String a7 = salary3[2];
        int a8 = Integer.parseInt(a7);
        System.out.println("Сумма заработка ребят = " + (a4 + a6 + a8) + " рублей");

        //ДЗ 4.5 (задание 2)
        String bigText = "Christmas is one of the most popular holidays on the planet. It seems strange but not everybody celebrate such a great holiday. It depends on the religion and the culture of people. So, who does not celebrate this holiday? Firstly, it’s a religious holiday. Christians commemorate the birth of Christ. So people who are neither Christian nor believers don’t have holiday mood on this day. Besides, other people refuse to take part in Christmas, because they consider that this holiday became too commercial. It’s true that in December stores are full of Christmas goods. And people spend a lot of money to have a good time with their friends, family and relatives. Most of them are ready to waste money for small useless presents. It is clear that they celebrate Christmas because it’s a family party and a great opportunity to be together. Nevertheless this cheerful and commercial side of this holiday attracts some countries, for example, China. They have been celebrating Christmas for several years. But this holiday is not in their culture. And in many countries we can find the same traditions. Families decorate a tree, prepare a holiday meal, and wait for Santa Claus and his gifts. But each country also has its things. In the USA there are garlands of popcorn, and in Sweden there are goats made of straw. Someone doesn’t celebrate Christmas, but two billion people, nearly every third person in the world, celebrate Christmas. And this year it will be again, and it will be magic.";
        String words1 = bigText.replaceAll("[^A-Za-z\\s]", "");
//        System.out.println(word1);
        String[] words2 = words1.split("\\s");
        for (int j = 0; j < words2.length; j++) {
            System.out.println(words2[j]);
        }
        System.out.println("Кол-во слов в тексте = " + words2.length);

        //ДЗ 4.5 (задание 3)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Укажите ФИО: ");
        String fullName = scanner.nextLine();
        String fullName2 = fullName.replaceAll("[^A-Za-zА-Яа-я\\s]", "");
        String fullName3 = fullName2.trim();
        String[] fullName4 = fullName3.split("[\\s+]");
        System.out.println("Фамилия: " + fullName4[0]);
        System.out.println("Имя: " + fullName4[1]);
        System.out.println("Отчество: " + fullName4[2]);



        //ДЗ 4.5 (задание 4)
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Укажите номер телефона: ");
        String phone = scanner2.nextLine();
        System.out.println("Ваш номер телефона - " + phone.replaceAll("[^0-9]", ""));

                //ДЗ 4.4 (1 задание)
        for(char i='a'; i<='z'; i++) {
            int c = i;
            System.out.println(i + " :" + c + ".");
        }

        //ДЗ 4.4 (задание 2)
        //ЗП Васи
        int spaceIndex = text.indexOf(' ');
        String text2 = text.substring(spaceIndex + 1);
        int spaceIndex2 = text2.indexOf(' ');
        String text3 = text2.substring(spaceIndex2 + 1);
        int spaceIndex3 = text3.indexOf(' ');
        String text4 = text3.substring(0, spaceIndex3);
        int a1 = Integer.parseInt(text4);
        //ЗП Маши
        int spaceIndex4 = text.lastIndexOf(' ');
        String text5 = text.substring(0, spaceIndex4);
        int spaceIndex5 = text5.lastIndexOf(' ');
        String text6 = text5.substring(spaceIndex5 + 1);
        int a2 = Integer.parseInt(text6);
        //ЗП Вася + Маша
        System.out.println("Сумма заработка Васи и Маши = " + (a1 + a2) + " рублей");




        //ДЗ 4.4 (задание 3 и задание 4 (со звездочкой))
        Scanner scanner = new Scanner(System.in);
        System.out.print("Укажите ФИО: ");
        String fullName = scanner.nextLine();
        String firstName = "";
        String lastName = "";
        String middleName = "";
        String space = " "; // символ, который нужно посчитать
        int len = fullName.length();
        int newlen = fullName.replaceAll(space, "").length();
        int spaceTotal = (len - newlen); // кол-во пробелов
        if (spaceTotal > 2)
        {
            System.out.println("Ошибка! Данные указаны не верно. Укажите, пожалуйста, правильно ваше ФИО");
        }
        else if (spaceTotal == 0 || spaceTotal == 1)
        {
            System.out.println("Ошибка! Данные указаны не полностью. Укажите, пожалуйста, полностью ваше ФИО");
        }
        else if (!fullName.matches("[\\p{L}| ]+"))
        {
            System.out.println("Ошибка! ФИО должно содержать только буквы. Укажите, пожалуйста, правильно ваше ФИО");
        }
        else
        {
            int spaceIndex6 = fullName.indexOf(' ');
            firstName = fullName.substring(0, spaceIndex6);
            int spaceIndex7 = fullName.lastIndexOf(" ");
            lastName = fullName.substring(spaceIndex6 + 1, spaceIndex7);
            middleName = fullName.substring(spaceIndex7 + 1);
            System.out.println("Фамилия: " + firstName);
            System.out.println("Имя: " + lastName);
            System.out.println("Отчество: " + middleName);
            System.out.println("Очень приятно познакомиться с вами, " + lastName + " " + middleName);
        }
    }
}
