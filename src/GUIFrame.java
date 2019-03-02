import javax.swing.*;
import java.awt.*;
import java.util.List;

import ParseData.Point;

public class GUIFrame extends JFrame
{
    private List<Point> points;

    public GUIFrame(List<Point> points) {
        this.setPreferredSize(new Dimension(800, 800));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.points = points;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawLine((int)points.get(4).x, (int)points.get(4).y, (int)points.get(3).x, (int)points.get(3).y);
        g.drawLine((int)points.get(2).x, (int)points.get(2).y, (int)points.get(3).x, (int)points.get(3).y);

        g.drawLine((int)points.get(2).x, (int)points.get(2).y, (int)points.get(1).x, (int)points.get(1).y);
        g.drawLine((int)points.get(5).x, (int)points.get(5).y, (int)points.get(1).x, (int)points.get(1).y);
        g.drawLine((int)points.get(5).x, (int)points.get(5).y, (int)points.get(6).x, (int)points.get(6).y);
        g.drawLine((int)points.get(7).x, (int)points.get(7).y, (int)points.get(6).x, (int)points.get(6).y);
    }

}
