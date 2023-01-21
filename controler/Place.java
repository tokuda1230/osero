package controler;
public class Place {
    private int x;
    private int y;
    private int truex;
    private int truey;

    public void setxy(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getplacex(){
        truex = 2 * x -1;
        return truex;
    }
    public int getplacey(){
        truey = 2 * y - 1;
        return truey;
    }
}

