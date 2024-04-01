package task2;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA512HashCracker {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String hash = "2a2375e1171723a0e04a3c49adccb4ec6db86b2f7527db45e0bb84d8d76a9b9d3536d39e01b92d303fc966b36aa73475f9aea541d63f5ad894a50dda63b68a1c";
        char[] charset = "abcde".toCharArray();
        int passwordLength = 5;

        char[] password = new char[passwordLength];
        Arrays.fill(password, charset[0]);

        while (true) {
            String passwordStr = new String(password);
            String hashAttempt = getSHA512Hash(passwordStr);
            if (hashAttempt.equals(hash)) {
                System.out.println("Password found: " + passwordStr);
                break;
            }

            increment(password, charset, passwordLength - 1);
        }
    }

    public static void increment(char[] password, char[] charset, int position) {
        if (position < 0) return;

        char currentChar = password[position];
        if (currentChar == charset[charset.length - 1]) {
            password[position] = charset[0];
            increment(password, charset, position - 1);
        } else {
            int index = Arrays.binarySearch(charset, currentChar);
            password[position] = charset[index + 1];
        }
    }

    public static String getSHA512Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}