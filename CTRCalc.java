public class CTRCalc {
	
	public static void main(String[] args) {
		String s = "Nowisthe time foNowisthe time fo";
	        
	    String plainHex = HexStringConverter.getHexStringConverterInstance().stringToHex(s);
        System.out.println("in HEX : " + plainHex + " " + plainHex.length());
        System.out.println("Reconvert to String : " + HexStringConverter.getHexStringConverterInstance().hexToString(plainHex));
	    
        String key = "Mytimeis gone ha";
        String keyHex = HexStringConverter.getHexStringConverterInstance().stringToHex(key);
        System.out.println("in HEX : " + keyHex + " " + keyHex.length());
        System.out.println("Reconvert to String : " + HexStringConverter.getHexStringConverterInstance().hexToString(keyHex));
	    
        CTR myCTR = new CTR(keyHex.length());
        
        System.out.println(myCTR.getRandomHex());
        
        //ENKRIPSI BELOM RAPI
        String cipher = "";
        boolean noPadding = plainHex.length() % keyHex.length() == 0;
        if(noPadding){
        	int round = plainHex.length() / keyHex.length();
        	cipher = myCTR.encrypt(plainHex, keyHex, round);
        	System.out.println(cipher + " " + cipher.length());
        }
        
       //DECRYPT BELOM RAPI
        if(noPadding){
        	int round = plainHex.length() / keyHex.length();
        	String plain = myCTR.decrypt(cipher, keyHex, round);
    	    System.out.println(plain);
    	    System.out.println("Reconvert to String : " + HexStringConverter.getHexStringConverterInstance().hexToString(plain));  		    
        }
	}
}

