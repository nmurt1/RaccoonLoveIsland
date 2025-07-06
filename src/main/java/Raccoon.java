import java.util.*;
public class Raccoon {

    private String name; // printed label in UI/console
    private int aura;
    private static final int ATTACK_DAMAGE = 10;
    boolean isEnemy = true;
    boolean isLoveInterest = false;

    public Raccoon(String name, int aura) {
        this.name = name;
        this.aura = aura;
    }

    //performing an attack on player
    public void attack(Player player, int a){//ensure the target is the player and it's aura
        if(isEnemy) {
            player.loseAura(ATTACK_DAMAGE);
        }
    }

    public boolean isKO(){ //for when the enemies aura reachs 0
        return aura ==0 ;
    }

    /* Nice toString for debug / console printouts */
    @Override public String toString() {
        return name + " (" + aura + " aura)";
    }

    public static void setLoveInterest(String id){
        //idk
        //isLoveInterest = true;
    }

    //returns
    public String getName(){
        return name;
    }

    public int getAura(){
        return aura;
    }

    public int getAttackDamage(){
        return ATTACK_DAMAGE;
    }

    public void loseAura(int pts){
        aura = Math.max(aura-pts, 0);
    }

    public boolean noAura(){
        return aura == 0;
    }

}
