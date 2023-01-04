package system.forms;

import java.util.Scanner;

public class LandlineForm extends Forms {

    private String landline_num;
    private String receipt;

    public void extra_info()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please Enter your landline number:");
        setLandline_num(sc.nextLine());
        System.out.println("Please Enter your landline receipt plan Monthly or Quarter:");
        setReceipt(sc.nextLine().toLowerCase());
        while(!this.getReceipt().equals("monthly") && !this.getReceipt().equals("quarter"))
        {
            System.out.println("Please enter a valid receipt plan:");
            setReceipt(sc.nextLine().toLowerCase());
        }
        info.add(getLandline_num());
        info.add(getReceipt());

    }



    public String getLandline_num() {
        return landline_num;
    }

    public void setLandline_num(String landline_num) {
        this.landline_num = landline_num;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
