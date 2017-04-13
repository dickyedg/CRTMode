import java.util.Random;

import javax.crypto.*;

public class CTR {
	
	static byte[] IV;
	
	public CTR(int byteSize){
		
	}
	
	static byte[] generateIV(){
		String randomHex = "";
		for(int i=0;i<16;i++){
			randomHex += generateRandomHex();
		}
		System.out.println(randomHex);
		byte[] x = Util.hex2byte(randomHex);
		return x;
	}
	
	static String generateRandomHex(){
		Random rand = new Random();
		int myRandomNumber = rand.nextInt(0x10) + 0x10; // Generates a random number between 0x10 and 0x20
		String result = Integer.toHexString(myRandomNumber);
		return result;
	}
	
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
}
