package Account;

import java.io.*;

/**
 * This is the main class of AdminAccountOperation.
 * This class will perform save all the accounts in a file after using.
 * @author BatamSingh
 * @version 1.0
 * @since 2023
 */
public class Operation {
    public static void main(String[] args) {

        AdminOperation adminOperation = new AdminOperation();
        adminOperation.getLogin();

        try {
            FileOutputStream fos = new FileOutputStream("D:\\IntelliJ\\Account\\AccountDetails.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            int num = adminOperation.accountList.size();

            oos.writeInt(num);

            for (Accounts y : adminOperation.accountList.values()) {
                oos.writeObject(y);
            }

            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {

        }



    }
}
