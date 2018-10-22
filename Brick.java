import greenfoot.*;

public class Brick extends SolidBlock
{
    public boolean isDeadly(PlatformActor thingBeingTouched)
    {
        if (thingBeingTouched instanceof Fireball)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}


