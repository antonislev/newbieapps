package classes.models;
import java.util.ArrayList;

/**
 * The Admin class represents an administrator user in the system.
 * It manages sellers, clients, and programs.
 */
public class Admin extends User {
    private ArrayList<Seller> sellers; // List of sellers
    private ArrayList<Client> clients; // List of clients
    private ArrayList<Program> programs; // List of programs
   

    /**
     * Constructs a new Admin object with the specified username, name, surname, and role.
     *
     * @param username   The username of the admin.
     * @param name       The name of the admin.
     * @param surname    The surname of the admin.
     * @param role       The role of the admin.
     */
    public Admin(String username, String name, String surname, String role,String password) {
        super(username, name, surname, role,password);
        this.sellers = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.programs = new ArrayList<>();
    }
    

    /**
     * Creates a new seller with the given details and adds it to the list of sellers.
     *
     * @param username The username of the seller.
     * @param name     The name of the seller.
     * @param surname  The surname of the seller.
     * @param company  The company name of the seller.
     */
    public void createSeller(String username, String name, String surname, String company,String password) {
        Seller seller = new Seller(username, name, surname, "seller", company,password);
        sellers.add(seller);
    }

    /**
     * Deletes the specified seller from the list of sellers.
     *
     * @param seller The seller to be deleted.
     */
    public void deleteSeller(Seller seller) {
        sellers.remove(seller);
    }

    /**
     * Creates a new user based on the provided parameters and adds it to the appropriate list.
     *
     * @param username The username of the user.
     * @param name     The name of the user.
     * @param surname  The surname of the user.
     * @param role     The role of the user.
     */
    public void createUser(String username, String name, String surname, String role,String password) {
        try {
            // Create a new user based on the provided parameters
            User newUser;

            // Determine the type of user to create based on the role
            switch (role) {
                case "client":
                    newUser = new Client(username, name, surname, role, "defaultTaxId", null,password); // Create a client user
                    break;
                case "seller":
                    newUser = new Seller(username, name, surname, role, "defaultCompany",password); // Create a seller user
                    break;
                case "admin":
                    newUser = new Admin(username, name, surname, role,password); // Create an admin user
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role. User creation failed.");
            }

            // Print a message indicating that the user has been created
            System.out.println("New user created successfully: " + newUser.getUsername());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Generates a string representation of the Admin object.
     *
     * @return A string representation of the Admin object.
     */
    @Override
    public String toString() {
        return "Admin{" +
                "sellers=" + sellers +
                ", clients=" + clients +
                ", programs=" + programs +
                '}';
    }

    /**
     * Deletes the specified user from the system.
     *
     * @param user The user to be deleted.
     */
    public void deleteUser(User user) {
        // Perform deletion logic here
        if (user instanceof Client) {
            // Additional logic specific to deleting a client user
            System.out.println("Deleting client user: " + user.getUsername());
        } else if (user instanceof Seller) {
            // Additional logic specific to deleting a seller user
            System.out.println("Deleting seller user: " + user.getUsername());
        } else if (user instanceof Admin) {
            // Additional logic specific to deleting an admin user
            System.out.println("Deleting admin user: " + user.getUsername());
        } else {
            // User type not recognized
            System.out.println("Unable to delete user. User type not recognized.");
            return;
        }

        // Perform deletion operation (remove user from database, etc.)
        System.out.println("User deleted successfully: " + user.getUsername());
    }

    /**
     * Adds a new program to the list of programs.
     *
     * @param programName The program to be added.
     */
    public void createProgram(Program programName) {
        this.programs.add(programName);
    }

    /**
     * Deletes the specified program from the list of programs.
     *
     * @param pr The program to be deleted.
     */
    public void deleteProgram(Program pr) {
        this.programs.remove(pr);
    }

    /**
     * Retrieves the list of programs.
     *
     * @return The list of programs.
     */
    public ArrayList<Program> getProgram() {
        return this.programs;
    }

    /**
     * Performs the login action for the admin user.
     */
    @Override
    public void login() {
        System.out.println("Admin" + this.getName() + " login.");
    }

    /**
     * Performs the logout action for the admin user.
     */
    @Override
    public void logout() {
        System.out.println("Admin" + this.getName() + " logout.");
    }
}
