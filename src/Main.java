import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Define the flight paths with the provided coordinates
        List<List<Point>> flights = new ArrayList<>();
        flights.add(List.of(new Point(1, 1), new Point(2, 2), new Point(3, 3))); // Flight 1
        flights.add(List.of(new Point(1, 1), new Point(2, 4), new Point(3, 2))); // Flight 2
        flights.add(List.of(new Point(1, 1), new Point(4, 2), new Point(3, 4))); // Flight 3

        // Create and set up the window
        JFrame frame = new JFrame("Flight Path Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new FlightPathVisualizer(flights));
        frame.setVisible(true);

    }
}