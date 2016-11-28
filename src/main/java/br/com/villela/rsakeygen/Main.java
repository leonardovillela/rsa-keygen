package br.com.villela.rsakeygen;

import br.com.villela.rsacore.config.KeysConfig;
import br.com.villela.rsacore.config.KeysConfigLoader;
import br.com.villela.rsacore.key.KeyPair;
import br.com.villela.rsakeygen.file.KeysFileCreator;
import br.com.villela.rsakeygen.rsa.KeysGenerator;

public class Main {

    public static void main(String[] args) {
        KeysConfig keysConfig = new KeysConfigLoader().load();
        KeyPair generatedKeyPair = KeysGenerator.generate();
        KeysFileCreator.createKeysFiles(generatedKeyPair, keysConfig);
    }
}
