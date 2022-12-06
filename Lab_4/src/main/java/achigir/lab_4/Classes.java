package achigir.lab_4;

/**
 *
 * @author achigir
 */
public class Classes {
    private int count1 = 1;
    private int count2 = 1;
    private int count3 = 1;
    private int count4 = 1;
    private int count5 = 1;
    private int count6 = 1;
    
    @N_calls(num = 1)
    public void method1() 
    {
        System.out.println("This is Public method 1: " + count1++);
    }

    public void method2() 
    {
        System.out.println("This is Public method 2: " + count2++);
    }

    @N_calls(num = 3)
    protected void method3()
    {
        System.out.println("This is Protected method 3: " + count3++);
    }

    @N_calls(num = 2)
    protected void method4() 
    {
        System.out.println("This is Protected method 4: " + count4++);
    }

    @N_calls(num = 2)
    private void method5() 
    {
        System.out.println("This is Private method 5: " + count5++);
    }

    @N_calls(num = 1)
    private void method6() 
    {
        System.out.println("This is Private method 6: " + count6++);
    }
}
