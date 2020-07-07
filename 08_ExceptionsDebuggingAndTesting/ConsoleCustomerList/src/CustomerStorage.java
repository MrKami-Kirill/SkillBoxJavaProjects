import Exceptions.EmptyException;
import Exceptions.NotContainException;
import Exceptions.ValidationException;

import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws ValidationException
    {
        String[] components = data.split("\\s+");
        if (components.length !=4) {
            throw new ValidationException("Wrong format. Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        //Exeption for email format
        if (!components[2].matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}")) {
            throw new ValidationException("Wrong format email. Correct format: \n" +
                    "sometext@sometext.sometext(2-4 any letters)");
        }
        //Exeption for phone format. Taken from: https://habr.com/ru/post/110731/
        if (!components[3].matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")) {
            throw new ValidationException("Wrong format prone. Correct format: \n" +
                    "+7NNNNNNNNNN\n" +
                    "8NNNNNNNNNN\n" +
                    "7NNNNNNNNNN\n" +
                    "+7 NNN NNN NN NN\n" +
                    "8(NNN)NNN-NN-NN\n" +
                    "NNN-NN-NN\n" +
                    "NNNNNNNNNN\n" +
                    "7NNNNNNNNNN\n" +
                    "(NNN)NNNNNNN\n" +
                    "(NNN) NNN NN NN\n" +
                    "8-NNN-NNN-NN-NN\n" +
                    "8 NNN NNNN NNN\n" +
                    "8 NNN NN NN NNN\n" +
                    "8 NNN NN NNN NN\n" +
                    "8 NNN NNN N NNN, where N:\n" +
                    " anything number from 0 to 9");
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() throws EmptyException
    {
        if (storage.isEmpty()) {
            throw new EmptyException("Map is empty");
        }

        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) throws NotContainException {
        if (!storage.containsKey(name)) {
            throw new NotContainException("Map does not contain a parameter @name");
        }
    }

    public int getCount() throws EmptyException
    {
        if (storage.isEmpty()) {
            throw new EmptyException("Map is empty");
        }
        return storage.size();
    }
}

