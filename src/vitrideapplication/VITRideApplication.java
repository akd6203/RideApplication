/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vitrideapplication;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Base class for all types of users
class User {
    private String name;
    private String email;
    private String password;

    // Constructor to initialize user properties
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters for email and password
    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // Methods to update user information
    public void updateName(String newName) {
        this.name = newName;
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}

// Passenger class, a subclass of User
class Passenger extends User {
    private double rating;            // Passenger's rating
    private ArrayList<String> rides;  // List of rides taken

    // Constructor to initialize passenger properties
    public Passenger(String name, String email, String password) {
        super(name, email, password);
        this.rating = 5.0;           // Initialize rating to 5.0
        this.rides = new ArrayList<>(); // Initialize an empty list of rides
    }

    // Method for booking a ride
    public void bookRide() {
        System.out.println("Booking a ride as #" + getEmail());
        // Add ride details to the list
        rides.add("Ride #" + (rides.size() + 1));
    }

    // Method for giving a rating to a driver
    public void giveRating(Driver driver, double rating) {
        if (rating >= 1.0 && rating <= 5.0) {
            driver.receiveRating(rating); // Pass the rating to the driver
            System.out.println("Rating of " + rating + " given to driver #" + driver.getEmail());
        } else {
            System.out.println("Invalid rating. Rating must be between 1.0 and 5.0.");
        }
    }

    // Method to get the passenger's rating
    public double getRating() {
        return rating;
    }

    // Method to view the list of rides
    public void viewRides() {
        System.out.println("Total Rides: " + rides.size());
        for (String ride : rides) {
            System.out.println(ride);
        }
    }
}

// Driver class, a subclass of User
class Driver extends User {
    private double rating;            // Driver's rating
    private ArrayList<String> rides;  // List of rides accepted

    // Constructor to initialize driver properties
    public Driver(String name, String email, String password) {
        super(name, email, password);
        this.rating = 0.0;           // Initialize rating to 0.0
        this.rides = new ArrayList<>(); // Initialize an empty list of rides
    }

    // Method for accepting a ride
    public void acceptRide() {
        System.out.println("Accepting a ride as #" + getName());
        rides.add("Ride #" + (rides.size() + 1));
    }

    // Method to receive a rating from a passenger
    public void receiveRating(double rating) {
         if (rides.size() > 0) {
            this.rating = ((this.rating * (rides.size() - 1)) + rating) / rides.size();
        } else {
            this.rating = rating;
        }
    }

    // Method to get the driver's rating
    public double getRating() {
        return rating;
    }

    // Method to view the list of accepted rides
    public void viewRides() {
        System.out.println("My Rides:");
        for (String ride : rides) {
            System.out.println(ride);
        }
    }
}

// Admin class, a subclass of User
class Admin extends User {
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    // Method to view all users
    public void viewAllUsers(ArrayList<User> users) {
        System.out.println("All Users:");
        for (User user : users) {
            System.out.println("Name: " + user.getName() + ", Email: " + user.getEmail());
        }
    }

    // Method to add a new user
    public void addUser(ArrayList<User> users, String name, String email, String password, String role) {
        User newUser;
        switch (role.toLowerCase()) {
            case "passenger":
                newUser = new Passenger(name, email, password);
                break;
            case "driver":
                newUser = new Driver(name, email, password);
                break;
            case "admin":
                newUser = new Admin(name, email, password);
                break;
            default:
                System.out.println("Invalid role. User not added.");
                return;
        }
        users.add(newUser);
        System.out.println("User added successfully.");
    }

    // Method to delete a user
    public void deleteUser(ArrayList<User> users, String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                users.remove(user);
                System.out.println("User with email " + email + " has been deleted.");
                return;
            }
        }
        System.out.println("User not found. Deletion failed.");
    }
}

public class VITRideApplication {
    private ArrayList<User> users = new ArrayList<>(); // List of all users
    private Admin admin; // Admin user

    // Constructor to initialize the application with an admin user
    public VITRideApplication() {
        admin = new Admin("Admin", "admin@example.com", "adminpass");
        users.add(admin);
    }

    // Method to register a new user
    public boolean register(String name, String email, String password, String role) {
        // Validation for empty values
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("Name, email, and password cannot be empty. Registration failed.");
            return false;
        }

