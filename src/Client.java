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
        game.resize(game.rows+1,game.columns+1);
        System.out.println(game.toString());
    }
}
