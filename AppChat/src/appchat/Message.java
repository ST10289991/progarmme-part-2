
package appchat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
//import java.util.regex.Pattern
public class Message {
    private String messageID;
    private int messageNumber;      // starts at 0 for the first message
    private String recipient;
    private String messageText;
    private String messageHash;

    // Shared by ALL messages while the program runs
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;

    public Message(String recipient, String messageText, int messageNumber) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Random 10-digit number (leading zeros allowed, e.g. 0012345678)
    private String generateMessageID() {
        long number = (long) (Math.random() * 10_000_000_000L);
        return String.format("%010d", number);
    }

    // Message ID must be no more than 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Used by the "Message ID is created" test
    public String messageIDCreated() {
        return "Message ID generated: " + messageID;
    }

    // Message must not exceed 250 characters
    public String checkMessageLength() {
        int length = messageText.length();
        if (length <= 250) {
            return "Message ready to send.";
        } else {
            int extra = length - 250;
            return "Message exceeds 250 characters by " + extra
                    + "; please reduce the size.";
        }
    }

    // Same rule as the Login task: +27 followed by 9 digits
    public String checkRecipientCell() {
        if (recipient != null && Pattern.matches("^\\+27\\d{9}$", recipient)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not "
                    + "contain an international code. Please correct the number and try again.";
        }
    }

    // Example: 00:0:HITONIGHT
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        // remove anything that is not a letter or number (commas, ?, !)
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");

        String firstTwo = messageID.substring(0, 2);
        String hash = firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // 1 = Send, 2 = Disregard, 3 = Store
    public String sentMessage(int choice) {
        if (choice == 1) {
            sentMessages.add(this);
            totalMessagesSent++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage();
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // Details of one message, in the required order
    public String getDetails() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText + "\n";
    }

    // All messages sent while the program is running
    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append(m.getDetails()).append("\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Saves ALL stored messages to a JSON file (written by hand, no library needed)
    public void storeMessage() {
        storedMessages.add(this);

        try (FileWriter writer = new FileWriter("storedMessages.json")) {
            writer.write("[\n");
            for (int i = 0; i < storedMessages.size(); i++) {
                Message m = storedMessages.get(i);
                writer.write("  {\n");
                writer.write("    \"messageID\": \"" + m.messageID + "\",\n");
                writer.write("    \"messageHash\": \"" + m.messageHash + "\",\n");
                writer.write("    \"recipient\": \"" + m.recipient + "\",\n");
                writer.write("    \"message\": \"" + escape(m.messageText) + "\"\n");
                writer.write(i < storedMessages.size() - 1 ? "  },\n" : "  }\n");
            }
            writer.write("]\n");
        } catch (IOException e) {
            System.out.println("Could not save message: " + e.getMessage());
        }
    }

    // Quotes inside the message would break the JSON, so escape them
    private String escape(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    // Used by the unit tests so each test starts fresh
    public static void clearAll() {
        sentMessages.clear();
        storedMessages.clear();
        totalMessagesSent = 0;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
}

