import Client.Entity;
import Client.Individual;
import Client.IndividualEntrepreneur;

public class Main {
    public static void main(String[] args) {
        Individual individual = new Individual(1000000.0);
        Entity entity = new Entity(1000000.0);
        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur(1000000.0);

        System.out.println();
        System.out.println("Физические лица");
        individual.getBalance();
        individual.addBalance(-1000);
        individual.getBalance();
        individual.addBalance(1000);
        individual.getBalance();

        System.out.println();
        System.out.println("Юридические лица");
        entity.getBalance();
        entity.addBalance(-9999);

        entity.getBalance();
        System.out.println("Индивидуальные предприниматели");
        individualEntrepreneur.getBalance();
        individualEntrepreneur.addBalance(500);
        individualEntrepreneur.getBalance();
        individualEntrepreneur.addBalance(1500);
        individualEntrepreneur.getBalance();
        individualEntrepreneur.addBalance(-2000);
        individualEntrepreneur.getBalance();
    }
}
