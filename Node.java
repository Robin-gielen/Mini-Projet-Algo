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
    }
    
    /**
     * Simplification de l'arbre.
     *
     * @pre l'arbre représente une expression arithmétique 
     *      bien construite
     * @post Si l'arbre contient au moins un opérateur, 
     *       l'arbre renvoyé est obtenu après une simplification 
     *       dans l'ordre d'un parcours postfixe
     *       Sinon, renvoit l'arbre original.
     */
    public ExprIF getReducedTree(){
        Node node = this;
        String chain = Main.chaine;
        String calculChain;
        boolean subtreefinished = true;
        
        while(node.left != null && node.right != null){
            while(subtreefinished){
                while (node.left != null){
                    node = node.left;
                }
                if (node.back.right.left == null){
                    calculChain = (node.expression + "" + node.back.expression + "" + node.back.right.expression+ "" ); 
                    node.back.expression = calcul(calculChain);
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
            chain = chain + (node.toString() + "   ");
        }
        Main.chaine = (chain + "/n");
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
        return "lol";
    }
    
    /**
     * 
     * @pre operation est un String non null sous la forme (chiffre operateur chiffre) sans espace
     * @post renvois la sulution du calcul contenue dans operation
     * 
     */
      public static char calcul (String operation){
          String calcul = "";
          try{
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