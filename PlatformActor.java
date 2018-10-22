import greenfoot.*;
import java.util.List;

public abstract class PlatformActor extends Actor
{
    protected double yVelocity = 0;
    protected double xVelocity = 0;
    protected boolean isTouchingGround = false;
    protected boolean isTouchingSpring = false;
    protected boolean didHitRightWall = false;
    protected boolean didHitLeftWall = false;
    
    public abstract boolean isDeadly(PlatformActor actor);
    
    public void clickHappened(int x, int y)
    {
    }
    
    public int getBotomY() 
    {
        int botomY = getY() + getImage().getHeight()/2;
        return botomY;
    }
    public int getTopY() 
    {
        int topY = getY() - getImage().getHeight()/2;
        return topY;
    }
    public int getLeftX()
    {
        int leftX = getX() - getImage().getWidth()/2;
        return leftX;
    }
    
    public int getRightX()
    {
        int rightX = getX() + getImage().getWidth()/2;
        return rightX;
    }
    
    public void act()
    {
        if (!getPlatformWorld().runLevelEditor)
        {
            handleMovement();
            
            isTouchingGround = false;
            isTouchingSpring = false;
            boolean die = checkForDeath();
            if (!die) 
            {
                dontGoThroughBricksLeftRight();
                dontGoThroughBricksUpDown();
            }
        }
    }
    
    protected PlatformWorld getPlatformWorld()
    {
        return ((PlatformWorld)getWorld());
    }
    
    protected void scroll(int scrollX,int scrollY)
    {
        if (scrollX != 0)
        {
            this.moveX(scrollX);
        }
        if (scrollY != 0)
        {
            this.moveY(scrollY);
        }
    }
    
    public void moveY(int change)
    {
        int oldX = getX();
        int oldY = getY();
        int newX = oldX;
        int newY = oldY+change;
        setLocation(newX, newY);
    }
    
    public void moveX(int change)
    {
        int oldX = getX();
        int oldY = getY();
        int newX = oldX + change;
        int newY = oldY;
        setLocation(newX, newY);
    }
    
    protected void handleMovement()
    {
        moveY((int) yVelocity);
        moveX((int) xVelocity);
    }
    
    protected void die(PlatformActor thingBeingTouched)
    {
        World world = getWorld();
        if (world != null)
        {
            world.removeObject(this);
        }
    }
    
    protected void afterKill(PlatformActor thingKilled)
    {
    }
    
    private boolean checkForDeath()
    {
        List<PlatformActor> thingsBeingTouched = getIntersectingObjects(PlatformActor.class);
        for (PlatformActor thingBeingTouched : thingsBeingTouched) {
            if (thingBeingTouched.isDeadly(this)) {
                die(thingBeingTouched);
                thingBeingTouched.afterKill(this);
                return true;
            }
        }
        return false;
    }
    
    private void dontGoThroughBricksLeftRight()
    {
        List<SolidBlock> touchingSolidBlocks = getIntersectingObjects(SolidBlock.class);
        didHitLeftWall = false;
        didHitRightWall = false;
        
        for (SolidBlock block : touchingSolidBlocks) 
        {
            Collision c = new Collision(this, block);
            
            if (c.shouldPushLeft())
            {
                moveX(c.pushLeftAmount());
                didHitRightWall = true;
            }
            
            if (c.shouldPushRight())
            {
                moveX(c.pushRightAmount());
                didHitLeftWall = true;
            }
        }
    }
    
    private void dontGoThroughBricksUpDown()
    {
        List<SolidBlock> touchingSolidBlocks = getIntersectingObjects(SolidBlock.class);
        for ( SolidBlock block : touchingSolidBlocks ) 
        {
            Collision c = new Collision(this, block);
            if (c.shouldPushUp())
            {
                // move up
                moveY(c.pushUpAmount());
                yVelocity = 0;
                isTouchingGround = true;
                String myType = block.getClass().getName();
                if (block instanceof Spring)
                {
                    isTouchingSpring = true;
                }
            }
            
            if (c.shouldPushDown())
            {
                moveY(c.pushDownAmount());
                if (yVelocity < 0)
                {
                    yVelocity = 0;
                }
            }
        }
    }
    
    public int getScreenX()
    {
        int x = getX();
        int scrollX = getPlatformWorld().getScrollHandler().getScrollX();
        return x - scrollX;
    }
    
    public int getScreenY()
    {
        int y = getY();
        int scrollY = getPlatformWorld().getScrollHandler().getScrollY();
        return y - scrollY;
    }
}   
