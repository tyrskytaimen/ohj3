public class Circle implements IShapeMetrics{
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Circle with radius: %.2f", radius);
    }

    @Override
    public String name() {
        return "circle";
    }

    @Override
    public double area() {
        return PI * Math.pow(radius,2);
    }

    @Override
    public double circumference() {
        return 2 * PI * radius;
    }
}
