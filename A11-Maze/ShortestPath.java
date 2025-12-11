import java.util.*;

/**
 * ShortestPath class implements Dijkstra's algorithm to find
 * the most efficient route through a Maze.
 *
 * Marks the shortest path from start to finish in the maze grid.
 * Uses a priority queue to expand nodes in order of increasing
 * distance until the finish is reached.
 *
 * @author Your Name
 * @version December 2025
 */
public class ShortestPath {

    private Maze maze;

    /**
     * Constructs a shortest path solver for the given maze.
     * @param maze the maze to solve
     */
    public ShortestPath(Maze maze) {
        this.maze = maze;
    }

    /**
     * Finds the shortest path using Dijkstra's algorithm.
     * @return true if a path was found, false otherwise
     */
    public boolean findShortestPath() {
        int rows = maze.getHeight();
        int cols = maze.getWidth();

        // Distance array initialized to infinity
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Predecessor array to reconstruct path
        MazeLocation[][] prev = new MazeLocation[rows][cols];

        MazeLocation start = maze.getStart();
        MazeLocation finish = maze.getFinish();

        dist[start.getRow()][start.getCol()] = 0;

        // Priority queue ordered by current distance
        PriorityQueue<MazeLocation> pq = new PriorityQueue<>(
            Comparator.comparingInt(loc -> dist[loc.getRow()][loc.getCol()])
        );
        pq.add(start);

        while (!pq.isEmpty()) {
            MazeLocation current = pq.poll();

            if (current.equals(finish)) {
                break;
            }

            for (MazeDirection dir : MazeDirection.values()) {
                MazeLocation neighbor = current.move(dir);
                int r = neighbor.getRow();
                int c = neighbor.getCol();

                if (r < 0 || r >= rows || c < 0 || c >= cols) {
                    continue; // out of bounds
                }
                if (!maze.checkExplorable(r, c)) {
                    continue; // not explorable
                }

                int newDist = dist[current.getRow()][current.getCol()] + 1;
                if (newDist < dist[r][c]) {
                    dist[r][c] = newDist;
                    prev[r][c] = current;
                    pq.add(neighbor);
                }
            }
        }

        // If finish is unreachable
        if (dist[finish.getRow()][finish.getCol()] == Integer.MAX_VALUE) {
            System.out.println("No path found.");
            return false;
        }

        // Reconstruct path backwards from finish
        MazeLocation step = finish;
        while (step != null) {
            maze.markPath(step);
            step = prev[step.getRow()][step.getCol()];
        }

        System.out.println("Shortest path found with length " +
            dist[finish.getRow()][finish.getCol()]);
        return true;
    }

    /**
     * Main method: reads maze file from command line and solves it.
     * @param args command line arguments (expects maze filename)
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ShortestPath <mazeFile>");
            return;
        }

        Maze maze = new Maze(args[0]);
        ShortestPath sp = new ShortestPath(maze);
        sp.findShortestPath();

        MazeViewer viewer = new MazeViewer(maze);
        viewer.showMaze();
    }
}
