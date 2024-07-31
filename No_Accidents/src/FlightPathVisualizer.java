

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.util.List;

public class FlightPathVisualizer extends JPanel {

    private List<List<Point>> flights;

    public FlightPathVisualizer(List<List<Point>> flights) {
        this.flights = flights;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Define colors for different flights
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.ORANGE};

        // Draw each flight path
        for (int i = 0; i < flights.size(); i++) {
            List<Point> flight = flights.get(i);
            g2d.setColor(colors[i % colors.length]);
            for (int j = 0; j < flight.size() - 1; j++) {
                Point p1 = flight.get(j);
                Point p2 = flight.get(j + 1);
                // Scale the points for better visibility
                int x1 = p1.x * 50;
                int y1 = p1.y * 50;
                int x2 = p2.x * 50;
                int y2 = p2.y * 50;

                // Draw the line between points
                g2d.drawLine(x1, y1, x2, y2);

                // Draw the point coordinates as text
                g2d.drawString("(" + p1.x + "," + p1.y + ")", x1 + 5, y1 - 5); // Print point coordinates
            }
            // Draw the last point of each flight path
            Point lastPoint = flight.get(flight.size() - 1);
            int xLast = lastPoint.x * 50;
            int yLast = lastPoint.y * 50;
            g2d.drawString("(" + lastPoint.x + "," + lastPoint.y + ")", xLast + 5, yLast - 5); // Print last point coordinates
        }
    }
}
