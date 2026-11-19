
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/* CURRENT TODO:
- Figure out if dropping tables that are reliant on each other will be an issue, as 2/3 tables rely on another table existing to run properly
- Complete implementation of creature database. (Add more creatures.)

*/
public class GameDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:game.db"; // Creates One DB file with multiple tables

        
        // Table 1: Location Database
        /* Locations have an ID, name, description, and encounters.
        Encounters exists to dictate the size of that location, and how many encounters there'll be before progressing
        to the next area. They should link like this:
        Forest -> Desert or Snow -> Caves or City -> Forlorn Citadel (From City) or Forgotten Mountain (Caves)
         */
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            
            // Table 1: Location Database
            statement.executeUpdate("DROP TABLE IF EXISTS location"); 
            String sql_locations = "CREATE TABLE location (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL UNIQUE, " +
                    "description TEXT, " +
                    "encounters INTEGER NOT NULL " +
                ")";
            statement.executeUpdate(sql_locations);

            // Forest Location
            statement.executeUpdate("insert into location values(1, 'Forest', 'A dark, wooded forest filled with goblins. . .', '8')");
            // Desert Location
            statement.executeUpdate("insert into location values(2, 'Desert', 'Harsh dunes that batter your body with strong winds. . . You can taste the dust. . .', '10')");
            // Snow Location
            statement.executeUpdate("insert into location values(3, 'Snow', 'A white abyss of nothing but ice and snow. . . It's chilly. . .', '10')");
            // Caves Location
            statement.executeUpdate("insert into location values(4, 'Caves', 'Crystalline structures glitter in the walls. . . They're very pretty.', '12')");
            // City Location
            statement.executeUpdate("insert into location values(5, 'Burning City', 'Homes crumble to the ground as fire erupts from windows and doors. . . What happened here?', '12')");
            // Mountain Location
            statement.executeUpdate("insert into location values(6, 'The Forgotten Mountain', 'A dark presence can be felt here, as if something is off. . .', '6')");
            // Forlorn Citadel
            statement.executeUpdate("insert into location values(7, 'The Forlorn Citadel', 'Ashes are spread throughout the air as the capital stands charred. . . and broken. . .', '6')");



            // Table 2: Location Connections/Transitions
            // This table dictates what locations are connected to where:
            // Forest -> Desert or Snow -> Caves or City -> Forlorn Citadel (From City) or Forgotten Mountain (Caves)
            statement.executeUpdate("DROP TABLE IF EXISTS locationconnection"); 
            String sql_location_connections = "CREATE TABLE locationconnection (" +
                    "from_location_id INTEGER NOT NULL, " +
                    "to_location_id INTEGER NOT NULL, " +
                    "PRIMARY KEY (from_location_id, to_location_id), " +
                    "FOREIGN KEY (from_location_id) REFERENCES location(id), " +
                    "FOREIGN KEY (to_location_id) REFERENCES location(id) " +
                    ")";
                statement.executeUpdate(sql_location_connections);
            // Forest -> Desert Link
            statement.executeUpdate("insert into locationconnection values(1, 2)");
            // Forest -> Snow Link
            statement.executeUpdate("insert into locationconnection values(1, 3)");
            // Desert -> Caves
            statement.executeUpdate("insert into locationconnection values(2, 4)");
            // Desert -> City
            statement.executeUpdate("insert into locationconnection values(2, 5)");
            // Snow -> Caves
            statement.executeUpdate("insert into locationconnection values(3, 4)");
            // Snow -> City
            statement.executeUpdate("insert into locationconnection values(3, 5)");
            // Caves -> Mountain
            statement.executeUpdate("insert into locationconnection values(4, 6)");
            // City -> Citadel
            statement.executeUpdate("insert into locationconnection values(5, 7)");

            // Table 3: Creature Database
            // Includes the foreign key for locations, so that specific creatures are attributed to their home locations.
            statement.executeUpdate("DROP TABLE IF EXISTS creature"); 
            String sql_creatures = "CREATE TABLE creature (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "max_health INTEGER NOT NULL, " +
                    "damage INTEGER NOT NULL, " +       
                    "armor INTEGER NOT NULL, " + 
                    "location_id INTEGER NOT NULL, " +
                    "FOREIGN KEY (location_id) REFERENCES location(id)" +
                    ")";
                statement.executeUpdate(sql_creatures);

            // Forest monsters (Forest tag: "1")
            statement.executeUpdate("insert into creature values(1, 'Goblin', '30', '5', '0', 1)");
            // Desert monsters (Desert tag: "2")
            
            //display the contents of the Location table
            String sqlLocationQuery = "SELECT * FROM location"; // Return all (*) rows from Location table
            ResultSet rsLocation = statement.executeQuery(sqlLocationQuery);
                //loop through all of the results
                while(rsLocation.next())
                {
                    // read the result set
                    System.out.print(rsLocation.getString("name"));
                    System.out.println(" id: " + rsLocation.getInt("id"));
                }       

            //display the contents of the Creature table
            String sqlCreatureQuery = "SELECT * FROM creature"; // Return all (*) rows from creature_template table
            ResultSet rsCreature = statement.executeQuery(sqlCreatureQuery);
                //loop through all of the results
                while(rsCreature.next())
                {
                    // read the result set
                    System.out.print(rsCreature.getString("name"));
                    System.out.println(" id: " + rsCreature.getInt("id"));
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
