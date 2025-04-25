package classes.models;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The PhoneNumber class represents a phone number associated with a program, call history, and bill.
 */
public class PhoneNumber {
    private String number; // The phone number
    private ArrayList<Call> callHistory; // The call history associated with this phone number
    private Program program; // The program associated with this phone number
    private Bill bill; // The bill associated with this phone number
    private boolean statusCall; // The status indicating if calls can be made with this phone number

   // private static Set<String> uniqueNumbers = new HashSet<>(); // Set to store unique phone numbers

    /**
     * Constructs a PhoneNumber object with the specified phone number and program.
     *
     * @param number  The phone number.
     * @param program The program associated with the phone number.
     * @throws IllegalArgumentException if the phone number is null or empty, or if it is a duplicate.
     */
    public PhoneNumber(String number, Program program) {
        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
       
        this.statusCall = true;
        this.number = number;
        this.callHistory = new ArrayList<>();
        this.program = program;
        bill = new Bill(program);

      //  uniqueNumbers.add(number);
    }

    /**
     * Returns a string representation of the PhoneNumber object.
     *
     * @return A string representation of the PhoneNumber object.
     */
    @Override
    public String toString() {
        return "PhoneNumber{" +
                "number='" + number + '\'' +
                ", callHistory=" + callHistory +
                ", program=" + program +
                ", bill=" + bill +
                "}";
    }

    /**
     * Updates the bill associated with this phone number.
     */
    public void updateBill() {
        this.bill.setExtraCharge(this.getProgram().getAdditionalCharges());
    }

    /**
     * Gets the status indicating whether calls can be made with this phone number.
     *
     * @return True if calls can be made, false otherwise.
     */
    public boolean getStatusCall() {
        return this.statusCall;
    }

    /**
     * Sets the status indicating whether calls can be made with this phone number.
     *
     * @param status The status to set.
     */
    public void setStatusCall(boolean status) {
        this.statusCall = status;
    }

    /**
     * Gets the bill associated with this phone number.
     *
     * @return The bill associated with this phone number.
     */
    public Bill getBill() {
        return bill;
    }

    // Getters and Setters

    /**
     * Gets the phone number.
     *
     * @return The phone number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Adds a call to the call history associated with this phone number.
     *
     * @param call The call to add.
     * @throws IllegalStateException if the phone number bill needs to be renewed.
     */
    public void addCall(Call call) {
        if (!statusCall) {
            throw new IllegalStateException("Phone number bill needs to be renewed.");
        }
        callHistory.add(call);
    }

    /**
     * Gets the call history associated with this phone number.
     *
     * @return The call history associated with this phone number.
     */
    public ArrayList<Call> getCallHistory() {
        return callHistory;
    }

    /**
     * Sets the call history associated with this phone number.
     *
     * @param callHistory The call history to set.
     */
    public void setCallHistory(ArrayList<Call> callHistory) {
        this.callHistory = callHistory;
    }

    /**
     * Gets the program associated with this phone number.
     *
     * @return The program associated with this phone number.
     */
    public Program getProgram() {
        return program;
    }

    /**
     * Sets the program associated with this phone number.
     *
     * @param program The program to set.
     */
    public void setProgram(Program program) {
        this.program = program;
    }
}
