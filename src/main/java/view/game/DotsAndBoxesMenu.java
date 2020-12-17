package view.game;

import view.Back;
import view.Client;
import view.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DotsAndBoxesMenu extends Menu implements Back {
    private static final DotsAndBoxesMenu dotsAndBoxesMenu = new DotsAndBoxesMenu();

    private DotsAndBoxesMenu() {
    }

    public static DotsAndBoxesMenu getDotsAndBoxesMenu() {
        return dotsAndBoxesMenu;
    }

    @Override
    public void run() {

        joinGuest();

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
                    Client.getClient().send("game DotsAndBoxes "+ command.replaceAll(" ", "") + " " + gameID);
                    System.out.println(Client.getClient().getResponse());
                    break;
                case "back":
                    if (backConfirm())
                        break commandLoop;
                default:
                    if (command.startsWith("draw line between ")) {
                        drawLine(command);
                    } else {
                        System.out.println("Not a valid command!");
                    }
                    break;
            }
        }
        Client.getClient().send("game DotsAndBoxes end " + gameID);
        System.out.println(Client.getClient().getResponse());
        back();
    }

    private void joinGuest() {
        while (true) {
            System.out.println("Enter second player username : ");
            String username = scanner.nextLine();
            if (username.equals("back")) {
                back();
                return;
            }
            Client.getClient().send("game DotsAndBoxes join " +gameID + " " + username);
            if (Client.getClient().getResponse().equals("joined")) {
                System.out.println("Player successfully joined");
                break;
            }
            else System.out.println("No player found with this username.");
        }
    }

    private void drawLine(String command) {
        Pattern pattern = Pattern.compile("(draw line between )(-?\\d+)(,)(-?\\d+)( and )(-?\\d+)(,)(-?\\d+)");
        Matcher matcher = pattern.matcher(command);

        if (matcher.matches()) {
            if (Integer.parseInt(matcher.group(2)) < 9 && Integer.parseInt(matcher.group(2)) > 0 &&
                    Integer.parseInt(matcher.group(4)) < 9 && Integer.parseInt(matcher.group(4)) > 0 &&
                    Integer.parseInt(matcher.group(6)) < 9 && Integer.parseInt(matcher.group(6)) > 0 &&
                    Integer.parseInt(matcher.group(8)) < 9 && Integer.parseInt(matcher.group(8)) > 0) {
                Client.getClient().send("game DotsAndBoxes occupy " + gameID + " " + matcher.group(2) + " " + matcher.group(4) + " " + matcher.group(6) + " " + matcher.group(8));
                System.out.println(Client.getClient().getResponse());
            } else
                System.out.println("coordinates must be inside the table");
        } else {
            System.out.println("The command should be in this form:" +
                    "draw line between x,y and x,y");
        }
    }

    private boolean backConfirm() {
        System.out.println("Are you sure you want to leave ?" +
                "If game is not over it means your forfeit the game. yes/no : " +
                "(you can check if game is over with <show result> command)");
        while (true) {
            String temp;
            temp = scanner.nextLine();
            if (temp.equalsIgnoreCase("yes")) {
                return true;
            } else if (temp.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Its a Yes/No question :/ ");
            }
        }
    }

    @Override
    public void next(Menu menu) {
    }
}
