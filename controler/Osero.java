package controler;
import java.util.Scanner;

public class Osero{ 
    
    public  void setStone(int turn ,Stone stone,Place place,Scanner sc){
        int truex;
        int truey;
        int x;
        int y;
        System.out.println("1~8の数字を入力");
        if(turn%2 == 0){
            while(true){
                System.out.print("●を置くx座標:");
                x = sc.nextInt();
                if(x<1||x>8){
                    System.out.println("1~8までの数字を入力してください");
                }else{
                    break;
                }
            }
            while(true){
                System.out.print("●を置くy座標:");
                y = sc.nextInt();
                if(y<1||y>8){
                    System.out.println("1~8までの数字を入力してください");
                }else{
                    break;
                }
            }
            place.setxy(x,y);
            System.out.println("●を置く座標を指定：("+x+","+y+")");
            System.out.println();
            truex = place.getplacex();
            truey = place.getplacey();
            stone.SetStone1(truex,truey);
            checkstone(truex,truey,stone,place,turn,x,y);

        }else if(turn%2 == 1){
            while(true){
                System.out.print("○を置くx座標:");
                x = sc.nextInt();
                if(x<1||x>8){
                    System.out.println("1~8までの数字を入力してください");
                }else{
                    break;
                }
            }
            while(true){
                System.out.print("○を置くy座標:");
                y = sc.nextInt();
                if(y<1||y>8){
                    System.out.println("1~8までの数字を入力してください");
                }else{
                    break;
                }
            }
            place.setxy(x,y);
            System.out.println("○を置く座標を指定：("+x+","+y+")");
            System.out.println();
            truex = place.getplacex();
            truey = place.getplacey();
            stone.SetStone2(truex,truey);
            checkstone(truex,truey,stone,place,turn,x,y);
        }
    }

    public void getStone(Stone stone){
        for(int i = 0;i<17;i++){   //iが行、jが列
            for(int j = 0;j<16;j++){
                if(j==0){
                    
                    System.out.print("+ ");
                }else if(i%2==0 && j%2==1 && i!=0 && i!=16){
                    System.out.print("-");
                }
                if(i==0||i==16){
                    System.out.print("+ ");
                }else if(j%2==0 && j!=0 ){
                    if(i%2==0 && j%2==0){
                        System.out.print(" + ");
                    }else{
                        System.out.print(" | ");
                    }
                }else if(i%2==1 && j%2==1){
                    System.out.print(stone.GetStone(j,i));
                }
            }
            if(i==0||i==16){
            System.out.println();
            }else{
            System.out.println(" +");
            }
        }
    }

    public int checkstoneAction(int truex,int truey,Stone stone,Place place,int x,int y,int stepCount){
        int edgenumberi = -1;
        int edgeCounti = 2;
        int edgenumberj = -1;
        int edgeCountj = 2;
        if(truex==1||truey==1||truex==15||truey==15){
        if(truex==1){
            edgenumberi = edgenumberi+1;
            if(truey==1){
                edgenumberj=edgenumberj+1;
            }else if(truey==15){
                edgeCountj = edgeCountj-1;
            }
        }else if(truex==15){
            edgeCounti = edgeCounti-1;
            if(truey==1){
                edgenumberj = edgenumberj+1;
            }else if(truey==15){
                edgeCountj = edgeCountj-1;
            }
        }else if(truey==1){
            edgenumberj = edgenumberj+1;
        }else if(truey==15){
            edgeCountj = edgeCountj-1;
        }
    }
        for(int i = edgenumberi;i<edgeCounti;i++){
            for(int j = edgenumberj;j<edgeCountj;j++){
                if(stone.GetStone(truex+2*i,truey+2*j) != stone.GetStone(truex,truey) && stone.GetStone(truex+2*i,truey+2*j) !=" " ){
                    System.out.print("checkstone動いてるよ");
                    System.out.println("truex:"+truex);
                    System.out.println("truey:"+truey);
                    checkTurnOver(truex,truey,truex+2*i,truey+2*j,stone,place,x,y);
                    stepCount ++;
                    continue;
                }
            }
        }
        return stepCount;
    }

    public  void checkstone(int truex,int truey,Stone stone,Place place,int turn,int x,int y){
        //端っこは処理しないことに関するコードを書く
        int stepCount = 0;
        stepCount = checkstoneAction(truex,truey,stone,place,x,y,stepCount);
        if(stepCount == 0 ){
            place.setxy(x,y);
            truex = place.getplacex();
            truey = place.getplacey();
            stone.SetStone3(truex,truey);
            stone.SetTrigger(true);
        }
    }

