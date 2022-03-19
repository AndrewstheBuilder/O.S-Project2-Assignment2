import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

class Bankers{
    ArrayList<int[]> allocation;
    int[] available;
    ArrayList<int[]> need;
    public Bankers(ArrayList<int[]> alloc, int[] avail, ArrayList<int[]> n){
        this.allocation = alloc;
        this.available = avail;
        this.need = n;
    }

    public void resourceRequest(int requestingProcess,int[] resources ){
        int[] requestedNeed = this.need.get(requestingProcess);

        //First two error checks of the algo
        //Request <= Need and Request <= Available
        if(compare(resources, requestedNeed) && compare(resources, available)){
            ArrayList<int[]> tempAlloc = new ArrayList<>(this.allocation);
            int[] tempAvail = available.clone();
            ArrayList<int[]> tempNeed = new ArrayList<>(need);

            //pretend to allocate requested resources(int[] resources)
            tempAvail = subtractVector(tempAvail,resources);
            tempAlloc.set(requestingProcess, addVector(tempAlloc.get(requestingProcess),resources));
            tempNeed.set(requestingProcess, subtractVector(tempNeed.get(requestingProcess),resources));
            System.out.println();
            // System.out.println("Possible new state");
            // printTempState(tempAlloc, tempAvail, tempNeed);
            if(safetyAlgo(tempAlloc, tempAvail, tempNeed) == true){
                allocation.clear();
                allocation.addAll(tempAlloc);
                available = tempAvail.clone();
                need.clear();
                need.addAll(tempNeed);
                System.out.println("New State");
                printState();
            } else {

            }
        } else {
            System.out.println("Requested resources could not be granted");
            System.out.println("Failed first two checks: 1. Request(process i) <= Need(at i) and 2. Request(process i) <= Available");
        }
    }

    public boolean safetyAlgo(ArrayList<int[]> alloc, int[] work, ArrayList<int[]> need){
        System.out.println("Running safety algorithm");
        int count  = 0;
        int m = 3;
        int size = alloc.size();
        boolean[] finish = {false,false,false,false,false};
        int[] safeSequence = new int[size];
        while(count < size){
            boolean flag = false;
            for(int i = 0; i < size; i++){
                if(finish[i] == false){
                    int k;
                    for(k = 0; k < m; k++){
                        // //DEBUG
                        // System.out.println("At i="+Integer.toString(i));
                        // System.out.println("NEED:"+Arrays.toString(need.get(i)));
                        // System.out.println("WORK:"+Arrays.toString(work));
                        // System.out.println("ALLOC:"+Arrays.toString(alloc.get(i)));
                        if((need.get(i))[k] > work[k]) {
                            break;
                        }
                    }
                    if(k == m){
                        safeSequence[count++]=i;
                        finish[i] = true;
                        flag = true;
                        work = addVector(work, alloc.get(i));
                    }
                }
            }
            if(flag == false){
                break;
            }
        }
        if(count < size){
            System.out.println("The safety sequence could not be found.");
            return false;
        } else {
            System.out.print("Found safe sequence: ");
            for(int i = 0; i < size;i++){
                System.out.print("P"+Integer.toString(safeSequence[i])+",");
            }
            System.out.println();
            return true;
        }
    }

    public int[] subtractVector(int[]v1, int[]v2){
        int[] res = new int[v1.length];
        for (int i = 0; i < v1.length; i++) {
            res[i] = v1[i]-v2[i];
        }
        return res;
    }

    public int[] addVector(int[]v1, int[]v2){
        int[] res = new int[v1.length];
        for (int i = 0; i < v1.length; i++) {
            res[i] = v1[i]+v2[i];
        }
        return res;
    }

    public void printState(){
        System.out.print("Available:");
        System.out.println(Arrays.toString(this.available));
        System.out.println("Allocation:");
        for(int i = 0; i < this.allocation.size(); i++){
            System.out.print("P"+Integer.toString(i)+":");
            System.out.println(Arrays.toString(this.allocation.get(i)));
        }

        System.out.println("Need:");
        for(int j = 0; j < this.need.size(); j++){
            System.out.print("P"+Integer.toString(j)+":");
            System.out.println(Arrays.toString(this.need.get(j)));
        }
    }

    public void printTempState(ArrayList<int[]> tempAlloc,int[] tempAvail,ArrayList<int[]> tempNeed){
        System.out.print("Possible New Available:");
        System.out.println(Arrays.toString(tempAvail));
        System.out.println("Possible New Allocation:");
        for(int i = 0; i < tempAlloc.size(); i++){
            System.out.print("P"+Integer.toString(i)+":");
            System.out.println(Arrays.toString(tempAlloc.get(i)));
        }

        System.out.println("Possible New Need:");
        for(int j = 0; j < tempNeed.size(); j++){
            System.out.print("P"+Integer.toString(j)+":");
            System.out.println(Arrays.toString(tempNeed.get(j)));
        }
    }

    //return if vector 1(v1) is less than or equal to v2 return True else False
    public boolean compare(int[] v1, int[]v2){
        if(v1.length != v2.length){
            System.out.print("Vectors are of unequal length");
            return false;
        }
        for(int i = 0; i < v1.length; i++){
            if(v1[0] > v2[0]){
                return false;
            }
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
        int[] n1 = {1,2,2};
        int[] n2 = {6,0,0};
        int[] n3 = {0,1,1};
        int[] n4 = {4,3,1};
        n.add(n0);
        n.add(n1);
        n.add(n2);
        n.add(n3);
        n.add(n4);
        int[] avail = {3,3,2};
        Bankers b1 = new Bankers(alloc, avail, n);
        System.out.println("Initial State");
        b1.printState();
        System.out.println();

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the process making the request(0-4):");
        int requestingProcess = Integer.parseInt(in.nextLine());
        System.out.println("Enter resources requested(ex. 0 1 2)");
        String strResources = in.nextLine();
        int[] resources = Stream.of(strResources.split(" ")).mapToInt(Integer::parseInt).toArray();
        b1.resourceRequest(requestingProcess, resources);

        // /*DEBUG*/
        // System.out.println("THIS SHOULDN'T HAVE CHANGED");
        // b1.printState();
        // System.out.println();

    }
}