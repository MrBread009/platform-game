import greenfoot.*;
import java.util.List;

public class Plate extends Brick
{
    public void act() 
    {
        boolean isBeingTouched = getIntersectingObjects(HumanPlayer.class).size() > 0;
        for (TrapSpike spike : getWorld().getObjects(TrapSpike.class))
        {
            spike.setisActive(isBeingTouched);
        }
    }    
    
    public boolean isDeadly()
    {
        return false;
    }
}
