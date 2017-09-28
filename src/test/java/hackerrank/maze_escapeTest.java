package hackerrank;

import org.junit.Test;

import static hackerrank.maze_escape.makeYourMove;
import static hackerrank.maze_escape.move;
import static org.junit.Assert.assertEquals;

public class maze_escapeTest {

    String l1 =
            "#######\n" +
            "#--#-b#\n" +
            "#--#--#\n" +
            "#--#--#\n" +
            "e-----#\n" +
            "#-----#\n" +
            "#######";

    @Test
    public void makeYourMoveT() {
        StringBuilder sb = new StringBuilder("1");
        // combine map
        makeYourMove(sb.toString());

    }


    @Test
    public void pointExit() throws Exception {
        String map = "1\n" +
                "#--\n" +
                "e--\n" +
                "#--\n";
        assertEquals(maze_escape.side.LEFT, move(map));

        map = "1\n" +
                "e--\n" +
                "#--\n" +
                "#--\n";
        assertEquals(maze_escape.side.DOWN, move(map));

        map = "1\n" +
                "##e\n" +
                "#--\n" +
                "##-\n";
        assertEquals(maze_escape.side.RIGHT, move(map));

        map = "1\n" +
                "---\n" +
                "--#\n" +
                "--e\n";
        assertEquals(maze_escape.side.UP, move(map));
    }


}