package classes.models;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 * The Bill class represents a bill for a user's program subscription.
 * It contains information about the billing month, total charge, and extra charges.
 */
public class Bill {
    private String month; // Billing month
    private double totalCharge; // Total charge
    private double extram; // Extra charges

    /**
     * Constructs a new Bill object based on the provided program.
     * The billing month is set to the current month plus 3 months.
     *
     * @param program The program for which the bill is generated.
     * @throws IllegalArgumentException If the program is null.
     */
    public Bill(Program program) throws IllegalArgumentException {
        if (program == null) {
            throw new IllegalArgumentException("Program cannot be null.");
        }
        this.totalCharge = program.getBaseCharge();
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusMonths(3);
        Month month2 = futureDate.getMonth();
        this.month = month2.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    /**
     * Renews the bill based on the provided program.
     * The billing month is set to the current month plus 3 months.
     *
     * @param program The program for which the bill is renewed.
     * @throws IllegalArgumentException If the program is null.
     */
    public void renewBill(Program program) throws IllegalArgumentException {
        if (program == null) {
            throw new IllegalArgumentException("Program cannot be null.");
        }
        this.totalCharge = program.getBaseCharge();
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusMonths(3);
        Month month2 = futureDate.getMonth();
        this.month = month2.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    /**
     * Automatically renews the bill based on the provided program.
     * The billing month is set to the current month plus 3 months,
     * and the total charge is updated.
     *
     * @param program The program for which the bill is automatically renewed.
     * @throws IllegalArgumentException If the program is null.
     */
    public void autoRenew(Program program) throws IllegalArgumentException {
        if (program == null) {
            throw new IllegalArgumentException("Program cannot be null.");
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusMonths(3);
        Month month2 = futureDate.getMonth();
        this.month = month2.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        this.totalCharge = program.getBaseCharge();
        this.extram = 0;
    }

    /**
     * Retrieves the billing month.
     *
     * @return The billing month.
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the billing month.
     *
     * @param month The billing month to set.
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Retrieves the total charge of the bill.
     *
     * @return The total charge.
     */
    public double getCharge() {
        return this.totalCharge;
    }

    /**
     * Sets the total charge of the bill.
     *
     * @param totalCharge The total charge to set.
     */
    public void setTotalCharge(int totalCharge) {
        this.totalCharge = totalCharge;
    }

    /**
     * Sets the extra charge of the bill based on the provided list of extras.
     *
     * @param extra The list of extras to calculate the extra charge.
     */
    public void setExtraCharge(List<Integer> extra) {
        this.extram = 0;
        double extram = (extra.get(0) * 0.2); // Assuming the first element of the list represents the duration
        this.totalCharge += extram;
    }

    /**
     * Generates a string representation of the Bill object.
     *
     * @return A string representation of the Bill object.
     */
    @Override
    public String toString() {
        return "Bill{" +
                "month='" + month + '\'' +
                ", totalCharge=" + totalCharge +
                ", extram=" + extram +
                '}';
    }
}
