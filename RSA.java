
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RSA {
    private static final int PRIVATE_KEY = 35;
    private static final int PUBLIC_KEY = 11;
    private static final int N = 221;  // N = p * q, where p=17 and q=13; φ(N) = (p-1)*(q-1)=192
    // Encrypt message
    public static List<Integer> encrypt(String message) {
        List<Integer> ciphertext = new ArrayList<>();
        for (char c : message.toCharArray()) {
            ciphertext.add(modularExp((int) c, PUBLIC_KEY, N));
        }
        return ciphertext;
    }
    // Decrypt ciphertext
    public static String decrypt(List<Integer> ciphertext) {
        List<Integer> decryption = new ArrayList<>();
        for (int c : ciphertext) {
            decryption.add(modularExp(c, PRIVATE_KEY, N));
        }
        System.out.println("Decrypted Ciphertext (numeric): " + decryption);
        StringBuilder decryptedText = new StringBuilder();
        for (int value : decryption) {
            decryptedText.append((char) value);
        }
        return decryptedText.toString();
    }
    // Modular exponentiation
    public static int modularExp(int b, int e, int m) {
        int res = 1;
        b = b % m;
        while (e > 0) {
            if ((e & 1) == 1) {
                res = (res * b) % m;
            }
            e = e >> 1;
            b = (b * b) % m;
        }
        return res;
    }
    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();
        System.out.println("Plaintext: " + plaintext);
        List<Integer> ciphertext = encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        String decryptedText = decrypt(ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);
        scanner.close();
    }
}

