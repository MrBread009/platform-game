import greenfoot.*;

public class Rocket extends Fireball
{
    public Rocket(int startingX, int startingY) 
    {
        super(Math.PI / 2, startingX, startingY);
    }
    
    public void act() 
    {
        super.act();
    }    
    
    public boolean isDeadly(PlatformActor actor)
    {
        if(actor instanceof HumanPlayer)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}
