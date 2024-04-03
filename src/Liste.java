public class Liste<Type> {
    public class Noeud {
        public final Type valeur;
        public Noeud precedent, prochain;

        public Noeud(Type valeur) {
            this.valeur = valeur;
            this.prochain = null;
            this.precedent = null;
        }

        public String toString() {
            return String.valueOf(valeur);
        }
    }

    private Noeud premier, dernier;
    private int nbElements;

    public Liste() {
        dernier = premier = null;
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

    public Type getElementAt(int index) {
        return getNoeudAt(index).valeur;
    }

    private Noeud getNoeudAt(int index) {
        for (Noeud courant = premier; courant != null; courant = courant.prochain)
            if (index-- == 0)
                return courant;
        return null;
    }

    public void ajouter(Type element) {
        if (estVide()) {
            dernier = premier = new Noeud(element);
            nbElements++;
            return;
        }

        Noeud nouveau = new Noeud(element);
        nouveau.precedent = dernier;
        dernier.prochain = nouveau;
        dernier = nouveau;
        nbElements++;
    }

    public boolean ajouter(Type element, int index) {
        if (index < 0 || index > nbElements)
            //throw new IndexOutOfBoundsException();
            return false;

        Noeud nouveau = new Noeud(element);
        Noeud precedent = getNoeudAt(index - 1);

        if (index == 0) {
            premier.precedent = nouveau;
            nouveau.prochain = premier;
            premier = nouveau;
        }
        else if (index == nbElements) {
            ajouter(element);
            return true;
        }
        else {
            nouveau.precedent = precedent;
            nouveau.prochain = precedent.prochain;
            precedent.prochain = nouveau;
            nouveau.prochain.precedent = nouveau;
        }

        nbElements++;
        return true;
    }

    public void ajouter(Liste<Type> autre) {
        for (int i = 0 ; i < autre.getNbElements(); i++)
            this.ajouter(autre.getElementAt(i));
    }

    public int trouver(Type valeur) {
        int index = 0;
        for (Noeud courant = premier; courant != null; courant = courant.prochain) {
            if (courant.valeur == valeur)
                return index;
            else
                index++;
        }
        return -1;
    }

    public boolean trouverTout(Liste<Type> autre) {
        for (int i = 0 ; i < autre.getNbElements(); i++)
            if (this.trouver(autre.getElementAt(i)) == -1)
                return false;
        return true;
    }

    public boolean effacerAt(int index) {
        if (index < 0 || index > nbElements)
            //throw new IndexOutOfBoundsException();
            return false;

        if (index == 0) {
            premier = premier.prochain.prochain;
            premier.precedent = null;
        }
        else if (index == nbElements - 1) {
            Noeud precedent = getNoeudAt(index - 1);
            precedent.prochain = null;
            dernier = precedent;
        }
        else {
            Noeud precedent = getNoeudAt(index - 1);
            precedent.prochain = precedent.prochain.prochain;
        }
        nbElements--;
        return true;
    }

    public boolean effacerTout(Liste<Type> autre) {
        boolean result = false;
        for (int i = 0; i < autre.getNbElements(); i++) {
            Type valeurAEffacer = autre.getElementAt(i);
            int indexAEffacer = trouver(valeurAEffacer);
            if (indexAEffacer != -1) {
                this.effacerAt(indexAEffacer);
                result = true;
            }
        }
        return result;
    }

    public void effacerTout() {
        dernier = premier = null;
        nbElements = 0;
    }
}
