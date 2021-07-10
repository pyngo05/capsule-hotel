package capsule;

import java.util.Arrays;
import java.util.Scanner;

public class CapsuleHotel {

    // array to hold capsules
    String[] guests;

    public void start() {
        printGreeting();
        int numberCapsules = getCapsuleCount();
        System.out.println("There are " + numberCapsules + " unoccupied capsules ready to be booked.");
        guests = new String[numberCapsules];

        int menuChoice = -1;

        boolean userWantsToQuit = false;
        while(!userWantsToQuit) {

            System.out.print("\nGuest Menu\n" +
                    "==========\n" +
                    "1. Check In\n" +
                    "2. Check Out\n" +
                    "3. View Guests\n" +
                    "4. Exit\n" +
                    "Choose on option [1-4]: ");

            // prevents the program from crashing when non-integer entered
            try {
                Scanner console = new Scanner(System.in);
                menuChoice = console.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid option, please select a valid option [1-4].");
                continue;
            }

            switch(menuChoice){
                case 1:
                    checkIn(numberCapsules);
                    break;
                case 2:
                    checkOut(numberCapsules);
                    break;
                case 3:
                    viewGuests(guests);
                    break;
                case 4:
                    userWantsToQuit = confirmExit();
                    break;
                // prevents the program from crashing when integer not in the range 1-4 entered
                default:
                    System.out.println("Invalid option, please select a valid option [1-4].");
                    break;
            }
        }
    }

    public void printGreeting() {
        System.out.println("\nWelcome to Capsule-Capsule.\n" +
                "===========================");
    }

    public int getCapsuleCount() {
        System.out.print("Enter the number of capsules available: ");
        Scanner console = new Scanner(System.in);
        int count;
        //if non-integer entered, the default number of capsules will be 100
        try {
            count = console.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: non-integer capsule count, defaulting to 100.");
            count = 100;
        }
        return count;
    }

    public void checkIn(int capsuleCount) {
        System.out.println("\nGuest Check In\n==============");
        Scanner console = new Scanner(System.in);

        String guestName = "";
        while (true) {
            System.out.print("Guest Name: ");
            guestName = console.next();

            if (guestName == "") {
                System.out.println("Error :(");
            } else {
                break;
            }
        }

        int capsuleNumber = -1;
        while (true) {
            System.out.printf("Capsule #[1-%d]: ", capsuleCount);
            capsuleNumber = console.nextInt();
            if (this.guests[capsuleNumber-1] != null) {
                System.out.printf("\nError :(\nCapsule #%d is occupied.\n", capsuleNumber);
            } else {
                break;
            }
        }

        // check in new guest
        this.guests[capsuleNumber-1] = guestName;
        System.out.printf("%s is booked in capsule %d\n", guestName, capsuleNumber);
    }

    public void checkOut(int capsuleCount) {
        System.out.println("\nGuest Check Out\n==============");
        Scanner console = new Scanner(System.in);

        int capsuleNumber = -1;
        while (true) {
            System.out.printf("Capsule #[1-%d]: ", capsuleCount);
            capsuleNumber = console.nextInt();

            if (guests[capsuleNumber-1] == null) {
                System.out.printf("Error :(\nCapsule #%d is unoccupied.\n", capsuleNumber);
                break;
            } else {
                System.out.printf("Success :)\n" + guests[capsuleNumber-1] +
                        " checked out from capsule %d\n", capsuleNumber);
                guests[capsuleNumber-1] = null;
                break;
            }
        }
    }

    public void viewGuests(String[] values) {
        System.out.printf("\nView Guests\n" +
                "===========\n" +
                "Capsule #[1-%d]: ", guests.length);
        int capsuleNumber = -1;
        Scanner console = new Scanner(System.in);
        capsuleNumber = console.nextInt();

        System.out.println("\nCapsule: Guest\n");
        // use min and max to control the range printed
        int firstCapsuleIndex = Math.max(0, capsuleNumber-6); // -1 to change to index, another -5 for range
        int lastCapsuleIndex = Math.min(guests.length-1, capsuleNumber+4); // -1 to change to index, +5 for range
        for (int i = firstCapsuleIndex; i <= lastCapsuleIndex; i++) {
            if (guests[i] == null) {
                System.out.printf("%d: [unoccupied]\n", i+1);
            } else {
                System.out.printf("%d: %s\n", i+1, guests[i]);
            }
        }

    }

    // Returns true if user confirmed that they want to exit.
    public boolean confirmExit() {
        Scanner console = new Scanner(System.in);
        String choice = "";
        System.out.println("Exit\n" +
                "====\n" +
                "Are you sure you want to exit?\n" +
                "All data will be lost.\n" +
                "Exit [y/n]:");
        choice = console.next();
        if (choice.toLowerCase().equals("y")) {
            System.out.println("\nGoodbye!");
            return true;
        } else {
            return false;
        }
    }



}
