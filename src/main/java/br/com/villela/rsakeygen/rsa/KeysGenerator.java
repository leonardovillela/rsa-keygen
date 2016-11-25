package br.com.villela.rsakeygen.rsa;

import br.com.villela.rsakeygen.prime.PrimeGenerator;
import br.com.villela.rsacoreapi.KeyPair;

import java.math.BigInteger;
import java.util.stream.Stream;

public class KeysGenerator {

    static BigInteger firstPrime;
    static BigInteger secondPrime;

    public static KeyPair generate() {
        generateParallelPrimes();

        BigInteger productOfPrimesKeys = firstPrime.multiply(secondPrime);
        BigInteger totientProductOfKeys = calculateTotientOfProducts(firstPrime, secondPrime);

        BigInteger publicKeyExponent = PublicKeyExponentGenerator.generate(totientProductOfKeys);
        BigInteger privateKeyExponent = PrivateKeyExponentGenerator.generate(totientProductOfKeys, publicKeyExponent);

        return new KeyPair(publicKeyExponent, privateKeyExponent, productOfPrimesKeys);
    }

    private static BigInteger calculateTotientOfProducts(BigInteger p, BigInteger q) {
        return (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    }

    private static void generateParallelPrimes() {
        Stream<Thread> threadStream = createThreadPrimesStream();

        threadStream
            .parallel()
            .peek(thread -> thread.start())
            .forEach(KeysGenerator::joinThreadHandler);
    }

    private static Stream<Thread> createThreadPrimesStream() {
        return Stream.of(
            new Thread(() -> KeysGenerator.firstPrime = PrimeGenerator.generate()),
            new Thread(() -> KeysGenerator.secondPrime = PrimeGenerator.generate())
        );
    }

    private static void joinThreadHandler(Thread thread) {
        try { thread.join(); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
}
