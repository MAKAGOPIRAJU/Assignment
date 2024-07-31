import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FindThePaths {

    public static List<co_ordinates> coOrdinatesList;

    public static String path2[][] = new String[10][10];

    FindThePaths( List<co_ordinates> coOrdinatesList) {

        this.coOrdinatesList = coOrdinatesList;
        fill();
    }

    public static void fill() {

        boolean vis[][] = new boolean[10][10];

        String path[][] = new String[10][10];

        vis[1][1] = true;
        vis[1][2] = true;
        vis[3][3] = true;

        vis[2][4] = true;
        vis[3][2] = true;

        vis[4][2] = true;
        vis[3][4] = true;

        path[1][1] = "p1";

        path[2][2] = "p1";
        path[2][3] = "p1";

        path[2][4] = "p2";
        path[3][2] = "p2";

        path[4][2] = "p3";
        path[3][4] = "p3";

        paths(vis , path , 1, 1 , 2,2, 1 , 1 , 1 , "p1");

    }

    static boolean paths(boolean vis[][] , String path[][] , int st_x , int st_y , int en_x , int en_y , int idx ,int i , int j , String str) {

//        System.out.println(idx + "  " + st_x + " " + st_y + " " + i + " " + j);

        if( i < 0 || j < 0 || i >= 5 || j >= 5) return false;

        if( idx == coOrdinatesList.size() - 1) {

            // if reach the destination

            if(i == en_x && j == en_y) {

                // At the time it reaches the end,n in the path  array the way of reaching the all the destinations will
                // be stored in the way of p1 for flight1

                // p2 for flight 2

                for(int k = 0;k < 10; k++) {

                    for(int l = 0;l < 10; l++) {

                        path2[k][l] = path[k][l];
                    }
                }
                return true;
            }
        }


        if(i == en_x && j == en_y) {

            // if not travel to the all the places

            return paths(vis , path , coOrdinatesList.get(idx).st_x , coOrdinatesList.get(idx).st_y , coOrdinatesList.get(idx).en_x , coOrdinatesList.get(idx).en_y,
                    idx + 1 , coOrdinatesList.get(idx).st_x , coOrdinatesList.get(idx).st_y , path[coOrdinatesList.get(idx).st_x][coOrdinatesList.get(idx).st_y]);
        }



        if( i != st_x && j != st_y && vis[i][j]) return  false;

        path[i][j] = str;

        vis[i][j] = true;

        // 8 calls

        boolean r_d_d = paths(vis , path ,st_x ,st_y , en_x , en_y , idx , i +1 , j + 1 , str); // right down diagonal

        boolean u = paths(vis , path , st_x , st_y, en_x , en_y , idx , i - 1 , j , str); // up

        boolean r = paths(vis , path , st_x , st_y , en_x , en_y , idx , i , j + 1 , str); // right

        boolean d = paths( vis , path , st_x , st_y , en_x , en_y , idx , i + 1 , j , str); // down

        boolean l_d_d = paths(vis , path , st_x , st_y , en_x , en_y , idx , i + 1 , j - 1 , str); // left down diagonal

        boolean l_U_d = paths(vis , path , st_x ,st_y , en_x ,en_y , idx , i - 1 , j - 1 , str); // left up diagonal

        boolean r_u_d = paths(vis , path , st_x , st_y , en_x , en_y , idx , i - 1 , j + 1 , str); // right up diagonal

        boolean l = paths(vis , path , st_x , st_y , en_x , en_y , idx , i , j - 1 , str); // left


        vis[i][j] = false;

        if(( i != st_x && j != st_y) && ( i != en_x && j != en_y)) path[i][j] = null;

        return  l || r || d || u || l_U_d || l_d_d || r_u_d || r_d_d;

    }

    List<List<Point>> pathsForAllTheFlights() {

        // Now in the paths2 array all the sucdessfull paths will be stored

        // get the coordinate paths for each flight

        List<List<Point>> all_flight_paths = new ArrayList<>();

        for(int i = 2;i < 10; i++) {

            List<Point> flight_path = new ArrayList<>();

            // The class provided by the Java from the package import java.awt.*;

            Point point = new Point(1,1);

            flight_path.add(point);

            for(int j = 2; j < 10 ; j++) {

                if(path2[i][j]!= null && path2[i][j].equals("p"+i)){

                    Point point1 = new Point( i , j);

                    flight_path.add(point1);
                }
            }

            all_flight_paths.add(flight_path);
        }

        return all_flight_paths;
    }

}
