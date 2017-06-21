package archive_06_06_2017;

import java.io.*;
import java.util.*;

public class Untagged {
    public static void main(String[] args) throws IOException {
        crypto2();
    }

    //ep1
    /*Сдпнпзю - ьсн йнвгя бре зжбдрсмн, мн мзцдвн мд пяансядс. Опяйсзйя - ьсн йнвгя бре пяансядс з мзйсн мд жмядс онцдлт. Лъ зф наыдгзмюдл, сднпзэ з опяйсзйт: мзцдвн мд пяансядс з мзйсн мд жмядс онцдлт. Опяйсзйтисдры, внронгя йпзоснямяпфзрсъ з чзупноямйз.
    абвгдэежзыийклмнопрстуфхцчшщьюя
    бвгдэежзыийклмнопрстуфхцчшщьюяа
     */
    //ep2
    /*ырыуэнжятм дксцрзсофь сьнющсюи рфжкфпьвюу. иу нмзуео мьввно нирфэ ды тхнуптр о яюы, май яоыоаюь ешгт, ддшочуръвмвк мосьроф нр пыу аклт ыи вонъма быжрсыоаю, кьощф бююпцнцк, дл и яюых уёт нш ъоп схыв. умнарьэе ф удшбжсы
    */
    static void crypto() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        for (char c : s.toCharArray()) {
            if (('А' <= c && c < 'Я') || ('а' <= c && c < 'я')) System.out.print((char) (c + 1));
            else if (c == 'я' || c == 'Я') System.out.print((char) (c - 'я' + 'а'));
            else System.out.print(c);
        }
    }

    static void crypto2() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(), key = "ламповаянаука";
        int iKey = 0;
        for (char c : s.toCharArray()) {
            c = (char) (c + key.charAt(iKey) - 'a');
            iKey++;
            iKey = (iKey == key.length()) ? 0 : iKey + 1;


            if (('А' <= c && c < 'Я') || ('а' <= c && c < 'я')) System.out.print(c);
            else System.out.print(c);
        }
    }

    static void problem1151() {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt(), b = in.nextInt(), n = a, ans = 0;
        for (int i = 0; i < b; i++) {
            if (n % b == 0) ans++;
            n += a;
        }
        System.out.println(ans);
    }

    static void problem2616() throws IOException {
        int sum = 0, n, x;
        Scanner in = new Scanner(System.in);
        in.useLocale(new Locale("US"));
        n = in.nextInt();
        String numberString = in.nextLine();
        numberString = numberString.substring(1);
        //TODO s: n-1 numbers, need to find what number is missing. Use bitwise operations
        for (String s : numberString.split(" ")) {
            x = Integer.parseInt(s);
            System.out.print(x + ", ");
        }
    }

    static void problem1() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int a = in.nextInt();
        int b, c;
        b = a / 10;
        c = a % 10;
        out.println(b + " " + c);
        out.flush();
    }

    public static void testMemory() throws IOException {
        Runtime rt = Runtime.getRuntime();
        long prevTotal = 1_0;
        long prevFree = rt.freeMemory();
        System.out.println("Used Memory   :  " + humanReadableByteCount(rt.totalMemory() - rt.freeMemory(), false));
        System.out.println("Free Memory   : " + humanReadableByteCount(rt.freeMemory(), false));
        System.out.println("Total Memory  : " + humanReadableByteCount(rt.totalMemory(), false));
        System.out.println("Max Memory    : " + humanReadableByteCount(rt.maxMemory(), false));

        System.setProperty("Xmx", "10m");
        rt.gc();
        System.out.println("Used Memory   :  " + humanReadableByteCount(rt.totalMemory() - rt.freeMemory(), false));
        System.out.println(System.getProperty("Xmx") + " Max Memory    : " + humanReadableByteCount(rt.maxMemory(), false));
        for (int i = 0; i < 2_000_000; i++) {
            long total = rt.totalMemory();
            long free = rt.freeMemory();
            if (total != prevTotal || free != prevFree) {
                System.out.println(
                        String.format("#%s, Total: %s, Free: %s, Diff: %s",
                                i,
                                total,
                                free,
                                prevFree - free));
                prevTotal = total;
                prevFree = free;
            }
        }
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}