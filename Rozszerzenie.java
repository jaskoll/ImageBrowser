import java.io.File;

import javax.swing.filechooser.FileFilter;

class Rozszerzenie extends FileFilter {
    private String rozszerzenie;
    private String opis;
 
    public Rozszerzenie(String rozszerzenie, String opis) {
        this.rozszerzenie = rozszerzenie;
        this.opis = opis;
    }
 
    public boolean accept(File plik) {
        if (plik.isDirectory()) {
            return true;
        }
        return plik.getName().endsWith(rozszerzenie);
    }
 
    public String getDescription() {
        return opis + String.format(" %s ", rozszerzenie);
    }
}