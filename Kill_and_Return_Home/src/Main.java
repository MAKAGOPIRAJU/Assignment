import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> paths = new ArrayList<>(); // This arraylist  will store all the paths

    public static void main(String[] args) {



        //-------------------------INPUT-----------------------------------

        Scanner sc = new Scanner(System.in);

        System.out.print(" find_my_home_castle–soldiers: ");

        int n = sc.nextInt();

        int max_row = -1; // this will help us to maintain the max_row then accordingly we can create the matrix
        int max_col = -1; // this will help us to maintain the max_col then accordingly we can create the matrix

        ArrayList<Co_ordinate> coOrdinates = new ArrayList<>();

        for(int i = 1; i <= n ;i++) {

            System.out.print("Enter coordinates for soldier "+ i + ": ");

            String point = sc.next(); // taking the string input

            /// 1,1 --> means 1st column first row

            // BASING THE QUESTIONS CONSIDERED 1 BASED INDEX

            int y = point.charAt(0)- '0'; // Extracting the y - axis number and convert into int
            int x = point.charAt(2)- '0'; // Extracting the x - axis number and convert into int

            max_row = Math.max(max_row,x); // updating these values
            max_col = Math.max(max_col,y); // updating these values

            Co_ordinate coOrdinate = new Co_ordinate(x,y);

            coOrdinates.add(coOrdinate);
        }

        System.out.print(" Enter the coordinates for your “special” castle: ");

        String s = sc.next();

        int special_castle_y = s.charAt(0) - '0';
        int special_castle_x = s.charAt(2)- '0';
        // Initialize the matrix

        int mat2[][] = new int[max_row + 1][max_col + 1];

        // store the soldier values in the mat

        for(int i = 0;i < coOrdinates.size(); i++) {

            Co_ordinate coOrdinate = coOrdinates.get(i); // get the co-ordinate

            int x = coOrdinate.x - 1;
            int y = coOrdinate.y - 1;

            mat2[x][y] = 1;
        }

        mat2[special_castle_x-1][special_castle_y-1] = 1;


       for(int arr[] : mat2) System.out.println(Arrays.toString(arr));


        // ----------------------- EXAMPLE INPUT ----------------------------------------

        /**
         * @1 __ soldier
         * @0 __ empty cell
         * */
       int mat[][] = {
               {1 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0},
               {1 , 0 , 0 , 1 , 0 , 0 , 0 , 1 , 0},
               {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
               {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
               {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
               {0 , 1 , 0 , 0 , 1 , 0 , 0 , 0 , 0},
               {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
               {0 , 1 , 0 , 1 , 0 , 0 , 0 , 0 , 0},
               {1 , 0 , 0 , 0 , 1 , 0 , 0 , 1 , 0}
       };




      int no_of_soldiers_who_can_reach_home = 0;

      int dp[][][] = new int[9][9][4];

      // We can print paths for all soldiers who are able to return back to home by performing the given operations

        //-------------------- PRINT THE PATHS FOR SPECIAL_CASTLE_SOLDIER ----------

        recur(special_castle_x,special_castle_y - 1,0, mat2 , special_castle_x - 1 , special_castle_y - 1,dp , "**** start at " + special_castle_y + " " + special_castle_x + " ***** \n");


        // --------- WE CAN PRINT THE PATHS FOR ALL THE SOLDIERS WHO CAN CAME BACK TO THE POSITION SAFELY


//      for(int i = 0;i < 9; i++) {
//
//          for(int j = 0; j< 9 ; j++) {
//
//              if(mat[i][j] ==  1) {
//
//                  if(recur(i + 1 , j , 0 , mat , i , j , dp , "**** start at " + (j + 1) + " " + (i + 1) + " ***** \n" )) no_of_soldiers_who_can_reach_home++;
//
//              }
//          }
//      }


//        System.out.println(no_of_soldiers_who_can_reach_home);


        // ---------- PRINT THE PATHS


      for(int i = 0;i < paths.size(); i++) {

          System.out.println(paths.get(i));
          System.out.println("#############################");
      }
    }

    static boolean recur(int i , int j , int dir , int mat[][] , int st_row , int st_col ,int dp[][][] , String psf) {

        /***
         * @Psf---> Path of the soldier
         * @dp[][][]---> If the problem requires finding count paths, we can use the dp array to reduce the time complexity for future calls.
         * @st_row---> The row where the soldier starts.
         * @st_col---> The column where the soldier starts.
         * @dir---> The current direction the soldier is moving in.
         * @dir=0---> The soldier is currently moving in the South direction.
         * @dir=1---> The soldier is currently moving in the North direction.
         * @dir=2---> The soldier is currently moving in the East direction.
         * @dir=3---> The soldier is currently moving in the West direction.
         * @i,j---> The current row and column numbers.
         */

        int n = mat.length;
        int m = mat[0].length;

        if( i == st_row && j == st_col) {

            // Reached the destination

            psf = psf + " **** reached destination at " + (j + 1) + " " + ( i + 1) + " ****** \n";

            paths.add(psf); // add the path into the paths arraylist

            return true; // return as true since i am reached the destination successfully

        }

        if( i < 0 || j < 0 || i >= n || j >= m ) return false; // out of bounds condition


        // If encounters the repetition with the help of dp array we can avoid this call

        // But to print paths we have to comment it because of Since the current cell is already visited either true or false it wont allow us forward to print the paths

        // If it is false no issue if it is true we need to find the entire path

        // In terms of finding the ways it can reduce the time complexity


//        if(dp[i][j][dir] != 0) return (dp[i][j][dir] == 1);


        boolean the_ele_is_one_pick = false;
        boolean the_ele_is_one_not_pick = false;

        boolean not_a_one = false;

        if(mat[i][j] == 1) {

            mat[i][j] = 0; // kill this guy

            if(dir == 0) {

                // moving to south

                // left means going to east(col + 1)

                // update the direction dir = 2

                the_ele_is_one_pick = the_ele_is_one_pick || recur( i , j + 1 , 2,mat , st_row , st_col , dp, psf + " --> kill at ( " +  (j + 1) + " " + (i + 1) + " ) . Turn left \n") ;

                mat[i][j] = 1; // back track

                // Even though the element is one(soldier) i can jump over to it

                the_ele_is_one_not_pick = the_ele_is_one_not_pick || recur( i + 1 , j , dir , mat,st_row,st_col,dp,psf + "  --> jump at ( " + (j  + 1) + " " + ( i + 1) + " ) \n");


            }

            else if( dir == 1) {

                // moving to North

                // left means going to west(col - 1)

                // update the direction dir = 3

                the_ele_is_one_pick = the_ele_is_one_pick || recur( i , j - 1 , 3, mat , st_row , st_col,dp , psf + " --> kill at ( " +  (j + 1) + " " + (i + 1) + " ) . Turn left \n");

                mat[i][j] = 1; // back track

                // Even though the element is one(soldier) i can jump over to it

                the_ele_is_one_not_pick = the_ele_is_one_not_pick || recur( i - 1 , j , dir , mat,st_row,st_col,dp , psf + "  --> jump at ( " + (j  + 1) + " " + ( i + 1) + " ) \n");


            }

            else if( dir == 2) {

                // moving East
                // left means turn to North(row - 1)
                // update the direction dir = 1

                the_ele_is_one_pick = the_ele_is_one_pick || recur(i - 1 , j , 1 , mat , st_row , st_col,dp , psf + " --> kill at ( " +  (j + 1) + " " + (i + 1) + " ) . Turn left \n");

                mat[i][j] = 1; // back track

                // Even though the element is one(soldier) i can jump over to it

                the_ele_is_one_not_pick = the_ele_is_one_not_pick || recur( i , j + 1, dir , mat,st_row,st_col,dp , psf + "  --> jump at ( " + (j  + 1) + " " + ( i + 1) + " ) \n");


            }

            else if( dir == 3) {

                // moving west

                // left means south(row + 1)

                // update the direction dir = 0

                the_ele_is_one_pick = the_ele_is_one_pick || recur( i + 1 , j , 0 , mat , st_row,st_col,dp , psf + " --> kill at ( " +  (j + 1) + " " + (i + 1) + " ) . Turn left \n");

                mat[i][j] = 1; // back track

                // Even though the element is one(soldier) i can jump over to it

                the_ele_is_one_not_pick = the_ele_is_one_not_pick || recur( i , j - 1, dir , mat,st_row,st_col,dp , psf + "  --> jump at ( " + (j  + 1) + " " + ( i + 1) + " ) \n");


            }


        }

        else {

             // If the current cell is zero no soldier is found just moving head in the same direction

            if (dir == 0) not_a_one = not_a_one || recur(i + 1, j, dir, mat, st_row, st_col, dp , psf);

            else if (dir == 1) not_a_one = not_a_one || recur(i - 1, j, dir, mat, st_row, st_col, dp , psf);

            else if (dir == 2) not_a_one = not_a_one || recur(i, j + 1, dir, mat, st_row, st_col, dp , psf);

            else if (dir == 3) not_a_one = not_a_one || recur(i, j - 1, dir, mat, st_row, st_col, dp , psf);


        }

        boolean res =  the_ele_is_one_pick || the_ele_is_one_not_pick || not_a_one ;

        // Update the current result into the dp array

//        dp[i][j][dir] = res ? 1 : -1;

        return  res;
    }
}