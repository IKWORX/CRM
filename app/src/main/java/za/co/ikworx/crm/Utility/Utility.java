package za.co.ikworx.crm.Utility;

public class Utility {

    public static String getIP() {
        return IP;
    }

    public static String IP="http://192.168.176.33/androidDB/";
    private static String username1;

    private static int status;
    private static String salesID;



    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        Utility.status = status;
    }

    public static String getUsername() {
        return username1;
    }

    public  static void setUsername(String username) {
        username1 = username;
    }

    public static String getPassword() {
        return password1;
    }

    public static void setPassword(String password) {
        password1 = password;
    }

    private static String password1;


    public static String getSalesID() {
        return salesID;
    }

    public static void setSalesID(String salesID) {
        Utility.salesID = salesID;
    }
}
