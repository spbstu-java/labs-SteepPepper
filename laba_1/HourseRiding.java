package laba_1;

/**
 *
 * @author achigir
 */
public class HourseRiding implements IMovement{
    private double speedCoefficient = 12;
    @Override
    public void move()
    {
        System.out.println("*Герой скачет на лошади*");
    }
    
    public double getSpeedCoefficient()
    {
        return speedCoefficient;
    }
}
