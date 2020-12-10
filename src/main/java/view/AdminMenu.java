package view;

import java.time.LocalDate;

public class AdminMenu extends Menu {
    private static final AdminMenu adminMenu = new AdminMenu();

    private AdminMenu() {
    }

    public static AdminMenu getAdminMenu() {
        return adminMenu;
    }

    private void addEvent() {
        System.out.print("Game Name (Battle Sea • Dots And Boxes): >");
        String game = scanner.nextLine().replaceAll(" ", "");

        int syear, smonth, sday;
        System.out.print("Start Year: >");
        syear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Start Month: >");
        smonth = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Start Day: >");
        sday = scanner.nextInt();
        scanner.nextLine();

        int fyear, fmonth, fday;
        System.out.print("Finish Year: >");
        fyear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Finish Month: >");
        fmonth = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Finish Day: >");
        fday = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Score: >");
        long score = scanner.nextInt();
        scanner.nextLine();

        LocalDate localDate = LocalDate.now();
        if (localDate.isAfter(LocalDate.of(syear, smonth, sday)) || localDate.isAfter(LocalDate.of(fyear, fmonth, fday))) {
            System.out.println("Invalid Start or End Date!");
        } else if (score < 1) {
            System.out.println("Score can not be zero on negative!");
        } else {
            Client.getClient().send("user addEvent " + game + " " + syear + " " + smonth + " " + sday + " " + fyear + " " + fmonth + " " + fday + " " + score);
        }

        System.out.println(Client.getClient().getResponse());
    }

    private void showEvents() {
        Client.getClient().send("user showEvents");

        if (Client.getClient().getResponse().length() > 0) {
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Empty");
        }
    }

    private void editEvent() {
        System.out.println("Event ID: >");
        long eventID = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Field: >");
        String field = scanner.nextLine().trim();
        if (field.equals("start")) {
            int syear, smonth, sday;
            System.out.print("Start Year: >");
            syear = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Start Month: >");
            smonth = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Start Day: >");
            sday = scanner.nextInt();
            scanner.nextLine();

            LocalDate localDate = LocalDate.now();
            if (localDate.isAfter(LocalDate.of(syear, smonth, sday))) {
                System.out.println("Invalid Start Date!");
            } else {
                Client.getClient().send("user editEvent " + eventID + " " + field + " " + syear + " " + smonth + " " + sday);
            }
        } else if (field.equals("finish")) {
            int fyear, fmonth, fday;
            System.out.print("Finish Year: >");
            fyear = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Finish Month: >");
            fmonth = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Finish Day: >");
            fday = scanner.nextInt();
            scanner.nextLine();

            LocalDate localDate = LocalDate.now();
            if (localDate.isAfter(LocalDate.of(fyear, fmonth, fday))) {
                System.out.println("Invalid End Date!");
            } else {
                Client.getClient().send("user editEvent " + eventID + " " + field + " " + fyear + " " + fmonth + " " + fday);
            }
        } else if (field.equals("score")) {
            System.out.print("New Score: >");
            int score = scanner.nextInt();
            scanner.nextLine();
            if (score > 0) {
                Client.getClient().send("user editEvent " + eventID + " " + score);
            } else {
                System.out.println("Score must be positive");
            }
        } else {
            System.out.println("There is no field with this label!");
        }

        System.out.println(Client.getClient().getResponse());
    }

    private void removeEvent() {
        System.out.println("Event ID: >");
        long eventID = scanner.nextLong();
        scanner.nextLine();
        Client.getClient().send("user removeEvent " + eventID);

        System.out.println(Client.getClient().getResponse());
    }

    private void addSuggestion() {
        System.out.print("Player Username: >");
        String player = scanner.nextLine().trim();
        if (player.matches("\\w+") && player.length() >= 3) {
                System.out.print("Game Name (Battle Sea • Dots And Boxes): >");
                String game = scanner.nextLine().replaceAll(" ", "");
                Client.getClient().send("user suggest " + player + " " + game);
        } else {
            System.out.println("Invalid username!");
        }

        System.out.println(Client.getClient().getResponse());
    }

    private void showSuggestions() {
        Client.getClient().send("user showSuggestions");

        if (Client.getClient().getResponse().length() > 0) {
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Empty");
        }
    }

    private void removeSuggestion() {
        System.out.println("Suggestion ID: >");
        long suggestionID = scanner.nextLong();
        scanner.nextLine();
        Client.getClient().send("user removeSuggestion " + suggestionID);

        System.out.println(Client.getClient().getResponse());
    }

    private void showUsers() {
        Client.getClient().send("user showUsers");
        System.out.println(Client.getClient().getResponse());
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

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine().trim()) {
                case "add event":
                    addEvent();
                    break;
                case "show events":
                    showEvents();
                    break;
                case "edit event":
                    editEvent();
                    break;
                case "remove event":
                    removeEvent();
                    break;
                case "add suggestion":
                    addSuggestion();
                    break;
                case "show suggestions":
                    showSuggestions();
                    break;
                case "remove suggestion":
                    removeSuggestion();
                    break;
                case "show users":
                    showUsers();
                    return;
                case "show user profile":
                    showUserProfile();
                    return;
                case "account menu":
                    next(AccountMenu.getAccountMenu());
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
