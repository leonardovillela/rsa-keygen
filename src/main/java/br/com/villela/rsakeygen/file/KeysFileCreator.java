package br.com.villela.rsakeygen.file;

import br.com.villela.rsacore.config.KeysConfig;
import br.com.villela.rsacore.key.Key;
import br.com.villela.rsacore.key.KeyPair;
import br.com.villela.rsakeygen.prime.BitSizePrimeProvider;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class KeysFileCreator {

    public static void createKeysFiles(KeyPair keyPair, KeysConfig keysConfig) {
        Path keyDirPath = keysConfig.getKeyDirPath();
        if (!Files.exists(keyDirPath))
            createDirKeyLocation(keyDirPath);

        createPublicKeyFile(keyPair.getPublicKey(), keysConfig.getPublicKeyFilePath());
        createPrivateKeyFile(keyPair.getPrivateKey(), keysConfig.getPrivateKeyFilePath());

        System.out.println("Chaves geradas com sucesso, tamanho " + BitSizePrimeProvider.getBitsSize() + " bits!");
    }

    private static void createPublicKeyFile(Key publicKey, Path publicKeyFilePath) {
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(publicKeyFilePath, CREATE, WRITE))) {
            byte[] serializedPublicKey = publicKey.toString().getBytes();
            out.write(serializedPublicKey, 0, serializedPublicKey.length);
        } catch (IOException e) {
            System.err.printf("Não foi possivel criar o arquivo da chave publica.");
        }
    }

    private static void createPrivateKeyFile(Key privateKey, Path privateKeyFilePath) {
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(privateKeyFilePath, CREATE, WRITE))) {
            byte[] serializedPublicKey = privateKey.toString().getBytes();
            out.write(serializedPublicKey, 0, serializedPublicKey.length);
        } catch (IOException e) {
            System.err.printf("Não foi possivel criar o arquivo da chave privada.");
        }
    }

    private static void createDirKeyLocation(Path keyDirPath) {
        try {
            Files.createDirectories(keyDirPath);
        } catch (IOException e) {
            System.err.printf("Não foi possivel criar a pasta para as chaves da aplicão.");
        }
    }
}
