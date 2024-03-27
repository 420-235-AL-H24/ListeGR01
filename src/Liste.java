public class Liste {
    public class Noeud {
        public final int valeur;
        public Noeud prochain;

        public Noeud(int valeur) {
            this.valeur = valeur;
            this.prochain = null;
        }

        public String toString() {
            return String.valueOf(valeur);
        }
    }

    private Noeud premier;
    private int nbElements;

    public Liste() {
        premier = null;
        nbElements = 0;
    }

    public String toString() {
        String str = "[";
        for (Noeud courant = premier; courant != null; courant = courant.prochain)
            str += courant.valeur +  ", " ;
        return str + "]";
    }

    public int getNbElements() {
        return nbElements;
    }

    public boolean estVide() {
        return nbElements == 0;
    }

    public int getElementAt(int index) {
        return getNoeudAt(index).valeur;
    }

    private Noeud getNoeudAt(int index) {
        for (Noeud courant = premier; courant != null; courant = courant.prochain)
            if (index-- == 0)
                return courant;
        return null;
    }

    public void ajouter(int element) {
        if (estVide()) {
            premier = new Noeud(element);
            nbElements++;
            return;
        }

        Noeud dernier = premier;
        while (dernier.prochain != null)
            dernier = dernier.prochain;

        Noeud nouveau = new Noeud(element);
        dernier.prochain = nouveau;

        nbElements++;
    }

    public boolean ajouter(int element, int index) {
        if (index < 0 || index > nbElements)
            //throw new IndexOutOfBoundsException();
            return false;

        Noeud nouveau = new Noeud(element);
        Noeud precedent = getNoeudAt(index - 1);

        if (index == 0) {
            nouveau.prochain = premier;
            premier = nouveau;
        }
        else {
            nouveau.prochain = precedent.prochain;
            precedent.prochain = nouveau;
        }

        nbElements++;
        return true;
    }

    public void ajouter(Liste autre) {
        for (int i = 0 ; i < autre.getNbElements(); i++)
            this.ajouter(autre.getElementAt(i));
    }

    public int trouver(int valeur) {
        int index = 0;
        for (Noeud courant = premier; courant != null; courant = courant.prochain) {
            if (courant.valeur == valeur)
                return index;
            else
                index++;
        }
        return -1;
    }

    public boolean trouverTout(Liste autre) {
        for (int i = 0 ; i < autre.getNbElements(); i++)
            if (this.trouver(autre.getElementAt(i)) == -1)
                return false;
        return true;
    }
/*
    public boolean effacerAt(int index) {
        if (index < 0 || index > nbElements)
            //throw new IndexOutOfBoundsException();
            return false;
        for (int i = index; i < nbElements; i++)
            tableau[i] = tableau[i + 1];
        nbElements--;
        return true;
    }

    public boolean effacerTout(Liste autre) {
        boolean result = false;
        for (int i = 0; i < autre.getNbElements(); i++) {
            int valeurAEffacer = autre.getElementAt(i);
            int indexAEffacer = trouver(valeurAEffacer);
            if (indexAEffacer != -1) {
                this.effacerAt(indexAEffacer);
                result = true;
            }
        }
        return result;
    }

    public void effacerTout() {
        tableau = new int[TAILLE_INITIALE];
        nbElements = 0;
    }

 */
}
