package br.com.villela.rsakeygen.random;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class PossiblePublicKeyExponentGenerator {

    private static final BigInteger MINIMUM_EXPONENT_VALUE = BigInteger.ONE;

    private static final Random random = new SecureRandom();

    public static BigInteger nextPossibleExponent(BigInteger totientProductOfKeys) {
        BigInteger possibleExponent;
        do {
            possibleExponent = new BigInteger(totientProductOfKeys.bitLength(), random);
        } while(checkExponentIsEligible(possibleExponent, totientProductOfKeys));

        return possibleExponent;
    }

    private static boolean checkExponentIsEligible(BigInteger exponent, BigInteger totientProductOfKeys) {
        boolean maxVerificationPassed = exponent.compareTo(totientProductOfKeys) >= 0;
        boolean minVerificationPassed = exponent.compareTo(MINIMUM_EXPONENT_VALUE) >= 0;
        return maxVerificationPassed && minVerificationPassed;
    }
}
