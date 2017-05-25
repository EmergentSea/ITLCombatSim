import java.util.Scanner;
import java.util.Random;
import java.util.Objects;
import java.io.*;


class CreateMeleeWeapons
{
  public static MeleeWeapon rapier = new MeleeWeapon(1, "Rapier", 1, 0, 9, 40, 0.5, 1, false);
  public static MeleeWeapon CreateThem(String string, Player p1)
  {
    MeleeWeapon weapon = rapier;
    try
    {
      BufferedReader WeaponInput = new BufferedReader(new FileReader("Weapons.txt"));
      String input = WeaponInput.readLine();
      while (( input != null) && string != weapon.name)
      {
        if(Objects.equals((input.substring(0,7)), "Weapon:"))
        {
          input = WeaponInput.readLine();
          weapon.name = input;
          input = WeaponInput.readLine();
          weapon.type = (Integer.parseInt(input));
          input = WeaponInput.readLine();
          weapon.damageDice = (Integer.parseInt(input));
          input = WeaponInput.readLine();
          weapon.damageFlat = (Integer.parseInt(input));
          input = WeaponInput.readLine();
          weapon.reqST = (Integer.parseInt(input));
          input = WeaponInput.readLine();
          weapon.cost = (Integer.parseInt(input));
          input = WeaponInput.readLine();
          weapon.weight = (Double.parseDouble(input));
          input = WeaponInput.readLine();
          weapon.reqHands = (Integer.parseInt(input));
          input = WeaponInput.readLine();
          weapon.throwable = (Boolean.parseBoolean(input));
        }
        input = WeaponInput.readLine();
      }
      WeaponInput.close();
    }
    catch (IOException iox)
    {
      System.out.println("fail");
    }
    return weapon;
  }
}


class DieRoll
{
  int Roll(int N)
  {
    int total = 0;
    Random rand = new Random();
    for (int count = 1; count <= N; count++)
    {
      total = total + (rand.nextInt(6) + 1);
    }
    return total;
  }
}


class MeleeWeapon
{
  public int type;
  public String name;
  public int damageDice;
  public int damageFlat;
  public int reqST;
  public int cost;
  public double weight;
  public boolean throwable;
  public int reqHands;
  public MeleeWeapon(int a, String b, int c, int d, int e, int f, double g, int h, boolean i)
  {
    type = a;
    name = b;
    damageDice = c;
    damageFlat = d;
    reqST = e;
    cost = f;
    weight = g;
    reqHands = h;
    throwable = i;
  }
  public void CheckWeapon(String string, Player p1)
  {
    if (Objects.equals(name, string))
      p1.Weapon = this;
  }
}


class ActivePlayer
{
  int PNum;
  void setPNum(int N)
  {
    PNum = N;
  }
  int getPNum()
  {
    return PNum;
  }
  void togglePNum()
  {
    if (PNum == 1)
      PNum = 2;
    else
      PNum = 1;
  }
}

  


class Player
{
  String name;
  int X;
  int Y;
  int F;
  int MA;
  int ST;
  int DX;
  int IQ;
  int HP;
  int MAUsed;
  int InitMod;
  int EnemyHasDagger;
  int InHTH;
  int HTHBonus;
  int PlayerNumber;
  int FlatDamage;
  int DiceDamage;
  int DamageTaken = 0;
  int FatigueTaken = 0;
  int XP = 0;
  MeleeWeapon Weapon = CreateMeleeWeapons.rapier;

