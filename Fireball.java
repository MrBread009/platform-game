import greenfoot.*;

public class Fireball extends PlatformActor
{
    private double _angle = 0;
    protected double _x = -1;
    protected double _y = -1;
    protected double speed = 3;
    
    
    
     protected void setSpeed()
    {
        
        speed = speed + 0.2;
        
    }
    
    public Fireball(double angle, int startingX, int startingY) 
    {
        _angle = angle;
        _x = startingX;
        _y = startingY;
    }
    
    protected void scroll(int scrollX,int scrollY)
    {
        _x += scrollX;
        _y += scrollY;
    }
    
    protected void handleMovement() 
    {
        if(_x == -1)
        {
            _x = getX();
            _y = getY();
        }
        _x += Math.sin(_angle) * speed;
        _y += Math.cos(_angle) * speed;
        setLocation((int)_x, (int)_y);
        super.handleMovement();
        setSpeed();
    }    
    
    public boolean isDeadly(PlatformActor actor)
    {
        if(actor instanceof HumanPlayer || actor instanceof PowerUp)
        {
            return false;
        }
        else 
        {
            return true;
        }
    }
    
    protected void afterKill(PlatformActor thingKilled)
    {
        die(thingKilled);
    }
    
   
    
    
}
