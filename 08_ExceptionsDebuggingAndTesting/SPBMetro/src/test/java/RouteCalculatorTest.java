import core.Line;
import core.Station;
import org.junit.*;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class RouteCalculatorTest {

    List<Station> firstLineStations;
    List<Station> secondLineStations;
    List<Station> thirdLineStations;
    RouteCalculator routeCalculator;
    StationIndex stationIndex;
    List<Station> route;


    @Before
    public void setUp() throws Exception {

        Line line1 = new Line(1, "Первая линия");
        line1.addStation(new Station("1-я станция", line1));
        line1.addStation(new Station("2-я станция", line1));
        line1.addStation(new Station("3-я станция", line1)); //This station connect to line3's Line "11-я станция" station
        line1.addStation(new Station("4-я станция", line1));
        line1.addStation(new Station("5-я станция", line1));
        firstLineStations = line1.getStations();

        Line line3 = new Line(3, "Вторая линия");
        line3.addStation(new Station("6-я станция", line3));
        line3.addStation(new Station("7-я станция", line3));
        line3.addStation(new Station("8-я станция", line3)); //This station connect to line2's Line "13-я станция" station
        line3.addStation(new Station("9-я станция", line3));
        line3.addStation(new Station("10-я станция", line3));
        thirdLineStations = line3.getStations();

        Line line2 = new Line(2, "Третья линия");
        line2.addStation(new Station("11-я станция", line2)); //This station connect to line1's Line "3-я станция" station
        line2.addStation(new Station("12-я станция", line2));
        line2.addStation(new Station("13-я станция", line2)); //This station connect to line3's Line "8-я станция" station
        secondLineStations = line2.getStations();

        stationIndex = new StationIndex();
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.stations.addAll(line1.getStations());
        stationIndex.stations.addAll(line2.getStations());
        stationIndex.stations.addAll(line3.getStations());

        TreeSet<Station> firstAndSecondLinesConnection = new TreeSet<>();
        firstAndSecondLinesConnection.add(secondLineStations.get(0));
        TreeSet<Station> secondAndFirstLinesConnection = new TreeSet<>();
        secondAndFirstLinesConnection.add(firstLineStations.get(2));
        TreeSet<Station> thirdAndSecondLinesConnection = new TreeSet<>();
        thirdAndSecondLinesConnection.add(secondLineStations.get(2));
        TreeSet<Station> secondAndThirdLinesConnection = new TreeSet<>();
        secondAndThirdLinesConnection.add(thirdLineStations.get(2));

        stationIndex.connections.put(firstLineStations.get(2), firstAndSecondLinesConnection);
        stationIndex.connections.put(thirdLineStations.get(2), thirdAndSecondLinesConnection);
        stationIndex.connections.put(secondLineStations.get(0), secondAndFirstLinesConnection);
        stationIndex.connections.put(secondLineStations.get(2), secondAndThirdLinesConnection);

        routeCalculator = new RouteCalculator(stationIndex);
    }

    @After
    public void tearDown() throws Exception {
        firstLineStations = null;
        secondLineStations = null;
        thirdLineStations = null;
        routeCalculator = null;
        route = null;
    }

    @Test
    public void getShortestRouteWithTwoConnections() {
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("1-я станция"), stationIndex.getStation("10-я станция"));
        List<Station> expected;
        expected = new ArrayList<>();
        expected.add(stationIndex.getStation("1-я станция"));
        expected.add(stationIndex.getStation("2-я станция"));
        expected.add(stationIndex.getStation("3-я станция"));
        expected.add(stationIndex.getStation("11-я станция"));
        expected.add(stationIndex.getStation("12-я станция"));
        expected.add(stationIndex.getStation("13-я станция"));
        expected.add(stationIndex.getStation("8-я станция"));
        expected.add(stationIndex.getStation("9-я станция"));
        expected.add(stationIndex.getStation("10-я станция"));
        assertEquals(expected, actual);
    }

    @Test
    public void getShortestRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("1-я станция"), stationIndex.getStation("12-я станция"));
        List<Station> expected;
        expected = new ArrayList<>();
        expected.add(stationIndex.getStation("1-я станция"));
        expected.add(stationIndex.getStation("2-я станция"));
        expected.add(stationIndex.getStation("3-я станция"));
        expected.add(stationIndex.getStation("11-я станция"));
        expected.add(stationIndex.getStation("12-я станция"));
        assertEquals(expected, actual);
    }

    @Test
    public void getShortestRouteOnTheLine() {
       List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("1-я станция"), stationIndex.getStation("5-я станция"));
       List<Station> expected;
       expected = new ArrayList<>();
       expected.add(stationIndex.getStation("1-я станция"));
       expected.add(stationIndex.getStation("2-я станция"));
       expected.add(stationIndex.getStation("3-я станция"));
       expected.add(stationIndex.getStation("4-я станция"));
       expected.add(stationIndex.getStation("5-я станция"));
       assertEquals(expected, actual);
    }

    @Test
    public void calculateDurationOnTheLine() {

        route = new ArrayList<>();
        route.add(stationIndex.getStation("1-я станция"));
        route.add(stationIndex.getStation("2-я станция"));
        route.add(stationIndex.getStation("3-я станция"));
        route.add(stationIndex.getStation("4-я станция"));
        route.add(stationIndex.getStation("5-я станция"));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 10.0;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void calculateDurationWithOneConnection() {

        route = new ArrayList<>();
        route.add(stationIndex.getStation("1-я станция"));
        route.add(stationIndex.getStation("2-я станция"));
        route.add(stationIndex.getStation("3-я станция"));
        route.add(stationIndex.getStation("11-я станция"));
        route.add(stationIndex.getStation("12-я станция"));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 11.0;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void calculateDurationWithTwoConnections() {

        route = new ArrayList<>();
        route.add(stationIndex.getStation("1-я станция"));
        route.add(stationIndex.getStation("2-я станция"));
        route.add(stationIndex.getStation("3-я станция"));
        route.add(stationIndex.getStation("11-я станция"));
        route.add(stationIndex.getStation("12-я станция"));
        route.add(stationIndex.getStation("13-я станция"));
        route.add(stationIndex.getStation("8-я станция"));
        route.add(stationIndex.getStation("9-я станция"));
        route.add(stationIndex.getStation("10-я станция"));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 22.0;
        assertEquals(expected, actual, 0);
    }
}