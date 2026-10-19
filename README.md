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
