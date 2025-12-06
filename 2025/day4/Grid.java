public class Grid {
    int[][] grid;
    int dimX, dimY;

    public Grid(int dimX, int dimY) {
        grid = new int[dimY][dimX];
        this.dimX = dimX;
        this.dimY = dimY;
    }
    public Grid(int[][] grid) {
        this.grid = grid;
        this.dimY = grid.length;
        this.dimX = grid[0].length;
    }

    public int getHeight() { return dimY; }
    public int getWidth() { return dimX; }

    public void print() {
        for (int y = 0; y < dimY; y++) {
            for (int x = 0; x < dimX; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public int get(int x, int y) {
        if (x < 0 || y < 0 || x >= dimX || y >= dimY) return 0;

        return grid[y][x];
    }

    public void set(int x, int y, int value) {
        if (x < 0 || y < 0 || x >= dimX || y >= dimY) throw new IllegalArgumentException("ERROR: Index out of bounds");
        if (value != 0 && value != 1) throw new IllegalArgumentException("ERROR: Illegal set value");

        grid[y][x] = value;
    }

    public int sumAdjacents(int x, int y) {
        int sum = 0;

        sum = get(x-1,y-1)+get(x,y-1)+get(x+1,y-1)+
            get(x-1,y)+get(x+1,y)+
            get(x-1,y+1)+get(x,y+1)+get(x+1,y+1);

        return sum;
    }
}
