package task1;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

public class Decryptor2000 {
    public static void main(String[] args) {

        try {
            FileInputStream keyInputStream = new FileInputStream("C:\\Users\\1\\IdeaProjects\\aud2\\src\\task1\\aes.key");
            byte[] keyAndIV = new byte[32];
            keyInputStream.read(keyAndIV);
            SecretKeySpec key = new SecretKeySpec(keyAndIV, 16, 16, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(keyAndIV, 0, 16);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(128, ivSpec.getIV()));

            FileInputStream encryptedInputStream = new FileInputStream("C:\\Users\\1\\IdeaProjects\\aud2\\src\\task1\\secret_text.enc");
            byte[] encryptedData = new byte[encryptedInputStream.available()];
            encryptedInputStream.read(encryptedData);
            byte[] decryptedData = cipher.doFinal(encryptedData);

            FileOutputStream decryptedOutputStream = new FileOutputStream("C:\\Users\\1\\IdeaProjects\\aud2\\src\\task1\\decrypted.txt");
            decryptedOutputStream.write(decryptedData);

            keyInputStream.close();
            encryptedInputStream.close();
            decryptedOutputStream.close();
            System.out.println("Всё сработало!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
