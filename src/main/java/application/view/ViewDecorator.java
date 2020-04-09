package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;

public abstract class ViewDecorator implements View {
    private View view;

    public ViewDecorator(View view) {
        this.view = view;
    }

    @Override
    public int getWidth() {
        return this.view.getWidth();
    }

    @Override
    public int getHeight() {
        return this.view.getHeight();
    }

    @Override
    public void draw() {
        this.view.draw();
    }

    @Override
    public void drawRectangle(Rectangle rectangle) {
        this.view.drawRectangle(rectangle);
    }

    @Override
    public void drawPolygon(Polygon polygon) {
        this.view.drawPolygon(polygon);
    }

    @Override
    public void drawSelection(int x,int y, int width, int height){ this.view.drawSelection(x,y,width,height);}

    @Override
    public void addPopUpMenu(int x, int y){this.view.addPopUpMenu(x,y);}

    @Override
     public boolean clickOnGroup(int x,int y){return this.view.clickOnGroup(x,y);}

    @Override
    public void undrawSelect(int x, int y, int width, int height) {this.view.undrawSelect(x,y,width,height);}

    @Override
    public void undrawMenu(){this.view.undrawMenu();}


}
