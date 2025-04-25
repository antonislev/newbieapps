package classes.models;
/**
 * The Call class represents a phone call made by a user.
 * It contains information about the call duration, recipient, and status.
 */
public class Call {
    private int duration; // Duration of the call
    private String callRecipient; // Recipient of the call
    private boolean status; // Status of the call (e.g., completed or ongoing)

    /**
     * Constructs a new Call object with the given duration, recipient, and status.
     *
     * @param duration      The duration of the call in minutes.
     * @param callRecipient The recipient of the call.
     * @param status        The status of the call (true for completed, false for ongoing).
     * @throws IllegalArgumentException If the duration is not greater than 0
     *                                  or if the call recipient is null or empty.
     */
    public Call(int duration, String callRecipient, boolean status) throws IllegalArgumentException {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }
        if (callRecipient == null || callRecipient.isEmpty()) {
            throw new IllegalArgumentException("Call recipient cannot be null or empty.");
        }
        this.duration = duration;
        this.callRecipient = callRecipient;
        this.status = status;
    }

    /**
     * Retrieves the duration of the call.
     *
     * @return The duration of the call in minutes.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the call.
     *
     * @param duration The duration of the call to set.
     * @throws IllegalArgumentException If the duration is not greater than 0.
     */
    public void setDuration(int duration) throws IllegalArgumentException {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }
        this.duration = duration;
    }

    /**
     * Retrieves the recipient of the call.
     *
     * @return The recipient of the call.
     */
    public String getCallRecipient() {
        return callRecipient;
    }

    /**
     * Sets the recipient of the call.
     *
     * @param callRecipient The recipient of the call to set.
     * @throws IllegalArgumentException If the call recipient is null or empty.
     */
    public void setCallRecipient(String callRecipient) throws IllegalArgumentException {
        if (callRecipient == null || callRecipient.isEmpty()) {
            throw new IllegalArgumentException("Call recipient cannot be null or empty.");
        }
        this.callRecipient = callRecipient;
    }

    /**
     * Retrieves the status of the call.
     *
     * @return The status of the call (true for completed, false for ongoing).
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the status of the call.
     *
     * @param status The status of the call to set (true for completed, false for ongoing).
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
