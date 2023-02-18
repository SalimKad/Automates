import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        //On test appartient
        ArrayList<String> alphabet = new ArrayList<>();
        String etatInitial;
        ArrayList<String> etatsFinaux = new ArrayList<String>();
        ArrayList<String> etats = new ArrayList<String>();
        ArrayList<Transition> transitions = new ArrayList<Transition>();

        alphabet.add("a");
        alphabet.add("b");

        etats.add("S1");
        etats.add("S2");
        etats.add("S3");
        etatInitial = "S1";
        etatsFinaux.add("S3");

        Transition t1 = new Transition("S1","S1", 'a');
        Transition t2 = new Transition("S1","S2", 'b');
        Transition t3 = new Transition("S2","S2", 'b');
        Transition t4 = new Transition("S2","S3", 'a');
        transitions.add(t1);
        transitions.add(t2);
        transitions.add(t3);
        transitions.add(t4);

        Automate a1 = new Automate(etatInitial, etats, alphabet, etatsFinaux, transitions);
        //System.out.println("Automate 1 : " + a1);
        String mot = "abba";
        String mot2 = "aab";
        boolean r1 = a1.appartient(mot);
        System.out.println(mot + " appartient à A1 ? " + r1);
        boolean r2 = a1.appartient(mot2);
        System.out.println(mot2 + " appartient à A1 ? " + r2);
        //On test Automate
        Automate a2 = new Automate("Exemple.txt");
        //System.out.println("Automate 2 : " + a2);
        boolean r3 = a2.appartient(mot);
        System.out.println(mot + " appartient à A2 ? " +r3);
        boolean r4 = a2.appartient(mot2);
        System.out.println(mot + " appartient à A2 ? " + r4);



        //Exo 2 :
        /*
        String alph[] = {"1","2","5","S","L","s","l","d","v","c"};
        for(String a : alph){
            alphabet.add(a);
        }
        System.out.println(alphabet);
        */
    }
}