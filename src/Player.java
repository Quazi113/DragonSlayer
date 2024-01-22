public class Player {
    private String name;
    private int health;
    private int gold;
    private boolean healthPot;
    private Sword sword;

    public Player(String name, boolean pot, Sword sword) {
        this.name = name;
        health = 100;
        gold = 0;
        healthPot = pot;
        this.sword = sword;
    }

    /*
    Setters and Getters
     */
    public void setHealth(int health) {
        this.health = health;
    }

    public void regenHealth(int health) {
        this.health += health;
    }

    public int getPlayerHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int money) {
        gold += money;
    }

    public Sword getSword() {
        return sword;
    }
    public String getName() {
        return name;
    }

    public boolean getHealthPot() {
        return healthPot;
    }

    public void setHealthpot(boolean pot) {
        healthPot = pot;
    }


    /*
    Methods
     */
    public int playerAttack() {
        return ((int)(Math.random() * 10) + 1) * sword.getPower();
    }

    public boolean playerDodge() {
        int chance = (int)(Math.random() * 100) + 1;
        if (chance <= sword.getDodge()) {
            return true;
        }
        return false;
    }

}
