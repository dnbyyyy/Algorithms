import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class taskC{

    static class Pair{
        int first, second;

        Pair(int f, int s){
            first = f;
            second = s;
        }
    }

    static boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] col = new int[V];
        Arrays.fill(col, -1);

        Queue<Pair> q = new LinkedList<>();

        for (int i = 0; i < V; i++) {

            if (col[i] == -1) {

                q.add(new Pair(i, 0));
                col[i] = 0;

                while (!q.isEmpty()) {
                    Pair p = q.peek();
                    q.poll();

                    int v = p.first;

                    int c = p.second;

                    for (int j : adj.get(v)) {
                        if (col[j] == c)
                            return false;

                        if (col[j] == -1) {
                            col[j] = (c==1) ? 0 : 1;
                            q.add(new Pair(j, col[j]));
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(new FileReader("bipartite.in"));
        FileWriter writer = new FileWriter("bipartite.out");

        int V = reader.nextInt(), E = reader.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int src = reader.nextInt() - 1, dst = reader.nextInt() - 1;
            adj.get(src).add(dst);
            adj.get(dst).add(src);
        }

        if (isBipartite(V, adj))
            writer.write("YES");
        else
            writer.write("NO");

        reader.close();
        writer.close();
    }
}

