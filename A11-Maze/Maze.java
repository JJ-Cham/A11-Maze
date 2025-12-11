import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Maze class implements the DisplayableMaze interface.
 * Encodes a maze as a 2D array of MazeContents and provides
 * methods to access and update maze state.
 *
 * Supports initialization from a demo maze or from a text file.
 *
 * @author Your Name
 * @version December 2025
 */
public class Maze implements DisplayableMaze {

    private int height;
    private int width;
    private MazeContents[][] mazeGrid;
    private MazeLocation start;
    private MazeLocation finish;

    /**
     * Default constructor.
     */
    public Maze() {
    }

    /**
     * Constructs a Maze by reading from a file.
     * @param filename the maze file to read
     */
    public Maze(String filename) {
        readFromFile(filename);
    }

    /**
     * Reads maze contents from a text file and encodes them.
     * @param filename the maze file to read
     */
    private void readFromFile(String filename) {
        try (Scanner file = new Scanner(new File(filename))) {
            List<String> lines = new ArrayList<>();
            while (file.hasNextLine()) {
                lines.add(file.nextLine());
            }

            height = lines.size();
            width = lines.stream().mapToInt(String::length).max().orElse(0);
            mazeGrid = new MazeContents[height][width];

            for (int i = 0; i < height; i++) {
                String line = lines.get(i);
                for (int j = 0; j < width; j++) {
                    char c = (j < line.length()) ? line.charAt(j) : '#';
                    switch (c) {
                        case '#':
                            mazeGrid[i][j] = MazeContents.WALL;
                            break;
                        case '.':
                        case ' ':
                            mazeGrid[i][j] = MazeContents.OPEN;
                            break;
                        case 'S':
                            start = new MazeLocation(i, j);
                            mazeGrid[i][j] = MazeContents.OPEN;
                            break;
                        case 'F':
                            finish = new MazeLocation(i, j);
                            mazeGrid[i][j] = MazeContents.OPEN;
                            break;
                        default:
                            mazeGrid[i][j] = MazeContents.WALL;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Maze file not found: " + filename);
            System.exit(-1);
        }
    }

    /** @return height of the maze */
    @Override
    public int getHeight() {
        return height;
    }

    /** @return width of the maze */
    @Override
    public int getWidth() {
        return width;
    }

    /** @return contents of the cell at (i,j) */
    @Override
    public MazeContents getContents(int i, int j) {
        return mazeGrid[i][j];
    }

    /** @return starting location of the maze */
    @Override
    public MazeLocation getStart() {
        return start;
    }

    /** @return finish location of the maze */
    @Override
    public MazeLocation getFinish() {
        return finish;
    }

    /**
     * Checks whether a cell is explorable (open and in bounds).
     * @param i row index
     * @param j column index
     * @return true if explorable, false otherwise
     */
    @Override
    public Boolean checkExplorable(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return false;
        }
        MazeContents cell = mazeGrid[i][j];
        return (cell == MazeContents.OPEN);
    }

    /** Marks a location as visited. */
    public void markVisited(MazeLocation loc) {
        mazeGrid[loc.getRow()][loc.getCol()] = MazeContents.VISITED;
    }

    /** Marks a location as a dead end. */
    public void markDeadEnd(MazeLocation loc) {
        mazeGrid[loc.getRow()][loc.getCol()] = MazeContents.DEAD_END;
    }

    /** Marks a location as part of the path. */
    public void markPath(MazeLocation loc) {
        mazeGrid[loc.getRow()][loc.getCol()] = MazeContents.PATH;
    }

    /** Initializes a demo maze for testing. */
    public void initDemoMaze() {
        this.height = 5;
        this.width = 5;
        this.mazeGrid = new MazeContents[height][width];
        this.start = new MazeLocation(0, 0);
        this.finish = new MazeLocation(4, 4);

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                mazeGrid[r][c] = MazeContents.OPEN;
            }
        }

        mazeGrid[1][1] = MazeContents.WALL;
        mazeGrid[2][2] = MazeContents.WALL;
    }