  void ReadFromFile(String string)
  {
    string = string.concat("               ");
    if(Objects.equals((string.substring(0,3)), "ST:"))
    {
      ST = (Integer.parseInt(string.substring(3, (string.indexOf(" ")))));
    }
    else if(Objects.equals((string.substring(0,3)), "DX:"))
      DX = (Integer.parseInt(string.substring(3, (string.indexOf(" ")))));
    else if(Objects.equals((string.substring(0,3)), "IQ:"))
      IQ = (Integer.parseInt(string.substring(3, (string.indexOf(" ")))));
    else if(Objects.equals((string.substring(0,5)), "Name:"))
    {
      name = (string.substring(5, string.indexOf(".")));
    }
    else if(Objects.equals((string.substring(0,3)), "MA:"))
      MA = (Integer.parseInt(string.substring(3, (string.indexOf(" ")))));
    else if((Objects.equals((string.substring(0,7)), "Weapon:")))
    {
      Weapon = CreateMeleeWeapons.CreateThem((string.substring(7, (string.indexOf(" ")))), this);
    }
    else if (Objects.equals((string.substring(0,8)), "Tactics:"))
    {
      InitMod = (Integer.parseInt(string.substring(8, (string.indexOf(" ")))));
    }
    else if (Objects.equals((string.substring(0,4)), "Dam:"))
    {
      DamageTaken = (Integer.parseInt(string.substring(4, (string.indexOf(" ")))));
    }
    else if (Objects.equals((string.substring(0,4)), "Fat:"))
    {
      FatigueTaken = (Integer.parseInt(string.substring(4, (string.indexOf(" ")))));
    }
    else if (Objects.equals((string.substring(0,3)), "XP:"))
    {
      XP = (Integer.parseInt(string.substring(3, (string.indexOf(" ")))));
    }
  }

  void setPlayerNumber(int N)
  {
    PlayerNumber = N;
  }
  int getPlayerNumber()
  {
    return PlayerNumber;
  }
  void setInHTH(int N)
  {
    InHTH = N;
  }
  int getInHTH()
  {
    return InHTH;
  }
  void setHTHBonus(int N)
  {
    HTHBonus = N;
  }
  int getHTHBonus()
  {
    return HTHBonus;
  }
  void setInitMod(int N)
  {
    InitMod = N;
  }
  int getInitMod()
  {
    return InitMod;
  }
  void setMAUsed(int N)
  {
    MAUsed = N;
  }
  int getMAUsed()
  {
    return MAUsed;
  }
  void setX(int N)
  {
    X = N;
  }
  void setY(int N)
  {
    Y = N;
  }
  void setF(int N)
  {
    F = N;
  }
  void setMA(int N)
  {
    MA = N;
  }
  void setST(int N)
  {
    ST = N;
  }
  void setDX(int N)
  {
    DX = N;
  }
  void setIQ(int N)
  {
    IQ = N;
  }
  void setHP(int N)
  {
    HP = N;
  }
  void Damage(int N)
  {
    HP = HP - N;
  }
  int getX()
  {
    return X;
  }
  int getY()
  {
    return Y;
  }
  int getF()
  {
    return F;
  }
  int getMA()
  {
    return MA;
  }
  int getST()
  {
    return ST;
  }
  int getDX()
  {
    return DX;
  }
  int getIQ()
  {
    return IQ;
  }
  int getHP()
  {
    HP = (ST - DamageTaken);
    HP = (HP - FatigueTaken);
    return HP;
  }
  int isDead()
  {
    HP = this.getHP();
    if ((HP < 1) || ((HP == 1) && (ST > 1)))
    {
      return 1;
    }
    else
    {
      return 0;
    }
  }
}


class InitiativeCalc
{
  int InitCalc( Player p1, Player p2 )
  {
    int N = p1.getInitMod();
    int M = p2.getInitMod();
    DieRoll roll = new DieRoll();
    int A = (roll.Roll(1) + N);
    int B = (roll.Roll(1) + M);
    if (A > B)
      return 1;
    else if (B > A)
      return 2;
    else
      return InitCalc( p1, p2 );
  }
}


