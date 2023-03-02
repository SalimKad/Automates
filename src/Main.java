import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        //On test appartient
        ArrayList<String> alphabet = new ArrayList<>();
        String etatInitial;
        ArrayList<String> etatsFinaux = new ArrayList<>();
        ArrayList<String> etats = new ArrayList<>();
        ArrayList<Transition> transitions = new ArrayList<>();

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
        Automate a2 = new Automate("Exemple2.txt");
        //System.out.println("Automate 2 : " + a2);
        boolean r3 = a2.appartient(mot);
        System.out.println(mot + " appartient à A2 ? " +r3);
        boolean r4 = a2.appartient(mot2);
        System.out.println(mot2 + " appartient à A2 ? " + r4);



        //Exo 2 :
        /*
        String alph[] = {"1","2","5","S","L","s","l","d","v","c"};
        for(String a : alph){
            alphabet.add(a);
        }
        System.out.println(alphabet);
        */
        System.out.println("Exo 2 :\n");
        Automate a3 = new Automate("Exo2.txt");
        System.out.println("Automate 3 : " + a3);
        String mot3 = "21Ss";
        boolean r5 = a3.appartient(mot3);
        System.out.println(mot3 + " appartient à A3 ? " + r5);
        String mot4 = "5Svs";
        boolean r6 = a3.appartient(mot4);
        System.out.println(mot4 + " appartient à A3 ? " + r6);
        String mot5 = "L22L1Ll";
        boolean r7 = a3.appartient(mot5);
        System.out.println(mot5 + " appartient à A3 ? " + r7);
        String mot6 = "2Ss";
        boolean r8 = a3.appartient(mot6);
        System.out.println(mot6 + " appartient à A3 ? " + r8);
        String mot7 = "5S5s";
        boolean r9 = a3.appartient(mot7);
        System.out.println(mot7 + " appartient à A3 ? " + r9);
        String mot8 = "d51l";
        boolean r10 = a3.appartient(mot8);
        System.out.println(mot8 + " appartient à A3 ? " + r10);



    }
}