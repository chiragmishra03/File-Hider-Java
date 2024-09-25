package Model;

public class Data {
    private int id;
    private String email;
    private String fileName;
    private String filePath;
    private String bin_data;

    // Constructor to include the 'email' parameter
    public Data(int id, String fileName, String filePath, String bin_data, String email) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.bin_data = bin_data;
        this.email = email; // Set the email properly
    }

    public int getId() {
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getBin_data() {
        return bin_data;
    }
}
