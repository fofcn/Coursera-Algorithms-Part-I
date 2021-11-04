/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PercolationTest {
    public static void main(String[] args) throws IOException {
        List<String> allLines = Files.readAllLines(
                Paths.get("C:\\Users\\Administrator\\Downloads\\percolation (1)\\greeting57.txt"));
        Percolation percolation = new Percolation(Integer.parseInt(allLines.get(0)));
        for (int i = 1; i < allLines.size(); i++) {
            if (allLines.get(i).trim().equals("")) {
                continue;
            }
            String[] cellIdx = allLines.get(i).trim().split("\\s+");
            System.out.println("lines: " + allLines.get(i));
            percolation.open(Integer.parseInt(cellIdx[0]), Integer.parseInt(cellIdx.length > 2 ? cellIdx[cellIdx.length - 1] : cellIdx[1]));
        }

        // boolean isFull = percolation.isFull(22, 28);
        // System.out.println("isFull(22, 28) ? " + isFull);

        System.out.println("percolation ? " + percolation.percolates());
    }
}
