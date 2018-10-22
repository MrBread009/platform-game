import greenfoot.*;
import javax.swing.Timer;
import java.awt.event.*;

public class PlatformWorld extends World
{
    public boolean runLevelEditor = false;
    private ScrollHandler _scrollHandler = new ScrollHandler(this);
    private LevelHandler _levelHandler = new LevelHandler(this);
    
    public PlatformWorld()
    {
        super(750,500, 1,false); 
        _levelHandler.recreateLevel();
    }

    public void advanceLevel()
    {
        _levelHandler.advanceLevel();
    }
    
    public ScrollHandler getScrollHandler()
    {
        return _scrollHandler;
    }
    
    public LevelHandler getLevelHandler()
    {
        return _levelHandler;
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getButton() == 1)
        {
            if(runLevelEditor)
            {
                _levelHandler.handleClick();
            }
            else
            {
                for(PlatformActor actor : this.getObjects(PlatformActor.class))
                {
                    actor.clickHappened(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                }
            }
        }

        if(Greenfoot.isKeyDown("p"))
        {
            System.out.println(_levelHandler.getLevelAsString());
        }

        if(Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getButton() == 3) 
        {
            runLevelEditor = !runLevelEditor;
        }
        
        getScrollHandler().scrollWorld();
    }
    
    public void addObjectAfterDelay(Actor originalActor, Actor newActor, int delay)
    {
        int screenX = originalActor.getX();
        int screenY = originalActor.getY();
        int originalScrollX = getScrollHandler().getScrollX();
        int originalScrollY = getScrollHandler().getScrollY();
        
        Timer timer = new Timer(delay, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                int newScrollX = getScrollHandler().getScrollX();
                int newScrollY = getScrollHandler().getScrollY();
                addObject(newActor, screenX + newScrollX - originalScrollX, screenY + newScrollY - originalScrollY);
            }
        });
        timer.setRepeats(false); 
        timer.start();
    }
}
