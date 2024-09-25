package service;

import DAO.DataDAO;
import Model.Data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataService {

    public static List GetFiles(String email){
        List <Data> listofFiles = new ArrayList <>();
        listofFiles = DataDAO.getAllFiles(email);
        return listofFiles;
    }
    public static int HideFile(Data file){
        return DataDAO.HideFile(file);
    }
    public static String UnhideFile(int id){
        try {
            return DataDAO.UnhideFile(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
