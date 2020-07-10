import lombok.extern.log4j.Log4j2;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Log4j2
public class Loader {
    public static void main(String[] args) throws Exception {
        String fileName = "D:\\Downloads\\VoteAnalyzer\\res\\data-1572M.xml";
        long start = System.currentTimeMillis();
        SAXParseFile(fileName);
        log.info("Duration of parsing a file and loading data into a database: " + (System.currentTimeMillis() - start) + " ms.");

        DBConnection.printDuplicateVoter();
        DBConnection.printWorkTimeStation();
        while (true) {
            System.out.println("What do you want to find? (S/V)");
            String input = readLine();
            if (input.strip().equalsIgnoreCase("S")) {
                System.out.println("Please, enter voting station number:");
                String station = readLine();
                DBConnection.searchStation(station);
            } else if (input.strip().equalsIgnoreCase("V")) {
                System.out.println("Please, enter voter's name:");
                String name = readLine();
                DBConnection.searchVoter(name);
            } else if (input.strip().equalsIgnoreCase("EXIT")) {
                break;
            } else {
                System.out.println("Command not recognized!");
            }
        }

    }
    private static void SAXParseFile (String fileName) throws Exception {
        DBConnection.getPreparedStatement();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        saxParser.parse(new File(fileName), handler);
        DBConnection.loadLeftovers();
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
