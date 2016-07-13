package boonedev.apptcc;

/**
 * Created by vitor.oliveira on 30/05/2016.
 */
public class User {
    private int level;
    private String idioma;

    // Getters ######################################
    public int getLevel() {
        return level;
    }
    public String getIdioma() {
        return idioma;
    }

    // Setters ######################################
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public void setLevel(int level) {
        this.level = level;
    }



}
