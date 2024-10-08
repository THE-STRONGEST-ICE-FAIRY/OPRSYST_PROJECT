import java.util.LinkedList;
import java.util.Scanner;

public class RoundRobin {
    Scanner sc = new Scanner(System.in);
    LinkedList<Program> programs;

    RoundRobin() {
        programs = new LinkedList<>();

        programs.add(new Program("Program 1", 0, 5));
        programs.add(new Program("Program 2", 2, 9));
        programs.add(new Program("Program 3", 6, 1));
        programs.add(new Program("Program 4", 12, 2));
        programs.add(new Program("Program 5", 1, 10));

        MainMenu();
    }

    void MainMenu() {
        System.out.println();
        System.out.println();
        System.out.println("Round Robin");
        System.out.println("1. Edit Programs");
        System.out.println("2. View Results");
        System.out.println("3. Exit Program");

        String line;
        do {
            System.out.print("\n > ");
            line = sc.nextLine();

        } while (!(line.equals("1") || line.equals("2") || line.equals("3")));
        System.out.println();

        switch (line) {
            case "1" -> EditPrograms();
            case "2" -> ViewResults();
            case "3" -> {
                System.out.println("This project is made thanks to your mom.");
                System.out.println("Thank her for everything she has done for you.");
            }
        }
    }

    void EditPrograms() {
        String line;
        do {
            System.out.println("Edit Programs");
            System.out.println("Program List: (" + programs.size() + ")");
            System.out.println("+-----+----------------+----------------+----------------+");
            System.out.println("| #   | NAME           | TIME IN        | DURATION       |");
            int limit = 14;
            for (int i = 0; i < programs.size(); i++) {
                String substring = Integer.toString(i).substring(0, Math.min(3, Integer.toString(i).length()));
                System.out.println(
                        "| " + substring + " ".repeat(3 - substring.length()) +
                        " | " + programs.get(i).getName(limit) +
                        " | " + programs.get(i).getTimeIn(limit) +
                        " | " + programs.get(i).getDuration(limit) + " |");
            }
            System.out.println();
            System.out.println("1. Add a Program");
            System.out.println("2. Edit a Program");
            System.out.println("3. Delete a Program");
            System.out.println("4. Return to Main Menu");

            do {
                System.out.print("\n > ");
                line = sc.nextLine();

            } while (!(line.equals("1") || line.equals("2") || line.equals("3") || line.equals("4")));

            switch (line) {
                case "1" -> {
                    System.out.println();
                    programs.add(new Program(sc));
                }
                case "2" -> {
                    System.out.print("\nEnter the number of your program: ");
                    int number = Integer.parseInt(sc.nextLine());
                    programs.get(number).edit(sc);
                }
                case "3" -> {
                    System.out.print("\nEnter the number of your program: ");
                    int number = Integer.parseInt(sc.nextLine());
                    programs.remove(number);
                }
            }
            System.out.println();
        } while (!line.equals("4"));

        MainMenu();
    }

    void ViewResults() {
        String line;
        do {
            System.out.println("View Results");

            int timeAllocated;
            do {
                System.out.print("Set Time Allocation: ");
                line = sc.nextLine();
                timeAllocated = Integer.parseInt(line);

            } while (timeAllocated < 1);
            System.out.println();

            new Gantt(programs, timeAllocated);

            System.out.println();
            System.out.println("1. Change Time Allocation");
            System.out.println("2. Return to Main Menu");

            do {
                System.out.print("\n > ");
                line = sc.nextLine();

            } while (!(line.equals("1") || line.equals("2")));
            System.out.println();
        } while (!line.equals("2"));

        MainMenu();
    }
}