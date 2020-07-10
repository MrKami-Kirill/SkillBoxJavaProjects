import lombok.extern.log4j.Log4j2;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class XMLHandler extends DefaultHandler
{
    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private Map<Voter, Integer> voterCounts;

    public XMLHandler() {
        voterCounts = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter") && voter == null) {
            try {
                Date birthDayDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDayDay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if (qName.equals("visit") && voter != null) {
            int count = voterCounts.getOrDefault(voter, 0);
            voterCounts.put(voter, count + 1);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("voter")) {
            voter = null;
        }
    }

    public void printDuplicatedVoters() {
        System.out.println("Duplicated voters: ");
        for(Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter.toString() + " - " + count);
            }
        }
    }
}
