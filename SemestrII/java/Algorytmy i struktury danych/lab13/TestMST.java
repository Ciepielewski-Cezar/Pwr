
public class TestMST{
    public static void main (String[] args){
        MST mst = new MST();
        int graf[][] = new int[][] {{0, 3, 0, 3, 0, 1},
                                    {3, 0, 1, 0, 5, 0},
                                    {0, 1, 0, 3, 0, 6},
                                    {3, 0, 3, 0, 2, 0},
                                    {0, 5, 0, 2, 0, 3},
                                    {1, 0, 6, 0, 3, 0},
                                   };
                                   
        mst.wykonajMST(graf);
    }
}