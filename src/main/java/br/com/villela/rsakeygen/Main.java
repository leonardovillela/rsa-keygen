package br.com.villela.rsakeygen;

import br.com.villela.rsacoreapi.KeyPair;
import br.com.villela.rsakeygen.file.KeysFileCreator;
import br.com.villela.rsakeygen.rsa.KeysGenerator;

public class Main {

    public static void main(String[] args) {
        KeyPair generatedKeyPair = KeysGenerator.generate();
        KeysFileCreator.createKeysFiles(generatedKeyPair);
    }
}
