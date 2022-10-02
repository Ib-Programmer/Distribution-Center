import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class File_reader extends Distribution_center {
    public static void main(String[] args) throws Exception {
        String dash1 = new String(new char[65]).replace('\0', '-');
        String dash2 = new String(new char[100]).replace('\0', '-');
        File donorfile = new File("donorinfo.csv");
        File NGOfile = new File("ngoinfo.csv");
        String[] onedonor = new String[100];
        String[] oneNGO = new String[100];
        String[][] donor = new String[500][4];
        String[][] NGO = new String[500][4];
        BufferedReader brdonor = new BufferedReader(new FileReader(donorfile));
        BufferedReader brNGO = new BufferedReader(new FileReader(NGOfile));
        String line;
        while ((line = brdonor.readLine()) != null)
            onedonor = line.split(",");
        for (int i = 0; i < onedonor.length; i++) {
            donor[i / 4][i % 4] = onedonor[i];
        }
        while ((line = brNGO.readLine()) != null)
            oneNGO = line.split(",");
        for (int i = 0; i < oneNGO.length; i++) {
            NGO[i / 4][i % 4] = oneNGO[i];
        }
        int NGOsize = oneNGO.length / 4;
        int donorsize = onedonor.length / 4;
        Distribution_center distribution_center = new Distribution_center();
        distribution_center.setter(donor, NGO, NGOsize, donorsize);
        distribution_center.setdonorquantity();
        distribution_center.setNGOquantity();
        System.out.println("\t\t\t" + dash1);
        System.out.println("\t\t\t" + "|" + "\t\t" + "Welcome to Distribution Center" + "\t\t\t" + "|");
        Scanner sc = new Scanner(System.in);
        int userinput = 0;
        int flag = 0;
        int selection = 0;
        do {
            System.out.println("\t\t\t" + dash1);
            System.out.println("\t\t\t" + "|" + "\t\t\t" + "1. View Donors" + "\t\t\t\t" + "|");
            System.out.println("\t\t\t" + "|" + "\t\t\t" + "2. View NGOs" + "\t\t\t\t" + "|");
            System.out.println("\t\t\t" + "|" + "\t\t" + "3. View NGOs  assign to  donors" + "\t\t\t" + "|");
            System.out.println("\t\t\t" + "|" + "\t\t\t" + "4. Main Menu" + "\t\t\t\t" + "|");
            System.out.println("\t\t\t" + dash1);
            System.out.println();
            System.out.print("\t\t\t\t" + "Please select  any  number of the above menu:");
            userinput = sc.nextInt();
            System.out.println("\n\n");
            if (userinput == 1) {
                //System.out.println(dash2);
                distribution_center.print_donor();
                System.out.println("\n\n");
                flag = 1;
            } else if (userinput == 2) {
                distribution_center.print_NGO();
                flag = 1;
            } else if (userinput == 3) {
                System.out.println(dash2);
                System.out.println("|" + " Donor" + "\t\t" + "|"+ " Phone" + "\t\t\t" + "|" + " Aid" + "\t\t\t" + "|" + " Quantity" + "\t" + "|" + " NGO" 
                        + "\t" + "|" + " Manpower" + " |");
                System.out.println(dash2);
                

                if (selection == 0) {
                    distribution_center.matching_one_to_one();
                    distribution_center.matching_manyNGO_to_manydonor();
                } else if (selection > 0) {
                    distribution_center.print_result();
                } else {
                }
                selection++;
                flag = 1;
            } else if (userinput == 4) {
                Main_Menu.main(args);
            } else {
                System.out.println("Wrong input");
                flag = 1;
            }
        } while (flag == 1);
    }
}