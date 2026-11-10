public class Creature extends Character {
// 
/**
 * TODO: Implement an ID system so that many creatures can exist at once, while also being organized
 */

    public Creature(int hp, int atk)
    {

        // stat block
        this.setMaxHealth(hp);
        this.setHealth(hp);
        this.setMaxMana(6);
        this.setMana(0);
        this.setDamage(atk);

        this.setArmor(0);
        this.setAlive(true);
        this.setDefending(false);

        
    }
}
