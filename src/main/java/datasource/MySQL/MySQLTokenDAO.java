package datasource.MySQL;

import datasource.IConnection;
import datasource.ITokenDAO;
import domain.Token;

import javax.inject.Inject;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTokenDAO implements ITokenDAO {

    @Inject
    private IConnection connection;

    @Override
    public Token read(String tokenString) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Token_User WHERE Token = ?");
            stmt.setString(1, tokenString);
            ResultSet rs = stmt.executeQuery();
            Token t = null;
            while(rs.next()) {
                t = new Token(rs.getString(1), rs.getString(2));
            }
            conn.close();
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Token token) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Token_User(Token, User) VALUES(?, ?)");
            stmt.setString(1, token.getToken());
            stmt.setString(2, token.getUser());
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
