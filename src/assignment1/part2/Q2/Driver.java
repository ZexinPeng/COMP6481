package assignment1.part2.Q2;

// -----------------------------------------------------
// Assignment 1
// © Sijie Min, Zexin Peng
// Written by: Sijie Min 40152234, Zexin Peng 40166520
// -----------------------------------------------------

public class Driver {
    public static void main(String[] args) {
        // print hello message
        ComputerStore.printHelloMessage();

        ComputerStore computerStore = new ComputerStore(getMaxComputers());

        while(true) {
            computerStore.displayMenu();
            int nextOption = Scan.getScanner().nextInt();
            // exit if the option is 5
            if (nextOption == 5) {
                computerStore.displayGoodBye();
                return;
            }
            // route the request
            computerStore.route(nextOption);
        }

    }

    private static int getMaxComputers() {
        System.out.print("Please input the maximum number of computers: ");
        return Scan.getScanner().nextInt();
    }
}

