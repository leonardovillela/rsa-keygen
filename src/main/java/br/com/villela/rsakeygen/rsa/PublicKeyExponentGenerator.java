package br.com.villela.rsakeygen.rsa;

import br.com.villela.rsakeygen.gdc.EuclidsGDC;
import br.com.villela.rsakeygen.random.PossiblePublicKeyExponentGenerator;

import java.math.BigInteger;

public class PublicKeyExponentGenerator {

    public static BigInteger generate(BigInteger totientProductOfKeys) {
        BigInteger possiblePublicExponent = PossiblePublicKeyExponentGenerator.nextPossibleExponent(totientProductOfKeys);
        boolean restIsOne = EuclidsGDC.calculate(totientProductOfKeys, possiblePublicExponent).equals(BigInteger.ONE);

        if (restIsOne) return possiblePublicExponent;
        else return generate(totientProductOfKeys);
    }
}
