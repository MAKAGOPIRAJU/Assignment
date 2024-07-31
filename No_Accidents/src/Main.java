
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {


    public static void main(String[] args) {

//         ------------------ EX INPUT

        List<co_ordinates> coOrdinatesList = new ArrayList<>();

        coOrdinatesList = new ArrayList<>();

        coOrdinatesList.add(new co_ordinates(1, 1,2,2));
        coOrdinatesList.add(new co_ordinates(2,2,3,3));
        coOrdinatesList.add(new co_ordinates(1,1,2,4));
        coOrdinatesList.add(new co_ordinates(2,4,3,2));
        coOrdinatesList.add(new co_ordinates(1,1,4,2));
        coOrdinatesList.add(new co_ordinates(4,2,3,4));

        // send the given the co-ordinates to the class Find the Paths to find the un intersected paths
        FindThePaths findThePaths = new FindThePaths(coOrdinatesList);

        // fetch the un intersected points

        List<List<Point>> all_flight_paths = findThePaths.pathsForAllTheFlights();


       // Draw the graph for fetched points

        // Create and set up the window
        JFrame frame = new JFrame("Flight Path Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new FlightPathVisualizer(all_flight_paths));
        frame.setVisible(true);

    }

}