package main.java.model.util;

/**
 * 
 * For sending email
 *
 */
public class MailUtil {

    /**
     * If you want to test send email to observer :
     * Step 1: you need to enter your email and password here.
     * Step 2: go to "https://myaccount.google.com/lesssecureapps" set
     *          low security application access
     */
    private static final String SENDER_EMAIL_ADDRESS = "xxxxxx@gmail.com"; // your gmail
    private static final String PASSWORD = "xxxxxx"; // your gmail password

    DES d = new DES();

    private final String code = new String(d.decryptor(PASSWORD));

    public String getMail() {
        return SENDER_EMAIL_ADDRESS;
    }

    public String getCode() {
        return code;
    }

}
