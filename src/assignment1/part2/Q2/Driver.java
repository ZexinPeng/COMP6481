package assignment1.part2.Q2;

import assignment1.part2.Q1.Computer;

public class Driver {
    public static void main(String[] args) {
        // print hello message
        ComputerStore.printHelloMessage();

        ComputerStore computerStore = new ComputerStore(getMaxComputers());

        while(true) {
            computerStore.displayMenu();
            int nextOption = Scan.getScanner().nextInt();
            if (nextOption == 5) {
                computerStore.displayGoodBye();
                return;
            }
            computerStore.route(nextOption);
        }

    }

    private static int getMaxComputers() {
        System.out.print("Please input the maximum number of computers: ");
        return Scan.getScanner().nextInt();
    }
}

