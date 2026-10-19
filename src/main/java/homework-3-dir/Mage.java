package arena;

public class Mage extends Hero implements Castable, Restable {
    private final int maxMana;
    private int mana;

    public Mage(String name, int maxHealth, int baseAttack, int maxMana) {
        super(name, maxHealth, baseAttack);
        this.maxMana = Math.max(0, maxMana);
        this.mana = this.maxMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    @Override
    public void attack(Hero target) {
        if (target == null || !target.isAlive() || !isAlive()) {
            return;
        }
        if (mana >= 10) {
            int damage = getBaseAttack() * 2;
            mana -= 10;
            target.takeDamage(damage);
            System.out.println(getName() + " выпускает магический выстрел (" + damage + " урона), мана: " + mana + ".");
        } else {
            int damage = getBaseAttack() / 2;
            target.takeDamage(damage);
            mana = Math.min(maxMana, mana + 5);
            System.out.println(getName() + " бьёт посохом (" + damage + " урона) и восстанавливает 5 маны. Мана: " + mana + ".");
        }
    }

    @Override
    public ActionType makeTurn(Hero target) {
        if (canCast()) {
            castSpecialSkill(target);
            return ActionType.SPECIAL_SKILL;
        }
        if (needsRest()) {
            rest();
            return ActionType.REST;
        }
        attack(target);
        return ActionType.BASE_ATTACK;
    }

    @Override
    public boolean canCast() {
        return mana >= 25;
    }

    @Override
    public void castSpecialSkill(Hero target) {
        if (mana < 25) {
            System.out.println(getName() + " не смог применить огненную глыбу: недостаточно маны.");
            return;
        }
        if (target == null || !target.isAlive()) {
            return;
        }
        int damage = getBaseAttack() * 3;
        mana -= 25;
        target.takeDamage(damage);
        System.out.println(getName() + " применяет огненную глыбу (" + damage + " урона), мана: " + mana + ".");
    }

    @Override
    public boolean needsRest() {
        return mana == 0 || getHealth() < getMaxHealth() * 0.30;
    }

    @Override
    public void rest() {
        mana = maxMana;
        heal(getBaseAttack());
        System.out.println(getName() + " медитирует: мана восстановлена до " + mana
                + ", здоровье восстановлено на " + getBaseAttack() + ".");
    }
}
