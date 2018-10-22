public class ScrollHandler  
{
    private int currentScrollX = 0;
    private int currentScrollY = 0;
    private PlatformWorld _world;
    
    public ScrollHandler(PlatformWorld world)
    {
        _world = world;
    }
    
    public int getScrollX()
    {
        return currentScrollX;
    }
    
    public int getScrollY()
    {
        return currentScrollY;
    }
    
    public void reset()
    {
        int oldScrollX = currentScrollX;
        int oldScrollY = currentScrollY;
        currentScrollX = 0;
        currentScrollY = 0;
        performScroll(oldScrollX, oldScrollY);
    }
    
    public void scrollWorld()
    {
        if(_world.runLevelEditor)
            return;
            
        int bufferX = 375;
        int bufferY = 200;
        int scrollX = 0;
        int scrollY = 0;
        HumanPlayer player = _world.getObjects(HumanPlayer.class).iterator().next();
        
        if(player.getX() < bufferX)
        {
            scrollX = bufferX - player.getX();
        }
        
        if(player.getY() < bufferY)
        {
            scrollY = bufferY - player.getY();
        }
            
        if(player.getY() > _world.getHeight() - bufferY)
        {
            scrollY = _world.getHeight() - bufferY - player.getY();
        }
        
        if(player.getX() > _world.getWidth() - bufferX)
        {
            scrollX = _world.getWidth() - bufferX - player.getX();
        }
        
        performScroll(scrollX, scrollY);
    }
    
    private void performScroll(int scrollX, int scrollY)
    {
        if (scrollX == 0 && scrollY == 0)
        {
            return;
        }
        
        for (PlatformActor actor : _world.getObjects(PlatformActor.class))
        {
            actor.scroll(scrollX,scrollY);
        }
        
        currentScrollX += scrollX;
        currentScrollY += scrollY;
    }
}
