import java.util.Arrays;

import static java.lang.System.out;

public class Lol {
    public static void main(String... args) {
        if (args.length == 0) {
            help();
            return;
        }
        switch (args[0]) {
            case "-m":
            case "--minions":
                minions(args[1]);
                break;
            case "-h":
            case "--help":
            default:
                help();
        }
    }

    // 1 2 !2 3 3 !4 4 5 !5 6 6 !7 7 8 !8 9 9 !10 10 11 !11 12
    //   6    19  31   44   57  69   82   95  107    120    133
    static int minions(String time) {
        final int minute = Integer.valueOf(time);
        int perfectFarm = 0;
        int minutesCounter = 1;
        double goldMelee = 20;
        double goldCaster = 17;
        double goldSiege = 45;
        double growthM = 0.125, growthS = 0.35;
        int gold = 0;
        int waves = 0;

        // in first 2 minutes you get 1 wave
        if (minutesCounter++ < minute) {
            perfectFarm += 6;
            gold += goldMelee * 3 + goldCaster * 3;
            goldMelee += growthM;
            goldCaster += growthM;
            goldSiege += growthS;
            waves++;
        }
        while (minutesCounter < minute) {
            perfectFarm += 12;
            gold += (int) goldMelee * 6 + (int) goldCaster * 6; // summing only int
            if (minutesCounter % 3 != 0) {
                perfectFarm++;
                gold += goldSiege;
            }
            goldMelee += growthM;
            goldCaster += growthM;
            goldSiege += growthS;
            minutesCounter++;
            waves += 2;
        }
        out.println("Perfect farm for " + time + " minute: " + perfectFarm);
        out.println("Minion waves: " + waves);
        out.println("Gold: " + gold);
        return perfectFarm;
    }

    private static void help() {
        out.println("Lol usage:\n" +
                "    -h | --help\n" +
                "    -m | --minions [time] e.g. [-m 11] for 11 minutes");
        out.println("League of Legends info:\n" +
                " - First minion wave spawns at 1:15.\n" +
                " - Minion waves spawns every 30 seconds.\n" +
                " - Minion waves meets at MM:15, MM:45.\n" +
                " - Every third minion wave has siege minion.\n" +
                ""
        );
    }
}
