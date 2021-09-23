package assignment1.part2.Q2;

public class Driver {
    public static void main(String[] args) {
        // print hello message
        ComputerStore.printHelloMessage();

        ComputerStore computerStore = new ComputerStore(getMaxComputers());

        while(true) {
            computerStore.displayMenu();
            int nextOption = Scan.getScanner().nextInt();
            computerStore.route(nextOption);
        }

    }

    private static int getMaxComputers() {
        System.out.print("Please input the maximum number of computers: ");
        return Scan.getScanner().nextInt();
    }
}
