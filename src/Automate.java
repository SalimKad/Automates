import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.*;

public class Automate {
    private ArrayList<String> alphabet;
    private String etatInitial;
    private ArrayList<String> etatsFinaux;
    private ArrayList<String> etats;
    private ArrayList<Transition> transitions;
    private ArrayList<String> pile;
    private ArrayList<String> alphabetPile;

    public Automate(String etatInitial, ArrayList<String> etats, ArrayList<String> alphabet, ArrayList<String> etatsFinaux, ArrayList<Transition> transitions, ArrayList<String> alphabetPile) {
        this.etatInitial = etatInitial;
        this.etatsFinaux = etatsFinaux;
        this.alphabet = alphabet;
        this.etats = etats;
        this.transitions = transitions;
        this.pile = new ArrayList<String>();
        pile.add("Z");
        this.alphabetPile = alphabetPile;
    }

    public Automate(String nomDeFichier) throws Exception {
        //Création des listes
        this.alphabet = new ArrayList<String>();
        this.etats = new ArrayList<String>();
        etatInitial = null;
        this.etatsFinaux = new ArrayList<String>();
        this.transitions = new ArrayList<Transition>();
        this.alphabetPile = new ArrayList<String>();

        //Déclaration des variables pour la lecture du fichier
        InputStream ins = new FileInputStream(new File(nomDeFichier)); //ouvrir le fichier
        Scanner obj = new Scanner(ins); //lire le fichier
        String line = obj.nextLine(); //lire la première ligne


        //Lecture du fichier
        //System.out.println("la ligne est " + line);
        if (line.startsWith("# alphabet automate")) {
            while (obj.hasNextLine()) {
                line = obj.nextLine();
                if (line.startsWith("#")) {
                    //System.out.println("On s'arrête à la ligne" + line);
                    break;
                }
                alphabet.add(line.trim()); //ajouter les lettres de l'alphabet dans la liste alphabet
                //la fonction trim() permet de supprimer les espaces avant et après la lettre
            }
            //System.out.println("la ligne est " + line);
        }
        if (line.startsWith("# alphabet pile")) {
            while (obj.hasNextLine()) {
                line = obj.nextLine();
                if (line.startsWith("#")) {
                    //System.out.println("On s'arrête à la ligne" + line);
                    break;
                }
                alphabetPile.add(line.trim()); //ajouter les lettres de l'alphabet dans la liste alphabet
                //la fonction trim() permet de supprimer les espaces avant et après la lettre
            }
        }
        if (line.startsWith("# etats")) {
            //System.out.println("la ligne est " + line);
            while (obj.hasNextLine()) {
                line = obj.nextLine();
                if (line.startsWith("#")) {
                    //System.out.println("Dans état, on s'arrête à la ligne" + line);
                    break;
                }
                etats.add(line.trim()); //ajouter les états dans la liste etats
            }
        }
        if (line.startsWith("# etat initial")) {
            etatInitial = obj.nextLine().trim();
            //System.out.println("Dans état initial, on s'arrête à la ligne" + line);
            line = obj.nextLine();
        }
        if (line.startsWith("# etats finaux")) {
            while (obj.hasNextLine()) {
                line = obj.nextLine();
                if (line.startsWith("#")) {
                    //System.out.println("Dans état final, on s'arrête à la ligne" + line);
                    break;
                }
                etatsFinaux.add(line.trim());
            }
        }
        if (line.startsWith("# transitions")) {
            while (obj.hasNextLine()) {
                line = obj.nextLine();
                if (line.startsWith("#")) {
                    break;
                }
                String[] parts = line.trim().split("\\s+");
                if (parts.length < 3) {
                    throw new Exception("Invalid transition format");
                }
                //System.out.println(Arrays.toString(parts));
                char[] symbolepile = parts[4].toCharArray();
                transitions.add(new Transition(parts[0], parts[3], parts[1].charAt(0), parts[2].charAt(0), symbolepile));
            }
        }
        this.etatInitial = etatInitial;
    }

