package br.com.villela.rsakeygen.file;

import br.com.villela.rsakeygen.prime.BitSizePrimeProvider;
import br.com.villela.rsacoreapi.KeyPair;
import br.com.villela.rsacoreapi.PrivateKey;
import br.com.villela.rsacoreapi.PublicKey;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KeysFileCreator {

    private static final String dirKeysName = ".simple-rsa";
    private static final String publicKeyFileName = "id_rsa.pub";
    private static final String privateKeyFileName = "id_rsa";

    // TODO(LV) resolver esse "* 2"
    public static void createKeysFiles(KeyPair keyPair) {
        if (!dirKeyLocationIsCreated())
            createDirKeyLocation();

        createPublicKeyFile(keyPair.getPublicKey());
        createPrivateKeyFile(keyPair.getPrivateKey());

        System.out.println("Keys generated Success, with " + BitSizePrimeProvider.getBitsSize() * 2 + " bits!");
    }

    private static void createPublicKeyFile(PublicKey publicKey) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getPublicKeyFilePath()))) {
            outputStream.writeUTF(publicKey.toString());
        } catch (IOException e) {
            System.err.printf("Não foi possivel criar o arquivo da chave publica.");
        }
    }

    private static void createPrivateKeyFile(PrivateKey privateKey) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getPrivateKeyFilePath()))) {
            outputStream.writeUTF(privateKey.toString());
        } catch (IOException e) {
            System.err.printf("Não foi possivel criar o arquivo da chave privada.");
        }
    }

    private static void createDirKeyLocation() {
        try {
            Files.createDirectories(Paths.get(getDirKeyLocation()));
        } catch (IOException e) {
            System.err.printf("Não foi possivel criar a pasta para as chaves da aplicão.");
        }
    }

    private static boolean dirKeyLocationIsCreated() {
        return Files.exists(Paths.get(getDirKeyLocation()));
    }

    private static String getDirKeyLocation() {
        String homeFolderPath = System.getenv().get("HOME");
        return homeFolderPath + "/" + dirKeysName + "/";
    }

    private static String getPublicKeyFilePath() {
        return getDirKeyLocation() + publicKeyFileName;
    }

    private static String getPrivateKeyFilePath() {
        return getDirKeyLocation() + privateKeyFileName;
    }
}
