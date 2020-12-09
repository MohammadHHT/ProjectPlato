package view;

import java.time.LocalDate;

public class AdminMenu extends Menu implements Back {
    private static final AdminMenu adminMenu = new AdminMenu();

    private AdminMenu() {
    }

    public static AdminMenu getAdminMenu() {
        return adminMenu;
    }

    private void addEvent() {
        System.out.print("Game Name: >");
        String game = scanner.nextLine().trim();

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
            System.err.println("Invalid Start or End Date!");
        } else if (score < 1) {
            System.err.println("Score can not be zero on negative");
        } else {
            Client.getClient().send("User addEvent " + game + " " + syear + " " + smonth + " " + sday + " " + fyear + " " + fmonth + " " + fday + " " + score);
        }
    }

    private void showEvents() {
        Client.getClient().send("User showEvents");
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
                System.err.println("Invalid Start Date!");
            } else {
                Client.getClient().send("User editEvent " + eventID + " " + field + " " + syear + " " + smonth + " " + sday);
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
                System.err.println("Invalid End Date!");
            } else {
                Client.getClient().send("User editEvent " + eventID + " " + field + " " + fyear + " " + fmonth + " " + fday);
            }
        } else if (field.equals("score")) {
            System.out.print("New Score: >");
            int score = scanner.nextInt();
            scanner.nextLine();
            if (score > 0) {
                Client.getClient().send("User editEvent " + eventID + " " + score);
            } else {
                System.err.println("Score must be positive");
            }
        } else {
            System.err.println("There is no field with this label");
        }
    }

    private void removeEvent() {
        System.out.println("Event ID: >");
        long eventID = scanner.nextLong();
        scanner.nextLine();
        Client.getClient().send("User removeEvent " + eventID);
    }

    private void suggest() {
        System.out.print("Player Name: >");
        String player = scanner.nextLine().trim();
        if (player.matches("\\w+")) {
            if (player.length() >= 3) {
                System.out.print("Game Name: >");
                String game = scanner.nextLine().trim();
                Client.getClient().send("User suggest " + player + " " + game);
            } else {
                System.err.println("Username must be at least 3 characters");
            }
        } else {
            System.err.println("Username includes alphanumeric characters only");
        }
    }

    private void showSuggestions() {
        Client.getClient().send("User showSuggestions");
    }

    private void removeSuggestion() {
        System.out.println("Suggestion ID: >");
        long sugID = scanner.nextLong();
        scanner.nextLine();
        Client.getClient().send("User removeSuggestion " + sugID);
    }

    private void showUsers() {
        Client.getClient().send("User showUsers");
    }

    private void showUserProfile() {
        System.out.println("User Name: >");
        String user = scanner.nextLine().trim();
        Client.getClient().send("User showUserProfile " + user);
    }

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine()) {
                case "addEvent":
                    addEvent();
                    break;
                case "showEvents":
                    showEvents();
                    break;
                case "editEvent":
                    editEvent();
                    break;
                case "removeEvent":
                    removeEvent();
                    break;
                case "suggest":
                    suggest();
                    break;
                case "showSuggestions":
                    showSuggestions();
                    break;
                case "removeSuggestion":
                    removeSuggestion();
                    break;
                case "showUsers":
                    showUsers();
                    return;
                case "showUserProfile":
                    showUserProfile();
                    return;
                case "accountMenu":
                    next(AccountMenu.getAccountMenu());
                    return;
                default:
                    System.err.println("Incorrect command");
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {
        push(menu);
        menu.run();
    }
}
