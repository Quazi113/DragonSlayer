public class Dragon {
    private int health;
    private int level;
    private int gold;

    public Dragon() {
        level = (int)(Math.random() * 3) + 1;
        health = 100;
        gold = (int)(Math.random() * 30) * level;
    }

    /**
     * Setter and Getter Methods
     */
    public int getDragonGold() {
        return gold;
    }
    public int getLevel() {
        return level;
    }
    public int getDragonHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    /*
     * Methods
     */
    // Gets dragon attack output
    public int dragonAttack() {
        return ((int)(Math.random() * 10) + 1) * level;
    }

}