    public  void checkTurnOver(int truex,int truey,int truex2,int truey2,Stone stone,Place place,int x,int y){
        int supeax;
        int supeay;
        int startx = truex;
        int starty = truey;
        int stepCountx;
        int stepCounty;
        
        while(true){
        stepCountx = truex2 - truex;
        stepCounty= truey2-truey;
        if(stepCountx >0){
            stepCountx +=2;
        }else if(stepCountx <0){
            stepCountx -=2;
        }
        if(stepCounty >0){
            stepCounty +=2;
        }else if(stepCounty <0){
            stepCounty -=2;
        }
        if(stone.GetStone(truex2,truey2) != stone.GetStone(truex+stepCountx,truey+stepCounty) &&stone.GetStone(truex+stepCountx,truey+stepCounty) !=" "){
            //違う種類の石で空白でない時→ひっくり返す
            turnOver(startx,starty,truex2,truey2,stone);
            break;
            
        }else if(stone.GetStone(truex2+stepCountx,truey2+stepCounty) == " "){//空白の時→終わり
            System.out.print("空白だからbreakするよ");
            break;

        }else if(stone.GetStone(truex2,truey2) == stone.GetStone(truex+stepCountx,truey+stepCounty)){//同じ種類の石の時→もう一度調べる
            supeax = truex2;
            supeay = truey2;
            truex2 = truex + stepCountx;
            truey2 = truey + stepCounty;
            truex = supeax;
            truey = supeay;
            //System.out.print("同じ種類の石だったよ");
        }
        }  
    }

    public  void turnOver(int startx,int starty,int truex2,int truey2,Stone stone){
        if(truex2-startx == 0){//xの差が０
            if(truey2-starty > 0){//yの差が正
                for(int i = 0;i<=truey2-starty;i = i+2){
                    turnOver3(startx,starty,truex2,truey2,stone,i);
                }
            }else if(truey2-starty<0){//yの差が負
                for(int i = 0;i>=truey2-starty;i=i-2){
                    turnOver3(startx,starty,truex2,truey2,stone,i);
                }
            }
        }
        if(truex2-startx < 0){//xの差が負
            if(truey2-starty>0){//yの差が正  OK
                for(int i = 0;i>=truex2-startx;i=i-2){
                    turnOver2(startx,starty,truex2,truey2,stone,i);
                }
            }else if(truey2-starty == 0){//yの差が０
                for(int i = 0;i>=truex2-startx;i=i-2){
                    turnOver4(startx,starty,truex2,truey2,stone,i);
                }
            }else if(truey2-starty < 0){//yの差が負  OK
                for(int i = 0;i>=truex2-startx;i=i-2){
                    turnOver1(startx,starty,truex2,truey2,stone,i);
                }
            }
        }
        if(truex2-startx > 0){//xの差が正
            if(truey2-starty >0){//yの差が正  OK
                for(int i = 0;i<=truex2-startx;i=i+2){
                        turnOver1(startx,starty,truex2,truey2,stone,i);
                }
            }else if(truey2-starty == 0){//yの差が０
                for(int i = 0;i<=truex2-startx;i=i+2){
                    turnOver4(startx,starty,truex2,truey2,stone,i);
                }
            }else if(truey2-starty <0){//yの差が負  OK
                for(int i = 0;i<=truex2-startx;i=i+2){
                        turnOver2(startx,starty,truex2,truey2,stone,i);
                }
            }
        }
        //System.out.print("turnOver終わり");
    }

    public  void turnOver1(int startx,int starty,int truex2,int truey2,Stone stone,int i){
        if(stone.GetStone(startx,starty)=="●"){
            stone.SetStoneTurnOver1(startx+i,starty+i);
        }else if(stone.GetStone(startx,starty)=="○"){
            stone.SetStoneTurnOver2(startx+i,starty+i);
        }
    }
    public static void turnOver2(int startx,int starty,int truex2,int truey2,Stone stone,int i){
        if(stone.GetStone(startx,starty)=="●"){
            stone.SetStoneTurnOver1(startx+i,starty-i);
        }else if(stone.GetStone(startx,starty)=="○"){
            stone.SetStoneTurnOver2(startx+i,starty-i);
        }
    }
    public static void turnOver3(int startx,int starty,int truex2,int truey2,Stone stone,int i){
        if(stone.GetStone(startx,starty)=="●"){
            stone.SetStoneTurnOver1(startx,starty+i);
        }else if(stone.GetStone(startx,starty)=="○"){
            stone.SetStoneTurnOver2(startx,starty+i);
        }
    }
    public static void turnOver4(int startx,int starty,int truex2,int truey2,Stone stone,int i){
        if(stone.GetStone(startx,starty)=="●"){
            stone.SetStoneTurnOver1(startx+i,starty);
        }else if(stone.GetStone(startx,starty)=="○"){
            stone.SetStoneTurnOver2(startx+i,starty);
        }
    }
}
    
