import java.util.*;

public final class Combat {

    /* Timing‑bar labels */
    public static final String[] ZONES = {"GREEN", "YELLOW", "MISS"};

    /* Damage values for each zone */
    private static final int GREEN_DMG  = 20;
    private static final int YELLOW_DMG = 15;
    private static final int DUO_DMG = 10;
    private static final int TRIO_DMG = 100;

    /* Fields */
    private final Player player;             // never null
    private final Raccoon partner;           // null if solo
    private Raccoon enemies;     // 0–3 enemies allowed
    int enemyCount;

    private int pTurnsLeft = 5;              // presses remaining this player turn

    // Solo player constructor
    public Combat(Player player, int enemyCount) {
        this(player, null, enemyCount);
    }

    // Player + love interest vs 2 haters
    public Combat(Player player, Raccoon partner, int enemyCount) {
        if (enemyCount != 2 && enemyCount != 3)
            throw new IllegalArgumentException("Enemy count must be 2 or 3");
        this.player  = player;
        this.partner = partner;

        if (enemyCount == 2) {
            enemies = new Raccoon("Duo Enemy", 150);
        } else {
            enemies = new Raccoon("Trio Enemy", 1000);
        }
    }

//    One space‑bar press from the user:
//    zoneIdx GREEN = 0, YELLOW = 1, MISS = 2 (use the array)

    public void playerTurn(int zoneIdx) {
        if (isFinished() || zoneIdx < 0 || zoneIdx >= ZONES.length) return; //dies rip poly ending L

        // Calculate damage from Bar
        int dmg = switch (zoneIdx) {
            case 0 -> GREEN_DMG;
            case 1 -> YELLOW_DMG;
            default -> 0; // MISS
        };

        /* Apply to every living enemy */
        if (dmg > 0) {
            System.out.println(zoneIdx);
            System.out.println(dmg);
           enemies.loseAura(zoneIdx);
        }

        if (--pTurnsLeft == 0 && !isFinished()) {
            enemyTurn();
            pTurnsLeft = 5;
        }
        if(enemiesKO() || isFinished()){
            if(enemyCount == 2){
                player.badEnding();
            }
            if(enemyCount == 3){

            }
        }
    }

    // will return true if player team is dead
    public boolean isFinished() {
        boolean playerTeamDead = player.badEnding() && (partner == null || partner.isKO());
        return playerTeamDead || enemiesKO();
    }

    public boolean playerWon() { return enemiesKO(); }

    private void enemyTurn() { //cant change, js call it once it's enemies turn

        /* Build list of live targets (player & partner) */
        List<Object> targets = new ArrayList<>(); // can hold Player or Raccoon
        if (!player.badEnding()) targets.add(player);
        if (partner != null && !partner.isKO()) targets.add(partner);

        for (Raccoon enemy : enemies) {
            if (enemy.isKO()) continue;
            if (targets.isEmpty()) break; // everyone dead
            Object tgt = targets.get(rng.nextInt(targets.size()));
            if (tgt instanceof Player pl) {
                pl.loseAura(5);
                if (pl.badEnding()) targets.remove(pl);
            } else if (tgt instanceof Raccoon rc) {
                rc.loseAura(5);
                if (rc.isKO()) targets.remove(rc);
            }
        }
    }

    private boolean enemiesKO() { return enemies.getAura()==0; }

    //print data
    public void printStatus() {
        System.out.println("Player  : " + player);
        if (partner != null) System.out.println("Partner : " + partner);
        System.out.println("Enemies: " + enemies);
        System.out.println();
    }
}
