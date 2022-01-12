package budget;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BudgetManager MyBudgetManager = new BudgetManager();
        MyBudgetManager.createFileIO();
        MyBudgetManager.mainMenu();

    }
}

