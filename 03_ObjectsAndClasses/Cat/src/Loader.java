public class Loader
{
    //Задание по уроку 5 модуль 3
    //Метод, возвращающий кошку с весом 1100.0 через конструктор
    private static Cat getKitten()
    {
        return new Cat(1000.0, "Мурка", ColorType.RED_HEAD);
    }
    public static void main(String[] args)
    {
            // Задание по уроку 1 модуль 3:
            System.out.println("Задание по уроку 1 модуль 3");
            Cat cat1 = new Cat();
            System.out.println("Текущий вес кошки №1 = " + cat1.getWeight());
            System.out.println("Перекормили кошку №1");
            cat1.feed((9000 / 1500) * cat1.getWeight());
            System.out.println("Новый вес кошки №1 = " + cat1.getWeight() + " Статус кошки №1 = " + cat1.getStatus());
            System.out.println();
            Cat cat2 = new Cat();
            System.out.println("Текущий вес кошки №2 = " + cat2.getWeight());
            System.out.println("Покормили кошку №2");
            cat2.feed(cat2.getWeight());
            System.out.println("Новый вес кошки №2 = " + cat2.getWeight() + " Статус кошки №2 = " + cat2.getStatus());
            System.out.println();
            Cat cat3 = new Cat();
            System.out.println("Текущий вес кошки №3 = " + cat3.getWeight());
            for (int i = 1; i < 9000; i++) {
                if (cat3.getWeight() <= 1000) break;
                cat3.meow();
            }
            System.out.println("Новый вес кошки №3 = " + cat3.getWeight() + " Статус кошки №3 = " + cat3.getStatus());
            System.out.println();
            System.out.println("Задание по уроку 2 модуль 3");

            //Задание по уроку 2 модуль 3:
            Cat cat4 = new Cat();
            System.out.println("Текущий вес кошки №4 = " + cat4.getWeight());
            System.out.println("Покормили кошку №4");
            cat4.feed(cat4.getWeight() / 10);
            System.out.println("Новый вес кошки №4 = " + cat4.getWeight() + " Статус кошки №4 = " + cat4.getStatus());
            System.out.println("Кол-во съеденной еды = " + cat4.getEatenFood());
            System.out.println();
            Cat cat5 = new Cat();
            System.out.println("Текущий вес кошки №5 = " + cat5.getWeight());
            cat5.pee();
            System.out.println("Новый вес кошки №5 = " + cat5.getWeight() + " Статус кошки №5 = " + cat5.getStatus());
            System.out.println();
            //Testing
            Cat cat6 = new Cat();
            cat6.feed(150.0);
            cat6.pee();
            cat6.pee();
            System.out.println("Кол-во съеденной еды = " + cat6.getEatenFood());
            //Success
            System.out.println();
            System.out.println("Задание по уроку 3 модуль 3");

            //Задание по уроку 3 модуль 3:
            //==========================================================================
            System.out.println("Количество кошек в начале = " + Cat.getCount());
            System.out.println();
            //=======================================================================================
            System.out.println("Создали кошку");
            Cat cat11 = new Cat();
            System.out.println("Вес кошки №1 = " + cat11.getWeight());
            System.out.println("Количество кошек = " + Cat.getCount());
            System.out.println("Покормили кошку");
            cat11.feed(9999.9);
            System.out.println("Вес кошки = " + cat11.getWeight());
            System.out.println("Стало кошек = " + Cat.getCount());
            System.out.println(cat11.isAlive()); // Условие, что кожка жива: true - нет, false - да
            //Тестируем, что кошка не может ничего делать, если умерла:
            System.out.println("Пробуем покормить кошку...");
            cat11.feed(1000.0);
            System.out.println("Пробуем напоить кошку...");
            cat11.drink(1000.0);
            System.out.println("Пробуем помяукать кошкой...");
            cat11.meow();
            System.out.println("Пробуем сходить в туалет кошкой...");
            cat11.pee();
            System.out.println();
            //=======================================================================================
            System.out.println("Создали кошку");
            Cat cat21 = new Cat();
            System.out.println("Вес кошки №2 = " + cat21.getWeight());
            System.out.println("Количество кошек = " + Cat.getCount());
            System.out.println("Покормили кошку");
            cat21.feed(0.0);
            System.out.println("Вес кошки = " + cat21.getWeight());
            System.out.println("Стало кошек = " + Cat.getCount());
            System.out.println(cat21.isAlive());
            System.out.println();
            //=======================================================================================
            System.out.println("Создали кошку");
            Cat cat31 = new Cat();
            System.out.println("Вес кошки №3 = " + cat31.getWeight());
            System.out.println("Количество кошек = " + Cat.getCount());
            System.out.println("Напоили кошку");
            cat31.drink(9999.9);
            System.out.println("Вес кошки = " + cat31.getWeight());
            System.out.println("Стало кошек = " + Cat.getCount());
            System.out.println(cat3.isAlive());
            System.out.println();
            //=======================================================================================
            System.out.println("Создали кошку");
            Cat cat41 = new Cat();
            System.out.println("Вес кошки №4 = " + cat41.getWeight());
            System.out.println("Количество кошек = " + Cat.getCount());
            System.out.println("Напоили кошку");
            cat41.drink(0.0);
            System.out.println("Вес кошки = " + cat41.getWeight());
            System.out.println("Стало кошек = " + Cat.getCount());
            System.out.println(cat41.isAlive());
            System.out.println();
            //=======================================================================================
            System.out.println("Создали кошку");
            Cat cat51 = new Cat();
            System.out.println("Вес кошки №5 = " + cat51.getWeight());
            System.out.println("Количество кошек = " + Cat.getCount());
            System.out.println("Кошка мяукнула");
            cat51.meow();
            System.out.println("Вес кошки = " + cat51.getWeight());
            System.out.println("Стало кошек = " + Cat.getCount());
            System.out.println(cat51.isAlive());
            System.out.println();
            //=======================================================================================
            System.out.println("Создали кошку");
            Cat cat61 = new Cat();
            System.out.println("Вес кошки №6 = " + cat61.getWeight());
            System.out.println("Количество кошек = " + Cat.getCount());
            System.out.println("Кошка мяукнула");
            cat61.meow();
            System.out.println("Вес кошки = " + cat61.getWeight());
            System.out.println("Стало кошек = " + Cat.getCount());
            System.out.println(cat61.isAlive());
            System.out.println();
            //=======================================================================================
            System.out.println();
            System.out.println("Задание по уроку 4 модуль 3");
        // Задание по уроку 4 модуль 3
            System.out.println("Константа (кол-во глаз у кошки) = " + Cat.EYE_COUNT);
            System.out.println("Константа (максимальный вес кошки) = " + Cat.MAX_WEIGHT);
            System.out.println("Константа (минимальный вес кошки) = " + Cat.MIN_WTIGHT);
            System.out.println();
            System.out.println("Задание по уроку 5 модуль 3");

            //Задание по уроку 5 модуля 3
            Cat cat01 = new Cat(1000.00, "Мурка", ColorType.BLACK);
            //Cat cat02 = new Cat("Мурка");
            System.out.println("Объект cat01 с weight = " + cat01.getWeight());
            //System.out.println("Объект cat02 с name = " + cat02.getName());
            //====================================================

            System.out.println("Создаем нового котенка с весом = " + getKitten().getWeight());
            System.out.println("Создаем нового котенка с весом = " + getKitten().getWeight());
            System.out.println("Создаем нового котенка с весом = " + getKitten().getWeight());
            System.out.println("Создаем нового котенка с весом = " + getKitten().getWeight());
            System.out.println("Создаем нового котенка с весом = " + getKitten().getWeight());
            System.out.println("Создаем нового котенка с весом = " + getKitten().getWeight());
            System.out.println();
            System.out.println("Задание по уроку 6 модуль 3");

            // Задание по уроку 6 модуль 3
            Cat cat34 = new Cat();
            cat34.setColorType(ColorType.ASHEN);
            System.out.println("Цвет кошки из списка = " + cat34.getColorType());
            System.out.println();
            System.out.println("Задание по уроку 7 модуль 3");

            // Задание по уроку 7 модуль 3


            System.out.println("Создали Мурку");
            Cat murka = new Cat(1000.0, "Мурка", ColorType.SPOTTED);
            System.out.println("Параметры мурки:");
            System.out.println("Вес Мурки = " + murka.getWeight());
            System.out.println("Имя Мурки = " + murka.getName());
            System.out.println("Цвет Мурки = " + murka.getColorType());
            System.out.println();
            System.out.println("Сделали глубокую копию Мурки - Ваську");
            Cat vasya = Cat.copyCat(murka);
            System.out.println("Вес Васьки = " + vasya.getWeight());
            System.out.println("Имя Васьки = " + vasya.getName());
            System.out.println("Цвет Васьки = " + vasya.getColorType());
            //Testing
            murka.feed(1500.0);
            System.out.println("Покормили мурку на - " + murka.getEatenFood() + " грамм");
            murka.setColorType(ColorType.BLACK);
            System.out.println("Изменили цвет кошки на - " + murka.getColorType());
            System.out.println("Вес Васьки = " + vasya.getWeight());
            System.out.println("Цвет Васьки = " + vasya.getColorType());
            System.out.println("Новый вес Мурки = " + murka.getWeight());
            System.out.println("Новый цвет Мурки = " + murka.getColorType());
        }

}