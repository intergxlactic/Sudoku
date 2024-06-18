/*Судоку — сложная логическая задача для человека, но с ней может отлично справиться компьютер.
Напишите консольную программу, которая получала бы на вход матрицу чисел решетки судоку.
Неизвестные числа замените нулем. На выход программа должна выдавать решенную головоломку.
Построить алгоритм будет проще, если помнить, что цифры от 1 до 9 должны встречаться
в каждой строке, в каждом столбце и в каждом малом квадрате 3×3 только один раз.*/

public class Main {
    private static final int GRID_SIZE = 9;
    public static void main(String[] args) {

        int[][] board = { // строим исходную доску
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if(solveBoard(board)){
            System.out.println("Судоку решена");
            printBoard(board);
        }
        else {
            System.out.println("Нерешаемая доска судоку");
        }


    }

    private static void printBoard(int[][] board) { // выводим доску
        for(int row = 0; row < GRID_SIZE;row++){
            for(int column = 0; column < GRID_SIZE;column++){
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    // проверка числа в строке
    private static boolean isNumberInRow(int[][] board,int number,int row){
        for(int i = 0; i < GRID_SIZE;i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    // проверка числа в колонке
    private static boolean isNumberInColumn(int[][] board,int number,int column){
        for(int i = 0; i < GRID_SIZE;i++){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    //проверка числа в коробке из 9 чисел
    private static boolean isNumberInBox(int[][] board,int number,int row,int column){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            for(int j = localBoxColumn; i < localBoxColumn + 3; i++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    // проверяем, являеются ли числа размещенными на доске
    private static boolean isValidPlacement(int board[][],int number,int row,int column){
        return !isNumberInRow(board,number,row) &&
                !isNumberInColumn(board,number,column) &&
                !isNumberInRow(board,number,row);
    }

    //тут выполняется сама проверка доски
    public static boolean solveBoard(int board[][]){
        for(int row = 0;row < GRID_SIZE;row++){
            for(int column = 0; column < GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int numberToTry = 1;numberToTry <= GRID_SIZE; numberToTry++){
                        if(isValidPlacement(board,numberToTry,row,column)){
                            board[row][column] = numberToTry;

                            if(solveBoard(board)){
                                return true;
                            }
                            else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}