package achigir.lab_4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 *
 * @author achigir
 */
public class Repeater {
    public void repeatAsAnnotated(Object object) 
    {
        Classes testClass = new Classes();
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) 
        {
            if (method.isAnnotationPresent(N_calls.class)) 
            {
                method.setAccessible(true);
                int counter = method.getAnnotation(N_calls.class).num();
                for (int i = 0; i < counter; i++) 
                {
                    try 
                    {
                        method.invoke(testClass);
                    } catch (IllegalAccessException | InvocationTargetException e) 
                    {
                        e.printStackTrace();
                    }
                }
                method.setAccessible(false);
            }
        }
    }
    
}
