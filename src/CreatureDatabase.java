
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatureDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:CreatureDatabase.db";

        

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            
            // Create a table for creatures. (Final Product: Make it so it doesn't get replaced)
            // Do this by adding IF NOT EXISTS between CREATE TABLE and creature_templates
            statement.executeUpdate("DROP TABLE IF EXISTS creature_templates"); //Remove in final product, or javadoc out.
            String sql = "CREATE TABLE Pet (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "max_health INTEGER NOT NULL, " +
                    "damage INTEGER NOT NULL, " +       
                    "armor INTEGER NOT NULL, " + 
                    // "location TEXT NOT NULL" + // (Maybe if null it can appear anywhere?)   
                    ")";
            /*statement.execute("""
                CREATE TABLE creature_template ( 
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL UNIQUE,
                    max_health INTEGER NOT NULL,
                    damage INTEGER NOT NULL,
                    armor INTEGER NOT NULL
                )
                """);
            */
                statement.executeUpdate(sql);

                // Creature template: (I'll make all of these)
            /*
            Creates *Creature*
            statement.executeUpdate("insert into creature_template values(1, 'name', 'max_hp', 'dmg', 'armor')"); 
            */
            // Forest monsters (Add forest tag once )
            statement.executeUpdate("insert into creature_template values(1, 'Goblin', '30', '5', '0')");
            //display the contents of the Creature table
            String sqlQuery = "SELECT * FROM Pet"; // Return all (*) rows from Pet table
            ResultSet rs = statement.executeQuery(sqlQuery);
                //loop through all of the results
                while(rs.next())
                {
                    // read the result set
                    System.out.print(rs.getString("name"));
                    System.out.println(" id: " + rs.getInt("id"));
                }         

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

/*
public class CreateDB {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:pets.db";

        // try-with-resources automatically closes the connection and statement.
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            // WARNING: Deletes the existing Pet table and all its records.
            // This allows us to start with the same sample data each time.
            statement.executeUpdate("DROP TABLE IF EXISTS Pet");

            String sql = """
                CREATE TABLE IF NOT EXISTS creature_templates (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL UNIQUE,
                    max_health INTEGER NOT NULL,
                    damage INTEGER NOT NULL,
                    armor INTEGER NOT NULL
                )
                """);
            statement.executeUpdate(sql);

            statement.executeUpdate("insert into Pet values(1, 'Buddy', 'Dog', '2018-08-04', '2024-05-20', 'Male', 'Brown', true, 'Labrador')");


*/