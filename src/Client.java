import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        BidimensionalMatrix<Integer> game = new BidimensionalMatrix<>(input);
        System.out.print(game.toString());
        game.resize(game.rows,game.columns);
        System.out.println("----------------------------");
        System.out.println(game.toString());
    }
}
