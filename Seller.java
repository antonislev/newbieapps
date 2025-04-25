package classes.models;

import java.util.ArrayList;

/**
 * The Seller class represents a seller user in the system.
 * It extends the User class and includes additional attributes and methods specific to sellers.
 */
public class Seller extends User {
    private String company;             // The company associated with the seller
    private ArrayList<Client> clients; // The list of clients associated with the seller

    /**
     * Constructs a new Seller object with the specified username, name, surname, role, and company.
     *
     * @param username The username of the seller.
     * @param name     The first name of the seller.
     * @param surname  The last name of the seller.
     * @param role     The role of the seller.
     * @param company  The company associated with the seller.
     * @throws IllegalArgumentException If the company name is null or empty.
     */
    public Seller(String username, String name, String surname, String role, String company,String password) {
        super(username, name, surname, role,password);
        if (company == null || company.isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty.");
        }
        this.company = company;
        this.clients = new ArrayList<>();
    }

    // Getters and Setters

    /**
     * Gets the company associated with the seller.
     *
     * @return The company associated with the seller.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company associated with the seller.
     *
     * @param company The new company associated with the seller.
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Gets the list of clients associated with the seller.
     *
     * @return The list of clients associated with the seller.
     */
    public ArrayList<Client> getClients() {
        return this.clients;
    }

    // Additional methods

    /**
     * Adds a client to the list of clients associated with the seller.
     *
     * @param client The client to add.
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Generates a string representation of the Seller object.
     *
     * @return A string representation of the Seller object.
     */
    @Override
    public String toString() {
        return "Seller{" +
                "company='" + company + '\'' +
                ", clients=" + clients +
                '}';
    }

    /**
     * Logs the seller into the system.
     */
    @Override
    public void login() {
        System.out.println("Seller" + this.getName() + " login");
    }

    /**
     * Logs the seller out of the system.
     */
    @Override
    public void logout() {
        System.out.println("Seller" + this.getName() + " logout");
    }

    /**
     * Registers the seller.
     */
    @Override
    public void register() {
        System.out.println("Seller" + this.getName() + " registered");
    }

    /**
     * Calculates and prints the total bill for a client.
     *
     * @param client The client for whom the bill is to be calculated.
     */
    public void makeBill(Client client) {
        System.out.println("The total bill is: " + client.getPhoneNumber().getBill().getCharge() + "$");
    }

    /**
     * Resets the program for a client.
     *
     * @param client  The client whose program is to be reset.
     * @param program The new program for the client.
     */
    public void resetProgram(Client client, Program program) {
        client.getPhoneNumber().setProgram(program);
        client.getPhoneNumber().getBill().renewBill(program);
    }

    /**
     * Checks if a client has a bill to pay.
     *
     * @param client The client to check.
     * @return True if the client has a bill to pay, false otherwise.
     */
    private boolean hasBillToPay(Client client) {
        if (client.getPhoneNumber().getProgram().servicesLeft()) {
            return false;
        }
        client.getPhoneNumber().setStatusCall(false);
        return true;
    }

    /**
     * Changes the program for a phone number.
     *
     * @param phoneNumber The phone number whose program is to be changed.
     * @param newProgram  The new program for the phone number.
     */
    public void changeProgram(PhoneNumber phoneNumber, Program newProgram) {
        phoneNumber.setProgram(newProgram);
    }
}
