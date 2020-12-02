package view;


public class FriendsMenu extends Menu {

    private int nextMenu;

    @Override
    public void run() {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("view account menu")) {
                nextMenu = 0;
                break;
            } else if (command.equalsIgnoreCase("back")) {
                nextMenu = 1;
                break;
            } else
                commandProcessor(command);
        }
        next();
    }

    private void showFriends() {
        Client.getClient().send("user showFriends");
        System.out.println(Client.getClient().getResponse());
    }

    private void remove(String username) {
        Client.getClient().send("user removeFriend " + username);
        System.out.println(Client.getClient().getResponse());
    }

    private void viewUserProfile(String username) {
        Client.getClient().send("user viewFriendProfile " + username);
        System.out.println(Client.getClient().getResponse());
    }

    private void add(String username) {
        Client.getClient().send("user addFriend " + username);
        System.out.println(Client.getClient().getResponse());
    }

    private void showFriendRequests() {
        Client.getClient().send("user showFriendRequests");
        System.out.println(Client.getClient().getResponse());
    }

    private void accept(String username) {
        Client.getClient().send("user acceptFriend " + username);
        System.out.println(Client.getClient().getResponse());
    }

    private void decline(String username) {
        Client.getClient().send("user declineFriend " + username);
        System.out.println(Client.getClient().getResponse());
    }

    private void commandProcessor(String command) {
        String[] splitCommand = command.trim().split("\\s");
        switch (splitCommand[0].toLowerCase()) {
            case "show":
                if (command.equals("show friends")) {
                    showFriends();
                } else if (command.equals("show friend requests")) {
                    showFriendRequests();
                } else {
                    System.out.println("The command is Wrong !");
                }
            case "remove" :
                if (splitCommand.length == 2)
                    remove(splitCommand[1]);
                else
                    System.out.println("The command is Wrong !");
            case "view" :
                if (splitCommand.length == 4 && splitCommand[1].equals("user") && splitCommand[2].equals("profile")) {
                    viewUserProfile(splitCommand[3]);
                } else
                    System.out.println("The command is Wrong !");
            case "add" :
                if (splitCommand.length == 2) {
                    add(splitCommand[1]);
                } else
                    System.out.println("The command is Wrong !");
            case "accept" :
                if (splitCommand.length == 2) {
                    accept(splitCommand[1]);
                } else
                    System.out.println("The command is Wrong !");
            case "decline" :
                if (splitCommand.length == 2) {
                    decline(splitCommand[1]);
                } else
                    System.out.println("The command is Wrong !");
        }
    }

    @Override
    public void next() {

    }
}
