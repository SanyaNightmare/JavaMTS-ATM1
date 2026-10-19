package arena;

public class Arena {
    private final Hero fighter1;
    private final Hero fighter2;
    private int roundCounter;

    public Arena(Hero fighter1, Hero fighter2) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.roundCounter = 0;
    }

    public void startTournament() {
        if (fighter1 == null || fighter2 == null) {
            System.out.println("Поединок невозможен: один из бойцов не создан.");
            return;
        }

        while (fighter1.isAlive() && fighter2.isAlive()) {
            roundCounter++;
            System.out.println("\n========== Раунд " + roundCounter + " ==========");

            Hero attacker;
            Hero defender;
            if (Math.random() < 0.5) {
                attacker = fighter1;
                defender = fighter2;
            } else {
                attacker = fighter2;
                defender = fighter1;
            }

            takeTurn(attacker, defender);
            if (!defender.isAlive()) {
                break;
            }

            takeTurn(defender, attacker);
            printStatus();
        }

        Hero winner = fighter1.isAlive() ? fighter1 : fighter2;
        if (winner.isAlive()) {
            System.out.println("\nПобедитель: " + winner.getName() + "!");
        } else {
            System.out.println("\nПоединок завершился без победителя.");
        }
    }

    private void takeTurn(Hero attacker, Hero defender) {
        ActionType action = attacker.makeTurn(defender);
        System.out.println("Игрок " + attacker.getName() + " совершил действие: " + action.getRussianName());
    }

    private void printStatus() {
        System.out.println("--- Состояние бойцов ---");
        printHeroStatus(fighter1);
        printHeroStatus(fighter2);
    }

    private void printHeroStatus(Hero hero) {
        System.out.print(hero.getName() + ": HP " + hero.getHealth() + "/" + hero.getMaxHealth());
        if (hero instanceof Warrior) {
            System.out.print(", броня " + ((Warrior) hero).getArmor());
        } else if (hero instanceof Mage) {
            Mage mage = (Mage) hero;
            System.out.print(", мана " + mage.getMana() + "/" + mage.getMaxMana());
        }
        System.out.println();
    }
}
