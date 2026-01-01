import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        int noOfQueries;
        int bucketSize;
        int inputPacketSize;
        int outputPacketSize;
        int storedBufferSize = 0;   // Current amount in bucket
        int sizeLeft;
        Scanner scanner = new Scanner(System.in);
        System.out.print("ENTER THE BUCKET SIZE: ");
        bucketSize = scanner.nextInt();
        System.out.print("ENTER THE NUMBER OF PACKETS: ");
        noOfQueries = scanner.nextInt();
        System.out.print("ENTER THE OUTPUT RATE: ");
        outputPacketSize = scanner.nextInt();
        for (int i = 0; i < noOfQueries; i++) {
            System.out.print("\nENTER THE SIZE OF THE PACKET: ");
            inputPacketSize = scanner.nextInt();
            sizeLeft = bucketSize - storedBufferSize;
            // Check if packet fits
            if (inputPacketSize <= sizeLeft) {
                storedBufferSize += inputPacketSize;
                System.out.println("Packet accepted → " + inputPacketSize);
            } else {
                int dropped = inputPacketSize - sizeLeft;
                System.out.println("Packet dropped → " + dropped);
                storedBufferSize = bucketSize;  // bucket becomes full
            }
            // Leak / output packets
            storedBufferSize -= outputPacketSize;
            if (storedBufferSize < 0)
                storedBufferSize = 0;
            System.out.println("Buffer after output = " + storedBufferSize);
        }
      scanner.close();
    }
}

    
