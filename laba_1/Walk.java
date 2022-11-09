package laba_1;

/**
 *
 * @author achigir
 */
public class Walk implements IMovement{
    private double speedCoefficient = 2;
    @Override
    public void move()
    {
        System.out.println("*Герой идет пешком*");
    }
    @Override
    public double getSpeedCoefficient()
    {
        return speedCoefficient;
    }
}