    boolean appartient(String mot) {
        int cpt=0;
        this.pile = new ArrayList<String>();
        pile.add("Z");


        for(int i=0; i<mot.length(); i++){ //parcours les lettres du mot
            for(String a : alphabet) {
                //vérifie si la lettre appartienne à l'alphabet
                if(mot.charAt(i) == a.charAt(0)) {
                    cpt++;
                }
            }
        }
        //System.out.println(cpt);
        if (cpt != mot.length()) {
            System.out.println(mot + "n’est pas un nombre valide ");
            return false;
            //on retourne false si tous les caractères du mot ne sont pas dans l'alphabet
        }

        // vérifie qu'on peut construire le mot avec les transitions
        String etatActuel = etatInitial;
        boolean trouve;
        for(int i=0; i<mot.length(); i++) {
            //System.out.println("Pile : " + pile);
            trouve = false;
            for(Transition t : transitions){
                //System.out.println("Transition : " + t);
                //vérification de l'état actuel et l'état initial de la transition
                if (t.getEtatInitial().equals(etatActuel)) {
                    //vérification du caractère du mot et du symbole de la transition
                    //System.out.println("t.getSymbole() " + t.getSymbole() + " mot.charAt(i) " + mot.charAt(i));
                    if (t.getSymbole() == mot.charAt(i)) { //si le symbole de la transition est égal au caractère du mot
                        //System.out.println("Transition trouvée : " + t);
                        int n = pile.size();
                        if (t.getSymbolesommet() == pile.get(n-1).charAt(0)) {
                            pile.remove(n - 1);
                            for (int j = t.getSymbolepile().length - 1; j >= 0; j--) {
                                char s = t.getSymbolepile()[j];
                                if(s != 'E'){
                                    pile.add("" + s);
                                }
                                //System.out.println("On a ajouté " + s + " à la Pile : " + pile);
                            }
                            etatActuel = t.getEtatFinal();
                            //System.out.println("L'état actuel est " + etatActuel);
                            trouve = true;
                            //System.out.println("Trouve = "+trouve);
                            break;
                        }  //si le symbole du sommet de la pile est différent du symbole du sommet de la transition
                    } else continue; //si le symbole de la transition est différent du caractère du mot
                } else continue; //si l'état actuel est différent de l'état initial de la transition
            }

                /*if(trouve) break;
                else {
                    System.out.println("Absence de transition à partir de l’état courant avec le symbole lu dans le mot " + mot);
                    return false;
                }*/

            if(i==(mot.length()-1) && !trouve) {
                System.out.println("Absence de transition à partir de l’état courant avec le symbole lu dans le mot " + mot);
                return false;
            }
        }
        //System.out.println("L'état actuel est " + etatActuel);
        for(String e : etatsFinaux) {
            if(etatActuel.equals(e)){
                if(pile.size() == 1 && pile.get(0).equals("Z")) {
                    System.out.println("L’expression " + mot + " est valide");
                    //System.out.println("Le mot " + mot + " est reconnu par l'automate et la pile est vide");
                    return true;
                }
                else {
                    /*System.out.println("Le mot " + mot + " est reconnu par l'automate mais la pile n'est pas vide");
                    System.out.println("Pile : " + pile);*/
                    System.out.println("Il manque une parenthèse fermante dans l’expression "+ mot);
                    return false;
                }
            }
            // on retourne vrai si l'état actuel de la fin du mot est un état final
        }

        System.out.println("On ne peut pas finir l'expression par un signe dans " + mot);
        return false;
    }

