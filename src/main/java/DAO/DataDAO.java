package DAO;
import Model.Data;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static db.dbConnection.connection;
import static db.dbConnection.getConnection;
import Model.Data;
public class DataDAO {
    public Connection conn = getConnection();

public static List<Data> getAllFiles(String email){
    String query = "Select * from data where email=?";
    try {
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1,email);
        ResultSet st = pst.executeQuery();
        List<Data> data = new ArrayList<>();
        while(st.next()){
            int id = st.getInt(1);
            String fileName = st.getString(2);
            String filePath = st.getString(3);
            String bin_data = st.getString(5);
            Data d = new Data(id,fileName,filePath,bin_data,email);
            data.add(d);
        }
return data;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
public static int HideFile(Data file) {
        String query = "insert into data (fileName,filePath,email,bin_data) values(?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, file.getFileName());
            pst.setString(2, file.getFilePath());
            pst.setString(3, file.getEmail());
            File f = new File(file.getFilePath());
            try {
                FileReader fr = new FileReader(f);
                pst.setCharacterStream(4,fr,f.length());
                int ans = pst.executeUpdate();
                fr.close();
                f.delete();
                return ans;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String UnhideFile(int id) throws SQLException,IOException{
    PreparedStatement pst = connection.prepareStatement("Select filePath,bin_data from data where id = ?");
    pst.setInt(1,id);
    ResultSet st = pst.executeQuery();
    st.next();
    String path = st.getString("filePath");
    Clob c = st.getClob("bin_data");
    Reader r  = c.getCharacterStream();
    FileWriter f = new FileWriter(path);
    int i;
    while((i=r.read())!=-1){
        f.write((char)i);
    }
    f.close();
    pst = connection.prepareStatement("delete from data where id = ?");
    pst.setInt(1,id);
    int ans = pst.executeUpdate();
    return path;
}
}
