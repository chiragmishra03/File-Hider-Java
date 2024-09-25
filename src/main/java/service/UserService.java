package service;

import DAO.UserDAO;
import Model.User;

public class UserService {

    public static Integer AuthenticateUser(String email,String Password){
        try{
            if(UserDAO.CheckLogin(email,Password)==1){
                return 1;
            }
            else {
                return 0;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }



    public static Integer saveUser(User user){
        try{
            if(UserDAO.isExist(user.getEmail())){
                return 0;
            }
            else{
                UserDAO.SaveUser(user);
                return 1;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }
}
