import java.util.LinkedList;
import java.util.ArrayList;

class Bankers{
    ArrayList<int[]> allocation;
    ArrayList<int[]> available;
    ArrayList<int[]> need;
    ArrayList<int[]> max;
    Bankers(ArrayList<int[]> alloc, ArrayList<int[]> avail, ArrayList<int[]> max){

    }
}

public class Project2{
    public static void main(String[] args){
        ArrayList<int[]> alloc = new ArrayList<int[]>();
        int[] p0 = {0,1,0};
        int[] p1 = {2,0,0};
        int[] p2 = {3,0,2};
        int[] p3 = {2,1,1};
        int[] p4 = {0,0,2};
        alloc.add(p0);
        alloc.add(p1);
        alloc.add(p2);
        alloc.add(p3);
        alloc.add(p4);
        for (int[] e : alloc) {
            System.out.println(e[0]);
        }

    }
}