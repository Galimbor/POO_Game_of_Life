import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int gen = Integer.parseInt(sc.nextLine());
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        try {
            Board board = new Board(input);
            board.displayGenerations(gen);
        }
        catch (SentinelLLException SLLe)
        {
            System.out.println(SLLe.toString());
        }
        catch (SparseMatrixException SMe)
        {
            System.out.println(SMe.toString());
        }
        catch (BoardException Be)
        {
            System.out.println(Be.toString());
        }
        catch (Exception E)
        {
            System.out.println(E.toString());;
        }
    }


}
