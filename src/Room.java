public class Room {
    private boolean isSearched;
    private boolean roomWin;
    private String name;
    private Dragon dragon1;
    private Dragon dragon2;
    private Dragon dragon3;
    private Dragon[] dragonList;
    private int dragonAMT;

    public Room(int floorLevel) {
        roomWin = false;
        isSearched = false;
        String[] roomName = {"Den","Village","Hatchery","Castle","Volcano Lair"};
        name = roomName[floorLevel];
        dragonAMT = (int) (Math.random() * 3) + 1;
        dragonList = new Dragon[dragonAMT];

        dragon1 = new Dragon();
        dragon2 = new Dragon();
        dragon3 = new Dragon();

        if (dragonAMT == 1) {
            dragonList[0] = dragon1;
        } else if (dragonAMT == 2) {
            dragonList[0] = dragon1;
            dragonList[1] = dragon2;
        } else {
            dragonList[0] = dragon1;
            dragonList[1] = dragon2;
            dragonList[2] = dragon3;
        }
    }

    /*
    Setter and Getters
     */

    public boolean getIsSearched() {
        return isSearched;
    }
    public void setSearched(boolean searched) {
        isSearched = searched;
    }
    public String getName() {
        return name;
    }
    public Dragon[] getDragonList() {
        return dragonList;
    }

    public int getDragonAMT() {
        return dragonAMT;
    }

    public Dragon getDragon1() {
        return dragon1;
    }

    public Dragon getDragon2() {
        return dragon2;
    }

    public Dragon getDragon3() {
        return dragon3;
    }


    /*
    Methods
     */

    // Return if boolean if room has been won or not
    public boolean isRoomWin() {
        if (dragonAMT == 1) {
            if (dragon1.getDragonHealth() <= 0) {
                roomWin = true;
            }
        } else if (dragonAMT == 2) {
            if (dragon1.getDragonHealth() <= 0 && dragon2.getDragonHealth() <= 0) {
                roomWin = true;
            }
        } else {
            if (dragon1.getDragonHealth() <= 0 && dragon2.getDragonHealth() <= 0 && dragon3.getDragonHealth() <= 0) {
                roomWin = true;
            }
        }
        return roomWin;
    }


}
