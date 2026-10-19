
package appchat;
  
  import java.util.regex.Pattern;
public class Login {
      // Stores the details of the ONE registered user (kept simple on purpose)
    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;
    private boolean isRegistered = false;
    // ---------------------------------------------------------
    // 1a. checkUserName
    // Rule: must contain an underscore AND be no more than 5 characters long
    // ---------------------------------------------------------
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        boolean hasUnderscore = username.contains("_");
        boolean correctLength = username.length() <= 5;
        return hasUnderscore && correctLength;
    }

    // ---------------------------------------------------------
    // 1a. checkPasswordComplexity
    // Rule: at least 8 characters, one capital letter, one number,
    //       one special character
    // ---------------------------------------------------------
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // ---------------------------------------------------------
    // 1b. checkCellPhoneNumber
    // Rule: must start with the South African international code (+27)
    //       followed by exactly 9 digits (e.g. +27838968976)
    // ---------------------------------------------------------
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) {
            return false;
        }
        String pattern = "^\\+27\\d{9}$";
        return Pattern.matches(pattern, cellNumber);
    }

    // ---------------------------------------------------------
    // 3. registerUser
    // Only stores the user's details if ALL checks pass.
    // Returns the correct message for whichever check fails first,
    // or a success message if everything is correct.
    // ---------------------------------------------------------
    public String registerUser(String username, String password, String cellNumber,
                                String firstName, String lastName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your "
                    + "username contains an underscore and is no more than five "
                    + "characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital "
                    + "letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an "
                    + "international code; please correct the number and try again.";
        }

        // All checks passed - store the user
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isRegistered = true;

        return "User " + username + " successfully registered!!";
    }

    // ---------------------------------------------------------
    // 3. loginUser
    // Returns true only if a user is registered AND the username
    // and password entered match the stored username and password.
    // ---------------------------------------------------------
    public boolean loginUser(String username, String password) {
        if (!isRegistered) {
            return false;
        }
        return this.username.equals(username) && this.password.equals(password);
    }

    // ---------------------------------------------------------
    // 3. returnLoginStatus
    // ---------------------------------------------------------
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Simple getters (useful for testing)
    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;

    }
}
