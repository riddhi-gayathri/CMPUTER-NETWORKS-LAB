import java.util.Scanner;

public class CRC {
    // Perform CRC calculation
    public static String crc(String data, String poly, boolean errChk) {
        String rem = data;
        // Append zeros only during sending (not during error check)
        if (!errChk) {
            for (int i = 0; i < poly.length() - 1; i++)
                rem += "0";
        }
        // Perform division using XOR (bitwise mod-2 division)
        for (int i = 0; i <= rem.length() - poly.length(); i++) {
            if (rem.charAt(i) == '1') {
                for (int j = 0; j < poly.length(); j++) {
                    rem = rem.substring(0, i + j)
                         + (rem.charAt(i + j) == poly.charAt(j) ? '0' : '1')
                         + rem.substring(i + j + 1);
                }
            }
        }
        // Return remainder (last bits)
        return rem.substring(rem.length() - poly.length() + 1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Polynomial: CRC-16-IBM (x^16 + x^15 + x^2 + 1)
        String poly = "10000100010001010";
        // === Sender Side ===
        System.out.print("Enter Data to be sent (in binary): ");
        String data = scanner.nextLine();
        // Generate CRC remainder
        String rem = crc(data, poly, false);
        String codeword = data + rem;
        System.out.println("CRC Remainder: " + rem);
        System.out.println("Transmitted Codeword: " + codeword);
        // === Receiver Side ===
        System.out.print("Enter Received Codeword: ");
        String recvCodeword = scanner.nextLine();
        String recvRem = crc(recvCodeword, poly, true);
        // Check if remainder is all zeros
        if (!recvRem.contains("1")) {
            System.out.println("No Error Detected in Received Data.");
        } else {
            System.out.println("Error Detected in Received Data!");
        }
        scanner.close();
    }
}
