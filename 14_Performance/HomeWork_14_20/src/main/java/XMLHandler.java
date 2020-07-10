import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler
{
    int limit = 5_000_000;
    int voters = 0;
    int visits = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter") && voters < limit) {
                String name = attributes.getValue("name");
                String birthDate = attributes.getValue("birthDay");
                DBConnection.loadingVotersWithBatchIntoDB(name, birthDate);
                voters++;
            }
            else if (qName.equals("visit") && visits < limit) {
                Integer station = Integer.parseInt(attributes.getValue("station"));
                String time = attributes.getValue("time");
                DBConnection.loadingVisitsWithBatchIntoDB(station, time);
                visits++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
