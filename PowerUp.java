import greenfoot.*;

public class PowerUp extends PlatformActor
{
    public boolean isDeadly (PlatformActor actor)
    {
        return false;
    }
    
    protected void die(PlatformActor thingBeingTouched)
    {
        getPlatformWorld().addObjectAfterDelay(this, new PowerUp(), 5000);
        super.die(thingBeingTouched);
        
    }
}
