package src.MysticMayhem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

    private ArrayList<String> army = new ArrayList<>();
    private int[] warriorStatus = {0, 0, 0, 0, 0};

    public void openShop(Player activePlayer){
        System.out.println("Hello "+activePlayer.getName()+"!");
        System.out.println("You have " + activePlayer.getGold() + "gold coins with you.\n");
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.print("\nWhat would you like to do?: \n " +
                    "1. Buy a Warrior\n" +
                    "2. Sell a Warrior\n" +
                    "3. Buy Equipment\n" +
                    "4. Sell Equipment \n" +
                    "5. Exit the Shop\n");
            System.out.print("Enter (1-6): ");
            int choice = sc.nextInt();
            if(choice == 1){
                buyWarrior();
            }else if(choice == 2){
                sellWarrior();
            }else if(choice == 3){
                buyEquipment();
            }else if(choice == 4){
                sellEquipment(activePlayer);
            }if(choice == 5 && !checkPlayerArmy(activePlayer) ) {
                System.out.println("Your Army is incomplete!");
                ArrayList<String> neededWarriors = getRamainingArmy(activePlayer);
                System.out.println("Your army lacks following warrior types: ");
                for (int i=0; i < neededWarriors.size(); i++){
                    System.out.println(i+1 + "." + neededWarriors.get(i)+"\n");
                }
            }else if (choice == 5){
                break;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }

    public boolean checkPlayerArmy(Player player){
//        if (player.getArcher().isActive()
//                && player.getArcher().isActive()
//                && player.getKnight().isActive()
//                && player.getMage().isActive()
//                && player.getHealer().isActive()
//                && player.getMythicalCreature().isActive()) return true;
        if (warriorStatus[0] == 1
                && warriorStatus[1] == 1
                && warriorStatus[2] == 1
                && warriorStatus[3] == 1
                && warriorStatus[4] == 1) return true;
        else return false;
    }

    public ArrayList<String> getRamainingArmy(Player player){
        ArrayList<String> neededWarriors = new ArrayList<>();
//        if (!player.getArcher().isActive()) neededWarriors.add("Archer");
//        if (!player.getKnight().isActive()) neededWarriors.add("Knight");
//        if (!player.getMage().isActive()) neededWarriors.add("Mage");
//        if (!player.getHealer().isActive()) neededWarriors.add("Healer");
//        if (!player.getMythicalCreature().isActive()) neededWarriors.add("Mythical creature");
        if (warriorStatus[0] == 0) neededWarriors.add("Archer");
        if (warriorStatus[1] == 0) neededWarriors.add("Knight");
        if (warriorStatus[2] == 0) neededWarriors.add("Mage");
        if (warriorStatus[3] == 0) neededWarriors.add("Healer");
        if (warriorStatus[4] == 0) neededWarriors.add("Mythical creature");
        return neededWarriors;
    }

    public void printShopItems(int n){
        String warriorType = GameFiles.getWarriorType(n);
        ArrayList<String> warriorNames = GameFiles.getNamesOfWarriors(n);
        System.out.println("    ╔════════════════╦══════════╦══════════╦══════════╦══════════╦══════════╗");
        System.out.println("    ║     Type       ║  Price   ║  Attack  ║ Defence  ║  Health  ║  Speed   ║");
        System.out.println("╔═══╠════════════════╬══════════╬══════════╬══════════╬══════════╬══════════╣");


        for (int j = 0; j < 5; j++) {
            String warriorName = warriorNames.get(j); // Use j instead of i
            System.out.printf("║%3s║%16s║%10s║%10.1f║%10.1f║%10.1f║%10.1f║%n",
                    j+1+"." , warriorName,
                    GameFiles.getWarriorDetail(warriorName, 0)+ " gc",
                    GameFiles.getWarriorDetail( warriorName, 1),
                    GameFiles.getWarriorDetail( warriorName, 2),
                    GameFiles.getWarriorDetail( warriorName, 3),
                    GameFiles.getWarriorDetail( warriorName, 4)
            );
        }
        System.out.println("╚═══╩════════════════╩══════════╩══════════╩══════════╩══════════╩══════════╝");
    }

    public void buyWarrior(Player buyer){
        int[] minPrices = {80, 85, 100, 95, 120};

        while(true) {
            System.out.println("You can buy the following warrior types. ");
            System.out.println("1. Archer\n" +
                    "2. Knight\n" +
                    "3. Mage\n" +
                    "4. Healer\n" +
                    "5. Mythical Creature\n" +
                    "6. Exit the Shop\n");
            System.out.println("Which warrior do you want to buy? : ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if (choice<1 || choice>6){
                System.out.println("Invalid Input");
                continue;
            } else if (minPrices[choice-1] > buyer.getGold()) {
                System.out.println("You do not have enough gold coins to purchase from this warrior type\n");
                return;
            } else if (choice == 6) {
                return;
            } else {
                printShopItems(choice-1);
                String warriorType = GameFiles.getWarriorType(choice-1);
                while (true){
                    System.out.println("\nSelect your "+warriorType+"! (Choose number):");
                    System.out.println("\nEnter 6 to buy a differet type of warrior.");
                    int userSelection = sc.nextInt();
                    if (userSelection > 6 || userSelection < 1)
                        System.out.println("Enter an integer between 1 and 5.\n");
                    else if (userSelection == 6) continue;
                    else {
                        army.add(choice-1,warriorType);
                        ArrayList<String> warriorNames = GameFiles.getNamesOfWarriors(choice-1);
                        String warriorName = warriorNames.get(userSelection-1);
                        buyer.setGold(-(int) GameFiles.getWarriorDetail(warriorName, 0));
                        warriorStatus[choice-1] = 1;
                    }
                }
            }
        }
    }

    public void sellWarrior(Player player){
        if (army.size() == 0){
            System.out.println("You do not have any warriors to sell.");
            return;
        }
        System.out.println("You have the following warriors to sell: ");
        for (int i=0; i<army.size(); i++){
            System.out.println(i+1 + ". " + army.get(i));
        }
        System.out.println("Which warrior do you want to sell? : ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice<1 || choice>army.size()){
            System.out.println("Invalid Input");
            return;
        }
        String warriorType = army.get(choice-1);
        ArrayList<String> warriorNames = GameFiles.getNamesOfWarriors(choice-1);
        printShopItems(choice-1);
        System.out.println("\nSelect your "+warriorType+"! (Choose number):");
        int userSelection = sc.nextInt();
        if (userSelection > 6 || userSelection < 1)
            System.out.println("Enter an integer between 1 and 5.\n");
        else {
            String warriorName = warriorNames.get(userSelection-1);
            player.setGold((int) (0.9*GameFiles.getWarriorDetail(warriorName, 0)));
            army.remove(choice-1);
            warriorStatus[choice-1] = 0;
        }

    }

    public static void main(String[]args){
        Player forNow = new Player("test", "Madhawa", 500, 2, 10000);
        new Shop().openShop(forNow);
    }

}
