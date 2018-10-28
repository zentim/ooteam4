package main.java.model.util;
import javax.mail.*;
import javax.mail.internet.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.activation.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
    final private String mailFrom = "xxxxxx@gmail.com"; // your gmail
    final private String password = "xxxxxx"; // your gmail password
    
    
	DES d = new DES();
	
	final private String code = new String(d.Decryptor(password));
       
	public String getMail() {
	    return mailFrom;
	}
	
	public String getCode() {
		return code;
	}

}
