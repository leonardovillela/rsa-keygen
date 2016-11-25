package br.com.villela.rsakeygen.gdc;

import java.math.BigInteger;

public class EuclidsGDC {

    public static BigInteger calculate(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return a;
        return calculate(b, a.mod(b));
    }
}
