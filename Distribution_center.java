import java.util.Scanner;

public class Distribution_center {
    String dashA = new String(new char[100]).replace('\0', '-');
    String dashB = new String(new char[92]).replace('\0', '-');
    private String[][] donor = new String[500][4];
    private String[][] NGO = new String[500][4];
    private String[] result = new String[100];
    private String[] donorquantity = new String[100];
    private String[] NGOquantity = new String[100];
    private int NGsize;
    private int donorsize;
    private int resultsize;

    public void setter(String[][] donor, String[][] NGO, int NGsize, int donorsize) {
        this.donor = donor;
        this.NGO = NGO;
        this.NGsize = NGsize;
        this.donorsize = donorsize;
    }

    public void setdonorquantity() {
        for (int i = 0; i < donorsize; i++) {
            donorquantity[i] = donor[i][3];
        }
    }

    public void setNGOquantity() {
        for (int i = 0; i < NGsize; i++) {
            NGOquantity[i] = NGO[i][3];
        }
    }

    public void print_donor() {
        System.out.println(dashA);
        System.out.println("| " + "Donor" + "\t\t\t" + "| " + "Phone Number" + "\t\t\t" + "| " + "Aid" + "\t\t\t\t" + "| " + "Quantity" + " |");
        System.out.println(dashA);
        for (int i = 0; i < donorsize; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + donor[i][j] + "\t\t\t");
            }
            System.out.print("| " + donorquantity[i] + "        |");
            System.out.println();
            System.out.println(dashA);
            System.out.println("\n");
        }
    } 

    public void print_NGO() {
        System.out.println(dashB);
        System.out.println("| " + "NGO" + "\t\t\t" + "| " + "Manpower" + "\t\t" + "| " + "Aid" + "\t\t\t\t" + "| " + "Quantity" + " |");
        System.out.println(dashB);
        for (int i = 0; i < NGsize; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + NGO[i][j] + "\t\t\t");
            }
            System.out.print("| " + NGOquantity[i] + "        |");
            System.out.println();
            System.out.println(dashB);
            System.out.println("\n");
        }
    }

    // print match donation aid and NGO aid
    public void print_match(int k, int l) {
        for (int i = 0; i < 3; i++) {
            System.out.print("| " + donor[l][i] + "\t\t");
            result[resultsize] = donor[l][i];
            resultsize++;
        }
        System.out.print("| " + NGO[k][3] + "\t" + "| " + NGO[k][0] + "\t" + "| " + NGO[k][1] + "\t\t" + "   |");
        System.out.println();
        result[resultsize] = NGO[k][3];
        resultsize++;
        result[resultsize] = NGO[k][0];
        resultsize++;
        result[resultsize] = NGO[k][1];
        resultsize++;
        System.out.println(dashA);
    }

    // print unmatched donated aid
    public void print_unmatch(int k, int l, int quantity) {
        for (int i = 0; i < 3; i++) {
            System.out.print("| " + donor[l][i] + "\t\t");
            result[resultsize] = donor[l][i];
            resultsize++;
        }
        System.out.print("| " + quantity + "\t\t");
        System.out.print("| " + NGO[k][0] + "\t" + "| " + NGO[k][1] + "\t"+ "   |");
        System.out.println();
        result[resultsize] = Integer.toString(quantity);
        resultsize++;
        result[resultsize] = NGO[k][0];
        resultsize++;
        result[resultsize] = NGO[k][1];
        resultsize++;
        System.out.println(dashA);
    }

    // print result of assigning NGO to donor
    public void print_result() {
        System.out.println();
        for (int i = 0; i < resultsize; i++) {
            System.out.print(result[i] + "\t\t");
            if ((i + 1) % 6 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        for (int i = 0; i < donorsize; i++) {
            int donated_aid = Integer.parseInt(donor[i][3]);
            if (donated_aid > 0) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(donor[i][j] + "\t\t");
                    if (j == 3) {
                        System.out.print("-" + "\t" + "-");
                        System.out.println();
                    }
                }
            }
        }
    }

    // match one donor to one NGO
    public void matching_one_to_one() {
        for (int i = 0; i < NGsize; i++) {
            for (int j = 0; j < donorsize; j++) {
                if (NGO[i][2].equalsIgnoreCase(donor[j][2])) {
                    int requested_aid = Integer.parseInt(NGO[i][3]);
                    int donated_aid = Integer.parseInt(donor[j][3]);
                    if (donated_aid != 0 && requested_aid != 0) {
                        if (requested_aid == donated_aid) {
                            int result = donated_aid - requested_aid;
                            donor[j][3] = Integer.toString(result);
                            print_match(i, j);
                        }
                    }
                }
            }
        }
    }

    // matching one donor to many NGO, one NGO to many donor and many NGO to many
    // donors
    public void matching_manyNGO_to_manydonor() {
        for (int i = 0; i < NGsize; i++) {
            for (int j = 0; j < donorsize; j++) {
                if (NGO[i][2].equalsIgnoreCase(donor[j][2])) {
                    int requested_aid = Integer.parseInt(NGO[i][3]);
                    int donated_aid = Integer.parseInt(donor[j][3]);
                    if (donated_aid != 0 && requested_aid != 0) {
                        if (requested_aid >= donated_aid) {
                            int result = requested_aid - donated_aid;
                            print_unmatch(i, j, donated_aid);
                            donated_aid -= donated_aid;
                            donor[j][3] = Integer.toString(donated_aid);
                            NGO[i][3] = Integer.toString(result);
                        }
                        if (donated_aid >= requested_aid) {
                            int result = donated_aid - requested_aid;
                            print_unmatch(i, j, requested_aid);
                            requested_aid -= requested_aid;
                            NGO[i][3] = Integer.toString(requested_aid);
                            donor[j][3] = Integer.toString(result);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < donorsize; i++) {
            int donated_aid = Integer.parseInt(donor[i][3]);
            if (donated_aid > 0) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(donor[i][j] + "\t\t");
                    if (j == 3) {
                        System.out.print("-" + "\t" + "-");
                        System.out.println();
                    }
                }
            }
        }
    }
}