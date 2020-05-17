package application.model.Memento;

import application.model.shape.CompositeShape;
import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadFunction {
    private static Shape loadRectangle(String line){
        String[] values = line.split(", ");
        double x = Double.parseDouble(values[0]);
        double y = Double.parseDouble(values[1]);
        double w = Double.parseDouble(values[2]);
        double h = Double.parseDouble(values[3]);
        double angle = Double.parseDouble(values[4]);
        int r = Integer.parseInt(values[5]);
        int g = Integer.parseInt(values[6]);
        int b = Integer.parseInt(values[7]);
        double a = Double.parseDouble(values[8]);
        double round = Double.parseDouble(values[9]);

        Rectangle rect = new Rectangle(x,y,w,h,new Color(r,g,b,a));
        rect.setAngle(angle);
        rect.setRoundValue(round);
        return rect;
    }

    private static Shape loadPolygon(String line){
        String[] values = line.split(", ");
        double x = Double.parseDouble(values[0]);
        double y = Double.parseDouble(values[1]);
        double size = Double.parseDouble(values[2]);
        int nbsize = Integer.parseInt(values[3]);
        double angle = Double.parseDouble(values[4]);
        int r = Integer.parseInt(values[5]);
        int g = Integer.parseInt(values[6]);
        int b = Integer.parseInt(values[7]);
        double a = Double.parseDouble(values[8]);

        Polygon p = new Polygon(x,y,size,nbsize, new Color(r,g,b,a));
        p.setAngle(angle);

        return p;
    }

    private static Shape loadComposite(Scanner input, int number) {
        CompositeShape shapes = new CompositeShape();
        for (int i = 0; i < number; i++) {
            String line = input.nextLine();
            if (line.contains("Rectangle")) {
                line = input.nextLine();
                shapes.add(loadRectangle(line));
            } else if (line.contains("Polygon")){
                line = input.nextLine();
                shapes.add(loadPolygon(line));
            }else {
                String[] values = line.split(" ");
                shapes.add(loadComposite(input, Integer.parseInt(values[1])));
            }
        }
        return shapes;
    }

    public static ArrayList<Shape> loading(String path){
        ArrayList<Shape> newShapes = new ArrayList<>();
        File file = new File((ToolBarState.class.getResource(path).getPath()));

        try {
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String line = input.nextLine();

                if(line.contains("Rectangle")){
                    line = input.nextLine();
                    newShapes.add(loadRectangle(line));
                } else if (line.contains("Polygon")){
                    line = input.nextLine();
                    newShapes.add(loadPolygon(line));
                }else if (line.contains("Composite")){
                    String[] values = line.split(" ");
                    newShapes.add(loadComposite(input, Integer.parseInt(values[1])));
                }
            }
            return newShapes;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
