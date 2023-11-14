/**
 * @author BatamSingh
 * @version 1.0
 * @since 2023
 */

package Account;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A menu-driven program of Admin account operation.
 * In this program an existing admin can add, remove, view accounts of users.
 * I use the concept of HashMap. HashMap is used to hold key as BankAccount number and value as Bank Details.
 * I also used the concept of serialization and deserialization of objects also.
 */
public class AdminOperation {
    Scanner sc = new Scanner(System.in);

    /**
     * @value adminData is to store the admin username and password
     */
    HashMap<String, String> adminData = new HashMap<>();

    /**
     * @value accountList is to store all the bank details of the existing accounts.
     */
    HashMap<String, Accounts> accountList = new HashMap<>();
    String userIn, passIn;

    /**
     * An admin has to log in to add, remove and view accounts
     */
    public void getLogin() {

        int x = 0;  // a counter variable for do while loop
        do {
            retrievingAdminData();  // first gathering existing admin information
            pullOutAccount();       // pull out all the existing accounts from a file

            System.out.println("***Welcome to Account ADMIN SECTION***");
            System.out.print("Please enter your username: ");
            setUserIn(sc.nextLine());
            System.out.print("Please input your password: ");
            setPassIn(sc.nextLine());

            if (verifyAdminData()) {
                System.out.println("Welcome BatamSingh");
                int choice;

                do {
                    System.out.println("Choose Option:");
                    System.out.println("1. Add Bank Account");
                    System.out.println("2. View Bank Account");
                    System.out.println("3. Remove Bank Account");
                    System.out.println("4. View all Bank Account");
                    System.out.println("5. Exit!");

                    choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) {
                        case 1:
                            addBankAccount();
                            break;
                        case 2:
                            viewBankAccount();
                            break;
                        case 3:
                            removeBankAccount();
                            break;
                        case 4:
                            viewAllBankAccount();
                            break;
                        case 5:
                            x = 1;
                            break;
                    }
                } while (x!=1);

            } else {
                System.out.println("Invalid Username or Password!");
                x = 2;
            }
        } while (x!=1);

    }

    /**
     * This method will pull out all the existing account details from the file as object and assign it to accountList (HashMap).
     * First, read the number of existing accounts then iterate.
     */
    private void pullOutAccount() {
        try {
            FileInputStream fis = new FileInputStream("D:\\IntelliJ\\Account\\AccountDetails.txt");     // path of the file
            ObjectInputStream ois = new ObjectInputStream(fis);

            Accounts ac = null;

            int size = ois.readInt();

            for (int i=0; i<size; i++) {
                ac = (Accounts)ois.readObject();
                accountList.put(ac.getAccNo(), ac);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void viewAllBankAccount() {
        if (accountList.isEmpty()) {
            System.out.println("No accounts found!");
        } else {
            for (Accounts x:accountList.values()) {
                System.out.println(x);
            }
        }
    }

    /**
     * Removing bank account from a Hashmap after verify a valid account number.
     */
    private void removeBankAccount() {
        String inputAccNo;
        System.out.print("Please enter the account number: ");

        inputAccNo = sc.nextLine();

        if (accountList.containsKey(inputAccNo)) {
            accountList.remove(inputAccNo);
            System.out.println("Successfully removed account!");
        }
        else System.out.println("Oops! No account details found!");

    }

    /**
     * View bank details for a customer after validating account number.
     * I used the concept of containsKey of a HashMap to verify.
     */
    private void viewBankAccount() {
        String inputAccNo;
        System.out.print("Please enter the account number: ");
        inputAccNo = sc.nextLine();

        if (accountList.containsKey(inputAccNo)) {
            System.out.println(accountList.get(inputAccNo));
        }
        else System.out.println("Oops! No account details found!");
    }

    /**
     * to add bank account I need only name, dob, address and phone... just for basic
     * And the account number will be generated automatically inside the Accounts class itself.
     */
    private void addBankAccount() {
        System.out.println("Input Account Details: 1. Name 2. Date of Birth 3. Address 4. Phone");

        String name, dob, add, ph;
        name = sc.nextLine();
        dob = sc.nextLine();
        add = sc.nextLine();
        ph = sc.nextLine();

        Accounts a1 = new Accounts(name, dob, add, ph);
        accountList.put(a1.getAccNo(), a1);
        System.out.println("\nSuccessfully Created Account for acc no.: " + a1.getAccNo());     // this will generate account number and print
    }

    /**
     * Retrieving admin username and password for further used as HashMap.
     */
    private void retrievingAdminData() {
        adminData.put("batamsingh", "1234");
        adminData.put("alisha", "1234");
    }

    /**
     * Verify admin username and password
     * I used the concept of Map.Entry as entry to iterate all the admin data and check true or false
     * @return true if username and password correct otherwise false.
     */
    private boolean verifyAdminData() {
        for (Map.Entry<String, String> entry : adminData.entrySet()) {
            if (entry.getKey().equals(getUserIn()) && entry.getValue().equals(getPassIn()))
                return true;
        }
        return false;
    }

    private void setUserIn(String s) {
        this.userIn = s;
    }

    private void setPassIn(String s) {
        this.passIn = s;
    }

    public String getUserIn() {
        return userIn;
    }

    public String getPassIn() {
        return passIn;
    }


}
