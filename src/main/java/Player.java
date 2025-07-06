public class Player {

    int aura;

    public Player(){
        this.aura = 100;
    }

    //getters
    public int getAura(){
        return aura;
    }

    public void loseAura(int pts){
        aura = Math.max(aura-pts, 0);
    }
    public void gainAura(int pts){
        aura = Math.min(aura+pts, 0);
    }

    //not sure wht to do with these
    public boolean badEnding(){
        return aura ==0 ;
    }


}


