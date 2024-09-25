package DAO;

import Model.User;
import Views.StartedScreen;
import com.mysql.cj.protocol.Resultset;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static db.dbConnection.getConnection;
public class UserDAO {

    public static Connection connection = getConnection();
    public static void CloseResources(PreparedStatement pstmt , ResultSet res){
        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(res!=null){
            try {
                res.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isExist(String email) throws SQLException{
        String prepQuery  = "select email from users where email=?";
        PreparedStatement pstmt = connection.prepareStatement(prepQuery);
        pstmt.setString(1,email);
        ResultSet res = pstmt.executeQuery();
        while(res.next()){
//            int id = res.getInt(1);
//            String name = res.getString(2);
            String e = res.getString(1);
            CloseResources(pstmt,res);
            if(e.equals(email))return true;
        }
        return false;
    }

    public static int CheckLogin(String email , String password){
        String query = "SELECT email,password from users where email=?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,email);
            ResultSet st = pst.executeQuery();
            while(st.next()){
                String e = st.getString(1);
                if(e.equals(email)){
                    String p = st.getString(2);
                    if(checkPassword(password,p)){
                        return 1;
                    }else{
                        return 0;
                    }
                }else{
                    return 0;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public static String encryptPass(String pass){
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
    public static boolean checkPassword(String enteredPassword, String storedHashedPassword) {
        return BCrypt.checkpw(enteredPassword, storedHashedPassword);
    }

    public static int SaveUser(User user) throws SQLException{
        boolean exist = isExist(user.getEmail());
        if(exist){
            System.out.println("User already Exists Try logging in");
            return 0;
        }
        String addQuery = "Insert into users(password,name,email) values(?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(addQuery);
        pstmt.setString(1,encryptPass(user.getPass()));
        pstmt.setString(2,user.getName());
        pstmt.setString(3,user.getEmail());
        int r = pstmt.executeUpdate();
        if(r>0){
            return 1;
        }
        else{
            System.out.println("There was some error creating the user");
        }
        return 0;
    }
}
