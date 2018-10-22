import greenfoot.*;

public class Flag extends PlatformActor
{
    public void act() 
    {
        boolean isBeingTouched = getIntersectingObjects(HumanPlayer.class).size() > 0;
        if (isBeingTouched)
        {
           getPlatformWorld().advanceLevel();
        }
    }    
    public boolean isDeadly(PlatformActor actor)
    {
        return false;
    }
}
