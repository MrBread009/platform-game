import greenfoot.*;

public class BeePlayer extends ComputerPlayer
{
    private BeeBoss _boss;
    
    public BeePlayer(int x, int y,BeeBoss boss)
    {
        super(x,y);
        _boss = boss;
    }
     
    public void die(PlatformActor thingBeingTouched) 
    {
       _boss.BeePlayers = _boss.BeePlayers - 1;
       super.die(thingBeingTouched);
    }
}
