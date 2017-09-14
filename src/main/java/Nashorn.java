import org.mozilla.javascript.tools.debugger.Main;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Nashorn {
    public static void main(String[] args) throws Exception {
        Main.main(null);
        
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");
    }
}
