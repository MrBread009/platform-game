import greenfoot.*;
import javax.swing.Timer;
import java.awt.event.*;

public class HumanPlayer extends Player
{
    private boolean _isCharged = true;
    private boolean _hasPowerUp = false;
    
    public boolean isDeadly(PlatformActor thingBeingTouched)
    {
        if(thingBeingTouched instanceof PowerUp)
        {
            return true;
        }
        
        return false;
    }
    
    public void die(PlatformActor thingBeingTouched)
    {
        PlatformWorld thisWorld = (PlatformWorld)getWorld();
        thisWorld.getLevelHandler().recreateLevel();
        thisWorld.addObject(new Flash(), thisWorld.getWidth() / 2, thisWorld.getHeight() / 2);
    }
    
    public HumanPlayer(int x, int y) 
    {
        super(x,y);
    }
    
    public void act() 
    {
        super.act();
    }    
    
    protected void handleMovement()
    {
        double normalMoveX = 3;
        double jumpOffWallMoveX = 3;
        double jumpOffWallMoveY = 5;
        
        if (Greenfoot.isKeyDown("left")) 
        {
            if (xVelocity > -normalMoveX || isTouchingGround)
            {
                xVelocity = -normalMoveX;
            }
           
            if(!isTouchingGround && didHitRightWall)
            {
                xVelocity = -jumpOffWallMoveX;
                yVelocity = -jumpOffWallMoveY;
            }
        }
        
        else if (Greenfoot.isKeyDown("right")) 
        {
            if (xVelocity < normalMoveX || isTouchingGround)
            {
                xVelocity = normalMoveX;
            }
            if(!isTouchingGround && didHitLeftWall)
            {
                xVelocity = jumpOffWallMoveX;
                yVelocity = -jumpOffWallMoveY;
            }
        }
        else
        {
            xVelocity = 0;
        }
        if (Greenfoot.isKeyDown("up") && isTouchingGround) 
        {
            if (isTouchingSpring)
            {
                yVelocity = -9;
            }
            else
            {
                yVelocity = -6.5; 
            }
        }  
        if (!isTouchingGround && Greenfoot.isKeyDown("right"))
        {
            xVelocity = 4;
        }
        if (!isTouchingGround && Greenfoot.isKeyDown("left"))
        {
            xVelocity = -4;
        }
        
        super.handleMovement();
    }
    
    public void clickHappened(int x, int y)
    {
        if (_hasPowerUp && _isCharged)
        {
            double angle = Math.atan2(x - getX(), y - getY());
            getWorld().addObject(new Fireball(angle, getX(), getY()), getX(), getY());
            _isCharged = false;
            Timer timer = new Timer(750, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    _isCharged = true;
                }
            });
            timer.setRepeats(false); 
            timer.start();
        }
    }
    
    protected void afterKill(PlatformActor thingKilled)
    {
        if(thingKilled instanceof PowerUp)
        {
            _hasPowerUp = true;
            setImage("images/firePlayer.png");
            Timer timer = new Timer(10000, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    setImage("images/player.png");
                    _hasPowerUp = false;
                }
            });
            timer.setRepeats(false); 
            timer.start();
        }
    }
}   
