import java.util.ArrayList;

class Bankers{
    ArrayList<int[]> allocation;
    int[] available;
    ArrayList<int[]> need;
    public Bankers(ArrayList<int[]> alloc, int[] avail, ArrayList<int[]> n){
        this.allocation = alloc;
        this.available = avail;
        this.need = n;
    }

    //return if vector 1(v1) is less than or equal to v2 return True else False
    public boolean compare(int[] v1, int[]v2){
        if(v1.length != v2.length){
            System.out.print("Vectors are of unequal length");
            return false;
        }
        for(int i = 0; i < v1.length; i++){
            if(v1[0] > v2[0]) return false;
        }
        return true;
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
        // for (int[] e : alloc) {
        //     System.out.println(e[0]);
        // }
        ArrayList<int[]> n = new ArrayList<int[]>();
        int[] n0 = {7,4,3};
        int[] n1 = {0,2,0};
        int[] n2 = {6,0,0};
        int[] n3 = {0,1,1};
        int[] n4 = {4,3,1};
        n.add(n0);
        n.add(n1);
        n.add(n2);
        n.add(n3);
        n.add(n4);
        int[] avail = {2,3,0};
        Bankers b1 = new Bankers(alloc, avail, n);
    }
}