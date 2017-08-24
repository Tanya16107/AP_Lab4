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

        if(arr[parentPos].getTimeStamp()>last.getTimeStamp())
        {
            while(i>0 && arr[parentPos].getTimeStamp()>last.getTimeStamp()){
                arr[i] = arr[parentPos];
                i = parentPos;
                parentPos = (parentPos-1)/2;

            }
            arr[i] = last;
            return;
        }
        if(arr[parentPos].getTimeStamp()==last.getTimeStamp()){

            if(arr[parentPos].getHealth()<last.getHealth()){
                while(i>0 && arr[parentPos].getHealth()<last.getHealth()){
                    arr[i] = arr[parentPos];
                    i = parentPos;
                    parentPos = (parentPos-1)/2;
            }
            arr[i] = last;
            return;
            }

            if(arr[parentPos].getHealth()==last.getHealth()){
                if(arr[parentPos].getType()<last.getType()){
                while(i>0 && arr[parentPos].getType()<last.getType()){
                arr[i] = arr[parentPos];
                i = parentPos;
                parentPos = (parentPos-1)/2;
                }
                arr[i] = last;
                return;
                }
        
                if (arr[parentPos].getType()==last.getType()) {
                    if(arr[parentPos].getDistFromOrigin()>last.getDistFromOrigin()){
                    while(i>0 && arr[parentPos].getDistFromOrigin()>last.getDistFromOrigin()){
                    arr[i] = arr[parentPos];
                    i = parentPos;
                    parentPos = (parentPos-1)/2;
                    }
                    arr[i] = last;
                    return;
                    }
                }

            }

        }

        
    }

    public void remove(){
        Animal root = arr[0];
        arr[0] = arr[--size];
        //System.out.println("Trickling down");
        trickleDown();
        //System.out.println("Trickled down");
    }

    public void trickleDown(){
        //System.out.println("in the method");
        int min;
        Animal start = arr[0];
        int i = 0;

        while(i<size/2){
            //System.out.println("in the while loop");
            //System.out.println("start "+start.toString());
            int left = 2*i+1;
            //System.out.println("left "+arr[left].toString());

            int right = left+1;
            //System.out.println("right "+arr[right].toString());

            min = left;
            //System.out.println("min is left");
            if(right<size){
            if(arr[left].getTimeStamp() > arr[right].getTimeStamp()){
                min = right;

            //System.out.println("min is right");
            }
            if(arr[left].getTimeStamp() == arr[right].getTimeStamp()){
                if(arr[left].getHealth() < arr[right].getHealth()){
                    min = right;

            //System.out.println("min is left");
                }
                if(arr[left].getHealth() == arr[right].getHealth()){
                    if(arr[left].getType() < arr[right].getType()){
                        min = right;

            //System.out.println("min is left");
                    }
                    if(arr[left].getType() == arr[right].getType()){
                        if(arr[left].getDistFromOrigin() > arr[right].getDistFromOrigin()){
                            min = right;

            //System.out.println("min is left");
                        }
                    }

                }

            }
        }     

        //System.out.println("next");

            if(start.getTimeStamp() < arr[min].getTimeStamp()){
                //System.out.println("Breaking because of timestamp");
                break;
            }
            if(start.getTimeStamp()==arr[min].getTimeStamp()){
                if(start.getHealth() > arr[min].getHealth())
                    {//System.out.println("Breaking because of health");
                    break;}
                if(start.getHealth() == arr[min].getHealth()){
                    if(start.getType() > arr[min].getType())
                        {//System.out.println("Breaking because of type");
                        break;}
                    if(start.getType()==arr[min].getType()){
                        if(start.getDistFromOrigin() < arr[min].getDistFromOrigin())
                            {//System.out.println("Breaking because of distance from origin");
                            break;}
                    }
                }
            }

            arr[i] = arr[min];
            i = min;
        }

        arr[i] = start;
    }

        public void printHeap(){
        for(int i=0; i<size; i++){
            System.out.println(arr[i].toString());
        }
    }




}

abstract class Animal {
    protected float x;
    protected float y;
    protected int t;
    protected int health;
    protected int type;

    public Animal(float x, float y, int t, int health){
        this.x = x;
        this.y = y;
        this.t = t;
        this.health = health;
    }

    public int getTimeStamp(){
        return t;
    }

    public int getHealth(){
        return health;
    }

    public int getType(){
        return type;
    }

    public double getDistFromOrigin(){
        return Math.sqrt(x*x+y*y);
    }


    public String toString(){
        return "x: "+x+" y: "+y+" timestamp: "+t+" health: "+health;
    }

    
}

class Herbivore extends Animal{
    private static int initial_health;
    private static int grassCap;


    public Herbivore(float x, float y, int t){
        super(x, y, t, initial_health);
        type = 1;
    }

    public Herbivore(float x, float y, int t, int h, int grassCap){
        super(x, y, t, h);
        this.initial_health = h;
        this.grassCap = grassCap;
        type = 1;
    }

    public String toString(){
        return super.toString()+" grassCap: "+grassCap;
    }

}

class Carnivore extends Animal{
    private static int initial_health;


    public Carnivore(float x, float y, int t){
        super(x, y, t, initial_health);
        type = 0;
    }

    public Carnivore(float x, float y, int t, int h){
        super(x, y, t, h);
        this.initial_health = h;
        type = 0;
    }

    

}

class Grassland{
    private float x;
    private float y;
    private float r;
    private int grassAvailable;

    public Grassland(float x, float y, float r, int grassAvailable){
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

        float x, y;
        /*
        System.out.println("Enter x, y centre, radius and Grass Available for First Grassland:");
        float x = rd.nextFloat();
        float y = rd.nextFloat();
        float r = rd.nextFloat();
        int grassAvailable = rd.nextInt();
        Grassland g1 = new Grassland(x, y, r, grassAvailable);

        System.out.println("Enter x, y centre, radius and Grass Available for Second Grassland:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        r = rd.nextFloat();
        grassAvailable = rd.nextInt();
        Grassland g2 = new Grassland(x, y, r, grassAvailable);
*/
        System.out.println("Enter Health and Grass Capacity for Herbivores:");
        int healthH = rd.nextInt();
        int grassCap = rd.nextInt();
        
        System.out.println("Enter x, y position and timestamp for First Herbivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        int t = rd.nextInt();
        Herbivore h1 = new Herbivore(x, y, t, healthH, grassCap);
        pq.insert(h1);

        System.out.println("Enter x, y position and timestamp for Second Herbivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        Herbivore h2 = new Herbivore(x, y, t);
        pq.insert(h2);


        System.out.println("Enter Health for Carnivores:");
        int healthC = rd.nextInt();
        //Carnivore a_Carnivore = new Carnivore(healthC);

        System.out.println("Enter x, y position and timestamp for First Carnivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        Carnivore c1 = new Carnivore(x, y, t, healthC);
        pq.insert(c1);

        System.out.println("Enter x, y position and timestamp for Second Carnivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        Carnivore c2 = new Carnivore(x, y, t);
        pq.insert(c2);

        //System.out.println("You entered:\nGrasslands:\n"+g1.toString()+"\n"+g2.toString());
        //System.out.println("Herbivores:\n"+h1.toString()+"\n"+h2.toString());
        //System.out.println("Carnivores:\n"+c1.toString()+"\n"+c2.toString());

        

        
        
    }
}