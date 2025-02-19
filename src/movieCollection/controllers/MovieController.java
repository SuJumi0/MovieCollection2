package movieCollection.controllers;

import movieCollection.*;

import java.util.*;

public class MovieController {
    Scanner scanner;
    private Map<Long, Movie> movieMap;

    public void setMovieMap(Map<Long, Movie> movieMap) { //Дарья
        this.movieMap = movieMap;
    }

    public MovieController() {// конструктор
        scanner = new Scanner(System.in);
        this.movieMap = new HashMap<>();
    }

    public void addMovie(Movie movie) { // метод для добавления фильмов в HashMap. Не работает ((
        movieMap.put(movie.getId(), movie);
    }

    Person person1 = new Person("Инна Веткина", false);
    Person person2 = new Person("Евгений Велтистов", true);
    Person person3 = new Person("Люк Бессон", true);
    Person person4 = new Person("David Fincher", true);
    Person person5 = new Person("Ryan Murphy", true);
    Person person6 = new Person("Fatih Akin", true);
    Person person7 = new Person("Tim Burton", true);
    Person person8 = new Person("Александр Войтинский", true);
    Movie movie1 = new Movie("Приключения Буратино", MovieGenre.ACTION, person1);
    Movie movie2 = new Movie("Приключения Электроника", MovieGenre.ADVENTURE, person2);
    Movie movie3 = new Movie("Люси", MovieGenre.ACTION, person3);
    Movie movie4 = new Movie("Gone Girl", MovieGenre.TRAGEDY, person4);
    Movie movie5 = new Movie("Dahmer-Monster", MovieGenre.HORROR, person5);
    Movie movie6 = new Movie("Gegen die Wand", MovieGenre.TRAGEDY, person6);
    Movie movie7 = new Movie("Die Insel der besonderen Kinder", MovieGenre.FANTASY, person7);
    Movie movie8 = new Movie("По щучъему велению.", MovieGenre.FANTASY, person8);



    public void startIntro() { //начальное сообщение с приглашением к выбору команды
        System.out.println(
                "Выберите команду из списка. Для получения справки по доступным командам выберите help.\n\n" +
                        "help\n" +
                        "info\n" +
                        "show\n" +
                        "insert\n" +
                        "update {id}\n" +
                        "remove_key {id}\n" +
                        "clear\n" +
                        "exit\n" +
                        "remove_greater {fieldValue}\n" +
                        "remove_lower {fieldValue}\n" +
                        "count_less_than_genre {genre}\n ");
        System.out.print("Введите выбранную команду: ");

    }

    public void startHelpCommand() {//Екатерина
        System.out.println(
                "info : вывести информацию о коллекции\n" +
                        "show : вывести все элементы коллекции\n" +
                        "insert: добавить новый элемент \n" +
                        "update {id} : обновить значение элемента коллекции, id которого равен заданному\n" +
                        "remove_key {id} : удалить элемент из коллекции по его ключу\n" +
                        "clear : очистить коллекцию\n" +
                        "exit : завершить программу (без сохранения в файл)\n" +
                        "remove_greater {fieldValue} : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
                        "remove_lower {fieldValue} : удалить из коллекции все элементы, ключ которых меньше, чем заданный\n" +
                        "count_less_than_genre {genre}: вывести количество элементов, значение поля genre которых меньше заданного\n");
    }

    public void startInfoCommand() {//Екатерина

        System.out.println("Коллекция содержит: " + this.movieMap.size() + " элементов\n");
    }

    public void startShowCommand() {
        //Екатерина
        System.out.println("Все элементы:");
        for (Movie movie : this.movieMap.values()) {
            System.out.println(movie);
        }
    }

    //добавление Фильма с построковой задачей полей
    public void startInsertCommand() {
        System.out.println("Добавить фильм");

        Person person = new Person(addPersonName(), addPersonGender());
        Movie movie1 = new Movie(addMovieName(), addMovieGenre(), person);
        movieMap.put(movie1.getId(), movie1);

        System.out.println("Фильм добавлен в коллекцию");
    }

    //подполя для получения аргументов
    private String addMovieName() {
        String movieName;
        do {
            System.out.println("Задайте название фильма: ");
            movieName = scanner.nextLine();
            if (movieName.equals("")) {
                System.out.println("Строка не может быть пустой");
                continue;
            } else {
                return movieName;
            }
        } while (true);
    }

    private MovieGenre addMovieGenre() {
        String lineIn;
        MovieGenre movieGenre = null;
        do {
            System.out.println("Задайте жанр: ");
            System.out.println(Arrays.toString(MovieGenre.values()));
            lineIn = scanner.nextLine().toUpperCase();
            switch (lineIn) {
                case "ACTION":
                    return MovieGenre.ACTION;
                case "ADVENTURE":
                    return MovieGenre.ADVENTURE;
                case "TRAGEDY":
                    return MovieGenre.TRAGEDY;
                case "HORROR":
                    return MovieGenre.HORROR;
                case "FANTASY":
                    return MovieGenre.FANTASY;
                default:
                    System.out.println("Неверно задан жанр");
            }
        } while (true);

    }

