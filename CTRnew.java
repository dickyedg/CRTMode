package AES;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CTRnew {

    private SecureRandom r = new SecureRandom();
    private Cipher c;
    //private IvParameterSpec IV;
    //private SecretKeySpec s_KEY;
    
    // Constructor
    public CTRnew(String key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        this.c = Cipher.getInstance("AES/CTR/PKCS5PADDING");
        //this.IV = generateIV();
        //this.s_KEY = generateKEY(key);
    }

    protected byte[] encrypt(byte[] plain, byte[] keyy) throws InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {

    	SecretKeySpec s_KEY = new SecretKeySpec(keyy, "AES");

    	//bikin IV
    	byte[] newSeed = r.generateSeed(16);
        r.setSeed(newSeed);

        byte[] byteIV = new byte[16];
        r.nextBytes(byteIV);
        IvParameterSpec IV = new IvParameterSpec(byteIV);
    	
        System.out.println(s_KEY);
        this.c.init(Cipher.ENCRYPT_MODE, s_KEY, IV);
        byte[] encryptedBytes = this.c.doFinal(plain);
       
        byte[] result = concateEncryption(byteIV,encryptedBytes);
        
        for(int i=0;i<byteIV.length;i++){
        	System.out.print(byteIV[i] + " ");
        }
        System.out.println();
        return result;

    }

    protected byte[] decrypt(byte[] byteToDecrypt, byte[] key) throws InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException {

    	byte[] byteIV = new byte[16];
    	byte[] actualCipher = new byte[byteToDecrypt.length - 16];
    	int counter = 0;
    	for(int i=0;i<byteToDecrypt.length;i++){
    		if(i>15){
    			actualCipher[counter] = byteToDecrypt[i];
    			counter++;
    		} else {
    			byteIV[i] = byteToDecrypt[i];
    		}
    	}
    	for(int i=0;i<byteIV.length;i++){
    		System.out.print(byteIV[i] + " ");
        }
    	System.out.println();
    	IvParameterSpec IV = new IvParameterSpec(byteIV);
    	
    	SecretKeySpec s_KEY = new SecretKeySpec(key, "AES");
        this.c.init(Cipher.DECRYPT_MODE, s_KEY, IV);

        byte[] plainByte = this.c.doFinal(actualCipher);

        return plainByte;

    }

    //buat gabungin IV ke ciphertext
    byte[] concateEncryption(byte[] IV, byte[] actualCipher){
    	byte[] cipher = new byte[IV.length + actualCipher.length];
    	System.arraycopy(IV, 0, cipher, 0, IV.length);
    	System.arraycopy(actualCipher, 0, cipher, IV.length, actualCipher.length);
    	return cipher;
    }
    
//    private IvParameterSpec generateIV() {
//        
//       
//        
//        return IV;
//    }

//	KALO MAU KEYNYA DIACAK ACAK JUGA
//    private SecretKeySpec generateKEY(String mykey) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//
//        // byte[] bytKey = AES_KEY.getBytes(); // Converts the Cipher Key to
//        // Byte format
//        // Should I use SHA-2 to get a random key or is this better?
//
////        byte[] newSeed = r.generateSeed(32);
////        r.setSeed(newSeed);
//
//    	MessageDigest sha = null;
//        
//        byte[] key = mykey.getBytes("UTF-8");
//        if(key.length==16){
//        	sha = MessageDigest.getInstance("SHA-1");
//        	key = sha.digest(key);
//        	key = Arrays.copyOf(key, 16); // use only first 128 bit
//        	System.out.println(key.length);
//        	System.out.println(new String(key,"UTF-8"));
//        	s_KEY = new SecretKeySpec(key, "AES");
//        } else if(key.length == 24){
//            sha = MessageDigest.getInstance("MD5");
//            key = sha.digest(key);
//            key = Arrays.copyOf(key, 24); // use only first 128 bit
//            System.out.println(key.length);
//            System.out.println(new String(key,"UTF-8"));
//            s_KEY = new SecretKeySpec(key, "AES");
//        } else {
//        	sha = MessageDigest.getInstance("SHA-256");
//            key = sha.digest(key);
//            System.out.println(key.length);
//            System.out.println(new String(key,"UTF-8"));
//            s_KEY = new SecretKeySpec(key, "AES");
//        }
//
//        return s_KEY;
//
//    }

    public String byteArrayToString(byte[] s) {
        String string = new String(s);

        return string;

    }

    // Get Methods for all class variables
    public Cipher getCipher() {

        return c;
    }

//    public IvParameterSpec getIV() {
//        return IV;
//    }

//    public SecretKey getSecretKey() {
//        return s_KEY;
//    }
 
    public String hexToBinary(String hex) {
    	int i = Integer.parseInt(hex, 16);
    	String bin = Integer.toBinaryString(i);
    	return bin;
    }

}