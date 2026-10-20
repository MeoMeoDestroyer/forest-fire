import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class FireTest {
    @Test
    public void testTimeToBurnExample() {
        char[][] forest = {
            {'t','.','.','t','t','t','t','.','t'},
            {'.','.','t','t','.','.','.','.','t'},
            {'.','.','t','t','t','t','t','t','t'},
            {'t','t','t','t','.','.','.','.','.'}
        };

        int matchR = 2;
        int matchC = 6;

        int expected = 8;
        int actual = Fire.timeToBurn(forest, matchR, matchC);

        assertEquals(expected, actual);
    }

    @Test
    public void nextMoves_allSides() {
        char[][] forest = {
            {'.','t','.'},
            {'t','t','t'},
            {'.','t','.'}
        };
        List<int[]> moves = Fire.nextMoves(forest, new int[] {1, 1});
        assertEquals(4, moves.size());
    }

    @Test
    public void nextMoves_corner() {
        char[][] forest = {
            {'t','t'},
            {'.','.'}
        };
        List<int[]> moves = Fire.nextMoves(forest, new int[] {0, 0});
        assertEquals(1, moves.size());
        assertEquals(0, moves.get(0)[0]);
        assertEquals(1, moves.get(0)[1]);
    }

    // test for timeToBurn
    @Test
    public void straightLine() {
        char[][] forest = { {'t','t','t','t','t'} };
        assertEquals(4, Fire.timeToBurn(forest, 0, 0));
    }

    @Test
    public void startInMiddle() {
        char[][] forest = { {'t','t','t','t','t'} };
        assertEquals(2, Fire.timeToBurn(forest, 0, 2));
    }
}
