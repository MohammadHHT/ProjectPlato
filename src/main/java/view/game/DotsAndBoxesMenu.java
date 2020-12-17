package view.game;

import view.Client;
import view.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DotsAndBoxesMenu extends Menu {
    private static final DotsAndBoxesMenu dotsAndBoxesMenu = new DotsAndBoxesMenu();

    private DotsAndBoxesMenu() {
    }

    public static DotsAndBoxesMenu getDotsAndBoxesMenu() {
        return dotsAndBoxesMenu;
    }

    @Override
    public void run() {
        String command;
        commandLoop:
        while (true) {
            command = scanner.nextLine();
            switch (command) {
                case "end of my turn":
                case "show score":
                case "show available lines":
                case "show table":
                case "who is next?":
                case "show result":
                    Client.getClient().send("Game DotsAndBoxes " + command);
                    System.out.println(Client.getClient().getResponse());
                    break;
                case "back":
                    System.out.println("Are you sure you want to leave ?" +
                            "If game is not over it means your forfeit the game. yes/no : ");
                    while (true) {
                        String temp;
                        temp = scanner.nextLine();
                        if (temp.equalsIgnoreCase("yes")) {
                            break commandLoop;
                        } else if (temp.equalsIgnoreCase("no")) {
                            break;
                        } else {
                            System.out.println("Its a Yes/No question :/ ");
                        }
                    }
                default:
                    if (command.startsWith("draw line between ")) {

                        Pattern pattern = Pattern.compile("(draw line between )(\\d+)(,)(\\d+)( and )(\\d+)(,)(\\d+)");
                        Matcher matcher = pattern.matcher(command);

                        if (matcher.matches()) {
                            if (Integer.parseInt(matcher.group(2)) < 9 && Integer.parseInt(matcher.group(2)) > 0 &&
                                    Integer.parseInt(matcher.group(4)) < 9 && Integer.parseInt(matcher.group(4)) > 0 &&
                                    Integer.parseInt(matcher.group(6)) < 9 && Integer.parseInt(matcher.group(6)) > 0 &&
                                    Integer.parseInt(matcher.group(8)) < 9 && Integer.parseInt(matcher.group(8)) > 0) {
                                Client.getClient().send("Game DotsAndBoxes draw " + matcher.group(2) + " " + matcher.group(4) + " " + matcher.group(6) + " " + matcher.group(8));
                            } else
                                System.out.println("coordinates must be inside the table");
                        } else {
                            System.out.println("The command should be in this form:" +
                                    "draw line between x,y and x,y");
                        }
                    } else {
                        System.out.println("Not a valid command!");
                    }
                    break;
            }
        }
        Client.getClient().send("Game DotsAndBoxes end");
        System.out.println(Client.getClient().getResponse());
        next(Menu.menus.peek());
    }

    @Override
    public void next(Menu menu) {
        menu.run();
    }
}
