import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LolTest {
    @Test
    public void minions() throws Exception {
        int[] checks = {0, 0, 6, 19, 31, 44, 57, 69, 82, 95, 107, 120, 133};
        for (int i = 0; i < checks.length; i++) {
            assertEquals(checks[i], Lol.minions(String.valueOf(i)));
        }
    }

}