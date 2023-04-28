import java.sql.*;

public class database {

    public static void insert(action act) {


        String jdbcURL = "jdbc:h2:~/test";

        String username = "sankalp";

        String password = "sankalp";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);) {

            System.out.println("Connected to H2 database.");

            PreparedStatement pst = connection.prepareStatement("insert  into Motadata values(?,?,?)");

            pst.setString(1, act.getName());

            pst.setString(2, act.getEmail());

            pst.setString(3, act.getMotadataally());

            pst.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}
