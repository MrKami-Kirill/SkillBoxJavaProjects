import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data

public class MovementList
{
    private String accountType;
    private String accountNumber;
    private String currency;
    private Date dateOfOperation;
    private String wiringReference;
    private String descriptionOperation;
    private Double coming;
    private Double consumption;

    public MovementList (String accountType,
                         String accountNumber,
                         String currency,
                         Date dateOfOperation,
                         String wiringReference,
                         String descriptionOperation,
                         Double coming,
                         Double consumption) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.dateOfOperation = dateOfOperation;
        this.wiringReference = wiringReference;
        this.descriptionOperation = descriptionOperation;
        this.coming = coming;
        this.consumption = consumption;
    }


//    public String getAccountType() {
//        return accountType;
//    }
//
//    public String getAccountNumber() {
//        return accountNumber;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public Date getDateOfOperation() {
//        return dateOfOperation;
//    }
//
//    public String getWiringReference() {
//        return wiringReference;
//    }
//
//    public String getDescriptionOperation() {
//        return descriptionOperation;
//    }
//
//    public Double getComing() {
//        return coming;
//    }
//
//    public Double getConsumption() {
//        return consumption;
//    }

    public String toString()
    {
        return accountType + " - "
                + accountNumber + " - "
                + currency + " - "
                + (new SimpleDateFormat("dd.MM.yy")).format(dateOfOperation) + " - "
                + wiringReference + " - "
                + descriptionOperation + " - "
                + coming + " - "
                + consumption;
    }

    /**
     * Метод, который разбивает параметр descriptionOperation класса MovementList
     * @return
     */
    public String getSplitDescriptionOperation() {
        if (descriptionOperation.matches("(.*)CARD2CARD(.*)")) {
            descriptionOperation = "CARD2CARD";
        }
        else if (descriptionOperation.matches("(.*)Alfa Iss(.*)")) {
            descriptionOperation = "Alfa Iss";
        }
        else if (descriptionOperation.matches("(.*)DIXY(.*)")) {
            descriptionOperation = "DIXY";
        }
        else if (descriptionOperation.matches("(.*)YANDEX TAXI(.*)")) {
            descriptionOperation = "YANDEX TAXI";
        }
        else if (descriptionOperation.matches("(.*)VPS NET(.*)")) {
            descriptionOperation = "VPS NET";
        }
        else if (descriptionOperation.matches("(.*)RYABINOVAYA 5(.*)")) {
            descriptionOperation = "RYABINOVAYA 5";
        }
        else if (descriptionOperation.matches("(.*)WWW HETZNER D(.*)")) {
            descriptionOperation = "WWW HETZNER D";
        }
        else if (descriptionOperation.matches("(.*)KFC ASHAN MAR(.*)")) {
            descriptionOperation = "KFC ASHAN MAR";
        }
        else if (descriptionOperation.matches("(.*)L ETOILE(.*)")) {
            descriptionOperation = "L ETOILE";
        }
        else if (descriptionOperation.matches("(.*)SUBWAY(.*)")) {
            descriptionOperation = "SUBWAY";
        }
        else if (descriptionOperation.matches("(.*)RAIKHONA(.*)")) {
            descriptionOperation = "RAIKHONA";
        }
        else if (descriptionOperation.matches("(.*)KUSCHAVEL(.*)")) {
            descriptionOperation = "KUSCHAVEL";
        }
        else if (descriptionOperation.matches("(.*)delivery club(.*)")) {
            descriptionOperation = "delivery club";
        }
        else if (descriptionOperation.matches("(.*)ZOTMAN(.*)")) {
            descriptionOperation = "ZOTMAN";
        }
        else if (descriptionOperation.matches("(.*)YANDEX EDA(.*)")) {
            descriptionOperation = "YANDEX EDA";
        }
        else if (descriptionOperation.matches("(.*)FSPRG UK(.*)")) {
            descriptionOperation = "FSPRG UK";
        }
        else if (descriptionOperation.matches("(.*)AWS EMEA(.*)")) {
            descriptionOperation = "AWS EMEA";
        }
        else if (descriptionOperation.matches("(.*)LOVE REPUBLIC(.*)")) {
            descriptionOperation = "LOVE REPUBLIC";
        }
        else if (descriptionOperation.matches("(.*)ZOOMAGAZIN 4(.*)")) {
            descriptionOperation = "ZOOMAGAZIN 4";
        }
        else if (descriptionOperation.matches("(.*)GOOGLE(.*)")) {
            descriptionOperation = "GOOGLE";
        }
        return descriptionOperation;
    }
}