    /** This DemoMaze method will allow you to generate a simple maze
     * to test your code on as you develop it. Ultimately, you need
     * to accept maze files as command line inputs or standard input.
     * You will need to implement the DisplayableMaze interface before you
     * can run the initDemoMaze method.
     * * @author Tianah Gooden
     * * @version October 17th 2023
     */
    public void initDemoMaze(){ //String fileName, 
        this.height = 10;
        this.width = 8;
        this.mazeGrid = new MazeContents[height][width];
        this.start = new MazeLocation(1,1);
        this.finish = new MazeLocation(8,6);

        this.mazeGrid[0][0] = MazeContents.WALL; this.mazeGrid[0][1] = MazeContents.WALL; this.mazeGrid[0][2] = MazeContents.WALL; this.mazeGrid[0][3] = MazeContents.WALL; this.mazeGrid[0][4] = MazeContents.WALL; this.mazeGrid[0][5] = MazeContents.WALL; this.mazeGrid[0][6] = MazeContents.WALL; this.mazeGrid[0][7] = MazeContents.WALL;
        this.mazeGrid[1][0] = MazeContents.WALL; this.mazeGrid[1][1] = MazeContents.OPEN; this.mazeGrid[1][2] = MazeContents.OPEN; this.mazeGrid[1][3] = MazeContents.OPEN; this.mazeGrid[1][4] = MazeContents.OPEN; this.mazeGrid[1][5] = MazeContents.OPEN; this.mazeGrid[1][6] = MazeContents.WALL; this.mazeGrid[1][7] = MazeContents.WALL;
        this.mazeGrid[2][0] = MazeContents.WALL; this.mazeGrid[2][1] = MazeContents.WALL; this.mazeGrid[2][2] = MazeContents.OPEN; this.mazeGrid[2][3] = MazeContents.WALL; this.mazeGrid[2][4] = MazeContents.WALL; this.mazeGrid[2][5] = MazeContents.OPEN; this.mazeGrid[2][6] = MazeContents.WALL; this.mazeGrid[2][7] = MazeContents.WALL;
        this.mazeGrid[3][0] = MazeContents.WALL; this.mazeGrid[3][1] = MazeContents.OPEN; this.mazeGrid[3][2] = MazeContents.WALL; this.mazeGrid[3][3] = MazeContents.OPEN; this.mazeGrid[3][4] = MazeContents.OPEN; this.mazeGrid[3][5] = MazeContents.OPEN; this.mazeGrid[3][6] = MazeContents.WALL; this.mazeGrid[3][7] = MazeContents.WALL;
        this.mazeGrid[4][0] = MazeContents.WALL; this.mazeGrid[4][1] = MazeContents.OPEN; this.mazeGrid[4][2] = MazeContents.OPEN; this.mazeGrid[4][3] = MazeContents.OPEN; this.mazeGrid[4][4] = MazeContents.WALL; this.mazeGrid[4][5] = MazeContents.WALL; this.mazeGrid[4][6] = MazeContents.OPEN; this.mazeGrid[4][7] = MazeContents.WALL;
        this.mazeGrid[5][0] = MazeContents.WALL; this.mazeGrid[5][1] = MazeContents.OPEN; this.mazeGrid[5][2] = MazeContents.WALL; this.mazeGrid[5][3] = MazeContents.OPEN; this.mazeGrid[5][4] = MazeContents.OPEN; this.mazeGrid[5][5] = MazeContents.WALL; this.mazeGrid[5][6] = MazeContents.WALL; this.mazeGrid[5][7] = MazeContents.WALL;
        this.mazeGrid[6][0] = MazeContents.WALL; this.mazeGrid[6][1] = MazeContents.OPEN; this.mazeGrid[6][2] = MazeContents.WALL; this.mazeGrid[6][3] = MazeContents.WALL; this.mazeGrid[6][4] = MazeContents.OPEN; this.mazeGrid[6][5] = MazeContents.OPEN; this.mazeGrid[6][6] = MazeContents.OPEN; this.mazeGrid[6][7] = MazeContents.WALL;
        this.mazeGrid[7][0] = MazeContents.WALL; this.mazeGrid[7][1] = MazeContents.OPEN; this.mazeGrid[7][2] = MazeContents.WALL; this.mazeGrid[7][3] = MazeContents.OPEN; this.mazeGrid[7][4] = MazeContents.OPEN; this.mazeGrid[7][5] = MazeContents.WALL; this.mazeGrid[7][6] = MazeContents.OPEN; this.mazeGrid[7][7] = MazeContents.WALL;
        this.mazeGrid[8][0] = MazeContents.WALL; this.mazeGrid[8][1] = MazeContents.OPEN; this.mazeGrid[8][2] = MazeContents.OPEN; this.mazeGrid[8][3] = MazeContents.WALL; this.mazeGrid[8][4] = MazeContents.OPEN; this.mazeGrid[8][5] = MazeContents.WALL; this.mazeGrid[8][6] = MazeContents.OPEN; this.mazeGrid[8][7] = MazeContents.WALL;
        this.mazeGrid[9][0] = MazeContents.WALL; this.mazeGrid[9][1] = MazeContents.WALL; this.mazeGrid[9][2] = MazeContents.WALL; this.mazeGrid[9][3] = MazeContents.WALL; this.mazeGrid[9][4] = MazeContents.WALL; this.mazeGrid[9][5] = MazeContents.WALL; this.mazeGrid[9][6] = MazeContents.WALL; this.mazeGrid[9][7] = MazeContents.WALL;
  }
}

