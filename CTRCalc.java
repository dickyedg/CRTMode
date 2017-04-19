import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CTRCalc {
	
	public static void main(String[] args) throws Exception {
		String s = "Masak indomie pake stopwatch digoreng dadakan cuma 500an hehehe";
	        
//	    String plainHex = HexStringConverter.getHexStringConverterInstance().stringToHex(s);
//        System.out.println("in HEX : " + plainHex + " " + plainHex.length());
//        System.out.println("Reconvert to String : " + HexStringConverter.getHexStringConverterInstance().hexToString(plainHex));
//	    
        String key = "Mytimeis gone haMytimeis gone ha"; //256 bit key
//        String keyHex = HexStringConverter.getHexStringConverterInstance().stringToHex(key);
//        System.out.println("in HEX : " + keyHex + " " + keyHex.length());
//        System.out.println("Reconvert to String : " + HexStringConverter.getHexStringConverterInstance().hexToString(keyHex));
//	    
        CTRnew myCTR = new CTRnew(key);
        //String binKey = myCTR.hexToBinary(keyHex);
        
        //ENKRIPSI
        byte[] plain = s.getBytes("UTF-8");
        byte[] keyy = key.getBytes("UTF-8");
        byte[] cipher = myCTR.encrypt(plain,keyy);
        String ciphertext = "";
        for(int i = 0;i<cipher.length;i++){
        	ciphertext = ciphertext + cipher[i];
        }
        System.out.println("Hasil ciphertext: " + ciphertext );
       
        //DECRYPT
        String plaintext = myCTR.decrypt(cipher,keyy);
        System.out.println("Hasil decrypt: " + plaintext);
	}
}

