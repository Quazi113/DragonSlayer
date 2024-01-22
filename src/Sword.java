public class Sword {
    private int power;
    private int dodge;
    public Sword() {
        power = 10;
        dodge = 20;
    }

    /*
    Setter and getter methods
     */
    public int getPower() {
        return power;
    }

    public int getDodge() {
        return dodge;
    }

    /*
    Methods
     */

    /**
     * Takes a chance to upgrade power, dodge, or both
     */
    public void upgradeSword() {
        int random = (int)(Math.random() * 3) + 1;
        if (random == 1) {
            power += (int)(Math.random() * 5) + 1;
        } else if (random == 2) {
            dodge += (int)(Math.random() * 5) + 1;
        } else {
            power += (int)(Math.random() * 3) + 1;
            dodge += (int)(Math.random() * 3) + 1;
        }
    }



}
