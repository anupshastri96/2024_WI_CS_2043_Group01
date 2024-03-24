
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.CallableStatement;

public class User {
    private String username;
    private String password;

    private int id;

    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;

    }

    public static User registerNewUser(Connection conn, Scanner input) throws SQLException {
        System.out.print("Enter your desired username.\nusername: ");
        String username = input.next();
        int id = 0;

        while (true) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username LIKE (?)");
            stmt.setString(1, username);
            ResultSet set = stmt.executeQuery();
            if(set.next()){
                System.out.println("Username already taken");
                username = input.next();
            }
            else break;
        }
        
        System.out.print("Enter your desired password.\npassword: ");
        String password = input.next();
        PreparedStatement idNum = conn.prepareStatement("select max(id) from users");
        ResultSet IDnum = idNum.executeQuery();
        while(IDnum.next())id = IDnum.getInt(1)+1;
        CallableStatement newUser = conn.prepareCall("insert into users values(?,?,?)");

        newUser.setInt(1, id);
        newUser.setString(2,username );
        newUser.setString(3, password);

        newUser.executeUpdate();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id) as id FROM users")) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) id = rs.getInt("id") + 1;
        }

        User user = new User(username, password, id);
        user.insertUser(conn);
        return user;
    }

    public static User login(Connection conn, Scanner input) throws SQLException {
        System.out.print("\nEnter your username and password.\nusername: ");
        String username = input.next();
        System.out.print("password: ");
        String password = input.next();

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username LIKE (?)")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

                password = rs.getString("password");
                int id = rs.getInt("id");

                return new User(username, password, id);
            }
        
        catch(Exception e){System.out.println(e.getMessage());}
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

