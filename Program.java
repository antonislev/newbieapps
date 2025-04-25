package classes.models;
import java.util.ArrayList;
import java.util.List;



/**
 * The Program class represents a service program with specific attributes such as name, services, base charge,
 * services left, and additional charges.
 */
public class Program {
    private String name; // The name of the program
    private  int id;
    private List<Integer> services; // The services provided by the program [MINUTES, GB, SMS]
    private double baseCharge; // The base charge of the program
    private List<Integer> servicesLeft; // The remaining services [MINUTES_LEFT, GB_LEFT, SMS_LEFT]
    private List<Integer> additionalCharges; // Additional charges for extra usage [MINUTES, GB, SMS]
   

    /**
     * Constructs a Program object with the specified name, services, and base charge.
     *
     * @param name        The name of the program.
     * @param services    The list of services provided by the program in the format [MINUTES, GB, SMS].
     * @param baseCharge  The base charge of the program.
     * @throws IllegalArgumentException if the name is null or empty, the services format is invalid, or the base charge is negative.
     */
    public Program(String name, List<Integer> services, double baseCharge,int id) {
    	
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Program name cannot be null or empty.");
        }
        if (services == null || services.size() != 3) {
            throw new IllegalArgumentException("Invalid services format.");
        }
        if (baseCharge < 0) {
            throw new IllegalArgumentException("Base charge cannot be negative.");
        }
        this.id=id;
        this.name = name;
        this.services = new ArrayList<>(services);
        this.baseCharge = baseCharge;
        this.servicesLeft = new ArrayList<>(services);
        this.additionalCharges = new ArrayList<>();
        this.additionalCharges.add(0);
        this.additionalCharges.set(0, 0);
    }
    public Program() {
    	id+=1;
    }
    
    public int getId() {
    	return id;
    }

    /**
     * Adds additional minutes to the extra charges.
     *
     * @param min The additional minutes to add.
     */
    public void addCharge(int min) {
        additionalCharges.set(0, additionalCharges.get(0) + min);
    }

    /**
     * Updates the remaining services by subtracting the provided minutes.
     *
     * @param min The minutes to subtract from the remaining minutes.
     */
    public void updateServicesLeft(int min) {
        this.servicesLeft.set(0, this.servicesLeft.get(0) - min);
    }

    /**
     * Returns a string representation of the Program object.
     *
     * @return A string representation of the Program object.
     */
    @Override
    public String toString() {
        return "Program{" +
                "name='" + name + '\'' +
                ", services=" + services +
                ", baseCharge=" + baseCharge +
                ", servicesLeft=" + servicesLeft +
                ", additionalCharges=" + additionalCharges +
                '}';
    }

    /**
     * Prints the remaining services (minutes, GB, SMS) to the console.
     *
     * @return The list of remaining services.
     */
    public List<Integer> getservicesLeft() {
        System.out.println("Remaining Minutes, GB, SMS:");
        System.out.println("Minutes: " + servicesLeft.get(0) + " | GB: " + servicesLeft.get(1) + "GB | SMS: " + servicesLeft.get(2));
        System.out.println("----------------------------------------------");
        return servicesLeft;
    }

    /**
     * Sets the remaining minutes.
     *
     * @param min The remaining minutes to set.
     */
    public void setMinutesLeft(int min) {
        this.servicesLeft.set(0, this.servicesLeft.get(0) - min);
    }

    /**
     * Gets the remaining minutes.
     *
     * @return The remaining minutes.
     */
    
    public int getMinutes() {
    	return this.services.get(0);
    	
    }
    public int getSms() {
    	return this.services.get(2);
    	
    }
    public int getGb() {
    	return this.services.get(1);
    	
    }
    public int getMinutesLeft() {
        return this.servicesLeft.get(0);
    }
    public int getSmsLeft() {
        return this.servicesLeft.get(2);
    }
    public int getGbLeft() {
        return this.servicesLeft.get(1);
    }
    public int getMinutesExtra() {
        return this.additionalCharges.get(0);
    }
    /**
     * Sets the remaining GB.
     *
     * @param GB The remaining GB to set.
     */
    public void setGBLeft(int GB) {
        this.servicesLeft.set(1, this.services.get(1) - GB);
    }

    /**
     * Sets the remaining SMS.
     *
     * @param sms The remaining SMS to set.
     */
    public void setSMSLeft(int sms) {
        this.servicesLeft.set(2, this.services.get(2) - sms);
    }

    /**
     * Checks if there are services left based on the provided duration.
     *
     * @param dur The duration to check for services.
     * @return True if there are services left, false otherwise.
     */
    public boolean servicesLeft(double dur) {
        return (this.servicesLeft.get(0) - dur) >= 0 && this.servicesLeft.get(1) > 0 && this.servicesLeft.get(2) > 0;
    }

    /**
     * Checks if there are services left based on the default duration (0.5).
     *
     * @return True if there are services left, false otherwise.
     */
    public boolean servicesLeft() {
        return servicesLeft(0.5); // Default value of 0 if called without parameter
    }

    // Getters and Setters

    /**
     * Gets the name of the program.
     *
     * @return The name of the program.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the program.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of services provided by the program.
     *
     * @return The list of services provided by the program.
     */
    public List<Integer> getServices() {
        return services;
    }

    /**
     * Sets the list of services provided by the program.
     *
     * @param services The list of services to set.
     */
    public void setServices(List<Integer> services) {
        this.services = services;
    }

    /**
     * Gets the base charge of the program.
     *
     * @return The base charge of the program.
     */
    public double getBaseCharge() {
        return baseCharge;
    }

    /**
     * Sets the base charge of the program.
     *
     * @param baseCharge The base charge to set.
     */
    public void setBaseCharge(double baseCharge) {
        this.baseCharge = baseCharge;
    }

    /**
     * Gets the additional charges for extra usage.
     *
     * @return The additional charges for extra usage.
     */
    public List<Integer> getAdditionalCharges() {
        return additionalCharges;
    }

    /**
     * Sets the additional charges for extra usage.
     *
     * @param additionalCharges The additional charges to set.
     */
    public void setAdditionalCharges(List<Integer> additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    // Additional methods

    /**
     * Adds a service to the program.
     *
     * @param service The service to add.
     */
    public void addService(Integer service) {
        services.add(service);
    }

    /**
     * Removes a service from the program.
     *
     * @param service The service to remove.
     */
    public void removeService(String service) {
        services.remove(service);
    }

    /**
     * Adds additional charge for extra usage.
     *
     * @param charge The additional charge to add.
     */
    public void addAdditionalCharge(Integer charge) {
        additionalCharges.set(0, charge);
    }

    /**
     * Removes additional charge for extra usage.
     *
     * @param charge The additional charge to remove.
     */
    public void removeAdditionalCharge(String charge) {
        additionalCharges.remove(charge);
    }
}
