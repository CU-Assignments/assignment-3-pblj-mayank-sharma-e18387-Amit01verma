import java.util.Scanner;

public class ATMWithdrawal {
    private static final String CORRECT_PIN = "1234";
    private static double balance = 3000;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        String enteredPIN = scanner.nextLine();
        try {
            if (!enteredPIN.equals(CORRECT_PIN)) {
                throw new InvalidPINException("Error: Invalid PIN.");
            }
            System.out.print("Enter withdrawal amount: ");
            double withdrawalAmount = Double.parseDouble(scanner.nextLine());
            if (withdrawalAmount > balance) {
                throw new InsufficientBalanceException("Error: Insufficient balance.");
            }
            balance -= withdrawalAmount;
            System.out.println("Withdrawal successful. Current Balance: " + balance);
        } catch (InvalidPINException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            System.out.println("Current Balance: " + balance);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid withdrawal amount.");
            System.out.println("Current Balance: " + balance);
        } finally {
            System.out.println("Remaining Balance: " + balance);
        }
        scanner.close();
    }
}
class InvalidPINException extends Exception {
    public InvalidPINException(String message) {
        super(message);
    }
}
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
