package src;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDatabase {
    private static final String URL = "jdbc:sqlite:data/game.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() throws Exception {
        Files.createDirectories(Path.of("data"));

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.execute("""
                CREATE TABLE IF NOT EXISTS creature_templates (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL UNIQUE,
                    max_health INTEGER NOT NULL,
                    damage INTEGER NOT NULL,
                    armor INTEGER NOT NULL
                )
                """);

            try (PreparedStatement insert = connection.prepareStatement("""
                INSERT OR IGNORE INTO creature_templates
                    (name, max_health, damage, armor)
                VALUES (?, ?, ?, ?)
                """)) {
                insert.setString(1, "Goblin");
                insert.setInt(2, 30);
                insert.setInt(3, 6);
                insert.setInt(4, 0);
                insert.executeUpdate();
            }
        }
    }
}
