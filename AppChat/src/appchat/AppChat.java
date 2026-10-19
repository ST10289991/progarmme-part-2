
package appchat;

import java.util.Scanner;

public class AppChat {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Registration ===");

        // ---------------- Username ----------------
         String username;
        while (true) {
            System.out.print("Enter a username (must contain '_' and be no more than 5 characters): ");
            username = scanner.nextLine();

            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break; // only leaves the loop once the username is correct
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your "
                        + "username contains an underscore and is no more than five "
                        + "characters in length.");
                // loop goes back to the top and asks again - user CANNOT move on
            }
        }

        // ---------------- Password ----------------
        String password;
        while (true) {
            System.out.print("Enter a password (min 8 chars, 1 capital letter, 1 number, 1 special character): ");
            password = scanner.nextLine();

            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the "
                        + "password contains at least eight characters, a capital "
                        + "letter, a number, and a special character.");
            }
        }

        // ---------------- Cell phone number ----------------
        String cellNumber;
        while (true) {
            System.out.print("Enter your cell phone number (with country code, e.g. +27831234567): ");
            cellNumber = scanner.nextLine();

            if (login.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell number successfully added.");
                break;
            } else {
                System.out.println("Cell number is incorrectly formatted or does not contain an "
                        + "international code; please correct the number and try again.");
            }
        }

        // First name / last name (used later in the welcome message)
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // At this point every field is already known to be valid,
        // so registerUser() will always succeed - but we still check
        // the message in case something was missed.
        String registrationMessage = login.registerUser(username, password, cellNumber, firstName, lastName);
        System.out.println(registrationMessage);

        // ---------------- Login ----------------
        System.out.println();
        System.out.println("=== Login ===");

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();

            loggedIn = login.loginUser(loginUsername, loginPassword);
            String status = login.returnLoginStatus(loggedIn);
            System.out.println(status);
            // if loggedIn is still false, the while loop runs again -
            // the user cannot get past the login step with wrong details
        }

                // ---------------- QuickChat ----------------
        // We only get here once the user has logged in successfully
        System.out.println();
        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages do you want to enter? ");
        int maxMessages = Integer.parseInt(scanner.nextLine());

        int messagesEntered = 0;   // also used as the message number (starts at 0)
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            if (option.equals("1")) {

                if (messagesEntered >= maxMessages) {
                    System.out.println("You have already entered all " + maxMessages + " messages.");
                    continue;
                }

                // Recipient - keep asking until valid
                String recipient;
                while (true) {
                    System.out.print("Enter recipient cell number (e.g. +27718693002): ");
                    recipient = scanner.nextLine();
                    Message check = new Message(recipient, "check", 0);
                    String result = check.checkRecipientCell();
                    System.out.println(result);
                    if (result.equals("Cell phone number successfully captured.")) {
                        break;
                    }
                }

                // Message text - keep asking until 250 characters or less
                String text;
                while (true) {
                    System.out.print("Enter your message: ");
                    text = scanner.nextLine();
                    Message check = new Message(recipient, text, 0);
                    String result = check.checkMessageLength();
                    if (result.equals("Message ready to send.")) {
                        System.out.println("Message sent");
                        break;
                    } else {
                        System.out.println("Please enter a message of less than 250 characters.");
                        System.out.println(result);
                    }
                }

                Message msg = new Message(recipient, text, messagesEntered);
                messagesEntered++;

                System.out.println("1) Send Message");
                System.out.println("2) Disregard Message");
                System.out.println("3) Store Message to send later");
                System.out.print("Choose: ");
                int choice = Integer.parseInt(scanner.nextLine());

                System.out.println(msg.sentMessage(choice));

                if (choice == 1) {
                    System.out.println(msg.getDetails());
                } else if (choice == 2) {
                    System.out.print("Press 0 to delete: ");
                    scanner.nextLine();   // message is simply not kept
                }

                if (messagesEntered == maxMessages) {
                    System.out.println("Total messages sent: " + Message.returnTotalMessages());
                }

            } else if (option.equals("2")) {
                System.out.println("Coming Soon.");

            } else if (option.equals("3")) {
                System.out.println("Total messages sent: " + Message.returnTotalMessages());
                running = false;

            } else {
                System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close();
    }
    
}
