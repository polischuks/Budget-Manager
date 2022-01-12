package budget;

import java.io.*;
import java.util.*;

class BudgetManager {

    private static final String FOOD = "Food";
    private static final String CLOTHES = "Clothes";
    private static final String ENTERTAINMENT= "Entertainment";
    private static final String OTHER = "Other";
    private static final String ALL = "All";

    Scanner sc = new Scanner(System.in);
    StringBuilder outS = new StringBuilder();
    String filename = "./purchases.txt";
    File file = new File(filename);

    Double balanceSum = 0.0;

    List<Purchase> listOfPurchases = new ArrayList<>();
    List<String> listOfLoaded = new ArrayList<>();
    List<Double> balance = new ArrayList<>();

    public void mainMenu() throws IOException {

        System.out.println("" +
                "Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
        switch (sc.nextLine()) {
            case "1": { addIncome(); }
            case "2": { addPurchase(); }
            case "3": { showListOfPurchases(); }
            case "4": { showBalance(); }
            case "5": { savePurchaseFromFile(); }
            case "6": { loadPurchaseFromFile(); }
            case "7": {
                new SortedInterface(listOfPurchases).run();
                mainMenu();
            }
            case "0": {
                System.out.println("\nBye!");
                sc.close();
                System.exit(0);
            }
        }
    }

    void createFileIO() {
        try {
            boolean createdNew = file.createNewFile();
            if (createdNew) {
              //  System.out.println("The file was successfully created.\n");
            } else {
              //  System.out.println("The file already exists.\n");
            }
        } catch (IOException e) {
            System.out.println("Cannot create the file: " + file.getPath());
        }
    }

    private void savePurchaseFromFile() throws IOException {

            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.println(saveBalance());
                for (Purchase purchas: listOfPurchases) {
                    String str = purchas.toSave();
                    printWriter.println(str);
                }
                System.out.println("\nPurchases were saved!\n");
                listOfPurchases.clear();

            } catch (IOException e) {
                System.out.printf("An exception occurred %s", e.getMessage());
            }
        mainMenu();
    }

    private void loadPurchaseFromFile() throws IOException {

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] s = scanner.nextLine().split("--");
                if (s.length == 1) {
                    balanceSum = Double.parseDouble(s[0]);
                } else {
                    listOfPurchases.add(new Purchase(s[0], s[1], Double.parseDouble(s[2])));
                }
            }
            System.out.println("\nPurchases were loaded!\n");
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filename);
        }
        mainMenu();
    }

    private void calculationBalance(){
        for (Double bal : balance) {
            balanceSum += bal;
        }
    }

    private void showBalance() throws IOException {
        calculationBalance();
        System.out.printf("\n" +
                "Balance: $%.2f", balanceSum);
        System.out.println("\n");
        mainMenu();
    }

    private String saveBalance() throws IOException {
        calculationBalance();
        return String.valueOf(balanceSum);
    }

    private void showListOfPurchases() throws IOException {
        String s = showListMenu();
        if (listOfPurchases.isEmpty()) {
            System.out.println("\nThe purchase list is empty\n");
            mainMenu();
        } else {
            Double total = 0.00;
            for (Purchase purchase : listOfPurchases) {
                if (Objects.equals(s, ALL)) {
                    outS.append(purchase).append("\n");
                    total += purchase.getPricePurchased();
                } else {
                    if (Objects.equals(purchase.getCategory(), s)) {
                        outS.append(purchase).append("\n");
                        total += purchase.getPricePurchased();
                    }
                }
            }
            System.out.println();
            System.out.println(s + ":");
            if (outS.length() > 0) {
                System.out.print(outS);
                System.out.printf("Total sum: $%.2f", total);
                System.out.println("\n");
            } else {
                System.out.println("The purchase list is empty!\n");
            }
            outS.delete(0, outS.length());
            showListOfPurchases();
        }
    }

    private String showListMenu() throws IOException {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
        switch (sc.nextLine()){
            case "1": return FOOD;
            case "2": return CLOTHES;
            case "3": return ENTERTAINMENT;
            case "4": return OTHER;
            case "5": return ALL;
            case "6": {
                System.out.println();
                mainMenu();
            }
        }
        return null;
    }

    private void addIncome() throws IOException {
        System.out.println("\n" +
                "Enter income:");
        balance.add(sc.nextDouble());
        sc.nextLine();
        System.out.println("Income was added!");
        System.out.println();
        mainMenu();
    }

    private void addPurchase() throws IOException {
        String n;
        while(true) {
            n = chosenPurchaseType();
            if (balanceSum < 0) {
                mainMenu();
            } else {

                System.out.println("\nEnter purchase name:");
                String s1 = sc.nextLine();

                System.out.println("Enter its price:");
                Double s = Double.valueOf(sc.nextLine());

                Purchase newPurchase = new Purchase(n, s1, s);
                balance.add(-s);
                listOfPurchases.add(newPurchase);
                System.out.println("Purchase was added!");
            }
        }
    }

    private String chosenPurchaseType() throws IOException {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
        switch (sc.nextLine()){
            case "1": return FOOD;
            case "2": return CLOTHES;
            case "3": return ENTERTAINMENT;
            case "4": return OTHER;
            case "5": {
                System.out.println();
                mainMenu();
            }
        }
        return null;
    }
}
