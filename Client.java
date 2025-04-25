package classes.models;
import java.util.ArrayList;

/**
 * This class represents a client in the phone service system.
 * It extends the User class and contains methods for managing client-specific actions,
 * such as creating calls, viewing call history, paying bills, and adjusting funds.
 */
public class Client extends User {
    private final String AFM; // Tax identification number
    private PhoneNumber phoneNumber; // Associated phone number
    private double funds; // Client's funds

    /**
     * Constructs a new Client object with the given parameters.
     * @param username The username of the client.
     * @param name The name of the client.
     * @param surname The surname of the client.
     * @param role The role of the client.
     * @param taxId The tax identification number (AFM) of the client.
     * @param phoneNumber The phone number associated with the client.
     */
    public Client(String username, String name, String surname, String role, String taxId, PhoneNumber phoneNumber,String password) {
        super(username, name, surname, role,password);
        this.AFM = taxId;
        this.phoneNumber = phoneNumber;
        this.funds = 0;
    }
    
    // Getters and setters for fields
    public double getFunds() {
        return funds;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Client Details:\n");
        stringBuilder.append("Username: ").append(getUsername()).append("\n");
        stringBuilder.append("Name: ").append(getName()).append("\n");
        stringBuilder.append("Surname: ").append(getSurname()).append("\n");
        stringBuilder.append("Role: ").append(getCharacteristic()).append("\n");
        stringBuilder.append("Tax ID (AFM): ").append(AFM).append("\n");
        stringBuilder.append("Phone Number: ").append(phoneNumber.getNumber()).append("\n");
        stringBuilder.append("Password: ").append(getPassword()).append("\n");
        stringBuilder.append("Funds: ").append(funds).append("\n");
        return stringBuilder.toString();
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public void adjustFunds(double f, boolean pos) {
        if (pos) {
            this.funds += f;
        } else {
            this.funds -= f;
        }
    }

    public String getAFM() {
        return AFM;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Creates a call with the specified duration, recipient, and success status.
     * @param dur The duration of the call.
     * @param to The recipient of the call.
     * @param success The success status of the call.
     */
    public void createCall(int dur, String to, boolean success) {

            try {
                Call call = new Call(dur, to, success);
                this.phoneNumber.addCall(call);

                // Check if the client's program has available minutes for the call
                if (this.getPhoneNumber().getProgram().servicesLeft(dur)) {
                    this.getPhoneNumber().getProgram().setMinutesLeft(dur);
                } else if ((this.getPhoneNumber().getProgram().getMinutesLeft() - dur) < 0) {
                    // If available minutes are over, charge the call and update the bill
                    int extra = dur - this.getPhoneNumber().getProgram().getMinutesLeft();
                    System.out.println("Your available minutes are over. Every call will be charged until further payment.");
                    System.out.println("You will be charged extra for the " + extra + " minutes");
                    this.getPhoneNumber().getProgram().setMinutesLeft(this.getPhoneNumber().getProgram().getMinutesLeft());
                    this.getPhoneNumber().getProgram().addAdditionalCharge(extra);
                    this.getPhoneNumber().updateBill();
                } else {
                    System.out.println("Your available minutes are over. Every call will be charged until further renew.");
                    this.getPhoneNumber().getProgram().addAdditionalCharge(dur);
                    this.getPhoneNumber().updateBill();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid call parameters: " + e.getMessage());
            }
        }




    /**
     * Displays the call history of the client.
     */
    public void showCalls() {
        this.phoneNumber.getCallHistory();
    }

    // Overrides methods from the User class
    @Override
    public void login() {
        System.out.println(this.getName() + " login.");
    }

    @Override
    public void logout() {
        System.out.println(this.getName() + " logout.");
    }

    @Override
    public void register() {
        System.out.println(this.getName() + " registered.");
    }

    /**
     * Displays the call history of the client.
     */
    public void viewCallHistory() {
        if (phoneNumber != null && phoneNumber.getCallHistory() != null) {
            ArrayList<Call> callHistory = phoneNumber.getCallHistory();
            System.out.println("Call History:");
            int counter = 0;
            for (Call call : callHistory) {
                counter++;
                System.out.println(counter + ") Duration: " + call.getDuration() + " minutes, Recipient: " + call.getCallRecipient() + " Status " + call.getStatus());
                System.out.println("----------------------------------------------------------------");
            }
        } else {
            System.out.println("No call history available.");
        }
    }

    /**
     * Pays the client's bill.
     * @param sel The seller responsible for resetting the program.
     * @param pr The program associated with the client's bill.
     */
    public void payBill(Seller sel, Program pr) {
        if (hasBillToPay()) {
            try {
                // Check if the client has sufficient funds to pay the bill
                if (this.funds < this.getPhoneNumber().getBill().getCharge()) {
                    throw new InsufficientFundsException("Not enough funds to pay.");
                }
                Bill bill = getBill();
                double totalAmountDue = calculateTotalAmountDue(bill);
                boolean paymentSuccessful = processPayment(totalAmountDue);
                if (paymentSuccessful) {
                    // Process the payment and reset the program
                    System.out.println("Payment successful. Bill has been paid.");
                    this.adjustFunds(this.getPhoneNumber().getBill().getCharge(), false);
                    sel.resetProgram(this, pr);
                } else {
                    System.out.println("Payment failed. Please try again later.");
                }
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Bill not expired yet");
        }
    }

    /**
     * Checks if the client has a bill to pay.
     * @return True if the client has a bill to pay, false otherwise.
     */
    private boolean hasBillToPay() {
        if (this.getPhoneNumber().getProgram().servicesLeft()) {
            return false;
        }
       this.getPhoneNumber().setStatusCall(false);
        return true;
    }

    /**
     * Retrieves the client's bill.
     * @return The client's bill.
     */
    private Bill getBill() {
        return this.getPhoneNumber().getBill();
    }

    /**
     * Calculates the total amount due for the client's bill.
     * @param bill The client's bill.
     * @return The total amount due for the client's bill.
     */
    private double calculateTotalAmountDue(Bill bill) {
        return 100.0; // Dummy amount for demonstration
    }

    /**
     * Processes the payment for the client's bill.
     * @param totalAmountDue The total amount due for the client's bill.
     * @return True if the payment was successful, false otherwise.
     */
    private boolean processPayment(double totalAmountDue) {
        return totalAmountDue > 0;
    }
}
