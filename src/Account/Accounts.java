/**
 * @author BatamSingh
 * @version 1.0
 * @since 2023
 */

package Account;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * A simple structure of Account class.
 *
 */
public class Accounts implements Serializable {
    private String accNo;
    private String accName;

    /**
     * @value 0 default counter value to set account number.
     */
    private static int count = 0;
    private String dob;
    private String address;

    /**
     * @value 2000 default account balance
     */
    private double bal = 2000;
    private String phone;

    /**
     * Default constructor which generates account number based on previous account number.
     * We have a counter variable called count to initialize account number.
     */
    public Accounts() {
        generateAccNo();
    }

    /**
     * Parametrized Constructor
     * It will also generate account number automatically based on previous account number using counter.
     * @param name name of the account holder
     * @param dob Date of Birth of the holder
     * @param address address of the holder
     * @param ph phone number of the holder
     */
    public Accounts(String name, String dob, String address, String ph) {
        generateAccNo();
        this.accName = name;
        this.dob = dob;
        this.address = address;
        this.phone = ph;

    }

    /**
     * This method will generate account number.
     * We have FileInputStream and ObjectInputStream object to read the existing number of accounts from a file.
     */
    private void generateAccNo() {
        try {
            FileInputStream fis = new FileInputStream("D:\\IntelliJ\\Account\\AccountDetails.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            count = count + ois.readInt();

            accNo = "SAV-" + ++count;

            ois.close();
            fis.close();
        } catch (IOException e) {
            accNo = "SAV-" + ++count;
        }
    }

    public String getAccNo() {
        return accNo;
    }

    public String getAccName() {
        return accName;
    }

    public static int getCount() {
        return count;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public double getBal() {
        return bal;
    }

    public String getPhone() {
        return phone;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBal(double bal) {
        this.bal = bal;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method overrides the toString method
     * @return toString method
     */
    @Override
    public String toString() {
        return  "accNo = " + accNo + '\n' +
                " accName = " + accName + '\n' +
                " dob = " + dob + '\n' +
                " address = " + address + '\n' +
                " bal = " + bal + '\n' +
                " phone = " + phone;
    }
}
