package classes.models;

/**
 * The User class represents a generic user in the system.
 * It contains basic information about the user such as , name, surname, and characteristic.
 */
public class User {
    private String username;        // The  of the user
    private String name;            // The first name of the user
    private String surname;         // The last name of the user
    private String characteristic;  // A characteristic of the user
    private static int usersCounter = 0;  // Counter for the total number of users in the system
    private String password;

    /**
     * Constructs a new User object with the specified , name, surname, and characteristic.
     *
     * @param        The  of the user.
     * @param name           The first name of the user.
     * @param surname        The last name of the user.
     * @param characteristic A characteristic of the user.
     */
    public User(String username, String name, String surname, String characteristic,String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.characteristic = characteristic;
        this.password=password;
        usersCounter++;  // Increment the counter for the total number of users
    }

    // Getters and Setters

    /**
     * Gets the  of the user.
     *
     * @return The  of the user.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the password of the user.
     *
     * @return The  of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the  of the user.
     *
     * @param  The new  of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the user.
     *
     * @param name The new first name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the last name of the user.
     *
     * @param surname The new last name of the user.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the characteristic of the user.
     *
     * @return The characteristic of the user.
     */
    public String getCharacteristic() {
        return characteristic;
    }

    /**
     * Sets the characteristic of the user.
     *
     * @param characteristic The new characteristic of the user.
     */
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    /**
     * Gets the total number of users in the system.
     *
     * @return The total number of users in the system.
     */
    public static int getUsersCounter() {
        return usersCounter;
    }

    // Methods to be overridden by subclasses

    /**
     * Method to be overridden by subclasses.
     * Registers the user.
     */
    public void register() {
        // Subclasses should implement registration logic
    }

    /**
     * Method to be overridden by subclasses.
     * Logs the user into the system.
     */
    public void login() {
        // Subclasses should implement login logic
    }

    /**
     * Method to be overridden by subclasses.
     * Logs the user out of the system.
     */
    public void logout() {
        // Subclasses should implement logout logic
    }
}
