
public class Cat
{
    // Задание по уроку 4 модуль 3
    public static final int EYE_COUNT = 2;
    public static final double MAX_WEIGHT = 9000.0;
    public static final double MIN_WTIGHT = 1000.0;

    public ColorType colorType;
    public static int count;
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private String name;

    private double eatenFood;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;

    }

    public Cat (double weight, String name, ColorType colorType)
    {
        this();
        this.weight = weight;
        this.name = name;
        this.colorType = colorType;
    }

    public static Cat copyCat(Cat original) {
        Cat cat = new Cat();
        cat.weight = original.getWeight();
        cat.name = original.getName();
        cat.colorType = original.getColorType();
        return cat;
    }



    public void meow()
    {
        if (isAlive())
        {
            addWeight(-1.0);
            System.out.println("Meow");
        }
        if (!isAlive()) {
            System.out.println("Кошка умерла. Действия заблокированы.");
        }
    }

    public static int getCount()
    {
        return count;
    }

    public String getName() {
        return name;
    }


    private void setWeight(Double weight)
    {
        if (isAlive())
        {
            this.weight = weight;

            if (!isAlive())
            {
                count--;
                System.out.println("Кошка умерла. Действия заблокированы.");
            }
        }
    }

    private void addWeight(Double weight)
    {
        setWeight(getWeight() + weight);
    }

    public void feed(Double amount)
    {
        if (isAlive())
        {
            eatenFood = amount;
            addWeight(amount);
            }
        if (!isAlive()) {
            System.out.println("Кошка умерла. Действия заблокированы.");
        }
    }


    public Double getEatenFood()
    {
        return eatenFood;
    }

    public void pee ()
    {
        if (isAlive()) {
            addWeight(-1.0);
            System.out.println("Кошка сходила в туалет...");
        }
        if (!isAlive()) {
            System.out.println("Кошка умерла. Действия заблокированы.");
        }
    }

    public void drink(Double amount)
    {
        if (isAlive())
        {
            addWeight(amount);
        }
        if (!isAlive())
            if (!isAlive()) {
                System.out.println("Кошка умерла. Действия заблокированы.");
            }
    }

    public Double getWeight()
    {
        return weight;
    }

    public void setColorType (ColorType colorType)
    {
        this.colorType = colorType;
    }

    public ColorType getColorType () {
        return colorType;
    }



    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public boolean isAlive()
    {
        if (getWeight() >= minWeight && getWeight() <= maxWeight)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}