package controler;
public class Stone {
    private String place[][] = new String[256][256];
    private boolean trigger = false;

    public Stone(){
        for(int i = 1;i<9;i++){
            for(int j=1;j<9;j++){
                this.place[2*i-1][2*j-1]=" ";
            }
        }
        this.place[7][7]="●";
        this.place[7][9]="○";
        this.place[9][7]="○";
        this.place[9][9]="●";
    }
    public void SetStone1(int truex,int truey){
        if(place[truex][truey]==" "|| truex>17 || truey>17){
            place[truex][truey]="●";
        }else{
            this.trigger = true;
        }
    }
    public void SetStone2(int truex,int truey){
        if(place[truex][truey]==" " || truex>17 || truey>17){
            place[truex][truey]="○";
        }else{
            this.trigger = true;
        }
    }
    public void SetStone3(int truex,int truey){
            place[truex][truey]=" ";
    }

    public void SetStoneTurnOver1(int truex,int truey){
        place[truex][truey] = "●";
    }
    public void SetStoneTurnOver2(int truex,int truey){
        place[truex][truey] = "○";
    }

    public String GetStone(int i,int j){
        return place[i][j];
    }
    public void SetTrigger(boolean trigger){
        this.trigger = trigger;
    }
    public boolean GetTrigger(){
        return trigger;
    }
}
