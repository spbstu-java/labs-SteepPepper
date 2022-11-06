package laba_1;

/**
 *
 * @author achigir
 */
public class Flight implements IMovement{
    private double speedCoefficient = 20;
    @Override
    public void move()
    {
        System.out.println("*Герой летит*");
    }
    @Override
    public double getSpeedCoefficient()
    {
        return speedCoefficient;
    }
}
