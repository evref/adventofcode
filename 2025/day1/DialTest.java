import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DialTest {
    private Dial dial;

    @Before
    public void setUp() {
        dial = new Dial();
    }

    @Test
    public void rightAdds30() {
        dial.right(30);
        assertEquals(80, dial.getCurrentTicks());
    }
    @Test
    public void rightResetsCorrectlyAt100() {
        dial.right(50);
        assertEquals(0, dial.getCurrentTicks());
    }
    @Test
    public void rightMovesPast100Correctly() {
        dial.right(70);
        assertEquals(20, dial.getCurrentTicks());
    }
    @Test
    public void largeRightMovesCorrectly() {
        dial.right(370);
        assertEquals(20, dial.getCurrentTicks());
    }
    @Test
    public void rightZeroStepsDoesNothing() {
        dial.right(0);
        assertEquals(50, dial.getCurrentTicks());
    }

    @Test
    public void rightZeroStepsPassesNoZero() {
        dial.right(0);
        assertEquals(0, dial.getNumPassedZero());
    }
    @Test
    public void right100StepsPasses1Zero() {
        dial.right(100);
        assertEquals(1, dial.getNumPassedZero());
    }
    @Test
    public void right50StepsPassesNoZero() {
        dial.right(50);
        assertEquals(0, dial.getNumPassedZero());
    }
    @Test
    public void right350StepsPasses3Zeros() {
        dial.right(350);
        assertEquals(3, dial.getNumPassedZero());
    }
    

    @Test
    public void leftRemoves30() {
        dial.left(30);
        assertEquals(20, dial.getCurrentTicks());
    }
    @Test
    public void leftResetsCorrectlyAt99() {
        dial.left(51);
        assertEquals(99, dial.getCurrentTicks());
    }
    @Test
    public void leftMovesPast100Correctly() {
        dial.left(70);
        assertEquals(80, dial.getCurrentTicks());
    }
    @Test
    public void largeLeftMovesCorrectly() {
        dial.left(370);
        assertEquals(80, dial.getCurrentTicks());
    }
    @Test
    public void leftZeroStepsDoesNothing() {
        dial.left(0);
        assertEquals(50, dial.getCurrentTicks());
    }
    
    @Test
    public void leftZeroStepsPassesNoZero() {
        dial.left(0);
        assertEquals(0, dial.getNumPassedZero());
    }
    @Test
    public void left100StepsPasses1Zero() {
        dial.left(100);
        assertEquals(1, dial.getNumPassedZero());
    }
    @Test
    public void left50StepsPassesNoZero() {
        dial.left(50);
        assertEquals(0, dial.getNumPassedZero());
    }
    @Test
    public void left350StepsPasses3Zeros() {
        dial.left(350);
        assertEquals(3, dial.getNumPassedZero());
    }

    @Test
    public void samplePassZeroTest() {
        dial.left(68);
        dial.left(30);
        dial.right(48);
        dial.left(5);
        dial.right(60);
        dial.left(55);
        dial.left(1);
        dial.left(99);
        dial.right(14);
        dial.left(82);
        assertEquals(6, dial.getNumPassedZero() + dial.getNumZeros());
    }
}
