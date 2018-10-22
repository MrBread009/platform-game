import greenfoot.*;

public class Flash extends Actor
{
    private int _transparency = 255;
    
    public void act() 
    {
        GreenfootImage image = new GreenfootImage (getWorld().getWidth(), getWorld().getHeight());
        image.setColor(Color.RED);
        image.setTransparency(_transparency);
        image.fill();
        setImage(image);
        _transparency -= 5;
        if (_transparency <= 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
