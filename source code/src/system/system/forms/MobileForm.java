package system.forms;

import java.util.Scanner;

public class MobileForm extends Forms {
    private String mobile_num;
    public void extra_info()
    {
        System.out.print("Please Enter your mobile number:");
        Scanner sc = new Scanner(System.in);
        setMobile_num(sc.nextLine());
        info.add(mobile_num);
    }
    public String  getMobile_num() {
        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        this.mobile_num = mobile_num;
    }
}
