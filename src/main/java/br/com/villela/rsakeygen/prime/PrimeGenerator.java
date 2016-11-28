package br.com.villela.rsakeygen.prime;

import java.math.BigInteger;
import java.util.Random;

public class PrimeGenerator {

    private static final Random random = new Random();
    private static final int PER_KEY_BIT_FACTOR = 2;

    public static BigInteger generate() {
        return BigInteger.probablePrime(BitSizePrimeProvider.getBitsSize() / PER_KEY_BIT_FACTOR, random);
    }
}