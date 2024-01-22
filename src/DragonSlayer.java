import java.util.Scanner;
public class DragonSlayer {

    private Scanner scan;
    private Sword sword;
    private Player player;
    private Room room;
    private int roomNumber;
    private int dragonScore;
    private Dragon currentDragon;
    public int currentScore;
    private int scoreHolder;

    public DragonSlayer() {
        scan = new Scanner(System.in);
    }


    public void play() {
        roomNumber = 0;
        dragonScore = 0;
        menu();
        while (player.getPlayerHealth() != 0 && roomNumber < 5) {
            enterRoom();
            roomFight();
        }
        if (roomNumber == 5) {
            System.out.println("CONGRATULATIONS YOU HAVE BEATEN THE GAME!!!");
            play();
        }

    }

    /**
     * Main Menu
     */
    public void menu() {
        System.out.println("Welcome to Dragon Slayer Hero!");
        System.out.println("---------Main Menu---------");
        System.out.println("(1) Start your adventure");
        System.out.println("(2) Manual");
        System.out.println("(3) View Top Score");
        System.out.println("(4) Quit");
        System.out.print("Pick your option Hero: ");
        String choice = scan.nextLine();
        if (choice.equals("1")) {
            startGame();
        } else if (choice.equals("2")){
            System.out.println("There are 5 floors to be defeated with dragons at different levels in them.\nDefeat every dragon in the room to win.\nOnce all rooms are defeated your hero has won the game.");// Make Game manual
            menu();
        } else if (choice.equals("3")) {
            if (currentScore > scoreHolder) {
                scoreHolder = currentScore;
            }
            System.out.println("Your top score is " + scoreHolder + " points!");
            menu();
        } else if (choice.equals("4")) {
            System.exit(0);
        }
    }

    /**
     * Starts a journey and unlocks a new game
     */
    public void startGame() {
        System.out.print("What will you Hero's name be? ");
        String name = scan.nextLine();
        sword = new Sword();
        player = new Player(name, false, sword);
        System.out.println("Welcome " + player.getName() + " here is the start of your journey!");
    }


    /**
     * Creates a new room object with randomized amount of dragons and dragon levels
     */
    public void enterRoom() {
        room = new Room(roomNumber);
        System.out.println();
        System.out.println("----------The " + room.getName() + "----------");
        System.out.println("You have entered the " + room.getName());
        if (room.getDragonAMT() == 1) {
            System.out.println("There is 1 dragon in this room");
        } else {
            System.out.println("There are " + room.getDragonAMT() + " dragon's in this room");
        }
        if (room.getDragonAMT() == 1) {
            System.out.println("Dragon 1: Level " + room.getDragon1().getLevel());
        } else if (room.getDragonAMT() == 2) {
            System.out.println("Dragon 1: Level " + room.getDragon1().getLevel());
            System.out.println("Dragon 2: Level " + room.getDragon2().getLevel());
        } else {
            System.out.println("Dragon 1: Level " + room.getDragon1().getLevel());
            System.out.println("Dragon 2: Level " + room.getDragon2().getLevel());
            System.out.println("Dragon 3: Level " + room.getDragon3().getLevel());
        }
        System.out.println("----------The " + room.getName() + "----------");
    }


    /**
     * Checks for if room has been beaten
     * Fights each dragon by order using a for loop
     * All dragons are dead floor is won
     */
    public void roomFight() {
        while (!room.isRoomWin()) {
            for (int i = 0; i < room.getDragonAMT(); i++) {
                if (room.getDragonList()[i].getDragonHealth() != 0) {
                    System.out.println();
                    System.out.println("You are battling Dragon " + (i + 1));
                    currentDragon = room.getDragonList()[i];
                    choices();
                }
            }
            room.isRoomWin();
        }
        System.out.println("------------------------Congratulations------------------------");
        System.out.println("Well done " + player.getName() + " you have defeated all the dragons.\nIt's time you move on to the next dungeon");
        roomNumber++;
        if (roomNumber > 4) {
            System.out.println("You have beaten every floor Congratulations Hero!");
            System.out.println("Your score is " + getscore() + " points");
            currentScore = getscore();
            play();
        }
        enterRoom();
    }


    /**
     * Gives the choices for the Hero to select
     */
    public void choices() {
        System.out.println("---------------Options---------------");
        System.out.println("(1) Search Room");
        System.out.println("(2) Inspect Dragon");
        System.out.println("(3) Use Health Potion");
        System.out.println("(4) Attack Dragon");
        System.out.println("(5) Check Stats");
        System.out.print("Pick an option " + player.getName() + ": ");
        String choice = scan.nextLine();
        if (choice.equals("1")) {
            searchRoom();
        } else if (choice.equals("2")) {
            inspectDragon();
        } else if (choice.equals("3")) {
            useHealthPot();
        } else if (choice.equals("4")) {
            fightDragon();
        } else if (choice.equals("5")) {
            checkHeroStats();
        }
    }


    /**
     * Check if room has been searched
     * Check for potion if not
     */
    public void searchRoom() {
        if (!room.getIsSearched()) {
            if (Math.random() <= 0.5) {
                System.out.println("You have found a health potion");
                if (player.getHealthPot()) {
                    System.out.println("You already have a health potion. You can only carry around one at a time.");
                } else {
                    player.setHealthpot(true);
                    System.out.println("HealthPotion is in your inventory use as you wish");
                }
            } else {
                System.out.println("No health potion in this room Better Luck next time!");
            }
            room.setSearched(true);
        } else {
            System.out.println("You already searched this room");
        }
        choices();
    }

