import java.sql.SQLOutput;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
/*

        Automate a1 = new Automate("Exemple.txt");
        System.out.println("Automate 3 : " + a1);

        //On test appartient
        ArrayList<String> alphabet = new ArrayList<>();
        String etatInitial;
        ArrayList<String> etatsFinaux = new ArrayList<>();
        ArrayList<String> etats = new ArrayList<>();
        ArrayList<Transition> transitions = new ArrayList<>();
        ArrayList<String> alphabetPile = new ArrayList<>();

        alphabet.add("a");
        alphabet.add("b");

        alphabetPile.add("A");
        alphabetPile.add("B");

        etats.add("S1");
        etats.add("S2");
        etats.add("S3");
        etatInitial = "S1";
        etatsFinaux.add("S3");

        char[] add_pile1 = {'A','A'};
        char[] add_pile2 = {'A','B'};
        char[] add_pile3 = {'B'};
        char[] add_pile4 = {'B','A'};

        Transition t1 = new Transition("S1","S1", 'a', 'Z', add_pile1);
        Transition t2 = new Transition("S1","S2", 'b','A',add_pile2);
        Transition t3 = new Transition("S2","S2", 'b','A', add_pile3);
        Transition t4 = new Transition("S2","S3", 'a','B', add_pile4);
        transitions.add(t1);
        transitions.add(t2);
        transitions.add(t3);
        transitions.add(t4);
*/
        Automate a2 = new Automate("ExoV2.txt");
        System.out.println("Automate calculatrice : " + a2);
        String mot = "8+4";
        System.out.println("Le mot " + mot + " est accepté par l'automate ? " + a2.appartient(mot));
        String mot2 = "((150-24)*72+4)/5";
        System.out.println("Le mot " + mot2 + " est accepté par l'automate ? " + a2.appartient(mot2));

    }
}