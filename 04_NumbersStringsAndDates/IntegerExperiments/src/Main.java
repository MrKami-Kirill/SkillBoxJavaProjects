public class Main {
    public static void main(String[] args)
    {
        //ДЗ 4.1
        Container container = new Container();
        container.count += 7843;
        System.out.println(container.count);
        System.out.println();


        //Used method sumDigits
        System.out.println(sumDigits(987654321));
        System.out.println();

        //ДЗ 4.2
        double a1 = +Double.MAX_VALUE;
        double a2 = -Double.MAX_VALUE;
        float a3 = +Float.MAX_VALUE;
        float a4 = -Float.MAX_VALUE;
        int a5 = Integer.MAX_VALUE;
        int a6 = Integer.MIN_VALUE;
        byte a7 = Byte.MAX_VALUE;
        byte a8 = Byte.MIN_VALUE;
        short a9 = Short.MAX_VALUE;
        short a10 = Short.MIN_VALUE;
        long a11 = Long.MAX_VALUE;
        long a12 = Long.MIN_VALUE;
        System.out.println("Максимальное значение типа double = " + a1);
        System.out.println("Минимальное значение типа double = " + a2);
        System.out.println();
        System.out.println("Максимальное значение типа float = " + a3);
        System.out.println("Минимальное значение типа float = " + a4);
        System.out.println();
        System.out.println("Максимальное значение типа int = " + a5);
        System.out.println("Минимальное значение типа int = " + a6);
        System.out.println();
        System.out.println("Максимальное значение типа byte = " + a7);
        System.out.println("Минимальное значение типа byte = " + a8);
        System.out.println();
        System.out.println("Максимальное значение типа short = " + a9);
        System.out.println("Минимальное значение типа short = " + a10);
        System.out.println();
        System.out.println("Максимальное значение типа long = " + a11);
        System.out.println("Минимальное значение типа long = " + a12);

        //ДЗ 4.3
        int box = 6100; //задаем число ящиков
        Cargo cargo = new Cargo(box);
        cargo.print();

//        //ДЗ 4.4 (1 задание)
//        for(char i='a'; i<='z'; i++){
//            int c=i;
//            System.out.println(i + " :" + c + ".");
//        }
    }

        public static Integer sumDigits(Integer number)
        {
            //@TODO: write code here
            String str = number.toString();
            int sumOfStr = str.length();
            int result = 0;
            for (int i = 0; i < sumOfStr; i++)
            {
                result += Character.getNumericValue(str.charAt(i));
            }
            return result;
        }
    public static class Cargo
    {
        private int truck;
        private int container;
        private int box;

        public Cargo(int box) {
            this.box = box;
            if (box != 0) {
                container = box % 27 == 0 ? box / 27 : box / 27 + 1;
            }
            if (container != 0) {
                truck = container % 12 == 0 ? container / 12 : container / 12 + 1;
            }
        }

        public void print() {
            int a = 1;
            int b = 1;
            for (int i = 1; i <= truck; i++)
            {
                System.out.printf("Грузовик %d:\n", i);
                int j = 0;
                while (j < 12 && a <= container)
                {
                    System.out.printf("Контейнер %3d:\n", a);
                    int k = 0;
                    while (k < 27 && b <= box)
                    {
                        System.out.printf("Ящик %d, ", b);
                        k++;
                        b++;
                    }
                    j++;
                    a++;
                    System.out.println();
                }
            }
        }
    }
}








