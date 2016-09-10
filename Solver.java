/**
 * Created by zhiqing on 16-9-10.
 */

import java.io.File;
import java.util.Scanner;

public class Solver{
    //用于保存数独数据的数组
    static char[][] arr = new char[9][9];

    //检查一个数在某一列是否存在
    public static boolean checkExistInVertical(int index, char c){
        for(int i = 0; i < 9; i++){
            if(arr[i][index] == c)
                return true;
        }
        return false;
    }

    //检查某数在某行是否存在
    public static boolean checkExistInTrans(int index, char c){
        for(int i = 0; i < 9; i++){
            if(arr[index][i] == c)
                return true;
        }
        return false;
    }

    //检查一个数在相应位置内的宫格中是否存在
    public static boolean checkExistInBox(int indexX, int indexY, char c){
        indexX = indexX / 3;
        indexY = indexY / 3;
        for(int i = indexX * 3; i < (indexX + 1) * 3; i++){
            for (int j = indexY * 3; j < (indexY + 1) * 3; j++){
                if(arr[i][j] == c)
                    return true;
            }
        }
        return false;
    }

    //检查一个boolean型数组中false的个数
    public static int countFalseOfBoolArr(boolean[][] bool){
        int sum = 0;
        for (boolean[] ba : bool) {
            for (boolean b: ba) {
                if(!b)
                    sum++;
            }
        }
        return sum;
    }

    //程序实现的第二部分
    public static boolean check(int indexX, int indexY, char c){
        boolean flag = true;
        int indexXX = indexX / 3;
        int indexYY = indexY / 3;
        int indexXShit = indexX % 3;
        int indexYShit = indexY % 3;
        if(checkExistInBox(indexX, indexY, c))
            return false;
        if(checkExistInVertical(indexY, c))
            return false;
        if(checkExistInTrans(indexX, c))
            return false;

        boolean[][] bool = new boolean[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(arr[(indexXX * 3) + i][(indexYY * 3) + j] == '0'){
                    bool[i][j] = false;
                } else {
                    bool[i][j] = true;
                }
            }
        }
        if(indexXShit == 0){
            if(checkExistInTrans(indexX + 1, c)){
                for(int i = 0; i < 3; i++){
                    bool[1][i] = true;
                }
            }if (checkExistInTrans(indexX + 2, c)){
                for(int i = 0; i < 3; i++){
                    bool[2][i] = true;
                }
            }
        } else if (indexXShit == 1) {
            if(checkExistInTrans(indexX - 1, c)){
                for(int i = 0; i < 3; i++){
                    bool[0][i] = true;
                }
            }if (checkExistInTrans(indexX + 1, c)){
                for(int i = 0; i < 3; i++){
                    bool[2][i] = true;
                }
            }
        } else {
            if(checkExistInTrans(indexX - 2, c)){
                for(int i = 0; i < 3; i++){
                    bool[0][i] = true;
                }
            }if (checkExistInTrans(indexX - 1, c)){
                for(int i = 0; i < 3; i++){
                    bool[1][i] = true;
                }
            }
        }

        if(indexYShit == 0){
            if(checkExistInVertical(indexY + 1, c))
                for(int i = 0; i < 3; i++){
                    bool[i][1] = true;
                }
            if(checkExistInVertical(indexY + 2, c))
                for(int i = 0; i < 3; i++){
                    bool[i][2] = true;
                }
        } else if (indexYShit == 1) {
            if(checkExistInVertical(indexY - 1, c))
                for(int i = 0; i < 3; i++){
                    bool[i][0] = true;
                }
            if(checkExistInVertical(indexY + 1, c))
                for(int i = 0; i < 3; i++){
                    bool[i][2] = true;
                }
        } else {
            if(checkExistInVertical(indexY - 2, c))
                for(int i = 0; i < 3; i++){
                    bool[i][0] = true;
                }
            if(checkExistInVertical(indexY - 1, c))
                for(int i = 0; i < 3; i++){
                    bool[i][1] = true;
                }
        }
        if(countFalseOfBoolArr(bool) == 1){
            System.out.println("Checking[" + indexX + "][" + indexY +"][" + c + "]  true...    from check");
            return true;
        }
        return false;
    }

    //将字符转换为相应的数字
    public static int getNum(char c){
        return (int)c - 48;
    }

    //程序实现的第一部分
    public static boolean shit(int indexX, int indexY){
        int indexXX = indexX / 3;
        int indexYY = indexY / 3;
        int count = 0;
        char result = 0;
        boolean[] av = new boolean[10];
        av[0] = false;
        for(int i = 1; i < 10; i++){
            av[i] = true;
        }

        try{
            for (int i = 1; i < 10; i++) {
                av[getNum(arr[indexX][i-1])] = false;
            }
            for (int i = 1; i < 10; i++) {
                av[getNum(arr[i-1][indexY])] = false;
            }
            for(int i = indexXX * 3; i < (indexXX + 1) * 3; i++){
                for(int j = indexYY * 3; j < (indexYY + 1) * 3; j++){
                    av[getNum(arr[i][j])] = false;
                }
            }
        } catch (Exception e){

        }

        for(int i = 1; i < 10; i++) {
            if(av[i] == true){
                count++;
                result = (char)(i + 48);
            }
        }

        if(count == 1){
            arr[indexX][indexY] = result;
            System.out.println("Checking[" + indexX + "][" + indexY +"][" + result + "]  true...    from shit");
            return true;
        }
        return false;
    }

    //程序主要逻辑
    public static boolean fuck(){
        boolean flag = false;
        int count = 0;
        while(true){
            flag = false;
            for (int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(arr[i][j] != '0')
                        continue;
                    if(shit(i, j)){
                        flag = true;
                        count++;
                        System.out.println("--------------------------------");
                    } else {
                        for(char c = '1'; c <= '9'; c++){
                            if(check(i, j, c)){
                                arr[i][j] = c;
                                flag = true;
                                count++;
                                System.out.println("--------------------------------");
                                break;
                            }
                        }
                    }
                }
            }
            if(flag == false)
                break;
        }
        System.out.println("共填入了" + count + "项");
        return true;
    }

    //从文件中读入数据
    public static void readFile(String file){
        Scanner in;
        try{
            in = new Scanner(new File(file));
            scan(in);
        } catch (Exception e){

        }
    }

    //从Scanner中读入数据
    public static void scan(Scanner in){
        for(int i = 0; i < 9; i++){
            String inString = in.nextLine();
            if(inString.length() != 9){
                System.out.println("输入错误");
                return;
            }
            for(int j = 0; j < 9; j++){
                char c = inString.charAt(j);
                if(c < '0' || c > '9'){
                    System.out.println("输入错误");
                    return;
                }
                arr[i][j] = c;
            }
        }
    }

    //最终结果输出
    public static void print(){
        System.out.println("最终结果：");
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(arr[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static void main(final String[] args) {

        Scanner in = new Scanner(System.in);
        scan(in);

        fuck();

        print();

    }
}
