package handlebars;


import java.io.IOException;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

public class HandlebarsTryout
{
    public static void main(String[] args)
    {
        Handlebars handlebars = new Handlebars();
        Context context = Context.newContext("undefined")
                .data("reportID", "153")
                .data("date", "12 January 2017");
        try
        {
            Template template = handlebars.compile("Response");
            System.out.println(template.apply(context));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
