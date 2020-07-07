import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Data

public class DbConfig {
    public static String url;
    public static String user;
    public static String pass;

    public DbConfig (String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public static Statement getConnectAndStatement() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();
        return statement;
    }
}
