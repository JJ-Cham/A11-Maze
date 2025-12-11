/**
 * SolveMaze class performs recursive exploration of a Maze.
 * Uses depth-first search with recursion to determine whether
 * the finish can be reached from the start.
 *
 * @author JJ Cham
 * @version December 2025
 */
public class SolveMaze {

    private Maze maze;

    /**
     * Constructs a solver for the given maze.
     * @param maze the maze to solve
     */
    public SolveMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Recursively explores the maze from a given location.
     * @param loc current location
     * @return true if finish is reachable, false otherwise
     */
    public boolean solve(MazeLocation loc) {
        try { Thread.sleep(50); } catch (InterruptedException e) {}

        if (loc.equals(maze.getFinish())) {
            maze.markPath(loc);
            return true;
        }

        if (!maze.checkExplorable(loc.getRow(), loc.getCol())) {
            return false;
        }

        maze.markVisited(loc);

        for (MazeDirection dir : MazeDirection.values()) {
            MazeLocation next = loc.move(dir);
            if (solve(next)) {
                maze.markPath(loc);
                return true;
            }
        }

        maze.markDeadEnd(loc);
        return false;
    }

    /**
     * Main method: reads maze file from command line and solves it.
     * @param args command line arguments (expects maze filename)
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java SolveMaze <mazeFile>");
            return;
        }

        Maze maze = new Maze(args[0]);
        SolveMaze solver = new SolveMaze(maze);

        boolean solved = solver.solve(maze.getStart());

        if (solved) {
            System.out.println("Maze solved!");
        } else {
            System.out.println("No solution found.");
        }

        MazeViewer viewer = new MazeViewer(maze);
        viewer.showMaze();
    }
}


