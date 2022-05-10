import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Properties;
import java.sql.*;
import java.net.URL;

public class GoodLoader {
    private static final int BATCH_SIZE = 500;//用于批处理
    private static URL propertyURL = GoodLoader.class
            .getResource("/loader.cnf");

    private static Connection con = null;
    private static PreparedStatement stmt = null;
    private static boolean verbose = false;

    private static void openDB(String host, String dbname,
                               String user, String pwd) {
        try {
            //
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
            System.exit(1);
        }
        String url = "jdbc:postgresql://" + host + "/" + dbname;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        try {
            con = DriverManager.getConnection(url, props);
            if (verbose) {
                System.out.println("Successfully connected to the database "
                        + dbname + " as " + user);
            }
            con.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            /**
             * here need reconsideration
             */
            stmt = con.prepareStatement("insert into users(login,id, name)"
                    + " values(?,?,?)");
        } catch (SQLException e) {
            System.err.println("Insert statement failed");
            System.err.println(e.getMessage());
            closeDB();
            System.exit(1);
        }
    }

    private static void closeDB() {
        if (con != null) {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                con.close();
                con = null;
            } catch (Exception e) {
                // Forget about it
            }
        }
    }

    private static void loadData(String login, Long id, String name)
            throws SQLException {
        if (con != null) {
            stmt.setString(1, login);

            stmt.setLong(2, id);

            stmt.setString(2, name);
            stmt.addBatch();
        }
    }

    public static void ImportUsersInfo(List<User> users) throws SQLException {


        if (propertyURL == null) {
            System.err.println("No configuration file (loader.cnf) found");
            System.exit(1);
        }
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "postgres");
        defprop.put("password", "123456");
        defprop.put("database", "postgres");
        Properties prop = new Properties(defprop);


        long start;
        long end;

        int cnt = 0;
        // Empty target table
        openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0;
        if (con != null) {
            stmt0 = con.createStatement();
            stmt0.execute("truncate table students");
            stmt0.close();
        }
        closeDB();

        //
        start = System.currentTimeMillis();
        openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            loadData(user.login, user.id, user.name);
            cnt++;
        }

        if (cnt % BATCH_SIZE == 0) {
            stmt.executeBatch();
            stmt.clearBatch();
        }


        if (cnt % BATCH_SIZE != 0) {
            stmt.executeBatch();
        }
        con.commit();
        stmt.close();

        closeDB();

        end = System.currentTimeMillis();
        System.out.println(cnt + " records successfully loaded");
        System.out.println("Loading speed : "
                + (cnt * 1000) / (end - start)
                + " records/s");

        closeDB();
    }
}

