import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {





    public static void main(String[] args) {
        Service service = new Service();

        try (Scanner scanner = new Scanner(System.in)) {

            int id;
            String newName;
            String newDescription;

            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    switch (scanner.nextInt()) {
                        case 1:
                            inputTask(scanner, service);
                            System.out.println("Задача добавлена");
                            service.printMap();
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            System.out.println("Какую задачу вы хотите удалить? Введите id.");
                            id = scanner.nextInt();
                            if (service.delete(id) != null) {
                                System.out.println("Задача удалена. Список задач:");
                                service.printMap();
                            } else {
                                System.out.println("Такого id нет.");
                            }
                            System.out.println();
                            break;
                        case 3:
                            while (true) {
                                try {
                                    System.out.println("Введите день");
                                    int day = Integer.parseInt(scanner.next());
                                    System.out.println("Введите месяц (цифрой)");
                                    int month = Integer.parseInt(scanner.next());
                                    System.out.println("Введите год");
                                    int year = Integer.parseInt(scanner.next());
                                    getTodayTasks(service, LocalDate.of(year, month, day));
                                    break;
                                } catch (DateTimeException | NumberFormatException e) {
                                    System.out.println("Дата введена неправильно!");
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Введите id задачи для редактирования");
                            id = scanner.nextInt();      // todo exception
                            System.out.println("Какое будет новое название?");
                            newName = scanner.next();
                            service.getTaskById(id).setName(newName);
                            System.out.println("Какое будет новое описание?");
                            newDescription = scanner.nextLine();
                            service.getTaskById(id).setDescription(newDescription);
                            System.out.println("Задача отредактирована! Отредактированная задача:");
                            System.out.println(service.getTaskById(id));
                            break;
                        case 5:
                            service.printByDate();
                        case 0:
                            break label;
                        default:
                            System.out.println("Такого варианта нет. Введите цифру от 0 до 3");
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");

                }
            }
        }
    }



    private static void getTodayTasks(Service service, LocalDate date) {

        System.out.println("СПИСОК ЗАДАЧ:");

        Task task;

        for (int i = 1; i < Task.getCounter(); i++) {       // continue возвращает с.да на следу.щий круг
            task = service.getTaskById(i);      // [2,3,4,5,6]
            if (task == null || task.isDeleted()) {
                continue;
            }
            LocalDate taskDate = task.getDateTime().toLocalDate();

            if (task instanceof SingleTask) {       // instanceof = является лм
                if (taskDate.equals(date)) {
                    System.out.println(task);
                }
            } else if (task instanceof DailyTask) {
                if (taskDate.isBefore(date) || taskDate.equals(date)) {
                    System.out.println(task);
                }
            } else if (task instanceof WeeklyTask) {
                if (taskDate.isBefore(date) || taskDate.equals(date)) {
                    if (taskDate.getDayOfWeek() == date.getDayOfWeek()) {
                        System.out.println(task);
                    }
                }
            } else if (task instanceof MonthlyTask) {
                if (taskDate.isBefore(date) || taskDate.equals(date)) {
                    if (taskDate.getDayOfMonth() == date.getDayOfMonth()) {
                        System.out.println(task);
                    } else if (date.getDayOfMonth() < taskDate.getDayOfMonth()){
                        if (taskDate.getDayOfMonth() == 31 && date.getDayOfMonth() == 30 && date.lengthOfMonth() == 30) {
                            System.out.println(task);
                        } else if (date.getMonth() == Month.FEBRUARY && (date.getDayOfMonth() == 28 || date.getDayOfMonth() == 29)) {
                            System.out.println(task);
                        }
                    }
            } else if (task instanceof YearlyTask) {
                if (taskDate.isBefore(date) || taskDate.equals(date)) {
                    if (taskDate.getMonth() == date.getMonth() && taskDate.getDayOfMonth() == date.getDayOfMonth()) {
                        System.out.println(task);
                    } else if (taskDate.getMonth() == Month.FEBRUARY && taskDate.getDayOfMonth() == 29) {
                        if (!date.isLeapYear() && date.getMonth() == Month.FEBRUARY && date.getDayOfMonth() == 28) {
                            System.out.println(task);
                        }
                    }


                }
                }
            }

        }

        System.out.println();
    }

    private static void inputTask(Scanner scanner, Service service) {
        System.out.println("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.println("Введите описание задачи: ");
        String description = scanner.next();
        String taskType;
        while (true) {
            System.out.println("Задача личная (1) или рабочая (2): ");
            taskType = scanner.next();
            if (taskType.equals("1") || taskType.equals("2")) {
                break;
            } else {
                System.out.println("Введите 1 или 2.");
            }
        }

        String taskFrequency;
        vneshniyTsikl:
        while (true) {
            System.out.println("Задача одноразовая (1), ежедневная (2),еженедельная (3), ежемесячная (4) или ежегодная (5)?: ");
            taskFrequency = scanner.next();
            switch (taskFrequency) {
                case "1","2","3","4","5":
                    break vneshniyTsikl;
                default:
                    System.out.println("Введите 1, 2, 3, 4 или 5.");
            }
        }

        LocalDateTime dateTime;
        while (true) {
            try {
                System.out.println("Когда нужно выполнить задачу в первый раз? Введите в формате: YYYY-MM-ddThh:mm:ss");
                dateTime = LocalDateTime.parse(scanner.next());
                break;
            }
            catch (DateTimeParseException e) {
                System.out.println("Вы неправильно ввели дату! Попробуйте снова");
            }
        }


        Type type = null;

        if (taskType.equals("1")) {
            type = Type.PERSONAL;
        } else if (taskType.equals("2")) {
            type = Type.WORK;
        }

        //2023-04-25T12:00:00
        Task task = null;

        switch (taskFrequency) {
            case "1":       // задача одноразовая
                task = new SingleTask(taskName,description,type,dateTime);
                break;
            case "2":
                task = new DailyTask(taskName,description,type,dateTime);
                break;
            case "3":       // задача еженедельная
                task = new WeeklyTask(taskName,description,type,dateTime);
                break;
            case "4":
                task = new MonthlyTask(taskName,description,type,dateTime);
                break;
            case "5":
                task = new YearlyTask(taskName,description,type,dateTime);
                break;
        }

        service.add(task);  // [1,task1; 2,task2; 3,task3]
    }



    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("4. Отредактировать задачу");
        System.out.println("5. Список задач по датам");
        System.out.println("0. Выход");
    }
}