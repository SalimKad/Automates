import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Automate {
    private ArrayList<String> alphabet;
    private String etatInitial;
    private ArrayList<String> etatsFinaux;
    private ArrayList<String> etats;
    private ArrayList<Transition> transitions;

    public Automate(String etatInitial, ArrayList<String> etats, ArrayList<String> alphabet, ArrayList<String> etatsFinaux, ArrayList<Transition> transitions) {
        this.etatInitial = etatInitial;
        this.etatsFinaux = etatsFinaux;
        this.alphabet = alphabet;
        this.etats = etats;
        this.transitions = transitions;
    }

    public Automate(String nomDeFichier) throws Exception {
        //Création des listes
        this.alphabet = new ArrayList<String>();
        this.etats = new ArrayList<String>();
        etatInitial = null;
        this.etatsFinaux = new ArrayList<String>();
        this.transitions = new ArrayList<Transition>();

        //Déclaration des variables pour la lecture du fichier
        InputStream ins = new FileInputStream(new File(nomDeFichier)); //ouvrir le fichier
        Scanner obj = new Scanner(ins); //lire le fichier
        String line = obj.nextLine(); //lire la première ligne

        //Lecture du fichier
        //System.out.println("la ligne est " + line);
        if (line.startsWith("# alphabet")) {
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
        } if (line.startsWith("# etats")) {
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
                if (parts.length != 3) {
                    throw new Exception("Invalid transition format");
                }
                transitions.add(new Transition(parts[0], parts[2], parts[1].charAt(0)));
            }
        }
        this.etatInitial = etatInitial;
    }

    boolean appartient(String mot) {
        int cpt=0;

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
            System.out.println("Symbole n’appartenant pas à l’alphabet de l’automate dans le mot " + mot);
            return false;
            //on retourne false si tous les caractères du mot ne sont pas dans l'alphabet
        }

        // vérifie qu'on peut construire le mot avec les transitions
        String etatActuel = etatInitial;
        boolean trouve;
        for(int i=0; i<mot.length(); i++) {
            trouve = false;
            for(Transition t : transitions){

                //vérification de l'état actuel et l'état initial de la transition
                if (t.getEtatInitial().equals(etatActuel)) {
                    //vérification du caractère du mot et du symbole de la transition
                    if (t.getSymbole() == mot.charAt(i)) { //si le symbole de la transition est égal au caractère du mot
                        etatActuel = t.getEtatFinal();
                        trouve = true;
                    } else continue;
                }

                if(trouve) break;
                // on sort de la boucle des transitions si une transition a été trouvée
            }
            if(i==(mot.length()-1) && !trouve) {
                System.out.println("Absence de transition à partir de l’état courant avec le symbole lu dans le mot " + mot);
                return false;
            }
        }
        //System.out.println("L'état actuel est " + etatActuel);
        for(String e : etatsFinaux) {
            if(etatActuel.equals(e)){
                System.out.println("Le mot " + mot + " est reconnu par l'automate");
                return true;
            }
            // on retourne vrai si l'état actuel de la fin du mot est un état final
        }

        System.out.println("Fin du mot  avant d’atteindre un état final dans le mot " + mot);
        return false;
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

    public String toString() {
        String str = "# alphabet\n";
        for (String a : alphabet) {
            str += a + "\n";
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
            str += t.getEtatInitial() + " " + t.getSymbole() + " " + t.getEtatFinal() + "\n";
        }
        return str;
    }

}
