import greenfoot.*;

public abstract class FallingObject extends PlatformActor
{
    protected void handleMovement()
    {
        fall();
        super.handleMovement();
    }
    
    private void fall()
    {
        yVelocity = yVelocity + 0.2;
    }
}
