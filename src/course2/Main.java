package course2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private Service service = new Service();

    public static void main(String[] args) {
        Service service = new Service();

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask(service, scanner);
                            break;
                        case 2:
                            removeTask(service, scanner);
                            break;
                        case 3:
                            getTaskByDay(service, scanner);
                            break;
                        case 4:
                            inputTask(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }


    private static void addTask(Service service, Scanner scanner) {
        System.out.println("Введите заголовок задачи ");
        String name = scanner.next();
        if (name.isBlank()) {
            throw new RuntimeException("Введены некоректные данные");
        }
        scanner.nextLine();
        System.out.println("Введите описание задачи ");
        String description = scanner.nextLine();
        if (description.isBlank()) {
            throw new RuntimeException("Введены некоректные данные");
        }
        System.out.println("Введите дату  задачи ");
        String date = scanner.nextLine();
        LocalDate dateTask = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println("Введите время задачи");
        String time = scanner.nextLine();
        LocalTime taskTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime resultDate = LocalDateTime.of(dateTask, taskTime);
        System.out.println("Введите тип задачи: личный(1) и рабочий (2)");
        int type = scanner.nextInt();
        Type taskType = type == 1 ? Type.PERSONAL : Type.WORK;

        System.out.println("Введите повторяемость задачи");
        System.out.println("0-не повторяется");
        System.out.println("1-дневная");
        System.out.println("2- недельная");
        System.out.println("3- месячная");
        System.out.println("4- годовая");

        int typeTask = scanner.nextInt();
        switch (typeTask) {
            case 0:
                service.add(new Task(name, description, taskType, resultDate));
                break;
            case 1:
                service.add(new DailyTask(name, description, taskType, resultDate));
                break;
            case 2:
                service.add(new WeeklyTask(name, description, taskType, resultDate));
                break;
            case 3:
                service.add(new MonthTask(name, description, taskType, resultDate));
                break;
            case 4:
                service.add(new YearTask(name, description, taskType, resultDate));
                break;
            default:
                throw new RuntimeException("Нет такого типа задачи!");
        }

    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
    }

    private static void removeTask(Service service, Scanner scanner) {
        System.out.println("Введите дату задачи");
        int id = scanner.nextInt();
        service.remove(id);
    }

    private static void getTaskByDay(Service service,Scanner scanner) {

        System.out.println("Введите дату задачи");
        String date = scanner.nextLine();
        LocalDate taskDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        var allTaskByDay = service.getAll(taskDate.atStartOfDay());
        System.out.println("Список задач этого дня");
        for (Task task : allTaskByDay) {
            System.out.println(task);
        }
    }

    private static void printMenu() {
        System.out.println(
                        "1. Добавить задачу 2. Удалить задачу 3. Получить задачу на указанный день 0. Выход"
        );
    }
}