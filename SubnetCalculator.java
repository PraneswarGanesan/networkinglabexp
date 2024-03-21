import java.util.Scanner;

class SubnetCalculator {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ip address: ");
        String ip = sc.nextLine();
        String split_ip[] = ip.split("\\.");
        String split_bip[] = new String[4]; 
        String bip = "";
        for (int i = 0; i < 4; i++) {
            split_bip[i] = appendZeros(Integer.toBinaryString(Integer.parseInt(split_ip[i])));
            bip += split_bip[i];
        }
        System.out.println("Binary Format " + bip);
        System.out.print("Enter the number of addresses in each subnet: ");
        int n = sc.nextInt();
        int bits = (int) Math.ceil(Math.log(n) / Math.log(2));
        int mask = 32 - bits;
        System.out.println("Subnet mask = " + mask);
        int fbip[] = new int[32];
        for (int i = 0; i < 32; i++)
            fbip[i] = (int) bip.charAt(i) - 48; 
        for (int i = 31; i > 31 - bits; i--) 
            fbip[i] &= 0;
        String fip[] = { "", "", "", "" };
        for (int i = 0; i < 32; i++)
            fip[i / 8] = new String(fip[i / 8] + fbip[i]);
        System.out.print("Network address is = ");
        for (int i = 0; i < 4; i++) {
            System.out.print(Integer.parseInt(fip[i], 2));
            if (i != 3)
                System.out.print(".");
        }
        int lbip[] = new int[32];
        for (int i = 0; i < 32; i++)
            lbip[i] = (int) bip.charAt(i) - 48; 
        for (int i = 31; i > 31 - bits; i--) 
            lbip[i] |= 1;
        String lip[] = { "", "", "", "" };
        for (int i = 0; i < 32; i++)
            lip[i / 8] = new String(lip[i / 8] + lbip[i]);
        System.out.print("Broadcast address is = ");
        for (int i = 0; i < 4; i++) {
            System.out.print(Integer.parseInt(lip[i], 2));
            if (i != 3)
                System.out.print(".");
        }
    }

    static String appendZeros(String s) {
        String temp = new String("00000000");
        return temp.substring(s.length()) + s;
    }
}
