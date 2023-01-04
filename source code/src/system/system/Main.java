package system;
import java.util.Scanner;
import system.menus.*;

public class Main {

    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);

        CustomerMenu m = new CustomerMenu();
        AdminMenu adminMenu = new AdminMenu();
        int x;

        System.out.println("[1]: Admin");
        System.out.println("[2]: User");
        System.out.println("[3]: Exit");
        System.out.print("Enter your choice: ");
        x = sc.nextInt();
        while(x!=3) {
            while (x != 1 && x != 2) {
                System.out.print("Wrong Choice, Enter again: ");
                x = sc.nextInt();
            }

            if (x == 1) {
                adminMenu.ShowAdminMenu();
            } else if (x == 2) {
                m.ShowUserMenu();
            }

            System.out.println("[1]: Admin");
            System.out.println("[2]: User");
            System.out.println("[3]: Exit");
            System.out.print("Enter your choice: ");
            x = sc.nextInt();
        }
    }
}
