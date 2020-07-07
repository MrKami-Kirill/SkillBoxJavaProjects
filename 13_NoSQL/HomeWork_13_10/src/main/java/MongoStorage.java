import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import java.util.Collections;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static java.util.Arrays.asList;

@Data
@Log4j2
public class MongoStorage
{
    private final String SHOP_NAME = "name";
    private final String GOOD_NAME = "name";
    private final String GOOD_PRICE = "price";
    private final String GOOD_LIST = "goods";
    private MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
    private CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private MongoDatabase mongoDatabase = mongoClient.getDatabase("ShopsAndGoods").withCodecRegistry(pojoCodecRegistry);
    private MongoCollection<Document> shopsCollection = mongoDatabase.getCollection("shops");
    private MongoCollection<Document> goodsCollection = mongoDatabase.getCollection("goods");

    public MongoStorage() {
        IndexOptions indexOptions = new IndexOptions().unique(true);
        shopsCollection.createIndex(Indexes.text("name"), indexOptions);
        goodsCollection.createIndex(Indexes.text("name"), indexOptions);
    }

    public boolean addShop(String command) {
        Document document = new Document();
        String[] commandArray = command.strip().split("\\s");
        String shopName = commandArray[1];
        Document foundShop = getShop(shopName);
        if (foundShop != null) {
            log.info(String.format("Магазин %s уже существует. Магазин не добавлен.", shopName));
            return false;
        }
        document.append(SHOP_NAME, shopName).append(GOOD_LIST, Collections.emptyList());
        mongoDatabase.getCollection("shops").insertOne(document);
        log.info(String.format("Магазин %s добавлен.", shopName));
        return true;
    }

    public boolean addGoods(String command) {
        Document document = new Document();
        String[] commandArray = command.strip().split("\\s");
        String goodsName = commandArray[1];
        Integer goodsPrice = Integer.parseInt(commandArray[2]);
        Document foundGoods = getGoods(goodsName);
        if (foundGoods != null) {
            log.info(String.format("Товар %s уже существует. Товар не добавлен.", goodsName));
            return false;
        }
        document.append(GOOD_NAME, goodsName).append(GOOD_PRICE, goodsPrice);
        goodsCollection.insertOne(document);
        log.info(String.format("Товар %s добавлен.", goodsName));
        return true;
    }

    public boolean putGoodsInShops (String command) {
        String[] commandArray = command.strip().split("\\s");
        String goodsName = commandArray[1];
        String shopName = commandArray[2];
        Document foundShop = getShop(shopName);
        Document foundGoods = getGoods(goodsName);
        Document document = goodsCollection.find(and(eq(SHOP_NAME, shopName), all(GOOD_LIST, goodsName))).first();
        if (foundGoods == null) {
            log.info(String.format("Товар %s не найден", goodsName));
            return false;
        }
        if (foundShop == null) {
            log.info(String.format("Магазин %s не найден", shopName));
            return false;
        }
        if ( document != null) {
            log.info(String.format("Товар %s ранее уже был выставлен в магазине %s. Товар не выставлен.", goodsName, shopName));
            return false;

        }
        shopsCollection.updateOne(eq(SHOP_NAME, shopName), addToSet(GOOD_LIST, goodsName));
        log.info(String.format("Товар %s выставлен в магазине %s.", goodsName, shopName));
        return true;
    }

    public boolean getStat() {
        if ((shopsCollection.countDocuments() == 0) || (goodsCollection.countDocuments() == 0)) {
            log.info("База данных не заполнена.");
            return false;
        }
        log.info("Статистика по магазинам:");
        System.out.println("------------------------------");
        Iterable<Document> statistics = shopsCollection.aggregate(
                asList(
                        lookup(
                                goodsCollection.getNamespace().getCollectionName(),
                                GOOD_LIST,
                                GOOD_NAME,
                                "goodsList"),
                        unwind("$goodsList", new UnwindOptions().preserveNullAndEmptyArrays(true)),
                        group("$" + SHOP_NAME,
                                sum("totalGoods", 1),
                                avg("avgPrice", "$goodsList.price"),
                                first("cheapGoodsName", "$goodsList.name"),
                                first("cheapGoodsPrice", "$goodsList.price"),
                                last("expensiveGoodsName", "$goodsList.name"),
                                last("expensiveGoodsPrice", "$goodsList.price"),
                                sum("less100", Document.parse("{ \"$cond\": [ { \"$lt\": [ \"$goodsList.price\", 100 ] }, 1, 0 ] }")))));
        statistics.forEach(stat ->
                System.out.println(
                        "Название магазина: " + stat.get("_id") + "\r\n" +
                                "\t" + "Общее количество товаров в магазине: " + stat.get("totalGoods") + " шт" + "\r\n" +
                                "\t" + "Средняя стоимость товаров в магазине: " + stat.get("avgPrice") + " рублей" + "\r\n" +
                                "\t" + "Самый дешевый товар: " + stat.get("cheapGoodsName") + " (" + stat.get("cheapGoodsPrice") + " рублей)" + "\r\n" +
                                "\t" + "Самый дорогой товар: " + stat.get("expensiveGoodsName") + " (" + stat.get("expensiveGoodsPrice") + " рублей)" + "\r\n" +
                                "\t" + "Количество товаров дешевле 100 рублей: " + stat.get("less100") + " шт" + "\r\n"));
        System.out.println("------------------------------");
        return true;
    }

    public Document getShop(String shopName) {
        BsonDocument query = BsonDocument.parse("{" + SHOP_NAME + ": {$eq: \"" + shopName + "\"}}");
        return mongoDatabase.getCollection("shops").find(query).first();
    }

    public Document getGoods(String goodName) {
        BsonDocument query = BsonDocument.parse("{" + GOOD_NAME + ": {$eq: \"" + goodName + "\"}}");
        return mongoDatabase.getCollection("goods").find(query).first();
    }

    public void dropDB() {
        mongoDatabase.drop();
        log.info(String.format("База данных %s очищена.", mongoDatabase.getName()));
    }

    public void exit() {
        mongoClient.close();
        log.info("Выход из программы.");
    }
}
