
package system.forms;

import java.util.Scanner;

public class DonationsForm extends Forms {
    private String donation_location;

    public void extra_info()
    {
        System.out.println("Please Enter the name of the institute that would receive the donation:");
        Scanner sc = new Scanner(System.in);
        setDonation_location(sc.nextLine());
        info.add(getDonation_location());

    }



    public String getDonation_location() {
        return donation_location;
    }

    public void setDonation_location(String donation_location) {
        this.donation_location = donation_location;
    }
}