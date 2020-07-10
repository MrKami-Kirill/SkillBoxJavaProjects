import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class DBConnection
{
    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "KiGa9879874";

    private static PreparedStatement voterPreparedStatement;
    private static PreparedStatement visitPreparedStatement;

    private static int voterCount = 0; //count voters in batch
    private static int visitCount= 0; //count visits in batch
    private static int allVoterCount = 0;
    private static int allVisitCount= 0;
    private static final int VOTER_BATCH_LIMIT = 10_000; //limit voters in batch
    private static final int VISIT_BATCH_LIMIT = 10_000; //limit visits in batch

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass + "&useUnicode=true" +
                                "&useJDBCCompliantTimeZoneShift=true" +
                                "&useLegacyDatetimeCode=false" +
                                "&serverTimezone=UTC");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void createTable() throws SQLException {
        try {
            connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
            connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY (id), " +
                    //"KEY (name(50)), " +
                    "UNIQUE KEY name_date(name(50), birthDate))");
            connection.createStatement().execute("DROP TABLE IF EXISTS station_time");
            connection.createStatement().execute("CREATE TABLE station_time(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "`station` INT NOT NULL, " +
                    "time_start DATETIME NOT NULL, " +
                    "time_finish DATETIME NOT NULL, " +
                    "PRIMARY KEY (id), " +
                    "KEY (`station`), " +
                    "UNIQUE KEY (`station`))");
        } catch (SQLException exception) {
            log.error(exception);
        }

    }

    public static void getPreparedStatement() throws Exception {
        if (connection == null) {
            getConnection();
        }
        DBConnection.createTable();
        StringBuilder voterBuilder = new StringBuilder("INSERT INTO voter_count(name, birthDate, `count`) ")
                .append("VALUES (?, ?, 1)")
                .append("ON DUPLICATE KEY UPDATE `count`=`count` + 1");
        voterPreparedStatement = connection.prepareStatement(voterBuilder.toString());
        StringBuilder visitBuilder = new StringBuilder("INSERT INTO station_time(`station`, time_start, time_finish) ")
                .append("VALUES (?, ?, ?)")
                .append("AS new (s, ts, tf)")
                .append("ON DUPLICATE KEY UPDATE ")
                .append("time_start=(SELECT IF(ts < time_start, ts, time_start)), ")
                .append("time_finish=(SELECT IF(tf > time_finish, tf, time_finish))");
        visitPreparedStatement = connection.prepareStatement(visitBuilder.toString());
    }
    public static void loadingVotersWithBatchIntoDB(String name, String birthDay) throws SQLException
    {
        if (voterCount >= VOTER_BATCH_LIMIT) {
            long start = System.currentTimeMillis();
            connection.setAutoCommit(false);
            voterPreparedStatement.executeBatch();
            connection.setAutoCommit(true);
            voterPreparedStatement.clearBatch();
            voterCount = 0;
            log.info("(Voter) The batch was successfully uploaded to the database for " + (System.currentTimeMillis() - start) + " ms.");
            log.info("(Voter) " + allVoterCount + " records loaded into the database.");
        }
        birthDay = birthDay.replace('.', '-');
        voterPreparedStatement.setString(1, name);
        voterPreparedStatement.setString(2, birthDay);
        voterPreparedStatement.addBatch();
        voterCount++;
        allVoterCount++;
    }

    public static void loadingVisitsWithBatchIntoDB(Integer station, String time) throws SQLException
    {
        if (visitCount >= VISIT_BATCH_LIMIT) {
            long start = System.currentTimeMillis();
            connection.setAutoCommit(false);
            visitPreparedStatement.executeBatch();
            connection.setAutoCommit(true);
            visitPreparedStatement.clearBatch();
            visitCount = 0;
            log.info("(Visit) The batch was successfully uploaded to the database in " + (System.currentTimeMillis() - start) + " ms.");
            log.info("(Visit) " + allVisitCount + " records loaded into the database.");
        }
        time = time.replace('.', '-');
        visitPreparedStatement.setInt(1, station);
        visitPreparedStatement.setString(2,time);
        visitPreparedStatement.setString(3,time);
        visitPreparedStatement.addBatch();
        visitCount++;
        allVisitCount++;
    }

    public static void loadLeftovers() throws SQLException {
        long start = System.currentTimeMillis();
        boolean commit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        voterPreparedStatement.executeBatch();
        visitPreparedStatement.executeBatch();
        connection.setAutoCommit(commit);
        voterPreparedStatement.clearBatch();
        visitPreparedStatement.clearBatch();
        voterCount=0;
        visitCount=0;
        voterPreparedStatement.close();
        visitPreparedStatement.close();
        log.info("The leftovers was successfully uploaded to the database in " + (System.currentTimeMillis() - start) + " ms.");
        log.info("(Voter) " + allVoterCount + " records loaded into the database.");
        log.info("(Visit) " + allVisitCount + " records loaded into the database.");
    }

    public static void printDuplicateVoter() throws SQLException {
        String query = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery(query);
        System.out.println("Duplicated voters:");
        while (resultSet.next()) {
            System.out.println("\t" + resultSet.getString("name") + " (" + resultSet.getString("birthDate") + ")" + " - " + resultSet.getInt("count"));
        }
    }

    public static void printWorkTimeStation() throws SQLException {
        String query = "SELECT station, time_start, time_finish FROM station_time ORDER BY station ASC";
        ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery(query);
        System.out.println("Voting station work times:");
        while (resultSet.next()) {
            System.out.println("\t Station №" + resultSet.getInt("station") +
                    " working from '" + resultSet.getString("time_start") + "' to '"
                    + resultSet.getString("time_finish") + "';");
        }
    }

    public static void searchVoter(String name) throws SQLException {
        long start = System.currentTimeMillis();
        String query = "SELECT ID, birthDate FROM voter_count WHERE name = '" + name + "'";
        ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery(query);
        if (!resultSet.next()) {
            //resultSet.close();
            System.out.println("Voter - " + name + " not found!");
        } else {
            System.out.println("Voter - " + name + "(" + resultSet.getString("birthDate") + ") has ID = " + resultSet.getInt("ID") + ".");
            //resultSet.close();
        }
        log.info("Query duration: " + (System.currentTimeMillis() - start) + " ms.");
    }

    public static void searchStation(String station) throws SQLException {
        long start = System.currentTimeMillis();
        if (isNumeric(station)) {
            String query = "SELECT `station`, time_start, time_finish FROM station_time WHERE `station` = " + station;
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery(query);
            if (!resultSet.next()) {
                //resultSet.close();
                System.out.println("Station №" + station + " not found!");
            } else {
                //resultSet.close();
                System.out.println("Station №" + station + " working from '" + resultSet.getString("time_start") + "' to '"
                        + resultSet.getString("time_finish") + ".");
            }
        } else {
            System.out.println("Not a number!!!");
        }
        log.info("Query duration: " + (System.currentTimeMillis() - start) + " ms.");
    }

    public static boolean isNumeric(String str)
    {
        try {
            int number = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
