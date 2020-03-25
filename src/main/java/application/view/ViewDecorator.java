package application.view;

public abstract class ViewDecorator implements View {
    private View view;

    public ViewDecorator(View view) {
        this.view = view;
    }

    @Override
    public void draw() {
        this.view.draw();
    }

    @Override
    public void drawRectangle() {
        this.view.drawRectangle();
    }

    @Override
    public void drawPolygon() {
        this.view.drawPolygon();
    }
}