class enteringHTH
{
  static Player EnterHTH(Player p1, int N)
  {
    DieRoll roll = new DieRoll();
    int Roll = roll.Roll(1);
    if ((Roll == 1) || (Roll == 2))
    {
      p1.setInHTH(1);
      p1.setHTHBonus(0);
      System.out.println("You are now in HTH combat, your enemy does not have his dagger out.");
    }
    else if ((Roll == 3) || (Roll == 4))
    {
      p1.setInHTH(1);
      p1.setHTHBonus(1);
      System.out.println("You are now in HTH combat, your enemy has his dagger out.");
    }
    else if (Roll == 5)
    {
      p1.setInHTH(0);
      p1.setHTHBonus(0);
      System.out.println("You failed to enter HTH combat.");
    }
    else if(N == 1)
    {
      p1.setInHTH(0);
      p1.setHTHBonus(1);
      System.out.println("You failed to enter HTH combat, and your enemy hit you!");
    }
    else
      return EnterHTH(p1, N);
    return p1;
  }
}


class RangeFinder
{
  int Range(Player p1, Player p2)
  {
    int result = 0;
    int Z = 0;
    int X1 = p1.getX();
    int Y1 = p1.getY();
    int X2 = p2.getX();
    int Y2 = p2.getY();
    if ((X1 < X2) && (Y1 < Y2))
    {
      Z = Math.min((X2-X1), (Y2-Y1));
    }
    else if ((X1 > X2) && (Y1 > Y2))
    {
      Z = Math.min((X2-X1), (Y2-Y1));
    }
    X1 = X1 + Z;
    Y1 = Y1 + Z;
    result = (result + (Math.abs(Z)));
    result = (result + (Math.abs(X1-X2)) + (Math.abs(Y1-Y2)));
    return result;
  }
}


class EngagementHexes
{
  int X1;
  int X2;
  int X3;
  int Y1;
  int Y2;
  int Y3;
  void Set(Player p1)
  {
    if (p1.getF() == 1)
    {
      X1 = (p1.getX() + 1);
      Y1 = (p1.getY());
      X2 = (p1.getX() + 1);
      Y2 = (p1.getY() + 1);
      X3 = (p1.getX());
      Y3 = (p1.getY() + 1);
    }
    else if (p1.getF() == 2)
    {
      X1 = (p1.getX() - 1);
      Y1 = (p1.getY());
      X2 = (p1.getX() + 1);
      Y2 = (p1.getY() + 1);
      X3 = (p1.getX());
      Y3 = (p1.getY() + 1);
    }
    else if (p1.getF() == 3)
    {
      X1 = (p1.getX() - 1);
      Y1 = (p1.getY());
      X2 = (p1.getX() - 1);
      Y2 = (p1.getY() - 1);
      X3 = (p1.getX());
      Y3 = (p1.getY() + 1);
    }
    else if (p1.getF() == 4)
    {
      X1 = (p1.getX() - 1);
      Y1 = (p1.getY());
      X2 = (p1.getX() - 1);
      Y2 = (p1.getY() - 1);
      X3 = (p1.getX());
      Y3 = (p1.getY() - 1);
    }
    else if (p1.getF() == 5)
    {
      X1 = (p1.getX() + 1);
      Y1 = (p1.getY());
      X2 = (p1.getX() - 1);
      Y2 = (p1.getY() - 1);
      X3 = (p1.getX());
      Y3 = (p1.getY() - 1);
    }
    else if (p1.getF() == 6)
    {
      X1 = (p1.getX() + 1);
      Y1 = (p1.getY());
      X2 = (p1.getX() + 1);
      Y2 = (p1.getY() + 1);
      X3 = (p1.getX());
      Y3 = (p1.getY() - 1);
    }
    else
    {
      X1 = Y1 = X2 = Y2 = X3 = Y3 = 10000;
    }
  }
  int Check(Player p1)
  {
    if (((p1.getX()==X1) && (p1.getY()==Y1)) || ((p1.getX()==X2) && (p1.getY()==Y2)) || ((p1.getX()==X3) && (p1.getY()==Y3)))
    {
      return 1;
    }
    else
    {
      return 0;
    }
  }
}


