package appchat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // ---------------------------------------------------------
    // checkUserName() tests
    // ---------------------------------------------------------

    @Test
    public void testCheckUserNameCorrect() {
        Login login = new Login();

        assertTrue(login.checkUserName("k_1"));
    }

    @Test
    public void testCheckUserNameIncorrect() {
        Login login = new Login();

        assertFalse(login.checkUserName("kyle"));
    }

    @Test
    public void testCheckUserNameTooLong() {
        Login login = new Login();

        assertFalse(login.checkUserName("kyle_123"));
    }

    @Test
    public void testCheckUserNameNull() {
        Login login = new Login();

        assertFalse(login.checkUserName(null));
    }


    // ---------------------------------------------------------
    // checkPasswordComplexity() tests
    // ---------------------------------------------------------

    @Test
    public void testCheckPasswordComplexityCorrect() {
        Login login = new Login();

        assertTrue(login.checkPasswordComplexity("Password1!"));
    }

    @Test
    public void testCheckPasswordTooShort() {
        Login login = new Login();

        assertFalse(login.checkPasswordComplexity("Pass1!"));
    }

    @Test
    public void testCheckPasswordNoCapitalLetter() {
        Login login = new Login();

        assertFalse(login.checkPasswordComplexity("password1!"));
    }

    @Test
    public void testCheckPasswordNoNumber() {
        Login login = new Login();

        assertFalse(login.checkPasswordComplexity("Password!"));
    }

    @Test
    public void testCheckPasswordNoSpecialCharacter() {
        Login login = new Login();

        assertFalse(login.checkPasswordComplexity("Password1"));
    }

    @Test
    public void testCheckPasswordNull() {
        Login login = new Login();

        assertFalse(login.checkPasswordComplexity(null));
    }


    // ---------------------------------------------------------
    // checkCellPhoneNumber() tests
    // ---------------------------------------------------------

    @Test
    public void testCheckCellPhoneNumberCorrect() {
        Login login = new Login();

        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberIncorrect() {
        Login login = new Login();

        assertFalse(login.checkCellPhoneNumber("0838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberWrongCountryCode() {
        Login login = new Login();

        assertFalse(login.checkCellPhoneNumber("+268838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberTooShort() {
        Login login = new Login();

        assertFalse(login.checkCellPhoneNumber("+278389"));
    }

    @Test
    public void testCheckCellPhoneNumberNull() {
        Login login = new Login();

        assertFalse(login.checkCellPhoneNumber(null));
    }


    // ---------------------------------------------------------
    // registerUser() tests
    // ---------------------------------------------------------

    @Test
    public void testRegisterUserSuccessful() {
        Login login = new Login();

        String result = login.registerUser(
                "k_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals("User k_1 successfully registered!!", result);
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        Login login = new Login();

        String result = login.registerUser(
                "kyle",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(result.contains("Username is not correctly formatted"));
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        Login login = new Login();

        String result = login.registerUser(
                "k_1",
                "password",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(result.contains("Password is not correctly formatted"));
    }

    @Test
    public void testRegisterUserInvalidCellNumber() {
        Login login = new Login();

        String result = login.registerUser(
                "k_1",
                "Password1!",
                "0838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(result.contains("Cell number is incorrectly formatted"));
    }


    // ---------------------------------------------------------
    // loginUser() tests
    // ---------------------------------------------------------

    @Test
    public void testLoginSuccessful() {
        Login login = new Login();

        login.registerUser(
                "k_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertTrue(login.loginUser("k_1", "Password1!"));
    }

    @Test
    public void testLoginIncorrectUsername() {
        Login login = new Login();

        login.registerUser(
                "k_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.loginUser("wrong", "Password1!"));
    }

    @Test
    public void testLoginIncorrectPassword() {
        Login login = new Login();

        login.registerUser(
                "k_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertFalse(login.loginUser("k_1", "WrongPassword1!"));
    }

    @Test
    public void testLoginBeforeRegistration() {
        Login login = new Login();

        assertFalse(login.loginUser("k_1", "Password1!"));
    }


    // ---------------------------------------------------------
    // returnLoginStatus() tests
    // ---------------------------------------------------------

    @Test
    public void testReturnLoginStatusSuccessful() {
        Login login = new Login();

        login.registerUser(
                "k_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        String result = login.returnLoginStatus(true);

        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                result
        );
    }

    @Test
    public void testReturnLoginStatusFailed() {
        Login login = new Login();

        String result = login.returnLoginStatus(false);

        assertEquals(
                "Username or password incorrect, please try again.",
                result
        );
    }


    // ---------------------------------------------------------
    // Getter tests
    // ---------------------------------------------------------

    @Test
    public void testGetUsername() {
        Login login = new Login();

        login.registerUser(
                "k_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals("k_1", login.getUsername());
    }

    @Test
    public void testGetFirstName() {
        Login login = new Login();

        login.registerUser(
                "k_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals("Kyle", login.getFirstName());
    }

    @Test
    public void testGetLastName() {
        Login login = new Login();

        login.registerUser(
                "k_1",
                "Password1!",
                "+27838968976",
                "Kyle",
                "Smith"
        );

        assertEquals("Smith", login.getLastName());
    }
}
