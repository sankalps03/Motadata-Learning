

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

public class database {


    static String jdbcURL = "jdbc:h2:~/test";

    static String username = "sankalp";

    static String password = "sankalp";


    public static void insert(action act) {


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

    public static String select() {

        String selectOutput;


        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);) {

            System.out.println("Connected to H2 database.");

            PreparedStatement pst = connection.prepareStatement("select * from MOTADATA");

            ResultSet allRows = pst.executeQuery();

            JSONObject json = null;
            JSONArray jsonArray = new JSONArray();

            while (allRows.next())
            {
                json = new JSONObject();

                json.put("name", allRows.getString("NAME"));

                json.put("email",allRows.getString("EMAIL"));

                json.put("ally", allRows.getString("ALLY"));

                jsonArray.add(json);

            }

            selectOutput = jsonArray.toJSONString();

            System.out.println(selectOutput);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return selectOutput ;
    }
}
