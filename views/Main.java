package views;
import java.util.Scanner;

import controler.Osero;
import controler.Place;
import controler.Stone;

public class Main {
    public static void main(String[]arg){
        Osero osero = new Osero();
        Stone stone = new Stone();
        Place place = new Place();
        Scanner sc = new Scanner(System.in);
        int turn = 0;
        osero.getStone(stone);
        while(true){
        osero.setStone(turn,stone,place,sc);//石を置く場所をセットする
        if(stone.GetTrigger() == true){
            stone.SetTrigger(false);
            System.out.println("その場所に石は置けません。別の場所を指定してください。");
            System.out.println();
            continue;
        }
        osero.getStone(stone);//セットした場所に石を置く
        turn++;
        }
    }
}
