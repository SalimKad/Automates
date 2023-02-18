public class Transition {
    private String etatFinal, etatInitial;
    private char symbole;

    public Transition(String etatInitial, String etatFinal, char symbole) {
        this.etatFinal = etatFinal;
        this.etatInitial = etatInitial;
        this.symbole = symbole;
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

    @Override
    public String toString() {
        return "Transition{" +
                "etatFinal='" + etatFinal + '\'' +
                ", symbole=" + symbole +
                ", etatInitial='" + etatInitial + '\'' +
                '}';
    }
}
