import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[1000][1000];
        int i = 0,j;
        while(sc.hasNextLine())
        {   j=0;
            String input [] = sc.nextLine().split("");
            for(String token : input)
            {
                arr[i][j++] = Integer.parseInt(token);
            }
            i++;
        }
    }
}
