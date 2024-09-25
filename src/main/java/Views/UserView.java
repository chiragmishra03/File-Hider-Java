package Views;

import Model.Data;
import service.DataService;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email ;
    public UserView(String email) {
        this.email  = email;
    }
    public void homeForData(){
        do{
            System.out.println("Welcome" + this.email);
            System.out.println("Press 1 to show hidden files");
            System.out.println("Press 2 to hide new file");
            System.out.println("Press 3 to Unhide a file");
            System.out.println("Press 0 to exit");
            Scanner sc = new Scanner(System.in);
            Integer choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1->{
                    List <Data > ls = DataService.GetFiles(this.email);
                    for (Data file : ls){
                        System.out.println(file.getId()+" "+file.getFileName());
                    }
                }
                case 2->{
                    System.out.println("Enter the file path");
                    String path = sc.next();
                    File f = new File(path);
                    Data dataFile = new Data(0,f.getName(),path,"",this.email);
                    DataService.HideFile(dataFile);
                }
                case 3->{
                    List<Data> ls = DataService.GetFiles(this.email);
                    for (Data file : ls){
                        System.out.println(file.getId()+" "+file.getFileName());
                    }
                    System.out.println("Enter file id");
                    int id = Integer.parseInt(sc.next());

                    boolean isValidId = false;

                    for (Data file : ls){
                        if(file.getId()==id){
                            isValidId=true;
                            break;
                        }
                    }
                    if(isValidId){
                        System.out.println("File location-> "+ DataService.UnhideFile(id));
                    }
                    else{
                        System.out.println("Invalid ID entered");
                    }
                }
                case 0->{
                    System.exit(0);
                }
            }
        }while(true);
    }



}
