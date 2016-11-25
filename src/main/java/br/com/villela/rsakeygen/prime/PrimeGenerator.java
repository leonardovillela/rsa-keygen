package br.com.villela.rsakeygen.prime;

import java.math.BigInteger;
import java.util.Random;

public class PrimeGenerator {

    static final Random random = new Random();

    public static BigInteger generate() {
        return BigInteger.probablePrime(BitSizePrimeProvider.getBitsSize(), random);
    }
}