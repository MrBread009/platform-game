import greenfoot.*;

public class ComputerPlayer extends Player
{
    private boolean isMovingLeft = false;
    
    public ComputerPlayer(int x, int y)
    {
        super(x,y);
    }
    
    public boolean isDeadly(PlatformActor thingBeingTouched)
    {
        if (thingBeingTouched instanceof ComputerPlayer ||
            thingBeingTouched instanceof Fireball ||
            thingBeingTouched instanceof BeeBoss ||
            thingBeingTouched instanceof PowerUp ||
            thingBeingTouched instanceof BeePlayer) 
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
    }   
    
    protected void handleMovement()
    {
        if(didHitLeftWall || didHitRightWall)
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
        
        if (Greenfoot.getRandomNumber(100) >  98 && isTouchingGround) 
        {
            if (isTouchingSpring)
            {
                yVelocity = -8;
            }
            else
            {
                yVelocity = -6; 
            }
        }
        
        super.handleMovement();
    }
}
    
