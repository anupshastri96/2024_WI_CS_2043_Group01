
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private int id;

    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public static User registerNewUser(Connection conn, String usernameIn, String passwordIn) throws SQLException {
        System.out.print(usernameIn);
        String username = usernameIn;
        int id = 0;
        boolean status = true;
        while (status) {
            try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username LIKE (?)")){
            stmt.setString(1, username);
            ResultSet set = stmt.executeQuery();
            if(!set.next()){break;}
            }  
            System.out.print("That username is already taken. Please choose another.\nusername: ");
            status = false;
            return null;

        }
        
        String password = passwordIn;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id) as id FROM users")) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) id = rs.getInt("id") + 1;
        }

        User user = new User(username, password, id);
        user.insertUser(conn);
        return user;
    }

    public static User login(Connection conn, String username, String password) throws SQLException {
        System.out.print("attempting to login\n");

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username LIKE (?)")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){

                String ActualPassword = rs.getString("password");
                int id = rs.getInt("id");

                if (!password.equals(ActualPassword)){
                    System.out.println("Incorrect password. Please try again.");
                    return null;
                }

                return new User(username, password, id);
            }
        }
        
        catch(Exception e){System.out.println("Error: "  + e.getMessage());}
        return null;
    }

    public String getUsername() { return username; }

    public String getpassword() { return password; }

    public int getId() { return id; }

    private void insertUser(Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(id, username, password) VALUES(?,?,?)")) {
            stmt.setInt(1, this.id);
            stmt.setString(2, this.username);
            stmt.setString(3, this.password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting new user.");
            throw e;
        }
    }
}

