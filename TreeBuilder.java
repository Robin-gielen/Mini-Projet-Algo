/**
 * 
 * Classe servant à la construction d'un arbre
 * 
 */ 

public class TreeBuilder implements TreeBuilderIF{
    public String untreatedLines; 
    
    /**
    *
    * Constructeur de la classe Treebuilder
    * 
    */
    public TreeBuilder(String untreatedLines){
        this.untreatedLines = untreatedLines;
    }
    
    /**
     * @pre L'expression arithmétique passée au constructeur est
     *      complètement parenthésée et bien formée. Les éléments
     *      de l'expression sont séparés par un espace.
     * @post renvoie un arbre représentant l'expression arithmétique
     *       passée au constructeur
     */
    public ExprIF build(){
        Pile pile = new Pile();
        String caractere;
        int compteur;
        Node tempon;

        for (int i = 0; i <= (untreatedLines.length()-1); i++){ //Boucle parcourant la totalite de la chaine de caractere
            caractere = (untreatedLines.charAt(i) + ""); // Variable contenant le caractere a traiter
            compteur = 1;
            if (caractere.charAt(0) != ('(') && caractere.charAt(0) !=(')')){ // Condition verifiant si le caractere est different de ( ou )
                if (untreatedLines.charAt(i+1) != ('.')){
                    tempon = new Node(caractere); // Noeud tempon servant a stocker les caracteres dans une pile
                }
                else{
                    tempon = new Node(caractere + ".");
                    for(int j = 2; Character.isDigit(untreatedLines.charAt(i+j)); j++){
                        tempon.expression = tempon.expression + untreatedLines.charAt(i+j);
                        compteur++;
                    }
                    i = i + compteur;
                }
                pile.push(tempon);
            }
            else if (caractere.charAt(0) == (')')){ // Condition servant a creer chaque racine de sous arbre
                Node n2 = pile.pop();
                Node n = pile.pop();
                Node n1 = pile.pop();
                n1.back = n;
                n2.back = n;
                n.left = n1;
                n.right = n2;
                pile.push(n);
            }
        }
        return pile.pop();
    }
}