class Shifter
{
  Player Shift(Player p1, Player p2)
  {
    ValidInput input = new ValidInput();
    System.out.print(p1.name + ", what side of your opponent do you want to shift to (7 = tackle)? ");
    int direction = input.Input(0,7);
    if (direction == 0)
      return p1;
    else if (direction == 1)
    {
      if((p1.getX() == (p2.getX() + 1)) && (p1.getY() == (p2.getY() + 1)))
      {
        return p1;
      }
      else if((p1.getX() == (p2.getX() + 1)) && (p1.getY() == (p2.getY())))
      {
        p1.setY((p1.getY()) + 1);
        return p1;
      }
      else if((p1.getX() == (p2.getX())) && (p1.getY() == (p2.getY() + 1)))
      {
        p1.setX((p1.getX()) + 1);
        return p1;
      }
      else
      {
        System.out.println("Please enter a valid direction");
        return Shift(p1, p2);
      }
    }
    else if (direction == 2)
    {
      if((p1.getX() == (p2.getX() + 1)) && (p1.getY() == (p2.getY() + 1)))
      {
        p1.setX((p1.getX()) - 1);
        return p1;
      }
      else if((p1.getX() == (p2.getX() - 1)) && (p1.getY() == (p2.getY())))
      {
        p1.setX((p1.getX()) + 1);
        p1.setY((p1.getY()) + 1);
        return p1;
      }
      else if((p1.getX() == (p2.getX())) && (p1.getY() == (p2.getY() + 1)))
      {
        return p1;
      }
      else
      {
        System.out.println("Please enter a valid direction");
        return Shift(p1, p2);
      }
    }
    else if (direction == 3)
    {
      if((p1.getX() == (p2.getX() - 1)) && (p1.getY() == (p2.getY() - 1)))
      {
        p1.setY((p1.getY()) + 1);
        return p1;
      }
      else if((p1.getX() == (p2.getX() - 1)) && (p1.getY() == (p2.getY())))
      {
        return p1;
      }
      else if((p1.getX() == (p2.getX())) && (p1.getY() == (p2.getY() + 1)))
      {
        p1.setX((p1.getX()) - 1);
        p1.setY((p1.getY()) - 1);
        return p1;
      }
      else
      {
        System.out.println("Please enter a valid direction");
        return Shift(p1, p2);
      }
    }
    else if (direction == 4)
    {
      if((p1.getX() == (p2.getX() - 1)) && (p1.getY() == (p2.getY() - 1)))
      {
        return p1;
      }
      else if((p1.getX() == (p2.getX() - 1)) && (p1.getY() == (p2.getY())))
      {
        p1.setY((p1.getY()) - 1);
        return p1;
      }
      else if((p1.getX() == (p2.getX())) && (p1.getY() == (p2.getY() - 1)))
      {
        p1.setX((p1.getX()) - 1);
        return p1;
      }
      else
      {
        System.out.println("Please enter a valid direction");
        return Shift(p1, p2);
      }
    }
    else if (direction == 5)
    {
      if((p1.getX() == (p2.getX() - 1)) && (p1.getY() == (p2.getY() - 1)))
      {
        p1.setX((p1.getX()) + 1);
        return p1;
      }
      else if((p1.getX() == (p2.getX() + 1)) && (p1.getY() == (p2.getY())))
      {
        p1.setY((p1.getY()) - 1);
        p1.setX((p1.getX()) - 1);
        return p1;
      }
      else if((p1.getX() == (p2.getX())) && (p1.getY() == (p2.getY() - 1)))
      {
        return p1;
      }
      else
      {
        System.out.println("Please enter a valid direction");
        return Shift(p1, p2);
      }
    }
    else if (direction == 6)
    {
      if((p1.getX() == (p2.getX() + 1)) && (p1.getY() == (p2.getY() + 1)))
      {
        p1.setY((p1.getY()) - 1);
        return p1;
      }
      else if((p1.getX() == (p2.getX() + 1)) && (p1.getY() == (p2.getY())))
      {
        return p1;
      }
      else if((p1.getX() == (p2.getX())) && (p1.getY() == (p2.getY() - 1)))
      {
        p1.setX((p1.getX()) + 1);
        p1.setY((p1.getY()) + 1);
        return p1;
      }
      else
      {
        System.out.println("Please enter a valid direction");
        return Shift(p1, p2);
      }
    }
    else
    {
      enteringHTH HTHCheck = new enteringHTH();
      p1 = HTHCheck.EnterHTH(p1, 1);
      return p1;
    }
  }
}