        // Validation for email format
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format. Registration failed.");
            return false;
        }

        // Validation for password format
        if (password.length() > 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            System.out.println("Password must be 8 characters or less, contain at least one lowercase letter, one uppercase letter, and one number.");
            return false;
        }

        // Check if a user with the same email already exists
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("User with this email already exists. Registration failed.");
                return false;
            }
        }

        User newUser;
        // Create a new user based on the specified role
        switch (role.toLowerCase()) {
            case "passenger":
                newUser = new Passenger(name, email, password);
                break;
            case "driver":
                newUser = new Driver(name, email, password);
                break;
            case "admin":
                newUser = new Admin(name, email, password);
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                return false;
        }

        // Add the new user to the list of users
        users.add(newUser);
        System.out.println("Registration successful for " + name + " with email " + email + " as a " + role);
        return true;
    }

    // Method to log in a user
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Login successful for " + user.getName());
                return user;
            }
        }
        System.out.println("Login failed. Invalid email or password.");
        return null;
    }

    // Method for a passenger to book a ride
    public void bookRide(Passenger passenger) {
        if (passenger != null) {
            passenger.bookRide();
            System.out.println("Ride booked for passenger #" + passenger.getName());
        } else {
            System.out.println("Passenger not found. Ride booking failed.");
        }
    }

    // Method for a driver to accept a ride
    public void acceptRide(Driver driver) {
        if (driver != null) {
            driver.acceptRide();
            System.out.println("Ride accepted by driver #" + driver.getName());
        } else {
            System.out.println("Driver not found. Ride acceptance failed.");
        }
    }

    // Method to update user information (name, email, password)
    public void updateUserInfo(User user, String name, String email, String password) {
        if (user != null) {
            user.updateName(name);
            user.updateEmail(email);
            user.updatePassword(password);
            System.out.println("User information updated for " + user.getName());
        } else {
            System.out.println("User not found. Update failed.");
        }
    }

    // Method to validate an email address format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        VITRideApplication system = new VITRideApplication();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the VIT Ride Application!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nRegister User:");
                    System.out.print("Enter your name: ");
                    String regName = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String regEmail = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String regPassword = scanner.nextLine();
                    System.out.print("Enter your role (passenger, driver, or admin): ");
                    String regRole = scanner.nextLine();
                    system.register(regName, regEmail, regPassword, regRole);
                    break;

                case 2:
                    System.out.println("\nLogin:");
                    System.out.print("Enter your email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String loginPassword = scanner.nextLine();
                    User loggedInUser = system.login(loginEmail, loginPassword);
                    if (loggedInUser != null) {
                        System.out.println("\nWelcome, #" + loggedInUser.getName() + "!");
                        if (loggedInUser instanceof Passenger) {
                            System.out.println("Passenger Options:");
                            System.out.println("1. Update Details");
                            System.out.println("2. Book Ride");
                            System.out.println("3. Give Rating to Driver");
                            System.out.println("4. View My Rides");
                            int passengerChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (passengerChoice == 1) {
                                System.out.print("Enter new name: ");
                                String newName = scanner.nextLine();
                                System.out.print("Enter new email: ");
                                String newEmail = scanner.nextLine();
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();
                                system.updateUserInfo(loggedInUser, newName, newEmail, newPassword);
                            } else if (passengerChoice == 2) {
                                system.bookRide((Passenger) loggedInUser);
                            } else if (passengerChoice == 3) {
                                System.out.print("Enter driver's email to give a rating: ");
                                String driverEmail = scanner.nextLine();
                                Driver ratedDriver = null;
                                for (User user : system.users) {
                                    if (user instanceof Driver && user.getEmail().equals(driverEmail)) {
                                        ratedDriver = (Driver) user;
                                        break;
                                    }
                                }
                                if (ratedDriver != null) {
                                    System.out.print("Enter rating (1.0 to 5.0): ");
                                    double rating = scanner.nextDouble();
                                    scanner.nextLine();
                                    ((Passenger) loggedInUser).giveRating(ratedDriver, rating);
                                } else {
                                    System.out.println("Driver not found. Rating failed.");
                                }
                            } else if (passengerChoice == 4) {
                                ((Passenger) loggedInUser).viewRides();
                            }
                        } else if (loggedInUser instanceof Driver) {
                            System.out.println("Driver Options:");
                            System.out.println("1. Update Profile");
                            System.out.println("2. Accept Ride");
                            System.out.println("3. View Rides");
                            System.out.println("4. View Ratings");
                            int driverChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (driverChoice == 1) {
                                System.out.print("Enter new name: ");
                                String newName = scanner.nextLine();
                                System.out.print("Enter new email: ");
                                String newEmail = scanner.nextLine();
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();
                                system.updateUserInfo(loggedInUser, newName, newEmail, newPassword);
                            } else if (driverChoice == 2) {
                                system.acceptRide((Driver) loggedInUser);
                            } else if (driverChoice == 3) {
                                ((Driver) loggedInUser).viewRides();
                            } else if (driverChoice == 4) {
                                System.out.println("My Rating: " + ((Driver) loggedInUser).getRating());
                            }
                        } else if (loggedInUser instanceof Admin) {
                            System.out.println("Admin Options:");
                            System.out.println("1. Add User");
                            System.out.println("2. Delete User");
                            System.out.println("3. View All Users");
                            int adminChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (adminChoice == 1) {
                                System.out.print("Enter user's name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter user's email: ");
                                String email = scanner.nextLine();
                                System.out.print("Enter user's password: ");
                                String password = scanner.nextLine();
                                System.out.print("Enter user's role (passenger, driver, or admin): ");
                                String role = scanner.nextLine();
                                ((Admin) loggedInUser).addUser(system.users, name, email, password, role);
                            } else if (adminChoice == 2) {
                                System.out.print("Enter email of the user to delete: ");
                                String emailToDelete = scanner.nextLine();
                                ((Admin) loggedInUser).deleteUser(system.users, emailToDelete);
                            } else if (adminChoice == 3) {
                                ((Admin) loggedInUser).viewAllUsers(system.users);
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}