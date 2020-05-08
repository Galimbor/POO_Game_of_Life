import java.time.Clock;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        int gen = Integer.parseInt(sc.nextLine());
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
//        long currentTime = System.currentTimeMillis();
        SparseMatrix<LivingCell> game = new SparseMatrix<>(input);
        game.showNextGenerations(gen);
//        long elapsedTime = System.currentTimeMillis() - currentTime;
//        System.out.println(elapsedTime + "ms");
    }
}
