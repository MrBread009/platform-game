import greenfoot.*;
import javax.swing.Timer;
import java.awt.event.*;

public class Turret extends SolidBlock
{
    private int _actsSinceLastRocket = 0;
    
    public void act() 
    {
       super.act();
       _actsSinceLastRocket++;
       
       if (_actsSinceLastRocket == 100)
       {
          fireRockets();
          _actsSinceLastRocket = 0;
       }
    }    
    
    public boolean isDeadly(PlatformActor thingBeingTouched)
    {
        return false;
    }
    
    public void fireRockets()
    {
        int rocketX = getX() + 75;
        int rocketY = getY();
        getWorld().addObject(new Rocket(rocketX, rocketY), rocketX, rocketY);
    }
}