    //подполя для получения имени Person
    private String addPersonName() {
        String personName;
        do {
            System.out.println("Задайте имя сценариста: ");
            personName = scanner.nextLine();
            if (personName.equals("")) {
                System.out.println("Строка не может быть пустой.");
                continue;
            } else {
                return personName;
            }
        } while (true);
    }

    //подполя для получения пола Person
    private boolean addPersonGender() {
        String lineIn;
        do {
            System.out.println("Сценарист мужчина? [yes/no]");
            lineIn = scanner.nextLine().toLowerCase();
            if (lineIn.equals("yes")) {
                return true;
            } else if (lineIn.equals("no")) {
                return false;
            } else {
                System.out.println("строка не может быть пустой.");
                continue;
            }
        } while (true);
    }

    public void startUpdateCommand(String argIn) { //Татьяна// метод недоделан

        if (!Utils.isInt(argIn) || argIn == null) {
            System.out.println("Неверное значение id");
            return;
        }
        long idValue = Long.parseLong(argIn);

        Movie mov = this.movieMap.get(idValue);
        if (mov == null) {
            System.out.println("Такого id нет");
            return;
        }

//         нижележащий код должен быть в цикле и должно повторяться если указанно что-то некорректно
//         (вместо return должен быть continue или аналогичная логика)
        UPPER:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Доступные поля для изменения:");
            for (MutableField field : MutableField.values()) {
                System.out.println(field);
            }
            System.out.print("Для отмены команды введите [SKIP]");
            System.out.print("Введите ответ: ");
            String answer = scanner.nextLine();

            if (!Utils.isEnum(answer, MutableField.class)) {
                System.out.println("Указано некорректное поле для изменения");
                continue;
            }
            MutableField answerValue = MutableField.valueOf(answer);

            System.out.print("Введите новое значение: ");
            System.out.println("Возможные значения длв поля GENRE: " + Arrays.toString(MovieGenre.values()));
            String newValue = scanner.nextLine();

            switch (answerValue) {
                case MOVIENAME:
                    if (newValue.isEmpty()) {
                        System.out.println("Указанно некорректное новое значение");
                        continue;
                    }
                    mov.setMovieName(newValue);
                    break;

                case GENRE:
                    if (!Utils.isEnum(newValue, MovieGenre.class)) {
                        System.out.println("Указанно некорректное новое значение");
                        continue;
                    }

                    MovieGenre genre = MovieGenre.valueOf(newValue);
                    mov.setGenre(genre);
                    break;

                default:
                    System.out.println("Чтобы сохранить изменения, наберите [ok]");
                    if (newValue.equalsIgnoreCase("ok")) {
                        break UPPER;
                    }
            }
        }
    }

    public void startRemoveKeyCommand(String argIn) {//Татьяна

        if (!Utils.isInt(argIn) || argIn == null) {
            System.out.println("Некорректный аргумент");
            return;
        }
        long idValue = Long.parseLong(argIn); // преобразование String в long "1982912981" -> 1982912981
        if (movieMap.containsKey(idValue)) {
            movieMap.remove(idValue);
            System.out.println("Элемент с id " + idValue + " успешно удалён");
        } else

            System.out.println("Такого элемента нет в списке");
    }


    public void startClearCommand() {//Татьяна

        do {
            System.out.print("Вы действительно хотите удалить все элементы из списка? [yes/no] : ");
            scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                Map<Long, Movie> movieMap1 = movieMap;// пока сделала на всякий случай с копией
                movieMap1.clear();
                System.out.println("Список очищен");
                break;

            } else if (answer.equalsIgnoreCase("no")) {
                System.out.println("Команда отменена");
                break;

            } else {
                System.out.println("Вы ввели неверное значение. Попробуйте ещё раз. ");
                continue;
            }

        } while (true);

    }

    public void startExitCommand() {//Татьяна
        System.out.println("Программа завершена. До свидания!");
    }

    public void startRemoveGreaterCommand(String argIn) {
        //Дарья

        if (!Utils.isLong(argIn)) {
            System.out.println("Указанно некорректное значение");
            return;
        }

        Long idIn = Long.valueOf(argIn);
        Set<Long> set = new HashSet<>();

        for (Long idMap : movieMap.keySet()) {
            if (idMap > idIn){
                set.add(idMap);
            }
        }

        for (Long id : set) {

            System.out.println("Удалили " + " " + movieMap.get(id));

            movieMap.remove(id);
        }
    }

    public void startRemoveLowerCommand(String argIn) {//Дарья

        if (!Utils.isLong(argIn)) {
            System.out.println("Указанно некорректное значение");
            return;
        }

        Long idIn = Long.valueOf(argIn);
        Set<Long> set = new HashSet<>();

        for (Long idMap : movieMap.keySet()) {
            if (idMap < idIn) {
                set.add(idMap);
            }
        }

        for (Long id : set) {

            System.out.println("Удалили " + " " + movieMap.get(id));

            movieMap.remove(id);
        }

    }

    public int startCountLessThanGenreCommand(MovieGenre targetGenre) { //Дарья

        int counter = 0;
        for (Movie movie : movieMap.values()) {

            if (movie.getGenre().ordinal() < targetGenre.ordinal()) {
                counter = counter + 1;
            }
        }

        return counter;
    }
}
