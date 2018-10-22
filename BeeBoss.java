import greenfoot.*;

public class BeeBoss extends PlatformActor
{
    private int _livesLeft = 5;
    
    public BeeBoss()
    {
        setRotation (-90);
    }
    
    public boolean isDeadly(PlatformActor thingBeingTouched)
    {
        if (thingBeingTouched instanceof BeePlayer
            || thingBeingTouched instanceof Fireball
            || thingBeingTouched instanceof BeeBoss) 
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void act() 
    {
        super.act();
        if(Greenfoot.getRandomNumber(210) >  208 && BeePlayers < 6)
        {
            getWorld().addObject(new BeePlayer(getX(),getY(),this), getX(), getY());
            BeePlayers = BeePlayers + 1;
        }
    }  
    
    private boolean isMovingLeft = false;
    public int BeePlayers = 0;
    protected void handleMovement()
    {
        if(didHitLeftWall || didHitRightWall || Greenfoot.getRandomNumber(275) >  273)
        {
            isMovingLeft = !isMovingLeft;
        } 
        
        if (isMovingLeft)
        {
            xVelocity = -1;
        }
        else
        {
            xVelocity = 1;
        }
        
        super.handleMovement();
    }
    
    protected void die(PlatformActor thingBeingTouched)
    {
        _livesLeft -= 1;
        if (_livesLeft == 0)
        {
            getWorld().removeObject(this);
            getPlatformWorld().advanceLevel();
        }
    }
}
