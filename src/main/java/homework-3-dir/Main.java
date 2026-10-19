package arena;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Создание первого бойца ===");
        Hero fighter1 = createHero(scanner);
        System.out.println("\n=== Создание второго бойца ===");
        Hero fighter2 = createHero(scanner);

        Arena arena = new Arena(fighter1, fighter2);
        arena.startTournament();
        scanner.close();
    }

    private static Hero createHero(Scanner scanner) {
        System.out.println("Выберите персонажа: 1 — Воин, 2 — Маг");
        int type = readInt(scanner);
        System.out.print("Имя: ");
        String name = scanner.nextLine();

        System.out.print("Максимальное здоровье: ");
        int maxHealth = readInt(scanner);
        System.out.print("Базовая атака: ");
        int baseAttack = readInt(scanner);

        if (type == 1) {
            System.out.print("Броня: ");
            int armor = readInt(scanner);
            return new Warrior(name, maxHealth, baseAttack, armor);
        }

        if (type == 2) {
            System.out.print("Максимальная мана: ");
            int maxMana = readInt(scanner);
            return new Mage(name, maxHealth, baseAttack, maxMana);
        }

        System.out.println("Неизвестный тип. Будет создан Воин.");
        System.out.print("Броня: ");
        int armor = readInt(scanner);
        return new Warrior(name, maxHealth, baseAttack, armor);
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите целое число:");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
