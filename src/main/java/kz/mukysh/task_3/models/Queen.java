package kz.mukysh.task_3.models;
import java.util.*;

public class Queen {
    private final int size = 8; // размер шахматной доски
    private int [] location; // позиция ферзя в каждом ряду шахматной доски
    private int [] colsOccupied; // Столбец, занятый королевой на шахматной доске
    private int [] cross1Occuptied; // Положительная диагональная линия, занятая королевой на доске
    private int [] cross2Occuptied; // Королева занимает противоположный угол на шахматной доске
    private static int count; // Количество решений
    private List< String> list = new ArrayList<>();
    public final static String[][] twoD = new String[92][8];
    private static final int STATUS_OCCUPIED = 1; // занятый статус
    private static final int STATUS_OCCUPY_CANCELED = 0; // незанятый статус

    public Queen() {
        this.location = new int[this.size];
        this.colsOccupied = new int[this.size];
        this.cross1Occuptied = new int[2*this.size];
        this.cross2Occuptied = new int[2*this.size];
        this.place(0);
    }
    public void printLocation() {
        for (int i=0; i <this.size; i++) {
            twoD[count-1][i] =  i + " " + this.location[i];
        }
    }
    private boolean isOccupied(int i, int j) {
        if(this.colsOccupied[j] == 1) {
            return true ;
        }
        if(this.cross1Occuptied[i-j+this.size-1] == 1) {
            return true ;
        }
        if (this.cross2Occuptied[i+j] == 1) {
            return true ;
        }
        return false ;
    }
    private void setStatus(int i, int j, int flag){
        this.colsOccupied [j] = flag;
        this.cross1Occuptied [i-j + this.size-1] = flag;
        this.cross2Occuptied [i + j] = flag;
    }
    public void place(int i) {
        for (int j = 0; j <this.size; j++) {
            if (! this.isOccupied (i, j)) {
                this.location [i] = j;
                this.setStatus (i, j, this.STATUS_OCCUPIED);
                if (i < this.size-1) {
                    this.place(i+1) ;
                }else {
                    this.count++ ;
                    this.printLocation() ;
                }
                this.setStatus(i, j, STATUS_OCCUPY_CANCELED) ;
            }
        }
    }
    public  void start() {
        this.place(0) ;
    }
}