class Movement
{
  public Player Move(Player p1, Player p2)
  {
    Scanner scan = new Scanner( System.in );
    Shifter shift = new Shifter();
    int ActivePlayer = p1.getPlayerNumber();
    ValidInput input = new ValidInput();
    EngagementHexes engagedHexes = new EngagementHexes();
    enteringHTH nowInHTH = new enteringHTH();
    int MARemaining = p1.getMA();
    int Moving = 1;
    int newX = 0;
    int newY = 0;
    int Engaged = 0;
    int HTH = 0;
    int ActiveF = 0;
    engagedHexes.Set(p2);
    if (engagedHexes.Check(p1) == 1)
    {
      p1 = shift.Shift(p1, p2);
    }
    else 
    {
      while((MARemaining > 0) && (Moving == 1))
      {
        System.out.print(p1.name + ", what direction do you want to move in? (current position " + p1.getX() + ", " + p1.getY() + ". Your enemy is at " + p2.getX() + ", " + p2.getY() + ", and is facing direction "+ p2.getF() + ") ");
        int direction = input.Input(0,6);
        if (direction == 1)
        {
          newX = p1.getX() + 1;
          newY = p1.getY() + 1;
        }
        else if (direction == 2)
        {
          newX = p1.getX();
          newY = p1.getY() + 1;
        }
        else if (direction == 3)
        {
          newX = p1.getX() - 1;
          newY = p1.getY();
        }
        else if (direction == 4)
        {
          newX = p1.getX() - 1;
          newY = p1.getY() - 1;
        }
        else if (direction == 5)
        {
          newX = p1.getX();
          newY = p1.getY() - 1;
        }
        else if (direction == 6)
        {
          newX = p1.getX() + 1;
          newY = p1.getY();
        }
        else
        {
          System.out.print("What direction do you want to face? ");
          p1.setF(input.Input(1,6));
          p1.setMAUsed(p1.getMA() - MARemaining);
          return p1;
        }
        if ((newX == p2.getX()) && (newY == p2.getY()))
        {
          p1 = (nowInHTH.EnterHTH(p1, 1));
          Moving = 0;
          if (p1.getInHTH() == 1)
          {
            p1.setX(newX);
            p1.setY(newY);
            HTH = 1;
          }
        }
        else
        {
          p1.setX(newX);
          p1.setY(newY);
        }
        if (engagedHexes.Check(p1) == 1)
        {
          Engaged = 1;
        }
        if (Engaged == 1)
        {
          Moving = 0;
        }
        MARemaining = MARemaining - 1;
      }
    }
    if ((p1.getInHTH() == 0) && (p2.getInHTH() == 0))
    {
      System.out.print("What direction do you want to face? ");
      p1.setF(input.Input(1,6));
    }
    p1.setMAUsed(p1.getMA() - MARemaining);
    return p1;
  }
}


class ValidInput
{
  int Input( int M, int O )
  {
    Scanner scan = new Scanner( System.in );
    int N = scan.nextInt();
    if (N <= O && N >= M)
      return N;
    else
    {
      System.out.println("please enter a valid input");
      return Input( M, O );
    }
  }
}


