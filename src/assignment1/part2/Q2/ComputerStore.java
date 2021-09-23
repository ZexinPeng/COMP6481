package assignment1.part2.Q2;

import assignment1.part2.Q1.Computer;

public class ComputerStore {

    private Computer[] inventory;
    private final String password = "password";
    private int currentComputerNum = 0;

    public ComputerStore(int maxComputers) {
        inventory = new Computer[maxComputers];
    }

    public void route(int option) {
        switch (option) {
            case 1:
                validatePassword(1);
                break;
            case 2:
                validatePassword(2);
                break;
        }
    }

    private void validatePassword(int option) {
        // only three time try
        for (int i = 0; i < 3; i++) {
            System.out.print("Please input the password: ");
            String inputPwd = Scan.getScanner().next();
            if (inputPwd.equals(password)) {
                // the password is correct
                switch (option) {
                    case 1:
                        addComputer();
                        break;
                    case 2:
                        changeComputerInformation();
                        break;
                }
                break;
            }
            System.out.println("The password you entered is not correct.");
        }
    }

    private void changeComputerInformation() {
        // TODO
    }

    private void addComputer() {
        System.out.print("The password is correct. Please input the number of computers that you want to create: ");
        int computerNum = Scan.getScanner().nextInt();
        if (hasEnoughSpace(computerNum)) {
            for (int i = 0; i < computerNum; i++) {
                System.out.println("We need information for computer " + i + 1);
                addComputer2Inventory();
            }
        } else {
            System.out.println("The number should be equal or smaller than " + (inventory.length - currentComputerNum));
        }
    }

    private void addComputer2Inventory() {
        System.out.print("Please input the brand: ");
        String brand = Scan.getScanner().next();
        System.out.print("Please input the model: ");
        String model = Scan.getScanner().next();
        System.out.print("Please input the SN: ");
        Long sn = Scan.getScanner().nextLong();
        System.out.print("Please input the price: ");
        Double price = Scan.getScanner().nextDouble();
        inventory[currentComputerNum] = new Computer(brand, model, sn, price);
        currentComputerNum++;
    }

    private boolean hasEnoughSpace(int computerNum) {
        if (computerNum > inventory.length - currentComputerNum) {
            return false;
        }
        return true;
    }

    public static void printHelloMessage() {
        System.out.println("Welcome to Zexin and Sijie computer store.");
    }

    public void displayMenu() {
        System.out.print("What do you want to do?\n" +
                "1. Enter new computers (password required)\n" +
                "2. Change information of a computer (password required)\n" +
                "3. Display all computers by a specific brand\n" +
                "4. Display all computers under a certain a price.\n" +
                "5. Quit\n" +
                "Please enter your choice > ");
    }
}
