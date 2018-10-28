package main.java.model.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * 
 * For sending email
 *
 */
public class DES {
    private KeyGenerator keygen;
    
    // SecretKey (Responsible for saving symmetric keys)  
    private SecretKey deskey;
    
    // Cipher (Responsible for completing encryption or decryption)
    private Cipher c;
    
    // This byte array is responsible for saving the encrypted result 
    private byte[] cipherByte;

    public DES()

    {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        
        // Instantiate the key generator that supports the DES algorithm 
        // (the name of the algorithm name should be specified, otherwise 
        // an exception is thrown)
        try {
            keygen = KeyGenerator.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // Generate key 
        deskey = keygen.generateKey();
        
        // Generate a Cipher object and specify its supported DES algorithm
        try {
            c = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** 
     * Encrypt string
     * 
     * @param str 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */
    public byte[] encrytor(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // The Cipher object is initialized according to the key, 
        // and ENCRYPT_MODE indicates the encryption mode.
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] src = str.getBytes();

        // Encrypted, the result is saved in cipherByte 
        cipherByte = c.doFinal(src);

        return cipherByte;
    }

    /** 
     * Decrypt the string
     * 
     * @param buff 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */
    public byte[] decryptor(String password)

    {
        byte[] en = null;

        try {
            en = encrytor(password);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        
        }
        

        // The Cipher object is initialized according to the key, 
        // and DECRYPT_MODE indicates the encryption mode.
        try {
            c.init(Cipher.DECRYPT_MODE, deskey);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            cipherByte = c.doFinal(en);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return cipherByte;
    }
}