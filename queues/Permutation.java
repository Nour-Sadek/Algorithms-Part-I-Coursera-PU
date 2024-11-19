/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            randomQueue.enqueue(StdIn.readString());
        }
        Iterator<String> iter = randomQueue.iterator();
        int i = 0;
        while (i < k && iter.hasNext()) {
            StdOut.println(iter.next());
            i++;
        }
    }

}
