import java.util.Scanner;

// _____________________________________________________________________________
// --- Validering av person m. Exception logik & State-hantering ---

public class Main3b1 {

    public static void main(String[] args) {
        // ANSI escape code for colored text.
        final String RED = "\u001B[31m";
        final String PURPLE = "\u001B[35m";
        final String RESET = "\u001B[0m"; // Resets the color to default.

        Scanner scanner = new Scanner(System.in);

        try {
            // _____________________________________________________________________________
            // --- 1. firstName ---

            String firstName = "";
            boolean validFirstName = false;
            do {
                System.out.println("\nEnter person's first name:");
                firstName = scanner.nextLine();
                try {
                    validateName(firstName, "First name");
                    validFirstName = true; // If validation is successful, exit loop.
                } catch (Exception e) {
                    System.err.println("* Error: " + e.getMessage());
                    if (!askToRetry(scanner)) {
                        System.out.println("\nExiting program. Goodbye!");
                        return; // Exit program
                    }
                }
            } while (!validFirstName); // Repeat until firstName is valid.

            // _____________________________________________________________________________
            // --- 2. lastName ---

            String lastName = "";
            boolean validLastName = false;
            do {
                System.out.println("\nEnter person's last name:");
                lastName = scanner.nextLine();
                try {
                    validateName(lastName, "Last name");
                    validLastName = true; // If validation is successful, exit loop.
                } catch (Exception e) {
                    System.err.println("* Error: " + e.getMessage());
                    if (!askToRetry(scanner)) {
                        System.out.println("\nExiting program. Goodbye!");
                        return; // Exit program
                    }
                }
            } while (!validLastName); // Repeat until lastName is valid.

            // _____________________________________________________________________________
            // --- 3. streetName ---

            String streetName = "";
            boolean validStreetName = false;
            do {
                System.out.println("\nEnter street name:");
                streetName = scanner.nextLine();
                try {
                    validateStreetName(streetName, "Street name");
                    streetName = streetName.trim(); // Store the trimmed version.
                    validStreetName = true; // If validation is successful, exit loop.
                } catch (Exception e) {
                    System.err.println("* Error: " + e.getMessage());
                    if (!askToRetry(scanner)) {
                        System.out.println("\nExiting program. Goodbye!");
                        return; // Exit program
                    }
                }
            } while (!validStreetName); // Repeat until streetName is valid.

            // _____________________________________________________________________________
            // --- 4. streetNumber ---

            String streetNumber = "";
            boolean validStreetNumber = false;
            do {
                System.out.println("\nEnter street number:");
                streetNumber = scanner.nextLine();
                try {
                    validateStreetNumber(streetNumber, "Street number");
                    validStreetNumber = true; // If validation is successful, exit loop.
                } catch (Exception e) {
                    System.err.println("* Error: " + e.getMessage());
                    if (!askToRetry(scanner)) {
                        System.out.println("\nExiting program. Goodbye!");
                        return; // Exit program
                    }
                }
            } while (!validStreetNumber); // Repeat until streetNumber is valid.

            // _____________________________________________________________________________
            // --- 5. personNumber ---

            // _____________________________________________________________________________
            // --- SUCCESS! ---

            System.out.println(
                PURPLE + "\nSuccessfully created person:" + RESET
                    + "\n   First name: " + firstName
                    + "\n    Last name: " + lastName
                    + "\n  Street name: " + streetName
                    + "\nStreet number: " + streetNumber
            );
        } catch (Exception e) {
            System.err.println("* Error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println(PURPLE + "\n* The TRY-CATCH is finished." + RESET);
        }
    } // main

// _____________________________________________________________________________
// --- Method ---

    public static void validateName(String name, String fieldName) throws Exception {
        // Check for null or empty strings.
        if (name == null || name.trim().isEmpty()) {
            throw new Exception(fieldName + " = Cannot be null or empty.");
        }

        // Check if name starts OR ends with a number.
        if (name.matches("^[0-9].*") || name.matches(".*[0-9]$")) {
            throw new Exception(fieldName + " = Cannot start or end with a number.");
        }

        // Check for invalid characters (only letters and hyphens allowed).
        if (!name.matches("^[a-öA-Ö-]+$")) {
            throw new Exception(fieldName + " = Only letters and hyphens allowed.");
        }

        // Check if name starts OR ends with a hyphen.
        if (name.startsWith("-") || name.endsWith("-")) {
            throw new Exception(fieldName + " = Cannot start or end with a hyphen.");
        }

        // Check for minimum of 2 letters.
        if (!name.matches(".*[a-öA-Ö].*[a-öA-Ö]")) {
            throw new Exception(fieldName + " = Must contain at least 2 letters.");
        }
    }

// _____________________________________________________________________________
// --- Method ---

    public static boolean askToRetry(Scanner scanner) {
        // ANSI escape code for colored text.
        final String RED = "\u001B[31m";
        final String PURPLE = "\u001B[35m";
        final String RESET = "\u001B[0m"; // Resets the color to default.

        while (true) {
            System.out.println("Do you want to try again? (y/n):");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("y")) {
                return true; // User wants to retry.
            } else if (response.equals("n")) {
                return false; // User wants to exit.
            } else {
                System.out.println(
                    PURPLE +
                        "Invalid input. Please enter 'y' for yes or 'n' for no."
                        + RESET);
            }

            /* --- Test cases ---
                -Dan, Dan-, 1Dan, Dan1, D4n, Dan#, D, 1, a1b, a-b, 1a2b,
            */
        }
    }

// _____________________________________________________________________________
// --- Method ---

    public static void validateStreetName(String name, String fieldName) throws Exception {
        // Trim leading and trailing spaces from the input.
        name = name.trim();

        // Check for null or empty strings.
        if (name.isEmpty()) {
            throw new Exception(fieldName + " = Cannot be null or empty.");
        }

        // Check for invalid characters (only letters, spaces, hyphens and apostrophes allowed).
        if (!name.matches("^[a-öA-Ö\\s'-]+$")) {
            throw new Exception(
                fieldName + " = Only letters, spaces, hyphens and apostrophes allowed.");
        }
    }

// _____________________________________________________________________________
// --- Method ---

    public static void validateStreetNumber(String streetNum, String fieldName) throws Exception {

        // Check for null or empty strings.
        if (streetNum.isEmpty()) {
            throw new Exception(fieldName + " = Cannot be null or empty.");
        }

        // Check for invalid characters: Only numbers and optionally one ending letter (with or without a space) allowed.
        if (!streetNum.matches("^[0-9 ]+\\s?[a-öA-Ö]?$")) {
            throw new Exception(
                fieldName + " = Only numbers and optionally one ending letter (with or without a space) allowed.");
        }
    }

// _____________________________________________________________________________
// --- Method ---



}

