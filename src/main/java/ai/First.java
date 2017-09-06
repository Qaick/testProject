package ai;

import java.util.Comparator;
import java.util.LinkedList;

import static ai.First.Learner.solve;

public class First
{
    static Comparator<neuro> neuroComparator = (o1, o2) -> o2.hit - o1.hit;
    static LinkedList<neuro> mind = new LinkedList();
    static {
        for (int i = 0; i < 26; i++)
        {
            mind.add(new neuro((char)('a'+i)));
        }
    }
    
    static class neuro{
        char c;
        int hit;
        neuro(char c){this.c = c;}
        @Override
        public String toString(){return ""+c;}
    }
    static class Learner
    {
        static String word = "";
        //finding new word depends on hits to neurons
        static void solve(Game g)
        {
            while (!g.win())
            {
                for (neuro n : mind)
                {
                    if (g.play(word + n))
                    {
                        n.hit++;
                        word = word + n;
                        break;
                    }
                }
                mind.sort(neuroComparator);
            }
            word = "";
        }
    }
    static class Game{
        private String question;
        private int cnt;
        private int tries;

        public Game(String word)
        {
            question = word;
        }

        boolean play(String ans){
            tries++;
            if (ans.charAt(cnt) == question.charAt(cnt)){
                cnt++;
                return true;
            }
            return false;
        }
        boolean win(){
            if (cnt == question.length()){
                System.out.println("Victory in "+ tries +" tries");
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args)
    {
        solve(new Game("cat"));
        solve(new Game("fat"));
        solve(new Game("what"));
    }
}
