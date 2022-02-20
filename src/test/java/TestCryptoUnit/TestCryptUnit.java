package TestCryptoUnit;

import CentralUnit.CryptoUnit;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestCryptUnit {
    private CryptoUnit cryptoUnit = new CryptoUnit();

    @TestFactory
    Stream<DynamicTest> testCryptoWithMullerExample() {
        ArrayList<DynamicTest> tests = new ArrayList<>();
        String plain = "DHBW MOS";
        String key = cryptoUnit.bitToString("0000000000000000000000000000000000000000011111111111111111111111");

        String cipher = cryptoUnit.encrypt(plain, key);

        Collections.addAll(tests,
                DynamicTest.dynamicTest("check string to bit", ()-> assertEquals("0100010001001000010000100101011100100000010011010100111101010011", cryptoUnit.stringToBit(plain))),
                DynamicTest.dynamicTest("check bit to string", ()-> assertEquals(plain, cryptoUnit.bitToString(cryptoUnit.stringToBit(plain)))),
                DynamicTest.dynamicTest("check cipher", ()-> assertEquals("1000100010000100100000011010101100010000100011101000111110100011", cipher)),
                DynamicTest.dynamicTest("check decrypted plain", ()-> assertEquals(plain, this.cryptoUnit.decrypt(cipher, key)))
        );
        return tests.stream();
    }

    @TestFactory
    Stream<DynamicTest> testCryptoWithProjectData() {
        ArrayList<DynamicTest> tests = new ArrayList<>();
        String plain = "DUS | FLF-5";
        String key = "6072";

        String cipher = cryptoUnit.encrypt(plain, key);

        Collections.addAll(tests,
                DynamicTest.dynamicTest("check string to bit", ()-> assertEquals(cryptoUnit.pushToNextByte("100010001010101010100110010000001111100001000000100011001001100010001100010110100110101"), cryptoUnit.stringToBit(plain))),
                DynamicTest.dynamicTest("check bit to string", ()-> assertEquals(plain, cryptoUnit.bitToString(cryptoUnit.stringToBit(plain)))),
                DynamicTest.dynamicTest("check cipher", ()-> assertEquals("0100010001010101010110011000000011110100100000000100110001100100", cipher)),
                DynamicTest.dynamicTest("check decrypted plain", ()-> assertEquals(plain, this.cryptoUnit.decrypt(cipher, key)))
        );
        return tests.stream();
    }
}
