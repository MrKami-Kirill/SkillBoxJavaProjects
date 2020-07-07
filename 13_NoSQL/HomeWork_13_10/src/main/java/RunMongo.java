import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Log4j2
public class RunMongo
{
    private static final String ADD_SHOP = "ДОБАВИТЬ_МАГАЗИН\\s[А-я\\w]+\\s*";
    private static final String ADD_GOODS = "ДОБАВИТЬ_ТОВАР\\s[А-я\\w]+\\s\\d{1,19}";
    private static final String PUT_GOODS_IN_SHOP = "ВЫСТАВИТЬ_ТОВАР\\s[А-я\\w]+\\s[А-я\\w]+";
    private static final String STATISTICS = "СТАТИСТИКА_ТОВАРОВ";
    private static final String EXIT = "ВЫХОД";
    private static final String CLEAN = "ОЧИСТИТЬ";
    private static final String TEST = "ТЕСТОВАЯ_БАЗА";
    public static void main(String[] args)
    {
        MongoStorage mongoStorage = new MongoStorage();
        while (true) {
            System.out.print("Введите запрос: \n");
            String input = readLine();

            if (input.toUpperCase().matches(EXIT)) {
                mongoStorage.exit();
                break;
            }
            else if (input.toUpperCase().matches(CLEAN)) {
                mongoStorage.dropDB();
            }
            else if (input.toUpperCase().matches(TEST)) {
                mongoStorage.addShop("ДОБАВИТЬ_МАГАЗИН МАГАЗИН_1");
                mongoStorage.addGoods("ДОБАВИТЬ_ТОВАР ТОВАР_1 69");
                mongoStorage.addGoods("ДОБАВИТЬ_ТОВАР ТОВАР_2 150");
                mongoStorage.putGoodsInShops("ВЫСТАВИТЬ_ТОВАР ТОВАР_1 МАГАЗИН_1");
                mongoStorage.putGoodsInShops("ВЫСТАВИТЬ_ТОВАР ТОВАР_2 МАГАЗИН_1");
                mongoStorage.addShop("ДОБАВИТЬ_МАГАЗИН МАГАЗИН_1");
                mongoStorage.addGoods("ДОБАВИТЬ_ТОВАР ТОВАР_3 50");
                mongoStorage.addGoods("ДОБАВИТЬ_ТОВАР ТОВАР_4 250");
                mongoStorage.putGoodsInShops("ВЫСТАВИТЬ_ТОВАР ТОВАР_3 МАГАЗИН_2");
                mongoStorage.putGoodsInShops("ВЫСТАВИТЬ_ТОВАР ТОВАР_4 МАГАЗИН_2");

            }
            else if (input.toUpperCase().matches(ADD_SHOP)) {
                mongoStorage.addShop(input);
            }
            else if (input.toUpperCase().matches(ADD_GOODS)) {
                mongoStorage.addGoods(input);
            }
            else if (input.toUpperCase().matches(PUT_GOODS_IN_SHOP)) {
                mongoStorage.putGoodsInShops(input);
            }
            else if (input.toUpperCase().matches(STATISTICS)) {
                mongoStorage.getStat();
            }
            else {
                log.info("Команда не распознана.");
            }
        }
    }
    private static String readLine() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
