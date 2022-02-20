package CentralUnit;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class CryptoUnit {
    private final KeySchedule keySchedule = new KeySchedule();
    private final InitialPermutation initialPermutation = new InitialPermutation();
    private final FeistelNetwork feistelNetwork = new FeistelNetwork();
    private final IPInverse ipInverse = new IPInverse();

    public String pushToNextByte(String input) {
        Integer nextByte = (int) (Math.pow(2, (int) (Math.log(input.length()) / Math.log(2)) + 1));
        while (input.length() < nextByte) {
            input = "0" + input;
        }
        return input;
    }

    public String pushTo64Bit(String input){
        while(input.length() < 64){
            input = "0" + input;
        }
        return  input;
    }

    public String stringToBit(String input) {
        return this.pushToNextByte(new BigInteger(input.getBytes(StandardCharsets.UTF_8)).toString(2));
    }

    public String bitToString(String input) {
        return new String(new BigInteger(input, 2).toByteArray());
    }

    public String decrypt(String cipher, String key) {
        String res = "";
        Integer limit = cipher.length() % 64 > 0 ? (cipher.length() / 64) + 1 : cipher.length() / 64;
        for (int i = 0; i < limit; i++) {
            Integer index = i * 64;
            String toProcess = cipher.substring(index, index + (cipher.length() - index >= 64 ? 64 : cipher.length()-index));
            res += this.bitToString(this.ipInverse.permute(this.feistelNetwork.iterate(this.initialPermutation.permute(pushTo64Bit(toProcess)), this.keySchedule.schedule(pushTo64Bit(this.stringToBit(key)), false))));
        }
         return res;
    }

    public String encrypt(String plain, String key) {
        String res = "";
        Integer limit = plain.length() % 8 > 0 ? (plain.length() / 8) + 1 : plain.length() / 8;
        for (int i = 0; i < limit; i++) {
            Integer index = i * 8;
            String toProcess = plain.substring(index, index + (plain.length() - index >= 8 ? 8 : plain.length()-index));
            res += this.ipInverse.permute(this.feistelNetwork.iterate(this.initialPermutation.permute(pushTo64Bit(stringToBit(toProcess))), this.keySchedule.schedule(pushTo64Bit(stringToBit(key)), true)));
        }
        return res;
    }

}
