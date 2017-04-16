import java.util.Random;

import javax.crypto.*;

public class CTR {
	
	String randomHex;
	
	public CTR(int keylen){
		randomHex = generateNonce(keylen);
	}
	
	public String getRandomHex() {
		return randomHex;
	}

	public void setRandomHex(String randomHex) {
		this.randomHex = randomHex;
	}

	//generate IV berdasarkan panjang key user
	byte[] generateIV(int keylen, int round){
		String counter = generateCTR(keylen,round);
		String IV = randomHex + counter;
		System.out.println("yang masuk ke AES : " + IV);
		byte[] x = Util.hex2byte(IV);
		return x;
	}
	
	String generateNonce(int keylen){
		String randomHex = "";
		if(keylen == 32){
			for(int i=0;i<8;i++){
				randomHex += generateRandomHex();
			}
		}	
		else if(keylen == 48){
			for(int i=0;i<12;i++){
				randomHex += generateRandomHex();
			}
		}else {
			for(int i=0;i<16;i++){
				randomHex += generateRandomHex();
			}
		}
		return randomHex;
	}
	//buat generate counter
	String generateCTR(int keylen, int round){
		String seqResult = "";
		if(keylen == 32){
			for(int i=0;i<15;i++){
				seqResult += "0";
			}
		}	
		else if(keylen == 47){
			for(int i=0;i<24;i++){
				seqResult += "0";
			}
		}else {
			for(int i=0;i<31;i++){
				seqResult += "0";
			}
		}
		String roundHex = "";
		if(round>9){
			roundHex = Integer.toHexString(round);
			seqResult = seqResult + roundHex;
		}else{
			seqResult = seqResult + round;
		}
		return seqResult;
	}
	
	//bikin random hex 2 digit
	String generateRandomHex(){
		Random rand = new Random();
		int myRandomNumber = rand.nextInt(0x10) + 0x10; // Generates a random number between 0x10 and 0x20
		String result = Integer.toHexString(myRandomNumber);
		return result;
	}
	
	
	
	//buat XOR, gatau kepake apa kagak
	byte XOR(byte[] key, byte[] IV){
		String a = "";
		String b = "";
		for(int i=0;i<key.length;i++){
			a += key[i];
			b += key[i];
		}
		int aInt = Integer.parseInt(a, 2);
		int bInt = Integer.parseInt(b, 2);

		byte aByte = (byte)aInt;
		byte bByte = (byte)bInt;

		// conversion routine compacted into single line
		byte xor = (byte)(0xff & ((int)aByte) ^ ((int)bByte));
		
		return xor;
	}
	
	//INPUT UDAH DALAM HEX
	String encrypt(String plaintext, String key, int round){
		String hasil = "";
		for(int i = 0; i<round;i++){
			String processStr = "";
    		if(i==0){
    			processStr = plaintext.substring(0,key.length());
    		} else{
    			processStr = plaintext.substring(i*key.length(),(i+1)*key.length());
    		}
    		
			int keylen = key.length();
			byte[] keyAES = Util.hex2byte(key);
		
			AES testAES = new AES();
			testAES.setKey(keyAES);
		
			byte[] data = new byte[keylen];
		
			data = generateIV(keylen, i);
		
			byte[] resultAES = testAES.encrypt(data);
		
			String resultHex = Util.toHEX(resultAES);
			resultHex = resultHex.replaceAll("\\s+","");
		
			System.out.println("Yang di xor : " + resultHex + " , " + processStr);
			String resultXOR = xorHex(processStr, resultHex);
			System.out.println("hasil xor : " + resultXOR);
			hasil = hasil + resultXOR;
		}
		hasil = getRandomHex() + hasil;
		return hasil;
	}
	
	String decrypt(String ciphertext, String key, int round){
		String hasil = "";
		for(int i = 0; i<round;i++){
			int keylen = key.length();
			byte[] keyAES = Util.hex2byte(key);
		
			AES testAES = new AES();
			testAES.setKey(keyAES);
		
			byte[] data = new byte[keylen];
		
			String IV = ciphertext.substring(0, randomHex.length());
			String actualCipher = ciphertext.substring(randomHex.length(),ciphertext.length());
			
			String processStr = "";
    		if(i==0){
    			processStr = actualCipher.substring(0,key.length());
    		} else{
    			processStr = actualCipher.substring(i*key.length(),(i+1)*key.length());
    		}
    		
			System.out.println("IV : " + IV);
			data = Util.hex2byte(IV + generateCTR(keylen,i));
			System.out.println(data.length);
			byte[] resultAES = testAES.encrypt(data);
		
			String resultHex = Util.toHEX(resultAES);
			resultHex = resultHex.replaceAll("\\s+","");
			
			System.out.println("Yang di xor : " + resultHex + " , " + processStr);
			String resultXOR = xorHex(processStr, resultHex);
			System.out.println("hasil xor : " + resultXOR);
			hasil = hasil + resultXOR;
		}
		return hasil;
	}
	
	//utility XOR
	public String xorHex(String a, String b) {
	    // TODO: Validation
	    char[] chars = new char[a.length()];
	    for (int i = 0; i < chars.length; i++) {
	        chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
	    }
	    return new String(chars);
	}

	private static int fromHex(char c) {
	    if (c >= '0' && c <= '9') {
	        return c - '0';
	    }
	    if (c >= 'A' && c <= 'F') {
	        return c - 'A' + 10;
	    }
	    if (c >= 'a' && c <= 'f') {
	        return c - 'a' + 10;
	    }
	    throw new IllegalArgumentException();
	}

	private char toHex(int nybble) {
	    if (nybble < 0 || nybble > 15) {
	        throw new IllegalArgumentException();
	    }
	    return "0123456789ABCDEF".charAt(nybble);
	}
}
