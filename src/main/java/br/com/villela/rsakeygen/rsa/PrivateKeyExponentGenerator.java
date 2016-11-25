package br.com.villela.rsakeygen.rsa;

import java.math.BigInteger;

public class PrivateKeyExponentGenerator {

    public static BigInteger generate(BigInteger totientProductOfKeys, BigInteger publicKeyExponent) {
        return publicKeyExponent.modInverse(totientProductOfKeys);
    }
}
