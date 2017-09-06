package jit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Bird { void doBird() { } }
class Sparrow extends Bird { }
class SpecificSparrow extends Sparrow { }
final class Final { void doFinal() { } }

// Producer Extends, Consumer Super.
public class Generics_QuestionMarkWildcard {

    public static void main(String[] args) {

        // Particular class that is superclass for jit.Bird. jit.Bird <= T <= Object
        List<? super Bird> birds = new ArrayList<Bird>();
        birds.add(new SpecificSparrow());
        birds.add(new Sparrow());
        birds.add(new Bird());
//        birds.add(new Object()); // list could point on List<jit.Bird>
        Object b = birds.get(0);
        System.out.println("Super working fine. " + b.getClass());


        // Particular class that extends jit.Bird. Could be anything below jit.Bird. T <= jit.Bird
        List<? extends Bird> birds2 = new ArrayList<Bird>(Arrays.asList(new Bird[]{new Bird(), new Sparrow()}));
//        birds2.add(new jit.Bird());
        Bird b2 = birds2.get(0);
        birds2.get(0).doBird();
        System.out.println("Extends working fine. " + b2.getClass());
    }
}