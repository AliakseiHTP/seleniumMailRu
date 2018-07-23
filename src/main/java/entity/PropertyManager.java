package entity;

import java.util.ResourceBundle;

public class PropertyManager {
    private static final ResourceBundle rb;

    static {
        rb = ResourceBundle.getBundle("data");
    }

    public static String getProperty(String s){
        return rb.getString(s);
    }
}
