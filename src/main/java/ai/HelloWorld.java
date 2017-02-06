package ai;

public class HelloWorld {
    public static void main(String[] args) {
        NetHello netHello = new NetHello();
        
    }
    static class NetHello{
        int l1l=2, l2l=2, l3l=1;
        int[] l2 = new int[l1l];
        int[] l3 = new int[l3l];
        double[][] l_12 = new double[l2l][l1l];
        double[][] l_23 = new double[l3l][l2l];
        NetHello() {
            for (int i = 0; i < l_12.length; i++) {
                for (int j = 0; j < l_12[0].length; j++) {
                    l_12[i][j] = 1.0/ l_12[0].length;
                }
            }
            for (int i = 0; i < l_23.length; i++) {
                for (int j = 0; j < l_23[0].length; j++) {
                    l_23[i][j] = 1.0/ l_23[0].length;
                }
            }
        }
        
        int get(int[] in){
            for (int i = 0; i < ; i++) {
                
            }
        }
    }
}
