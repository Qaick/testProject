import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.startsWith;

public class ForTestsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void method() throws Exception {
        thrown.expectMessage(startsWith("some \"hello\" Message"));
        ForTests.method();
    }
    
    @Test
    public void method2() throws Exception {
        thrown.expectMessage(startsWith("wrong some Message"));
        ForTests.method();
    }

}