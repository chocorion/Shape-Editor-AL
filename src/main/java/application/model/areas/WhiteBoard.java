package application.model.areas;

import application.model.Model;
import application.model.shape.CompositeShape;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ModelObservableImp;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class WhiteBoard extends ModelObservableImp implements ShapeContainer {
    private ArrayList<Shape> shapes;
    private Model model;

    public WhiteBoard(Model model) {
        shapes = new ArrayList<>();
        this.model = model;
    }

    public void update() {
        super.notifyObserver();
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        update();
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        update();
    }

    public ArrayList<Shape> getInnerShapes() {
        return new ArrayList<>(shapes);
    }

    public Shape getShapeAt(int x, int y) {
        for (Shape shape : shapes) {
            if (shape.isIn(x, y)) {
                return shape;
            }
        }

        return null;
    }

    public void moveShape(Shape shape, int x, int y) {
        shape.moveTo(x, y);
        update();
    }

    public void replace(Set<Shape> oldShape, Set<Shape> newShape) {
        for (Shape s : oldShape) {
            removeShape(s);
        }

        for (Shape s : newShape) {
            addShape(s);
        }

        update();
    }

    public void save( String path){
        StringBuilder str = new StringBuilder();
        for (Shape s : shapes){
            str.append(s.toString());
        }

        File file = new File(path);
        try {
            FileWriter output = new FileWriter(file.getAbsoluteFile());
            output.write(str.toString());
            output.close();
        } catch (IOException e) {
            // File not found
            e.printStackTrace();
        }
    }

    private Shape loadRectangle(String line){
        String[] values = line.split(", ");
        double x = Double.parseDouble(values[0]);
        double y = Double.parseDouble(values[1]);
        double w = Double.parseDouble(values[2]);
        double h = Double.parseDouble(values[3]);
        int r = Integer.parseInt(values[4]);
        int g = Integer.parseInt(values[5]);
        int b = Integer.parseInt(values[6]);
        double a = Double.parseDouble(values[7]);

        return new Rectangle(x,y,w,h,new Color(r,g,b,a));
    }

    private Shape loadComposite(Scanner input, int number) {
        CompositeShape shapes = new CompositeShape();
        for (int i = 0; i < number; i++) {
            String line = input.nextLine();
            if (line.contains("Rectangle")) {
                line = input.nextLine();
                shapes.add(loadRectangle(line));
            } else {
                String[] values = line.split(" ");
                shapes.add(loadComposite(input, Integer.parseInt(values[1])));
            }
        }
        return shapes;
    }

    public void load(String path){
        ArrayList<Shape> newShapes = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner input = new Scanner(file.getAbsoluteFile());
            while(input.hasNextLine()){
                String line = input.nextLine();
                if(line.contains("Rectangle")){
                    line = input.nextLine();
                    newShapes.add(loadRectangle(line));
                }else if (line.contains("Composite")){
                     String[] values = line.split(" ");
                     newShapes.add(loadComposite(input, Integer.parseInt(values[1])));
                }
            }
            this.shapes = newShapes;
            update();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void toFirstPlan(Shape currentShape) {
        shapes.remove(currentShape);
        shapes.add(currentShape);
        update();
    }

    public int getShapePlace(Shape shape) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shape == shapes.get(i)) {
                return i;
            }
        }

        return -1;
    }
}
