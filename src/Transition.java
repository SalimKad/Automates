public class Transition {
    private String etatFinal, etatInitial;
    private char symbole, sommet, nv_symboles;

    public Transition(String etatInitial, String etatFinal, char symbole, char sommet, char nv_symboles) {
        this.etatFinal = etatFinal;
        this.etatInitial = etatInitial;
        this.symbole = symbole;
        this.sommet = sommet;
        this.nv_symboles = nv_symboles;
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

    public void setSommet(char sommet) {
        this.sommet = sommet;
    }

    public char getSommet() {
        return sommet;
    }

    public void setNv_symboles(char nv_symboles) {
        this.nv_symboles = nv_symboles;
    }

    public char getNv_symboles() {
        return nv_symboles;
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
