import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridTest {
    Grid adjacentGrid, differentElementsGrid;

    @Before
    public void setUp() {
        int[][] tempAdjacentGrid = {
            {1, 0, 1, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 1},
            {1, 0, 0, 1}
        };
        adjacentGrid = new Grid(tempAdjacentGrid);

        int[][] tempDifferentElementsGrid = {
            {1, 0, 5, 0},
            {0, 2, 0, 8},
            {6, 0, 3, 9},
            {0, 7, 0, 4}
        };
        differentElementsGrid = new Grid(tempDifferentElementsGrid);
    }

    @Test
    public void getReturnsCorrectDiagonalElements() {
        for (int i = 0; i < differentElementsGrid.getWidth(); i++) {
            assertEquals(i+1, differentElementsGrid.get(i, i));
        }
    }
    @Test
    public void getReturnsCorrect() {
        assertEquals(5, differentElementsGrid.get(2, 0));
        assertEquals(8, differentElementsGrid.get(3, 1));
        assertEquals(6, differentElementsGrid.get(0, 2));
        assertEquals(7, differentElementsGrid.get(1, 3));
        assertEquals(2, differentElementsGrid.get(1, 1));
    }

    @Test
    public void sumAdjacentsMiddleReturnCorrect() {
        assertEquals(4, adjacentGrid.sumAdjacents(2, 1));
        assertEquals(3, adjacentGrid.sumAdjacents(1, 1));
        assertEquals(3, adjacentGrid.sumAdjacents(1, 2));
        assertEquals(3, adjacentGrid.sumAdjacents(2, 2));
    }
    @Test
    public void sumAdjacentsCornersReturnCorrect() {
        assertEquals(1, adjacentGrid.sumAdjacents(0, 0));
        assertEquals(1, adjacentGrid.sumAdjacents(3, 0));
        assertEquals(0, adjacentGrid.sumAdjacents(0, 3));
        assertEquals(2, adjacentGrid.sumAdjacents(3, 3));
    }
    @Test
    public void sumAdjacentsEdgeReturnCorrect() {
        assertEquals(3, adjacentGrid.sumAdjacents(1, 0));
        assertEquals(2, adjacentGrid.sumAdjacents(3, 2));
        assertEquals(3, adjacentGrid.sumAdjacents(2, 3));
        assertEquals(2, adjacentGrid.sumAdjacents(0, 1));
    }
}
