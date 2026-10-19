package arena;

public class Warrior extends Hero implements Castable, Restable {
    private final int armor;

    public Warrior(String name, int maxHealth, int baseAttack, int armor) {
        super(name, maxHealth, baseAttack);
        this.armor = Math.max(0, armor);
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public void takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) {
            return;
        }
        int finalDamage = Math.max(1, damage - armor);
        super.takeDamage(finalDamage);
    }

    @Override
    public void attack(Hero target) {
        if (target == null || !target.isAlive()) {
            return;
        }
        target.takeDamage(getBaseAttack());
        System.out.println(getName() + " наносит физический удар (" + getBaseAttack() + " урона).");
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
        return getHealth() < getMaxHealth() * 0.5;
    }

    @Override
    public void castSpecialSkill(Hero target) {
        if (target == null || !target.isAlive()) {
            return;
        }
        if (canCast()) {
            int damage = getBaseAttack() + armor * 2;
            target.takeDamage(damage);
            System.out.println(getName() + " использует удар щитом и наносит " + damage + " урона!");
        } else {
            System.out.println(getName() + " попытался ударить щитом, но потерял равновесие!");
        }
    }

    @Override
    public boolean needsRest() {
        return getHealth() < getMaxHealth() * 0.15;
    }

    @Override
    public void rest() {
        int restored = armor * 2;
        heal(restored);
        System.out.println(getName() + " использует второе дыхание и восстанавливает " + restored + " здоровья.");
    }
}
