import greenfoot.*;

public class Collision  
{
    int playerBotom;
    int brickTop;
    int playerTop;
    int brickBotom;
    int playerRight;
    int brickLeft;
    int playerLeft;
    int brickRight;
    int brickLeftOverlap;
    int brickRightOverlap;
    int brickTopOverlap;
    int brickBotomOverlap;
    boolean isOverlappingTop;
    boolean isOverlappingBotom;
    boolean isOverlappingLeft;
    boolean isOverlappingRight;
    
    public Collision(PlatformActor a, PlatformActor b)
    {
        calculate (a, b);
    }

    private void calculate(PlatformActor a, PlatformActor b)
    {
        playerBotom = a.getBotomY();
        brickTop = b.getTopY();
        playerTop = a.getTopY();
        brickBotom = b.getBotomY();
        playerRight = a.getRightX();
        brickLeft = b.getLeftX();
        playerLeft = a.getLeftX();
        brickRight = b.getRightX();
        
        brickLeftOverlap = Math.abs(brickLeft - playerRight);
        brickRightOverlap = Math.abs(brickRight - playerLeft);
        brickTopOverlap = Math.abs(brickTop - playerBotom);
        brickBotomOverlap = Math.abs(brickBotom - playerTop);
        
        isOverlappingTop = playerBotom >= brickTop && a.getY() < brickTop;
        isOverlappingBotom = playerTop <= brickBotom && a.getY() > brickBotom;
        isOverlappingLeft = playerRight >= brickLeft && a.getX() < brickLeft;
        isOverlappingRight = playerLeft <=  brickRight && a.getX() > brickRight;
    }
    
    public boolean shouldPushLeft()
    {
        return isOverlappingLeft && (
                    (isOverlappingTop && brickTopOverlap > brickLeftOverlap)
                    || (isOverlappingBotom && brickBotomOverlap > brickLeftOverlap)
                    || (!isOverlappingTop && !isOverlappingBotom));
    }
    
    public boolean shouldPushRight()
    {
        return isOverlappingRight && (
                    (isOverlappingTop && brickTopOverlap > brickRightOverlap)
                    || (isOverlappingBotom && brickBotomOverlap > brickRightOverlap)
                    || (!isOverlappingTop && !isOverlappingBotom));

    }
    
    public boolean shouldPushUp()
    {
        return isOverlappingTop && (
                    (isOverlappingLeft && brickLeftOverlap > brickTopOverlap)
                    || (isOverlappingRight && brickRightOverlap > brickTopOverlap)
                    || (!isOverlappingRight && !isOverlappingLeft));
    }
    
    public boolean shouldPushDown()
    {
        return  isOverlappingBotom && (
                    (isOverlappingLeft && brickLeftOverlap > brickBotomOverlap)
                    || (isOverlappingRight && brickRightOverlap > brickBotomOverlap)
                    || (!isOverlappingRight && !isOverlappingLeft));
    }
    public int pushUpAmount()
    {
        return brickTop - playerBotom + 1;
    }
    public int pushDownAmount()
    {
        return brickBotom - playerTop - 1;
    }
    public int pushLeftAmount()
    {
        return brickLeft - playerRight + 1;
    }
    public int pushRightAmount()
    {
        return brickRight - playerLeft - 1;
    }
}
