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

    public abstract void takeTurn(int numC, int numH, Animal nearestAnimal, MinHeap pq);

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
    public void takeTurn(int numC, int numH, Animal nearestAnimal, MinHeap pq){
        if(numC==0){
            System.out.println("Number of carnivores is 0.");
                if(this.inGrassland()==1){
                    System.out.println("It's in grassland 1");
                    int chance = random.nextInt(100);
                    System.out.println("Random number generated is "+chance);
                    if(chance<50){
                    System.out.println("Since chance was greater than 50%");
                    this.move(g2.getX(), g2.getY(), 5);
                    System.out.println("the animal moved towards grassland 2 and is now at "+this.x+" "+this.y);
                    this.health-=25;
                    System.out.println("Since it chose to not stay, health is "+health);
                    }
                    else{
                        System.out.println("Since chance was less than 50%");
                        if(g1.grassAvailable()>=this.grassCap){
                            System.out.println("grassAvailable in grassland 1 is greater than its capacity and equal to "+g1.grassAvailable());
                            g1.subGrass(this.grassCap);
                            System.out.println("The H ate to its full capacity and grassland 1 now has grass available = "+g1.grassAvailable());
                            this.health+=(this.health/2);
                            System.out.print(" and the H's health has been incremented by 50% and is now "+health+"\n");
         
                        }
                        else{
                            System.out.println("grassAvailable in grassland 1 is less than its capacity and equal to "+g1.grassAvailable());
                            g1.subGrass(g1.grassAvailable());
                            System.out.print(" the H ate all the grass in the grassland and grass available there is now "+g1.grassAvailable());
                            this.health+=(this.health/5);
                            System.out.print(" and the health was incremented by 0.2 and is now "+health+"\n");
                            }
                    }
                    return;
                }

                if(this.inGrassland()==2){
                    System.out.println("It's in grassland 2");
                    int chance = random.nextInt(100);
                    System.out.println("Random number generated is "+chance);
                    if(chance<50){
                        System.out.println("Since chance was greater than 50%");
                    this.move(g1.getX(), g1.getY(), 5);
                    System.out.println("the animal moved towards grassland 1 and is now at "+this.x+" "+this.y);
                    this.health-=25;
                System.out.println("Since it chose to not stay, health is "+health);}

                else{
                        System.out.println("Since chance was less than 50%");
                        if(g2.grassAvailable()>=this.grassCap){
                            System.out.println("grassAvailable in grassland 2 is greater than its capacity and equal to "+g2.grassAvailable());
                            g2.subGrass(this.grassCap);
                            System.out.println("The H ate to its full capacity and grassland 1 now has grass available = "+g2.grassAvailable());
                            this.health+=(this.health/2);
                            System.out.print(" and the H's health has been incremented by 50% and is now "+health+"\n");
         
                        }
                        else{
                            System.out.println("grassAvailable in grassland 2 is less than its capacity and equal to "+g2.grassAvailable());
                            g2.subGrass(g2.grassAvailable());
                            System.out.print(" the H ate all the grass in the grassland and grass available there is now "+g2.grassAvailable());
                            this.health+=(this.health/5);
                            System.out.print(" and the health was incremented by 0.2 and is now "+health+"\n");
                            }
                    }
                    return;
                }
                
                if(this.inGrassland()==0){
                    int chance = random.nextInt(100);
                    System.out.println("It's NOT in grassland");
                    if(chance<50){
                    this.goToNearestGrassland();
                    System.out.println("the animal moved towards nearest grassland and is now at "+this.x+" "+this.y);}
                    return;
                }       


        }

        else{
            System.out.println("Number of carnivores is "+numC);
            if(this.inGrassland()==0){
                System.out.println("It's NOT in grassland");
                int chance = random.nextInt(100);
                System.out.println("Random number generated is "+chance);
                if(chance<95){
                    System.out.println("Since chance was greater than 95%");
                    chance = random.nextInt(100);
                    System.out.println("Random number again generated is "+chance);
                    if(chance<65){
                    System.out.println("Since chance was greater than 65%");  
                    this.goToNearestGrassland();
                    System.out.println("The herbivore moved to nearest grassland and is now at "+x+" "+y);
                    }
                    if(chance>=65 && nearestAnimal.getType()==0){
                    System.out.println("Since chance was less than 65%"); 
                    this.move(nearestAnimal.getX(), nearestAnimal.getY(), -4);
                    System.out.println("The herbivore moved 4 units away from nearestAnimal and is now at "+x+" "+y);
                    }

                }
                return;
            }
            if(this.inGrassland()==1){
                System.out.println("It's in grassland 1");
                if(g1.grassAvailable()>=this.grassCap){
                    System.out.println("grassAvailable in grassland 1 is greater than its capacity and equal to "+g1.grassAvailable());
                    int chance = random.nextInt(100);
                    System.out.println("Random number generated is "+chance);
                    if(chance<90){
                        System.out.println("Since chance was greater than 90%"); 
                        g1.subGrass(this.grassCap);
                        System.out.println("The H ate to its full capacity and grassland 1 now has grass available = "+g1.grassAvailable());
                        this.health+=(this.health/2);
                        System.out.print(" and the H's health has been incremented by 50% and is now "+health+"\n");
                    }
                    else{
                        System.out.println("Since chance was less than 90%");
                        chance = random.nextInt(100);

                        System.out.println("Random number generated is "+chance);
                        if(chance<50 && nearestAnimal.getType()==0){
                            System.out.println("Since chance was greater than 50%"); 
                            this.move(nearestAnimal.getX(), nearestAnimal.getY(), -2);
                            System.out.println("The herbivore moved 2 units away from nearestAnimal and is now at "+x+" "+y);
                            health-=25;
                            System.out.print(" and since it chose to not stay, its health was decremented by 25 and is now "+health+"\n");
                        }
                        else{
                            System.out.println("Since chance was less than 50%");
                            this.move(g2.getX(), g2.getY(), 3);
                            System.out.println("The herbivore moved 3 units to nearest grassland and is now at "+x+" "+y);
                            health-=25;
                            System.out.print(" and since it chose to not stay, its health was decremented by 25 and is now "+health+"\n");
                        }
                    }
                    return;

                }
                if(g1.grassAvailable()<this.grassCap){
                    System.out.println("grassAvailable in grassland 1 is less than its capacity and equal to "+g1.grassAvailable());
                       
                    int chance = random.nextInt(100);
                    System.out.println("Random number generated is "+chance);
                    if(chance>=80){
                        System.out.println("Since chance was less than 80%"); 
                        g1.subGrass(g1.grassAvailable());
                        System.out.print(" the H ate all the grass in the grassland and grass available there is now "+g1.grassAvailable());
                        this.health+=(this.health/5);
                        System.out.print(" and the health was incremented by 0.2 and is now "+health+"\n");

                    }
                    else{
                        System.out.println("Since chance was greater than 80%");
                        chance = random.nextInt(100);
                        System.out.println("Random number generated is "+chance);
                        if(chance<70 && nearestAnimal.getType()==0){
                            System.out.println("Since chance was greater than 70%");
                            this.move(nearestAnimal.getX(), nearestAnimal.getY(), -4);
                            System.out.print(" the H moved away from nearestAnimal and is now at "+x+","+y+"\n");
                        }
                        else{
                            System.out.println("Since chance was less than 70%");
                            this.move(g2.getX(), g2.getY(), 2);
                            System.out.print(" the H moved 2 units towards nearest Grassland and is now at "+x+","+y);
                        }

                        health-=25;
                        System.out.print(" and since it chose to move away, its health is decremented by 25 and is now "+health+"\n");
                    }
                    return;
                }
            }

            if(this.inGrassland()==2){
                System.out.println("It's in grassland 2");
                if(g2.grassAvailable()>=this.grassCap){
                    System.out.println("grassAvailable in grassland 2 is greater than its capacity and equal to "+g2.grassAvailable());
                    int chance = random.nextInt(100);
                    System.out.println("Random number generated is "+chance);
                    if(chance<90){
                        System.out.println("Since chance was greater than 90%"); 
                        g2.subGrass(this.grassCap);
                        System.out.println("The H ate to its full capacity and grassland 2 now has grass available = "+g2.grassAvailable());
                        this.health+=(this.health/2);
                        System.out.print(" and the H's health has been incremented by 50% and is now "+health+"\n");
                    }
                    else{
                        System.out.println("Since chance was less than 90%");
                        chance = random.nextInt(100);

                        System.out.println("Random number generated is "+chance);
                        if(chance<50 && nearestAnimal.getType()==0){
                            System.out.println("Since chance was greater than 50%"); 
                            this.move(nearestAnimal.getX(), nearestAnimal.getY(), -2);
                            System.out.println("The herbivore moved 2 units away from nearestAnimal and is now at "+x+" "+y);
                            health-=25;
                            System.out.print(" and since it chose to not stay, its health was decremented by 25 and is now "+health+"\n");
                        }
                        else{
                            System.out.println("Since chance was less than 50%");
                            this.move(g1.getX(), g1.getY(), 3);
                            System.out.println("The herbivore moved 3 units to nearest grassland and is now at "+x+" "+y);
                            health-=25;
                            System.out.print(" and since it chose to not stay, its health was decremented by 25 and is now "+health+"\n");
                        }
                    }
                    return;

                }

                if(g2.grassAvailable()<this.grassCap){
                    System.out.println("grassAvailable in grassland 2 is less than its capacity and equal to "+g2.grassAvailable());
                       
                    int chance = random.nextInt(100);
                    System.out.println("Random number generated is "+chance);
                    if(chance>=80){
                        System.out.println("Since chance was less than 80%"); 
                        g2.subGrass(g2.grassAvailable());
                        System.out.print(" the H ate all the grass in the grassland and grass available there is now "+g2.grassAvailable());
                        this.health+=(this.health/5);
                        System.out.print(" and the health was incremented by 0.2 and is now "+health+"\n");

                    }
                    else{
                        System.out.println("Since chance was greater than 80%");
                        chance = random.nextInt(100);
                        System.out.println("Random number generated is "+chance);
                        if(chance<70 && nearestAnimal.getType()==0){
                            System.out.println("Since chance was greater than 70%");
                            this.move(nearestAnimal.getX(), nearestAnimal.getY(), -4);
                            System.out.print(" the H moved away from nearestAnimal and is now at "+x+","+y);
                        }
                        else{
                            System.out.println("Since chance was less than 70%");
                            this.move(g1.getX(), g1.getY(), 2);
                            System.out.print(" the H moved 2 units towards nearest Grassland and is now at "+x+","+y);
                        }

                        health-=25;
                        System.out.print(" and since it chose to move away, its health is decremented by 25 and is now "+health+"\n");
                    }
                    return;
                }
            }


        }
    }


    @Override
    public void additional(Animal nearestAnimal){
        System.out.println("The current animal is a H");
        if(this.inGrassland()!=0){
            System.out.println("It's in grassland");
            turnsOutsideGrassland=0;
            System.out.println("So it's turns outside grassland = "+turnsOutsideGrassland);
            return;
        }
        else{
            System.out.println("It's NOT in grassland");
            turnsOutsideGrassland++;
            System.out.println("So it's turns outside grassland = "+turnsOutsideGrassland);
            if(turnsOutsideGrassland>7){
                System.out.println("Since it's turns outside grassland = "+turnsOutsideGrassland);
                this.health-=5;
                System.out.println("It's health was reduced by 5 and is "+health);
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
    public void takeTurn(int numC, int numH, Animal nearestAnimal, MinHeap pq){
        if(numH>0){
            System.out.println("No. of herbivores = "+numH);
            if(Math.sqrt((this.x-nearestAnimal.getX())*(this.x-nearestAnimal.getX())+(this.y-nearestAnimal.getY())*(this.y-nearestAnimal.getY()))<=1 && nearestAnimal.getType()==1){
                System.out.println("The C is near 1 mile radius of H.");
                pq.eat(nearestAnimal);
                System.out.println("It ate ->"+nearestAnimal.toString());
                numH-=1;
                health+=(2*nearestAnimal.getHealth())/3;
                System.out.println("The carnivore's health was hence incremented by 2/3rds and its health now is "+health);
                return;
            }
            if(this.inGrassland()==0){
                System.out.println("It's not within a mile of H and not in Grassland either");
                Random random = new Random();
                if(random.nextInt(100)<92){
                    System.out.println("Since chance was greater than 92%");
                    this.move(nearestAnimal.getX(), nearestAnimal.getY(), 4);
                    System.out.print(" it moved 4 units towards the nearestHerbivore and is now at "+x+","+y+"\n");
                }
                else{
                    System.out.println("Since chance was less than 92%, it stayed");
                    this.health-=60;
                    System.out.println("Health decremented by 60 and is now "+this.health);
                    

                }

                return;
            }
            else{
                System.out.println("It's not within 1 mile of a H but in Grassland");
                Random random = new Random();
                if(random.nextInt(100)<75){
                    System.out.println("Since chance was greater than 75%");
                    this.move(nearestAnimal.getX(), nearestAnimal.getY(), 2);
                    System.out.print(" it moved 2 units towards the nearestHerbivore and is now at "+x+","+y+"\n");

                }
                else{
                    System.out.println("Since chance was less than 75%, it stayed");
                    this.health-=30;
                    System.out.println("Health decremented by 30 and is now "+this.health);
                    

                }



                return;
            }

        }
        else{
            System.out.println("No herbivores.");
            if(this.inGrassland()==0){
                System.out.println("Not in Grassland and staying");
                health-=60;
                System.out.print(" hence, its health was decremented by 60 and is now "+health+"\n");
            }
            else{
                System.out.println("In Grassland and staying");
                health-=30;
                System.out.print(" hence, its health was decremented by 30 and is now "+health+"\n");
            }
        }

    }

    public int nearHerbivore(Animal nearestAnimal){
        if(Math.sqrt((this.x-nearestAnimal.getX())*(this.x-nearestAnimal.getX())+(this.y-nearestAnimal.getY())*(this.y-nearestAnimal.getY()))<=5 && nearestAnimal.getType()==1){

            return 1;
        }
        else
            return 0;

    }

    @Override
    public void additional(Animal nearestAnimal){
        System.out.println("The current animal is C.");
            if(this.nearHerbivore(nearestAnimal)!=0){
                System.out.println("The Carnivore is near Herbivore");
            turnsNotNearHerbivore=0;
                System.out.println("So, its turns not near Herbivores is "+turnsNotNearHerbivore);
        }
        else{
            System.out.println("The Carnivore is NOT near Herbivore");
            turnsNotNearHerbivore++;
            System.out.println("So, its turns not near Herbivores = "+turnsNotNearHerbivore);
            if(turnsNotNearHerbivore>7){
                System.out.println("Since turns not nearHerbivore = "+turnsNotNearHerbivore);
                this.health-=6;
                System.out.println("It's health has been reduced by 6 and is now "+health);
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
        Herbivore h1 = new Herbivore("First Herbivore", x, y, t, healthH, grassCap);
        if(t<=time){
        pq.insert(h1);
        numH++;
        if(t>maxTS){
            maxTS = t;
        }}


        System.out.println("Enter x, y position and timestamp for Second Herbivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        Herbivore h2 = new Herbivore("Second Herbivore",x, y, t);
        if(t<=time){
        pq.insert(h2);
        numH++;
        if(t>maxTS){
            maxTS = t;
        }}


        System.out.println("Enter Health for Carnivores:");
        int healthC = rd.nextInt();
        //Carnivore a_Carnivore = new Carnivore(healthC);

        System.out.println("Enter x, y position and timestamp for First Carnivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        
        Carnivore c1 = new Carnivore("First Carnivore",x, y, t, healthC);
        if(t<=time){
        pq.insert(c1);
        numC++;
        if(t>maxTS){
            maxTS = t;
        }}

        System.out.println("Enter x, y position and timestamp for Second Carnivore:");
        x = rd.nextFloat();
        y = rd.nextFloat();
        t = rd.nextInt();
        Carnivore c2 = new Carnivore("Second Carnivore",x, y, t);
        if(t<=time){
        pq.insert(c2);
        numC++;
        if(t>maxTS){
            maxTS = t;
        }}

        //System.out.println("You entered:\nGrasslands:\n"+g1.toString()+"\n"+g2.toString());
        //System.out.println("Herbivores:\n"+h1.toString()+"\n"+h2.toString());
        //System.out.println("Carnivores:\n"+c1.toString()+"\n"+c2.toString());
        //System.out.println("Printing queue: ");
        //pq.printHeap();

        while(!pq.isEmpty() && counter!=0){
            System.out.println("");
        System.out.println("Status of pq:");
        pq.printHeap();

        Animal curAnimal = pq.remove();
        curAnimal.setGrassland(g1, g2);
        System.out.println("The animal whose turn is");
        System.out.println(curAnimal.toString());

        Animal nearestAnimal = curAnimal.getNearestAnimal(pq);
        System.out.println("The nearestAnimal is");
        System.out.println(nearestAnimal.toString());

        


        curAnimal.takeTurn(numC, numH, nearestAnimal, pq);
        curAnimal.additional(nearestAnimal);

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
            else{
                pq.eat(curAnimal);
                if(curAnimal.getType()==1){
                numH-=1;
                System.out.println("No. of herbivores was decremented and are now "+numH);
            }
            else{
                numC-=1;

                System.out.println("No. of carnivores was decremented and are now "+numC);}

            }
        }
        else{
            System.out.println("It is dead."+curAnimal.getHealth()+" "+curAnimal.getType());
            if(curAnimal.getType()==1){
                numH-=1;
                System.out.println("No. of herbivores was decremented and are now "+numH);
            }
            else{
                numC-=1;

                System.out.println("No. of carnivores was decremented and are now "+numC);}
        }
    }

        
        
    }
}

