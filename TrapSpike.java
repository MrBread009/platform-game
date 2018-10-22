import greenfoot.*;

public class TrapSpike extends Spike
{
    private boolean _isActive = false;
    
    public void act() 
    {
        if (_isActive) 
        {
            getImage().setTransparency(255);
        }
        else 
        {
            getImage().setTransparency(0);
        }
    }
    
    @Override
    public boolean isDeadly(PlatformActor actor) {
        return _isActive;
    }
    
    public void setisActive(boolean value)
    {
        _isActive = value;
    }
}
