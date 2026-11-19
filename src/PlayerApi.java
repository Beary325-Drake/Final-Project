import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerApi {
    private static final String DATABASE_URL = "jdbc:sqlite:game.db";

    private PlayerApi() {
    }

    public static Object[] getPlayerById(int id) {
        String sql = "SELECT name, max_health, damage, armor FROM player WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    return null;
                }

                return new Object[] {
                    result.getInt("max_health"),
                    result.getInt("damage"),
                    5,
                    result.getInt("armor"),
                    result.getString("name")
                };
            }
        } catch (SQLException e) {
            System.out.println("Could not fetch player: " + e.getMessage());
            return null;
        }
    }
}