    /*public static double evalueExpression(String expression) {
        // Créer une pile pour stocker les nombres et les opérateurs
        Stack<Double> stack = new Stack<>();

        // Variables pour stocker les nombres et les opérateurs
        double num = 0;
        char operateur = '+';

        // Analyser l'expression caractère par caractère
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Si c'est un chiffre, l'ajouter au nombre en cours de lecture
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            // Si c'est un opérateur ou le dernier caractère de l'expression
            if (!Character.isDigit(c) || i == expression.length() - 1) {
                // Appliquer l'opérateur précédent
                if (operateur == '+') {
                    stack.push(num);
                } else if (operateur == '-') {
                    stack.push(-num);
                } else if (operateur == '*') {
                    stack.push(stack.pop() * num);
                } else if (operateur == '/') {
                    stack.push(stack.pop() / num);
                }

                // Mise à jour de l'opérateur en cours de traitement
                operateur = c;

                // Réinitialiser le nombre en cours de lecture
                num = 0;

            }
        }

        // Additionner les nombres dans la pile
        double result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }*/
    public static double evalueExpression(String expression) {
    // Créer une pile pour stocker les nombres et les opérateurs
        Stack<Double> stack = new Stack<>();
    // Créer une pile pour stocker les parenthèses ouvrantes
        Stack<Character> parentheses = new Stack<>();
    // Variables pour stocker les nombres et les opérateurs
    double num = 0;
    char operateur = '+';

    // Analyser l'expression caractère par caractère
    for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Si c'est un chiffre, l'ajouter au nombre en cours de lecture
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            // Si c'est une parenthèse ouvrante
            else if (c == '(') {
                // Empiler l'opérateur et le nombre en cours de lecture
                stack.push((double) operateur);
                stack.push(num);
                // Empiler la parenthèse ouvrante
                parentheses.push(c);
                // Réinitialiser le nombre en cours de lecture et l'opérateur en cours
                num = 0;
                operateur = '+';
            }

            // Si c'est une parenthèse fermante ou le dernier caractère de l'expression
            if (c == ')' || i == expression.length() - 1) {
                // Appliquer l'opérateur précédent
                if (operateur == '+') {
                    stack.push(num);
                } else if (operateur == '-') {
                    stack.push(-num);
                } else if (operateur == '*') {
                    stack.push(stack.pop() * num);
                } else if (operateur == '/') {
                    stack.push(stack.pop() / num);
                }

                // Réinitialiser le nombre en cours de lecture et l'opérateur en cours
                num = 0;
                operateur = '+';

                // Tant qu'il y a des parenthèses ouvrantes dans la pile
                while (!parentheses.isEmpty()) {
                    // Appliquer l'opérateur précédent
                    if (stack.peek() == '+') {
                        stack.push(stack.pop() + stack.pop());
                    } else if (stack.peek() == '-') {
                        stack.push(-stack.pop() + stack.pop());
                    } else if (stack.peek() == '*') {
                        stack.push(stack.pop() * stack.pop());
                    } else if (stack.peek() == '/') {
                        stack.push(1 / stack.pop() * stack.pop());
                    }
                    // Dépiler la parenthèse ouvrante
                    parentheses.pop();
                }
            }

            // Si c'est un opérateur
            if (!Character.isDigit(c) && c != '(' && c != ')') {
                // Appliquer l'opérateur précédent
                if (operateur == '+') {
                    stack.push(num);
                } else if (operateur == '-') {
                    stack.push(-num);
                } else if (operateur == '*') {
                    stack.push(stack.pop() * num);
                } else if (operateur == '/') {
                    stack.push(stack.pop() / num);
                }
                // Mise à jour de l'opérateur en cours de traitement
                operateur = c;
                // Réinitialiser le nombre en cours de lecture
                num = 0;
            }

        }

        // Additionner les nombres dans la pile
        double result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }


            public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }

    public String getEtatInitial() {
        return etatInitial;
    }

    public void setEtatInitial(String etatInitial) {
        this.etatInitial = etatInitial;
    }

    public ArrayList<String> getEtatsFinaux() {
        return etatsFinaux;
    }

    public void setEtatsFinaux(ArrayList<String> etatsFinaux) {
        this.etatsFinaux = etatsFinaux;
    }

    public ArrayList<String> getEtats() {
        return etats;
    }

    public void setEtats(ArrayList<String> etats) {
        this.etats = etats;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    public ArrayList<String> getPile() {
        return pile;
    }

    public void setPile(ArrayList<String> pile) {
        this.pile = pile;
    }

    public String toString() {
        String str = "# alphabet automate\n";
        for (String aa : alphabet) {
            str += aa + "\n";
        }
        str += "# alphabet pile\n";
        for(String ap : alphabetPile) {
            str += ap + "\n";
        }
        str += "# etats\n";
        for (String e : etats) {
            str += e + "\n";
        }
        str += "# etat initial\n" + etatInitial + "\n";
        str += "# etats finaux\n";
        for (String ef : etatsFinaux) {
            str += ef + "\n";
        }
        str += "# transitions\n";
        for (Transition t : transitions) {
            str += t.getEtatInitial() + " " + t.getSymbole() + " " + t.getSymbolesommet() + " " + t.getEtatFinal() + " " + Arrays.toString(t.getSymbolepile()) + "\n";
        }
        return str;
    }

}
