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

    public boolean isEmpty(){
        return size==0;
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

    public void eat(Animal h){
        for (int i=0; i<size; i++) {
            if(h.getName()==arr[i].getName()){
                Animal temp = arr[0];
                arr[0] = h;
                arr[i] = temp;
                this.remove();
                return;
            }
        }

    }


    public Animal remove(){
        Animal root = arr[0];
        arr[0] = arr[--size];
        //System.out.println("Trickling down");
        trickleDown();
        return root;
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

    public Animal iterateToGetNearestAnimal(int type, float x, float y){
        int min = Integer.MAX_VALUE;
        Animal out = arr[0];
        for (int i=0; i<size ; i++ ) {
            int dist = (int)Math.round(Math.sqrt((arr[i].getX()-x)*(arr[i].getX()-x) + (arr[i].getY()-y)*(arr[i].getY()-y)));
            if (dist<min && type!=arr[i].getType()){
                min = dist;
                out = arr[i];
            }

            
        }
        return out;
    }




}

abstract class Animal {
    protected String name;
    protected float x;
    protected float y;
    protected int t;
    protected int health;
    protected int type;
    protected static Grassland g1;
    protected static Grassland g2;
    Random random = new Random();

    public Animal(String name, float x, float y, int t, int health){
        this.name = name;
        this.x = x;
        this.y = y;
        this.t = t;
        this.health = health;
    }

    public void updateTS(int t){
        this.t = t;
    }

    public String getName(){
        return name;
    }



    public void setGrassland(Grassland g1, Grassland g2){
        this.g1 = g1;
        this.g2 = g2;
    }

    public void move(float fx, float fy, float dist){
        float s = (float)Math.sqrt((fx-x)*(fx-x)+(fy-y)*(fy-y));
        float costheta = (fx-x)/s;
        float sintheta = (fy-y)/s;
        x+=dist*costheta;
        y+=dist*sintheta;
        x = Math.round(x);
        y = Math.round(y);

    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public Animal getNearestAnimal(MinHeap pq){
        return pq.iterateToGetNearestAnimal(this.type, this.x, this.y);

    }

    public abstract void takeTurn(int numH, int numC, Animal nearestAnimal, MinHeap pq);

    public abstract void additional(Animal nearestAnimal);

    public int getTimeStamp(){
        return t;
    }

    public int inGrassland(){
        boolean _x = Math.sqrt((this.x-g1.getX())*(this.x-g1.getX())+(this.y-g1.getY())*(this.y-g1.getY()))<=g1.getR();
        if(_x){
            return 1;
        }
        _x = Math.sqrt((this.x-g2.getX())*(this.x-g2.getX())+(this.y-g2.getY())*(this.y-g2.getY()))<=g2.getR();
        if(_x){
            return 2;
        }
        return 0;

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
    private int turnsOutsideGrassland = 0;


    public Herbivore(String name, float x, float y, int t){
        super(name, x, y, t, initial_health);
        type = 1;
    }

    public Herbivore(String name, float x, float y, int t, int h, int grassCap){
        super(name, x, y, t, h);
        this.initial_health = h;
        this.grassCap = grassCap;
        type = 1;
    }

    public void goToNearestGrassland(){

        float dist1 = (float)Math.sqrt((((this.x)-g1.getX()*(this.x)-g1.getX())+((this.x)-g1.getY()*(this.x)-g1.getY())));
        float dist2 = (float)Math.sqrt((((this.x)-g2.getX()*(this.x)-g2.getX())+((this.x)-g2.getY()*(this.x)-g2.getY())));
        float min = dist1;
        if(dist2<dist1){
            this.move(g2.getX(), g2.getY(), 5);
            return;
        }
        this.move(g1.getX(), g1.getY(), 5);

    }

    @Override
    public void takeTurn(int numH, int numC, Animal nearestAnimal, MinHeap pq){
        if(numC==0){
                if(this.inGrassland()==1){
                    int chance = random.nextInt();
                    if(chance<50){
                    this.move(g2.getX(), g2.getY(), 5);
                    this.health-=25;}
                    return;
                }

                if(this.inGrassland()==2){
                    int chance = random.nextInt();
                    if(chance<50){
                    this.move(g1.getX(), g1.getY(), 5);
                    this.health-=25;}
                    return;
                }
                
                if(this.inGrassland()==0){
                    int chance = random.nextInt();
                    if(chance<50){
                    this.goToNearestGrassland();}
                    return;
                }       


        }

        else{
            if(this.inGrassland()==0){
                int chance = random.nextInt(100);
                if(chance<95){
                    chance = random.nextInt(100);
                    if(chance<65){
                    this.goToNearestGrassland();
                    }
                    if(chance>=65){
                    this.move(nearestAnimal.getX(), nearestAnimal.getY(), -4);
                    }

                }
            }
            if(this.inGrassland()==1){
                if(g1.grassAvailable()>=this.grassCap){
                    int chance = random.nextInt(100);
                    if(chance<90){
                        g1.subGrass(this.grassCap);
                        this.health+=(this.health/2);}
                    else{
                        chance = random.nextInt(100);
                        if(chance<50){
                            this.move(nearestAnimal.getX(), nearestAnimal.getY(), -2);
                            health-=25;
                        }
                        else{
                            this.move(g2.getX(), g2.getY(), 3);
                            health-=25;}
                    }
                    return;

                }
                if(g1.grassAvailable()<this.grassCap){
                    int chance = random.nextInt(100);
                    if(chance>=80){
                        g1.subGrass(g1.grassAvailable());
                        this.health+=(this.health/5);

                    }
                    else{
                        chance = random.nextInt(100);
                        if(chance<70)
                            this.move(nearestAnimal.getX(), nearestAnimal.getY(), -4);
                        else
                            this.move(g2.getX(), g2.getY(), 2);

                        health-=25;
                    }
                    return;
                }
            }

            if(this.inGrassland()==2){
                if(g2.grassAvailable()>=this.grassCap){
                    int chance = random.nextInt(100);
                    if(chance<90){
                        g2.subGrass(this.grassCap);
                        this.health+=(this.health/2);}
                    else{
                        chance = random.nextInt(100);
                        if(chance<50){
                            this.move(nearestAnimal.getX(), nearestAnimal.getY(), -2);
                        }
                        else
                            this.move(g1.getX(), g1.getY(), 3);
                        health-=25;
                    }
                    return;

                }
                if(g2.grassAvailable()<this.grassCap){
                    int chance = random.nextInt(100);
                    if(chance>=80){
                        g2.subGrass(g2.grassAvailable());
                        this.health+=(this.health/5);
                    }
                    else{
                        chance = random.nextInt(100);
                        if(chance<70)
                            this.move(nearestAnimal.getX(), nearestAnimal.getY(), -4);
                        else
                            this.move(g1.getX(), g1.getY(), 2);
                        health-=25;
                    }
                    return;


                }

            }
        }
    }


    @Override
    public void additional(Animal nearestAnimal){
                if(this.inGrassland()!=0){
            turnsOutsideGrassland=0;
        }
        else{
            turnsOutsideGrassland++;
            if(turnsOutsideGrassland>7){
                this.health-=5;
            }
        }


    }


    public String toString(){
        return super.toString()+" grassCap: "+grassCap;
    }
}




class Carnivore extends Animal{
    private static int initial_health;
    private int turnsNotNearHerbivore = 0 ;



    public Carnivore(String name, float x, float y, int t){
        super(name, x, y, t, initial_health);
        type = 0;
    }

    public Carnivore(String name, float x, float y, int t, int h){
        super(name, x, y, t, h);
        this.initial_health = h;
        type = 0;
    }

    @Override
    public void takeTurn(int numH, int numC, Animal nearestAnimal, MinHeap pq){
        if(numH>0){
            if(Math.sqrt((this.x-nearestAnimal.getX())*(this.x-nearestAnimal.getX())+(this.y-nearestAnimal.getY())*(this.y-nearestAnimal.getY()))<=1){
                pq.eat(nearestAnimal);
                health+=(2*nearestAnimal.getHealth())/3;
                return;
            }
            if(this.inGrassland()==0){
                Random random = new Random();
                if(random.nextInt(100)<92){
                    this.move(nearestAnimal.getX(), nearestAnimal.getY(), 4);
                }

                return;
            }
            else{
                Random random = new Random();
                if(random.nextInt(100)<75){
                    this.move(nearestAnimal.getX(), nearestAnimal.getY(), 2);
                }

                return;
            }

        }
        else{
            if(this.inGrassland()==0){
                health-=60;
            }
            else{
                health-=30;
            }
        }

    }

    public int nearHerbivore(Animal nearestAnimal){
        if(Math.sqrt((this.x-nearestAnimal.getX())*(this.x-nearestAnimal.getX())+(this.y-nearestAnimal.getY())*(this.y-nearestAnimal.getY()))<=5){
            return 1;
        }
        else
            return 0;

    }

    @Override
    public void additional(Animal nearestAnimal){
                if(this.nearHerbivore(nearestAnimal)!=0){
            turnsNotNearHerbivore=0;
        }
        else{
            turnsNotNearHerbivore++;
            if(turnsNotNearHerbivore>7){
                this.health-=6;
            }
        }
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

    public float getX(){
        return x;
    }


    public float getY(){
        return y;
    }

    public float getR(){
        return r;
    }

    public int grassAvailable(){
        return grassAvailable;
    }

    public void subGrass(int eaten){
        grassAvailable-=eaten;
    }

    public String toString(){
        return "x: "+x+" y: "+y+" radius: "+r+" grass available: "+grassAvailable;
    }

}


class World{
    public static void main(String[] args) throws IOException{
        Reader rd = new Reader();
        MinHeap pq = new MinHeap(4);
        int numH = 0;
        int numC = 0;

        System.out.println("Enter Total Final Time for Simulation:");
        int time = rd.nextInt();
        int counter = time;
        int maxTS = 0;

       
        
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

        System.out.println("Enter Health and Grass Capacity for Herbivores:");
        int healthH = rd.nextInt();
        int grassCap = rd.nextInt();
        
        System.out.println("Enter x, y position and timestamp for First Herbivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        int t = rd.nextInt();
        if(t>maxTS){
            maxTS = t;
        }
        Herbivore h1 = new Herbivore("First Herbivore", x, y, t, healthH, grassCap);
        h1.setGrassland(g1, g2);
        pq.insert(h1);
        numH++;

        System.out.println("Enter x, y position and timestamp for Second Herbivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        if(t>maxTS){
            maxTS = t;
        }
        Herbivore h2 = new Herbivore("Second Herbivore",x, y, t);
        pq.insert(h2);
        numH++;


        System.out.println("Enter Health for Carnivores:");
        int healthC = rd.nextInt();
        //Carnivore a_Carnivore = new Carnivore(healthC);

        System.out.println("Enter x, y position and timestamp for First Carnivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        if(t>maxTS){
            maxTS = t;
        }
        Carnivore c1 = new Carnivore("First Carnivore",x, y, t, healthC);
        pq.insert(c1);
        numC++;

        System.out.println("Enter x, y position and timestamp for Second Carnivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        if(t>maxTS){
            maxTS = t;
        }
        Carnivore c2 = new Carnivore("Second Carnivore",x, y, t);
        pq.insert(c2);
        numC++;

        //System.out.println("You entered:\nGrasslands:\n"+g1.toString()+"\n"+g2.toString());
        //System.out.println("Herbivores:\n"+h1.toString()+"\n"+h2.toString());
        //System.out.println("Carnivores:\n"+c1.toString()+"\n"+c2.toString());
        System.out.println("Printing queue: ");
        pq.printHeap();

        while(!pq.isEmpty() && counter!=0){
        Animal curAnimal = pq.remove();
        //System.out.println("Printing queue: ");
        //pq.printHeap();
        Animal nearestAnimal = curAnimal.getNearestAnimal(pq);

        curAnimal.additional(nearestAnimal);


        curAnimal.takeTurn(numC, numH, nearestAnimal, pq);

        counter--;
        System.out.println("It's "+curAnimal.getName());
        if(curAnimal.getHealth()>=0){
            System.out.println("It's health after taking turn is "+curAnimal.getHealth()+".");
            Random random = new Random();
            int ranTS = random.nextInt(time - maxTS) + maxTS;
            if(ranTS!=(time-1)){
                curAnimal.updateTS(ranTS);
                pq.insert(curAnimal);
                if(ranTS>maxTS){
                    maxTS = ranTS;
                }
            }
        }
        else{
            System.out.println("It is dead."+curAnimal.getHealth());
        }
    }

        
        
    }
}

