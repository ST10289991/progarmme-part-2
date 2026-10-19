package appchat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Mashi
 */
public class MessageTest {

    private Message msg1;
    private Message msg2;

    @BeforeEach
    public void setUp() {
        Message.clearAll();   // reset the counters before every test
        msg1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        msg2 = new Message("08575975889", "Hi Keegan, did you receive the payment?", 1);
    }

    // ---- Message length ----
    @Test
    public void testMessageLengthSuccess() {
        assertEquals("Message ready to send.", msg1.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "a".repeat(260);
        Message longMsg = new Message("+27718693002", longText, 0);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                longMsg.checkMessageLength());
    }

    // ---- Recipient number ----
    @Test
    public void testRecipientSuccess() {
        assertEquals("Cell phone number successfully captured.", msg1.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        assertEquals("Cell phone number is incorrectly formatted or does not "
                + "contain an international code. Please correct the number and try again.",
                msg2.checkRecipientCell());
    }

    // ---- Message hash (loop through both test messages) ----
    @Test
    public void testMessageHash() {
        Message[] messages = { msg1, msg2 };
        String[] endings = { "0:HITONIGHT", "1:HIPAYMENT" };

        for (int i = 0; i < messages.length; i++) {
            String id = messages[i].getMessageID();
            String expected = id.substring(0, 2) + ":" + endings[i];
            assertEquals(expected, messages[i].getMessageHash());
        }
    }

    // ---- Message ID ----
    @Test
    public void testMessageIDCreated() {
        assertEquals("Message ID generated: " + msg1.getMessageID(), msg1.messageIDCreated());
        assertTrue(msg1.checkMessageID());
    }

    // ---- Send / Disregard / Store ----
    @Test
    public void testSendMessage() {
        assertEquals("Message successfully sent.", msg1.sentMessage(1));
    }

    @Test
    public void testDisregardMessage() {
        assertEquals("Press 0 to delete the message.", msg2.sentMessage(2));
    }

    @Test
    public void testStoreMessage() {
        assertEquals("Message successfully stored.", msg1.sentMessage(3));
    }

    // ---- Total sent: message 1 sent, message 2 discarded = 1 ----
    @Test
    public void testTotalMessagesSent() {
        msg1.sentMessage(1);
        msg2.sentMessage(2);
        assertEquals(1, Message.returnTotalMessages());
    }
}
