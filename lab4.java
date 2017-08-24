//Tanya Gupta 
//2016107
import java.util.*;
import java.io.*;


/*Reader class*/
class Reader
{
    BufferedReader br;
    StringTokenizer tk;
    Reader() throws IOException
    {
        br=new BufferedReader(new InputStreamReader(System.in));
        tk=new StringTokenizer("");
    }
    public int nextInt() throws IOException
    {
        while(!tk.hasMoreTokens())
        {
            tk=new StringTokenizer(br.readLine().replaceAll("\r",""));
        }
        return Integer.parseInt(tk.nextToken());
    }
    public Float nextFloat() throws IOException
    {
        while(!tk.hasMoreTokens())
        {
            tk=new StringTokenizer(br.readLine().replaceAll("\r",""));
        }
        return Float.parseFloat(tk.nextToken());
    }
    public String next() throws IOException
    {
        return br.readLine();
    }
}


class MinHeap{
    private Animal[] arr;
    private int maxSize;
    private int size;

    public MinHeap(int maxSize){
        this.maxSize = maxSize;
        arr = new Animal[maxSize];
        size = 0;
    }

    public void insert(Animal newAnimal){
        if(size==maxSize){
            return;
        }
        else{
            arr[size] = newAnimal;

            trickleUp(size++);
        }
    }

    public void trickleUp(int i){
        int parentPos = (i-1)/2;
        Animal last = arr[i];

        while(i>0 && arr[parentPos].getTimeStamp()>last.getTimeStamp()){
            arr[i] = arr[parentPos];
            i = parentPos;
            parentPos = (parentPos-1)/2;

        }
        arr[i] = last;
    }

    public Animal remove(){
        Animal root = arr[0];
        arr[0] = arr[--size];
        trickleDown();
        return root;
    }

    public void trickleDown(){
        int min;
        Animal start = arr[0];
        int i = 0;
        while(i<size/2){
            int left = 2*i+1;
            int right = left+1;
            if(right<size && arr[left].getTimeStamp() > arr[right].getTimeStamp()){
                min = right;
            }
            else{
                min = left;
            }

            if(start.getTimeStamp() <= arr[min].getTimeStamp()){
                break;
            }

            arr[i] = arr[min];
            i = min;
        }
        arr[i] = start;
    }


}

abstract class Animal {
    protected int x;
    protected int y;
    protected int t;
    protected int health;

    public Animal(int x, int y, int t, int health){
        this.x = x;
        this.y = y;
        this.t = t;
        this.health = health;
    }

    public int getTimeStamp(){
        return t;
    }

    public String toString(){
        return "x: "+x+" y: "+y+" timestamp: "+t+" health: "+health;
    }

    
}

class Herbivore extends Animal{
    private static int initial_health;
    private static int grassCap;

    public Herbivore(int x, int y, int t){
        super(x, y, t, initial_health);
    }

    public Herbivore(int x, int y, int t, int h, int grassCap){
        super(x, y, t, h);
        this.initial_health = h;
        this.grassCap = grassCap;
    }

    public String toString(){
        return super.toString()+" grassCap: "+grassCap;
    }

}

class Carnivore extends Animal{
    private static int initial_health;

    public Carnivore(int x, int y, int t){
        super(x, y, t, initial_health);
    }

    public Carnivore(int x, int y, int t, int h){
        super(x, y, t, h);
        this.initial_health = h;
    }

    

}

class Grassland{
    private int x;
    private int y;
    private int r;
    private int grassAvailable;

    public Grassland(int x, int y, int r, int grassAvailable){
        this.x = x;
        this.y = y;
        this.r = r;
        this.grassAvailable = grassAvailable;
    }

    public String toString(){
        return "x: "+x+" y: "+y+" radius: "+r+" grass available: "+grassAvailable;
    }

}


class World{
    public static void main(String[] args) throws IOException{
        Reader rd = new Reader();
        MinHeap pq = new MinHeap(4);

        System.out.println("Enter Total Final Time for Simulation:");
        int tSim = rd.nextInt();

        //int x, y;
        System.out.println("Enter x, y centre, radius and Grass Available for First Grassland:");
        int x = rd.nextInt();
        int y = rd.nextInt();
        int r = rd.nextInt();
        int grassAvailable = rd.nextInt();
        Grassland g1 = new Grassland(x, y, r, grassAvailable);

        System.out.println("Enter x, y centre, radius and Grass Available for Second Grassland:");
        x = rd.nextInt();
        y = rd.nextInt();
        r = rd.nextInt();
        grassAvailable = rd.nextInt();
        Grassland g2 = new Grassland(x, y, r, grassAvailable);

        System.out.println("Enter Health and Grass Capacity for Herbivores:");
        int healthH = rd.nextInt();
        int grassCap = rd.nextInt();
        
        System.out.println("Enter x, y position and timestamp for First Herbivore:");
        x = rd.nextInt();
        y = rd.nextInt();
        int t = rd.nextInt();
        Herbivore h1 = new Herbivore(x, y, t, healthH, grassCap);
        pq.insert(h1);

        System.out.println("Enter x, y position and timestamp for Second Herbivore:");
        x = rd.nextInt();
        y = rd.nextInt();
        t = rd.nextInt();
        Herbivore h2 = new Herbivore(x, y, t);
        pq.insert(h2);


        System.out.println("Enter Health for Carnivores:");
        int healthC = rd.nextInt();
        //Carnivore a_Carnivore = new Carnivore(healthC);

        System.out.println("Enter x, y position and timestamp for First Carnivore:");
        x = rd.nextInt();
        y = rd.nextInt();
        t = rd.nextInt();
        Carnivore c1 = new Carnivore(x, y, t, healthC);
        pq.insert(c1);

        System.out.println("Enter x, y position and timestamp for Second Carnivore:");
        x = rd.nextInt();
        y = rd.nextInt();
        t = rd.nextInt();
        Carnivore c2 = new Carnivore(x, y, t);
        pq.insert(c2);

        //System.out.println("You entered:\nGrasslands:\n"+g1.toString()+"\n"+g2.toString());
        //System.out.println("Herbivores:\n"+h1.toString()+"\n"+h2.toString());
        //System.out.println("Carnivores:\n"+c1.toString()+"\n"+c2.toString());

        
        

        
        







        

        
        
    }
}