class ITLCombatSimulator
{
  public static void main ( String[] args )
  {
    Player p1 = new Player();
    Player p2 = new Player();
    String inputFromFile;
    p1.name = "Player 1";
    p2.name = "Player 2";
    try
    {
      BufferedReader player1Input = new BufferedReader(new FileReader("Player1.txt"));
      BufferedReader player2Input = new BufferedReader(new FileReader("Player2.txt"));
    
      inputFromFile = player1Input.readLine();
      while ( inputFromFile != null)
      {
        p1.ReadFromFile(inputFromFile);
        inputFromFile = player1Input.readLine();
      }
      player1Input.close();
      inputFromFile = player2Input.readLine();
      while ( inputFromFile != null)
      {
        p2.ReadFromFile(inputFromFile);
        inputFromFile = player2Input.readLine();
      }
      player2Input.close();
    }
    catch (IOException iox)
    {
      System.out.println("fail");
    }

    p1.setPlayerNumber(1);
    p2.setPlayerNumber(2);
    ActivePlayer active = new ActivePlayer();
    Movement move = new Movement();
    Scanner scan = new Scanner( System.in );
    RangeFinder range = new RangeFinder();
    InitiativeCalc InitiativeCalculator = new InitiativeCalc();
    DieRoll roll = new DieRoll();
    ValidInput input = new ValidInput();
    int ActiveX = 0;
    int ActiveY = 0;
    int ActiveMA = 0;
    int InactiveX = 0;
    int InactiveY = 0;
    int InactiveF = 0;
    int p1Initiative = 0;
    int p2Initiative = 0;
    int Initiative = 0;
    int ActivePlayer = 0;
    int ForwardsMA = 0;
    int MoveDistance = 0;
    int Engaged = 0;
    int BackwardsMA = 0;
    int p1Dead = 0;
    int p2Dead = 0;


    System.out.println(p1.name + ":");

    System.out.print("Enter " + p1.name + "'s X coordinate: ");
    p1.setX(input.Input(-1000, 1000));

    System.out.print("Enter " + p1.name + "'s Y coordinate: ");
    p1.setY(input.Input(-1000, 1000));

    System.out.print("Enter " + p1.name + "'s Facing: ");
    p1.setF(input.Input(1,6));


    System.out.println(p2.name + ":");

    System.out.print("Enter " + p2.name + "'s X coordinate: ");
    p2.setX(input.Input(-1000, 1000));

    System.out.print("Enter " + p2.name + "'s Y coordinate: ");
    p2.setY(input.Input(-1000, 1000));

    System.out.print("Enter " + p2.name + "'s Facing: ");
    p2.setF(input.Input(1,6));




    while ((p1.isDead()==0)&&(p2.isDead()==0))
    {
      System.out.println("New Turn!");
      System.out.println("Movement:");
      if ((p1.InHTH == 0) && (p2.InHTH == 0))
      {
        Initiative = InitiativeCalculator.InitCalc(p1, p2);
        if (Initiative == 1)
        {							//Start of movement
          System.out.print(p1.name + ", who do you want to move first (" + p1.name + " is 1, " + p2.name + " is 2)? ");
        }
        else
        {
          System.out.print(p2.name + ", who do you want to move first (" + p1.name + " is 1, " + p2.name + " is 2)? ");
        }
        active.setPNum(input.Input(1, 2));
        for ( int i = 1; i <= 2; i++)
        {
          if (active.getPNum() == 1)
          {
            p1 = move.Move(p1, p2);
          }
          else
          {
            p2 = move.Move(p2, p1);
          }
          active.togglePNum();
          if ((p1.InHTH == 1) || (p2.InHTH == 1))
          {
            i = 500;
          }
        }
      }
      else
      {
        System.out.println( "Players are in HTH, skipping movement.");
      }
    }                  														    //End of movement






    if ((p1.isDead() == 1) && (p2.isDead() == 0))
    {
      System.out.println(p2.name + " is victorious!");
      if (p1.getHP() == 1)
        System.out.println(p1.name + " is unconcious, but alive!");
      else
        System.out.println(p1.name + " is dead!");
    }
    else if ((p1.isDead() == 0) && (p2.isDead() == 1))
    {
      System.out.println(p1.name + " is victorious!");
      if (p2.getHP() == 1)
        System.out.println(p2.name + " is unconcious, but alive!");
      else
        System.out.println(p2.name + " is dead!");
    }
    else if ((p1.isDead() == 1) && (p2.isDead() == 1))
    {
      System.out.println("Both players are dead. There is no victor. Great going, guys.");
    }
    else
    {
      System.out.println("Fight terminated. Both players are still alive.");
    }
  }
}

      
      











