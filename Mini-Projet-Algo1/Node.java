import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

/**
 * 
 * Classe creant les noeuds necessaires au programme et simplifiant l'arbre en cour en suivant un parcours postfixe
 * 
 */
class Node implements ExprIF{
    char expression;
    Node left;
    Node right;
    Node back;
    Node next;
    
    /**
     * 
     * Constructeur de la classe Node
     * 
     */
    public Node(char expression){
        this.expression = expression;
        left = null;
        right = null;
        back = null;
        next = null;
    }
    
    /**
     * Simplification de l'arbre.
     *
     * @pre l'arbre represente une expression arithmetique 
     *      bien construite
     * @post Si l'arbre contient au moins un operateur, 
     *       l'arbre renvoye est obtenu après une simplification 
     *       dans l'ordre d'un parcours postfixe
     *       Sinon, renvoit l'arbre original.
     */
    public ExprIF getReducedTree(){
        Node node = this;
        String chain = Main.chaine; 
        String calculChain = "";
        boolean subtreefinished = true; // Variable servant a verifier si l'arbre a ete reduit
        
        while(node.left != null && node.right != null){ // Boucle servant a resoudre la totalite de l'arbre
            while(subtreefinished){
                while (node.left != null){ // Boucle servant a attaindre l'element positionne le plus bas dans l'arbre
                    System.out.println (node.expression);
                    node = node.left;
                }
                if (node.back.right.left == null){ // Condition verifiant si le calcul peut etre fait
                    calculChain = (node.expression + "" + node.back.expression + "" + node.back.right.expression+ "" );// creation du string servant a faire le calcul 
                    char s = calcul(calculChain);
                    node.back.expression = s;
                    node.back.left = null;
                    node.back.right = null;
                    subtreefinished = false;
                }
                else{
                    node = node.back.right;
                }
            }
            subtreefinished = true;
            node = this;
            chain = chain + (node.toString() + " = ");
        }
        Main.chaine = (chain);// Modification du String de la classe main en vue d'etre sauvegardee dans un fichier
        return node;
    }

    /**
     * Conversion de l'arbre en un String.
     *
     * @pre l'arbre représente une expression arithmétique 
     *      bien construite
     * @post le String renvoyé est la représentation 
     *       complètement parenthésée de l'arbre  
     */
    public String toString(){
        
        Node n = this;
        
        String stringTreated = ""; // Contiendra la representation completement parenthesee de l arbre 
        boolean littleTreeUnfinished = true; // Boolean servant a savoir si on a finis de parcourir la partie actuellement envisagee de l arbre 
        boolean beOnRight = false; // Boolean servant a savoir si l on vient de la droite ou de la gauche lorsque l on remonte dans l arbre
		boolean treeUnfinished = true;
        int rightCounter = 0;
        if (n.left == null && n.right == null){
                stringTreated = "(" + n.expression + ")";
                return stringTreated;
        }
        while (treeUnfinished){ // Lorsque 
            while (littleTreeUnfinished){

                while (n.left != null){
                    beOnRight = false;
                    n = n.left;
                }
                if (!beOnRight && n.back != null){
                    stringTreated = stringTreated + "(" + n.expression + "" + n.back.expression;
                    n = n.back.right;
                }
                if (beOnRight && n.back.back != null){

                    rightCounter = 0;
                    stringTreated = "(" + stringTreated;
                    stringTreated = stringTreated + "" + n.back.expression + "" + n.expression +")";
                    n = n.back.back;
                    littleTreeUnfinished = false;
                }
				else if (beOnRight && n.back == null){
					treeUnfinished = false;
				}
                if (n.left == null && !beOnRight){
                    stringTreated = stringTreated + "" + n.expression; // + ")";
                    n = n.back; 
                    littleTreeUnfinished = false;
                }
                else if (!beOnRight){
                    rightCounter++;
                }
            }
            for (int i = 0; i < rightCounter + 1 ; i++){
                stringTreated = stringTreated + ")";
                if (n.back != null){
					n = n.back;
				}
				else{
					treeUnfinished = false;
				}
            }
            n = n.right;
            beOnRight = true;
            littleTreeUnfinished = true;
        }  
        
        return stringTreated;
    }
    
    /**
     * 
     * @pre operation est un String non null sous la forme (chiffre operateur chiffre) sans espace
     * @post renvois la sulution du calcul contenue dans operation
     * 
     */
      public static char calcul (String operation){
          String calcul = "";
          try{ // Implementation des elements necessaires a la resolution du calcul a partir d'une chaine de caracteres
              ScriptEngineManager mgr = new ScriptEngineManager(); 
              ScriptEngine engine = mgr.getEngineByName("JavaScript");
              calcul = "" + (engine.eval(operation)); // Effectue l'operation contenue dans operation
          }
          catch(Exception e){
              System.exit(0);
          }
          return calcul.charAt(0);
    } 
}