import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main
{
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/sys?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String pass = "KiGa9879874";
        try {
            DbConfig dbConfig = new DbConfig(url, user, pass);
            ResultSet resultSet = DbConfig.getConnectAndStatement().executeQuery("SELECT course_name, COUNT(subscription_date)/MAX(MONTH(subscription_date)) AS Avg_purchases FROM purchaselist GROUP BY course_name ORDER BY Avg_purchases DESC;");
            while (resultSet.next()) {
                String avgPurchases = resultSet.getString("Avg_purchases");
                String courseName = resultSet.getString("course_name");
                System.out.println(courseName + " | " +  avgPurchases);
            }
            resultSet.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
