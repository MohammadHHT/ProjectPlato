package view;


public class FriendMenu extends Menu implements Back {
    private static final FriendMenu friendMenu = new FriendMenu();

    private FriendMenu() {
    }

    public static FriendMenu getFriendMenu() {
        return friendMenu;
    }

    private void showFriends() {
        Client.getClient().send("user showFriends " + username);
        if (Client.getClient().getResponse().length() > 0) {
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("0 friends");
        }
    }

    private void removeFriend() {
        System.out.print("Friend Username: >");
        String username = scanner.nextLine().trim();
        if (username.matches("\\w+") && username.length() >= 3) {
            Client.getClient().send("user removeFriend " + username);
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Invalid username!");
        }
    }

    private void showUserProfile() {
        System.out.println("Username: >");
        String user = scanner.nextLine().trim();
        if (user.matches("\\w+") && user.length() >= 3) {
            Client.getClient().send("user showUserProfile " + user);
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Invalid username!");
        }
    }

    private void addFriend() {
        System.out.print("Friend Username: >");
        String friend = scanner.nextLine().trim();
        if (friend.matches("\\w+") && friend.length() >= 3 && !friend.equals(username)) {
            Client.getClient().send("user addFriend " + username + " " + friend);
        } else {
            System.out.println("Invalid username!");
        }

        System.out.println(Client.getClient().getResponse());
    }

    private void showRequests() {
        Client.getClient().send("user showRequests " + username);
        if (Client.getClient().getResponse().length() > 0) {
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Empty");
        }
    }

    private void accept() {
        System.out.println("Username: >");
        String friend = scanner.nextLine().trim();
        if (friend.matches("\\w+") && friend.length() >= 3) {
            Client.getClient().send("user accept " + username + " " + friend);
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Invalid username!");
        }
    }

    private void decline() {
        System.out.println("Username: >");
        String friend = scanner.nextLine().trim();
        if (friend.matches("\\w+") && friend.length() >= 3) {
            Client.getClient().send("user decline " + username + " " + friend);
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Invalid username!");
        }
    }

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine().trim()) {
                case "show friends":
                    showFriends();
                    break;
                case "remove friend":
                    removeFriend();
                    break;
                case "show user profile":
                    showUserProfile();
                    break;
                case "add friend":
                    addFriend();
                    break;
                case "show requests":
                    showRequests();
                    break;
                case "accept":
                    accept();
                    break;
                case "decline":
                    decline();
                    return;
                case "primary menu":
                    if (rank == Rank.PLAYER) {
                        next(PlayerMenu.getPlayerMenu());
                    } else {
                        next(AdminMenu.getAdminMenu());
                    }
                    break;
                case "back":
                    back();
                    return;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {
        menus.push(menu);
        menu.run();
    }
}
