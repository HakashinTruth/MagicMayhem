/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicmayhem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 9706
 */
public  class Maze {

    private int width;
    private int height;
    private int cellSize;
    private int ROWS;
    private int COLS;
    private Color WallColor = Color.WHITE;
    private Color CellColor = Color.BLACK;
    private int wallWidth = 4;
    private int visited = 8;
    private int left = 1;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int getROWS() {
        return ROWS;
    }

    public void setROWS(int ROWS) {
        this.ROWS = ROWS;
    }

    public int getCOLS() {
        return COLS;
    }

    public void setCOLS(int COLS) {
        this.COLS = COLS;
    }

    public Color getWallColor() {
        return WallColor;
    }

    public void setWallColor(Color WallColor) {
        this.WallColor = WallColor;
    }

    public Color getCellColor() {
        return CellColor;
    }

    public void setCellColor(Color CellColor) {
        this.CellColor = CellColor;
    }

    public int getWallWidth() {
        return wallWidth;
    }

    public void setWallWidth(int wallWidth) {
        this.wallWidth = wallWidth;
    }

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Wall getH() {
        return h;
    }

    public void setH(Wall h) {
        this.h = h;
    }

    public Wall getV() {
        return v;
    }

    public void setV(Wall v) {
        this.v = v;
    }
    public int up = 2;
    public int right = 3;
    public int down = 4;
    public List<Wall> walls = new ArrayList<>();
    public int[][] maze;
    public Random random = new Random();
    Wall  h;
    Wall v;
      
      
    public Maze(int scenewidth, int sceneheight) {
        height = sceneheight;
        width = scenewidth;
        cellSize = width / 10;
        ROWS = width / 10;
        COLS = height / 10;
        maze = new int[ROWS][COLS];

                // Generate the maze
        generateMaze(random.nextInt(ROWS), random.nextInt(COLS));

          // Create the rectangles for the walls
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if ((maze[r][c] & 1) == 0) {
                    // Add a horizontal wall
                    //seperate  walls horizontally evenly between each vertical rectangle
                     h = new Wall(/**location X**/(c * cellSize), /**location Y**/(r * cellSize), /**height**/(cellSize),/**width**/(wallWidth), false);
                    h.setFill(WallColor); //WHITE
                    walls.add(h); //ADD WALL TO THE LIST
                }
                if ((maze[r][c] & 2) == 0) {
                    // Add a vertical wall
                    //seperate walls vertically evenly between each horizontal rectangle
                     v = new Wall(/**location X**/(c * cellSize),/**location Y**/(r * cellSize), /**height**/(wallWidth),/**width**/(cellSize), true);
                    v.setFill(WallColor); //WHITE
                    walls.add(v); //ADD WALL TO THE LIST
                }
            }
        }

    }

    public void generateMaze(int row, int col) {
        maze[row][col] |= visited; // Mark the cell as visited

        while (true) {
            List<Integer> directions = new ArrayList<>();
            if (col > 0 && (maze[row][col - 1] & visited) == 0) {
                directions.add(1); // Left
            }
            if (row > 0 && (maze[row - 1][col] & visited) == 0) {
                directions.add(2); // Up
            }
            if (col < COLS - 1 && (maze[row][col + 1] & visited) == 0) {
                directions.add(3); // Right
            }
            if (row < ROWS - 1 && (maze[row + 1][col] & visited) == 0) {
                directions.add(4); // Down
            }

            if (directions.isEmpty()) {
                return;
            }

            int dir = directions.get(random.nextInt(directions.size()));// get random number within the array

            if (dir == 1) { //left
                maze[row][col] |= left; // Remove left wall
                col = col - 1;
                maze[row][col] |= visited; // Mark next cell as visited
                maze[row][col] |= 3; // Add right wall to next cell
            } else if (dir == 2) { //up
                maze[row][col] |= up; // Remove top wall
                row = row - 1;
                maze[row][col] |= visited; // Mark next cell as visited
                maze[row][col] |= 4; // Add bottom wall
            } else if (dir == 3) { //right
                maze[row][col] |= right; // Remove right wall
                col = col + 1;
                maze[row][col] |= visited; // Mark next cell as visited
                maze[row][col] |= 1; // Add left wall to next cell
            } else if (dir == 4) { //down
                maze[row][col] |= down; // Remove bottom wall
                row = row + 1;
                maze[row][col] |= visited; // Mark next cell as visited
                maze[row][col] |= 2; // Add top wall to next cell
            }

            generateMaze(row, col);
        }
    }
}
    

