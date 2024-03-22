package movieCollection.models;

import java.util.Objects;

public class Movie implements Comparable<Movie> {
    private long id; //Значение поля должно быть больше 0,
    // Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String movieName; //Поле не может быть null, Строка не может быть пустой
    //@Tatjana поменяла имя поля, чтобы не было повтора с name из класса Person
    private MovieGenre genre; //Поле может быть null
    private Person screenwriter;

    private static int counter = 0;

    public Movie(String movieName, MovieGenre genre, Person screenwriter) {
        if(!Movie.validateMovieName(movieName)){
            throw new RuntimeException("Некорректное значение");
        }
        this.movieName = movieName;
        this.genre = genre;
        this.screenwriter = screenwriter;
        Movie.counter++;
        this.id = 918291822 + Movie.counter; //автоматическая генерация id
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public Person getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(Person screenwriter) {
        this.screenwriter = screenwriter;
    }
    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return  "Movie " +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", genre=" + genre +
                ", screenwriter=" + screenwriter +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return getId() == movie.getId() && Objects.equals(getMovieName(), movie.getMovieName())
                && getGenre() == movie.getGenre() && Objects.equals(getScreenwriter(), movie.getScreenwriter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMovieName(), getGenre(), getScreenwriter());
    }


    @Override
    public int compareTo(Movie o) { // переопределяем метод, приводим результат к int
        return (int)(this.getId() - o.getId());
    }

    // валидация movieName (проверка на пограничные значения из задания)
    // Поле не может быть null, Строка не может быть пустой
    // Татьяна
    public static boolean validateMovieName(String movieName) { // Татьяна
        if (movieName == null){ // Поле не может быть null
            return false;
        }
        if (movieName.isEmpty()){ // Строка не может быть пустой
            return false;
        }
        return true;
    }
    // валидация id
    // Значение поля должно быть больше 0
    // Татьяна
    public static boolean validateId(Long id) {
        return (id >= 0);
    }
}

