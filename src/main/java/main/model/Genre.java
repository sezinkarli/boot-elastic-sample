package main.model;

/**
 * @author sezin karli
 * @since 2/28/15 1:56 PM
 *        User: Sezin Karli
 */
public class Genre {
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
