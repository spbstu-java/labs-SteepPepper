package laba_1;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
/**
 *
 * @author achigir
 */
public class Hero {
    IMovement movement = new Walk();
    Point point = new Point();
    
    public void setMovement(IMovement movement)
    {
        this.movement = movement;
    }
    
    public void executeMovement(Point point)
    {
        movement.move();
        double x = abs(this.point.getX() - point.getX());
        double y = abs(this.point.getY() - point.getY());
        double moving = sqrt(x * x + y * y);
        
        System.out.printf("Расстояние в %.3f у. е. преодолели за %.3f у. е.\n",
                moving, moving / movement.getSpeedCoefficient());
        this.point = point;
    }
    
}
