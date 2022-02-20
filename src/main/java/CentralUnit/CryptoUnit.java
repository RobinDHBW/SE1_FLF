package CentralUnit;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class CryptoUnit {
    private final KeySchedule keySchedule = new KeySchedule();
    private final InitialPermutation initialPermutation = new InitialPermutation();
    private final FeistelNetwork feistelNetwork = new FeistelNetwork();
    private final IPInverse ipInverse = new IPInverse();

    public String stringToBit(String input){
       return this.prepareBitString(new BigInteger(input.getBytes(StandardCharsets.UTF_8)).toString(2));
    }

    public String bitToString(String input){
        return new String(new BigInteger(input, 2).toByteArray());
    }

    private String prepareBitString(String input){
        while (input.length()<64){
            input = "0"+input;
        }
        return input;
    }

    public String decrypt(String cipher, String key){
        return this.bitToString(this.ipInverse.permute(this.feistelNetwork.iterate(this.initialPermutation.permute(cipher),this.keySchedule.schedule(this.stringToBit(key), false))));
    }

    public String encrypt(String plain, String key){
        return this.ipInverse.permute(this.feistelNetwork.iterate(this.initialPermutation.permute(stringToBit(plain)),this.keySchedule.schedule(stringToBit(key), true)));
    }

}
