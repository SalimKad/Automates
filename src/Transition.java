import java.util.ArrayList;
import java.util.Arrays;

public class Transition {
    private String etatFinal, etatInitial;
    private char symbole, symbolesommet;
    private char[] symbolepile;

    public Transition(String etatInitial, String etatFinal, char symbole, char symbolesommet, char[] symbolepile) {
        this.etatInitial = etatInitial;
        this.symbole = symbole;
        this.symbolesommet = symbolesommet;
        this.etatFinal = etatFinal;
        this.symbolepile = symbolepile;
    }

    public String getEtatFinal() {
        return etatFinal;
    }

    public void setEtatFinal(String etatFinal) {
        this.etatFinal = etatFinal;
    }

    public String getEtatInitial() {
        return etatInitial;
    }

    public void setEtatInitial(String etatInitial) {
        this.etatInitial = etatInitial;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }

    public void setSymbolesommet(char symbolesommet) {
        this.symbolesommet = symbolesommet;
    }

    public char getSymbolesommet() {
        return symbolesommet;
    }

    public void setSymbolepile(char[] symbolepile) {
        this.symbolepile = symbolepile;
    }

    public char[] getSymbolepile() {
        return symbolepile;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "etatFinal='" + etatFinal + '\'' +
                ", symbole=" + symbole +
                ", etatInitial='" + etatInitial + '\'' +
                ", symbolesommet=" + symbolesommet +
                ", symbolepile=" + Arrays.toString(symbolepile) +
                '}';
    }
}
