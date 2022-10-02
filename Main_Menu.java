import java.io.File;
import java.util.Scanner;

public class Main_Menu{
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int input;
        int flag;
        String line = new String(new char[65]).replace('\0', '-');
        System.out.println("\t\t\t" + line);
        System.out.println("\t\t\t" + "|" + "\t\t" + "Please select an option below" + "\t\t\t" + "|");
        System.out.println("\t\t\t" + line);
        System.out.println("\t\t\t" + "|" + "\t\t" + "0. Distibution center" + "\t\t\t\t" + "|");
        System.out.println("\t\t\t" + "|" + "\t\t" + "1. Quit" + "\t\t\t\t\t\t" + "|");
        System.out.println("\t\t\t" + line);
        System.out.println();
        System.out.print("\t\t\t\t" + "Please select  any  number of the above menu: ");
        input = sc.nextInt();
        System.out.println("\n\n\n");


        switch(input){
            case 0:
                File_reader.main(args);
                break;
            case 1:
                System.out.println("Exiting.....");
                System.exit(0);
            default :
                System.out.println("Invalid selection! Please input again.");
                main(args);
        }
    }
}
     