    /**
     * Inspects Dragon's stats
     */
    public void inspectDragon() {
        System.out.println("The current Dragon's level is " + currentDragon.getLevel());
        System.out.println("Dragon's Health: " + currentDragon.getDragonHealth());
        choices();
    }


    /**
     * Check if has health potion
     * Uses if hero does
     * Returns half of lost health
     */
    public void useHealthPot() {
        if (player.getHealthPot()) {
            System.out.println("You are using your health potion your current health is at " + player.getPlayerHealth() + "\nThis potion will regenerate half the health you have lost");
            int playerRegen = (100 - player.getPlayerHealth()) / 2;
            playerRegen += player.getPlayerHealth();
            player.setHealth(playerRegen);
            System.out.println(player.getName() + " has " + player.getPlayerHealth() + " Health Points");
            player.setHealthpot(false);
        } else {
            System.out.println("You do not have a health potion please continue");
        }
        choices();
    }

    /**
     *  A lot of if statements for different scenarios when fighting the dragon
     *  If Player dies
     *  If Dragon dies
     *  If Dragon misses
     *  Calculate and sets the players health
     *  Calculate and sets the dragons health
     */

    public void fightDragon() {
        while (player.getPlayerHealth() != 0 && currentDragon.getDragonHealth() != 0) {
            System.out.println("----------Fight----------");
            int dragonDamage = player.playerAttack();
            System.out.println(player.getName() + " has attacked with " + dragonDamage + " damage");
            int dragonHealth = currentDragon.getDragonHealth() - dragonDamage;
            if (dragonHealth < 0) {
                dragonHealth = 0;
            }
            currentDragon.setHealth(dragonHealth);
            System.out.println("The dragon is currently at " + dragonHealth + " Health Points");
            if (currentDragon.getDragonHealth() == 0) {
                System.out.println("The Current Dragon has been defeated!!");
                dragonSlayed();
                roomFight();
            } else {
                System.out.println("CAREFUL!!! The dragon is swinging its big claws at you!!");
                int playerDamage = currentDragon.dragonAttack();
                if (player.playerDodge()) {
                    System.out.println("You dodged the dragon's attack!");
                    choices();
                } else {
                    System.out.println("The dragon attacked with " + playerDamage + " damage");
                    int playerHealth = player.getPlayerHealth() - playerDamage;
                    if (playerHealth < 0) {
                        playerHealth = 0;
                    }
                    player.setHealth(playerHealth);
                    System.out.println("You have " + player.getPlayerHealth() + " Health Points");
                    if (playerHealth == 0) {
                        System.out.println("Your hero has fallen. " + player.getName() + " has lost the game.");
                        play();
                    } else {
                        choices();
                    }
                }
            }
        }
        if (player.getPlayerHealth() == 0) {
            System.out.println();
            System.out.println("You have died a fatal death. Time to begin a new journey!");
            System.out.println("-------------------------New Game-------------------------");
            play();
        }
    }


    /**
     * Checks hero's stats and sword stats
     */
    public void checkHeroStats() {
        System.out.println(player.getName() + " is at " + player.getPlayerHealth() + " Health Points");
        System.out.println("----------Sword Stats----------");
        System.out.println("Power: " + sword.getPower() + "\nDodge: " + sword.getDodge());
        choices();
    }


    /**
     * After dragon has been slayed game randomizes option on what to get from the dragon
     * Get gold
     * Get Sword upgrade
     * Earn back health
     * Leaves empty-handed
     */
    public void dragonSlayed() {
        int choice = (int)(Math.random() * 4) + 1;
        if (choice == 1) {
            System.out.println("You have obtain gold from slaying the dragon: " + currentDragon.getDragonGold() + " gold");
            player.addGold(currentDragon.getDragonGold());
        } else if (choice == 2) {
            System.out.println("The dragon has dropped a sword upgrade let's apply it!");
            player.getSword().upgradeSword();
            System.out.println("----------Sword Stats----------");
            System.out.println("Power: " + sword.getPower() + "\nDodge: " + sword.getDodge());
        } else if (choice == 3) {
            System.out.println("You are given some health back");
            int playerLostHealth = 100 - player.getPlayerHealth();
            int addHealth = ((int)(Math.random() * playerLostHealth) + 1) / 2;
            System.out.println("Your hero has gained " + addHealth + " Health Points");
            player.regenHealth(addHealth);
            System.out.println(player.getName() + " has " + player.getPlayerHealth() + " Health Points");
        } else {
            System.out.println("Sorry you got nothing from the dragon move on!");
        }
        dragonScore += currentDragon.getLevel();
    }

    /**
     * Calculate Game Score
     */
    public int getscore() {
        int goldScore = player.getGold() * 5;
        int dragonScore = this.dragonScore * 50;
        int swordStatsScore = (player.getSword().getDodge() + player.getSword().getPower()) * 10;
        int healthScore = player.getPlayerHealth() * 10;
        return goldScore + dragonScore + swordStatsScore + healthScore;
    }
}
