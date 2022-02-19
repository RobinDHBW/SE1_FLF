package CentralUnit;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class CryptoUnit {

    private String stringToBit(String input){
       return new BigInteger(input.getBytes(StandardCharsets.UTF_8)).toString(2);
    }

    private String bitToString(String input){
        return new String(new BigInteger(input, 2).toByteArray());
    }

    //@TODO write logic
    public String decrypt(String cipher, String key){
        String BitCipher = stringToBit(cipher);
        return null;
    }

}
