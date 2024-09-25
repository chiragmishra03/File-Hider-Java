package Views;
import DAO.UserDAO;
import Model.User;
import service.UserService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class StartedScreen {
    public void WelcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the application");
        System.out.println("Press 1 to Login");
        System.out.println("Press 2 to Signup");
        System.out.println("Press 0 to Exit");
        Integer choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
            case 0:
                System.out.println("Byebye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again."); // Handle unexpected values
                break;
        }


    }
    private void signup() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Enter your name");
            String name = br.readLine();
            System.out.println("Enter your email");
            String email = br.readLine();
            System.out.println("Enter your password");
            String pass = br.readLine();
            User createdUser = new User(name,email,pass);
            Integer res = UserService.saveUser(createdUser);
            if(res==0){
                System.out.println("User Already Exists");
            }
            else if(res==1){
                System.out.println("User Signup Successfull");
            }
        }catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email:");
        String email  = sc.next();
        System.out.println("Enter your password: ");
        String password = sc.next();
        if(UserService.AuthenticateUser(email,password)==1){
            UserView userView = new UserView(email);
            System.out.println("Successful Login");
            userView.homeForData();
        }
        else{
            System.out.println("Incorrect Password or EmailId");
        }
    }
}
