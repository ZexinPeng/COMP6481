package assignment1.part2.Q2;

import assignment1.part2.Q1.Computer;

import java.util.LinkedList;

// -----------------------------------------------------
// Assignment 1
// © Sijie Min, Zexin Peng
// Written by: Sijie Min 40152234, Zexin Peng 40166520
// -----------------------------------------------------

public class ComputerStore {

    private Computer[] inventory;
    private final String password = "password";
    private int currentComputerNum = 0;

    public ComputerStore(int maxComputers) {
        inventory = new Computer[maxComputers];
    }

    // concrete router
    public void route(int option) {
        switch (option) {
            case 1:
                validatePassword(1);
                break;
            case 2:
                validatePassword(2);
                break;
            case 3:
                option3();
                break;
            case 4:
                option4();
                break;
        }
    }

    private void option4() {
        System.out.print("please input the price: ");
        double price = Scan.getScanner().nextDouble();
        LinkedList<Computer> computerList = findCheaperThan(price);
        if (computerList.size() == 0) {
            System.out.println("no computer found!");
        }
        for (Computer computer: computerList) {
            displayComputerInfo(computer);
        }
    }

    private LinkedList<Computer> findCheaperThan(double price) {
        LinkedList<Computer> computerList = new LinkedList<>();
        for (Computer computer: inventory) {
            if (computer == null) {
                return computerList;
            }
            if (computer.getPrice() < price) {
                computerList.add(computer);
            }
        }
        return computerList;
    }

    private void option3() {
        System.out.print("please input the brand name: ");
        String brand = Scan.getScanner().next();
        LinkedList<Computer> computerList = findComputerByBrand(brand);
        if (computerList.size() == 0) {
            System.out.println("no computer found!");
        }
        for (Computer computer: computerList) {
            displayComputerInfo(computer);
        }
    }

    private LinkedList<Computer> findComputerByBrand(String brand) {
        LinkedList<Computer> computerList = new LinkedList<>();
        for (Computer computer: inventory) {
            if (computer == null) {
                return computerList;
            }
            String str = "abc";
            String str1 = new String("abc");
            if (computer.getBrand().equals(brand)) {
                computerList.add(computer);
            }
        }
        return computerList;
    }

    /**
     * This method will validate the password and route the request by the option
     * @param option the option entered by the user
     */
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
        System.out.print("Please input the index of the computer(start from 0): ");
        int index = Scan.getScanner().nextInt();
        if (index >= currentComputerNum) {
            System.out.println("The index does not exist. Please input a number that below " + currentComputerNum);
            System.out.println("Retry input 1, go back to main menu please input 2");
            switch (Scan.getScanner().nextInt()) {
                case 1:
                    changeComputerInformation();
                    break;
                case 2:
                    return;
            }
        }

        Computer computer = inventory[index];
        System.out.println("Computer #" + index);
        displayComputerInfo(computer);
        changeComputerInfoImpl(computer);
    }

    private void changeComputerInfoImpl(Computer computer) {
        displayChangeComputerInfo();
        try {
            int index = Scan.getScanner().nextInt();
            if (index < 1 || index > 5) {
                throw new Exception();
            }
            switch (index) {
                case 1:
                    System.out.print("Please input new brand: ");
                    computer.setBrand(Scan.getScanner().next());
                    break;
                case 2:
                    System.out.print("Please input new model: ");
                    computer.setModel(Scan.getScanner().next());
                    break;
                case 3:
                    System.out.print("Please input new SN: ");
                    computer.setSN(Scan.getScanner().nextLong());
                    break;
                case 4:
                    System.out.print("Please input new price: ");
                    computer.setPrice(Scan.getScanner().nextDouble());
                    break;
                case 5:
                    return;
            }
            System.out.println("Information has been changed.");
            displayComputerInfo(computer);
            changeComputerInfoImpl(computer);
        } catch (Exception e) {
            System.out.println("Please input the valid number from 1 to 5");
            Scan.reset();
            changeComputerInfoImpl(computer);
        }
    }

    private void addComputer() {
        System.out.print("The password is correct. Please input the number of computers that you want to create: ");
        int computerNum = Scan.getScanner().nextInt();
        if (hasEnoughSpace(computerNum)) {
            for (int i = 0; i < computerNum; i++) {
                System.out.println("We need information for computer " + (i + 1));
                addComputer2Inventory();
            }
        } else {
            System.out.println("The number should be equal or smaller than " + (inventory.length - currentComputerNum));
        }
    }

    private void addComputer2Inventory() {
        System.out.print("Please input the brand(String): ");
        String brand = Scan.getScanner().next();
        System.out.print("Please input the model(String): ");
        String model = Scan.getScanner().next();
        System.out.print("Please input the SN(Long): ");
        long sn = Scan.getScanner().nextLong();
        System.out.print("Please input the price(Double): ");
        double price = Scan.getScanner().nextDouble();
        inventory[currentComputerNum] = new Computer(brand, model, sn, price);
        currentComputerNum++;
    }

    private boolean hasEnoughSpace(int computerNum) {
        return computerNum <= inventory.length - currentComputerNum;
    }

    public static void printHelloMessage() {
        System.out.println("Welcome to Zexin and Sijie computer store.");
    }

    private void displayComputerInfo(Computer computer) {
        System.out.println("Brand: " + computer.getBrand());
        System.out.println("Model: " + computer.getModel());
        System.out.println("SN: " + computer.getSN());
        System.out.println("Price: $" + computer.getPrice());
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

    private void displayChangeComputerInfo() {
        System.out.print("What information would you like to change?\n" +
                "1. brand\n" +
                "2. model\n" +
                "3. SN\n" +
                "4. price\n" +
                "5. Quit\n" +
                "Enter your choice > ");
    }

    public void displayGoodBye() {
        System.out.println("Thank you for using, bye bye!");
    }
}
