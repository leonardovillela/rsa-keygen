package br.com.villela.rsakeygen.prime;

public class BitSizePrimeProvider {

    private static final String BIT_SIZE_PROPERTY_NAME = "key.bit.size";
    private static final int DEFAULT_BITS_SIZE = 64;

    public static int getBitsSize() {
        if (bitSizeIsDefined())
            return Integer.parseInt(System.getProperty(BIT_SIZE_PROPERTY_NAME));

        return DEFAULT_BITS_SIZE;
    }

    private static boolean bitSizeIsDefined() {
        String bitSize = System.getProperty(BIT_SIZE_PROPERTY_NAME);

        if (bitSize == null || bitSize.isEmpty())
            return false;
        return true;
    }
}
