# progarmme-part-2
# QuickChat

A console-based chat application written in Java (NetBeans). Users register,
log in, and then send, discard or store messages.

**Module:** [module name and code]
**Student(s):** [names and student numbers]
**Date:** October 2026
## Features

- User registration with validation:
  - Username contains an underscore and is no more than 5 characters
  - Password has at least 8 characters, a capital letter, a number and a special character
  - Cell number starts with +27 followed by 9 digits
- Login that only lets the user in with the correct details
- "Welcome to QuickChat" message and a numeric menu:
  1. Send Messages
  2. Show recently sent messages ("Coming Soon.")
  3. Quit
- The user chooses how many messages to enter when the app starts, and cannot enter more than that number
- Each message has an auto-generated 10-digit ID, a message number, a recipient, the message text and a message hash
- Messages are limited to 250 characters
- For each message the user can Send, Disregard or Store it to send later
- Stored messages are saved to a JSON file (`storedMessages.json`)
- The total number of messages sent is displayed at the end
## Project structure

| File | Purpose |
|------|---------|
| `Login.java` | Registration, validation and login logic |
| `Message.java` | Message creation, ID, hash, validation, sending and JSON storage |
| `AppChat.java` | Main program: registration, login and the QuickChat menu |
| `MessageTest.java` | JUnit unit tests for the Message class |
# How to run

1. Open the project in NetBeans.
2. Run `AppChat.java` (right-click, then Run File).
3. Follow the prompts to register, log in, and send messages.

To run the unit tests, right-click `MessageTest.java` and choose **Test File**.
## Message hash

The hash is made from the first two digits of the message ID, a colon, the
message number, a colon, and the first and last words of the message in capitals.
Example: `00:0:HITONIGHT`

## Storing messages in JSON

Messages are written to `storedMessages.json` using Java's `FileWriter`.
No external library was needed. Libraries such as Gson or org.json could be used instead.

## Unit tests

The tests use the test data from the brief and cover:

- Message length (success and failure)
- Recipient number format (success and failure)
- Message hash and message ID
- Send, Disregard and Store results
- Total number of messages sent

## Notes and assumptions

- The brief says the recipient number must have "no more than ten characters",
  but the test number `+27718693002` is 12 characters long. We followed the rule
  from the previous task (+27 followed by 9 digits) so that the test data is valid.
- The message ID is random, so the hash test reads the first two digits of the
  real ID instead of using a fixed value.
- Punctuation (such as `?`) is removed from the first and last words when
  building the hash.

## Use of AI

We used Claude (an AI assistant made by Anthropic) while working on this
assignment. It was used to:

- Explain the assignment steps in simple terms
- Help draft the `Message` class, the QuickChat menu code and the unit tests
- Check our code for errors and help fix them (for example, a misplaced
  constructor and a missing closing brace in `MessageTest.java`)
