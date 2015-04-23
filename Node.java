import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

class Node implements ExprIF{
    char carac;
    Node left;
    Node right;
    Node back;
    public Node(char carac){
        this.carac = carac;
        left = null;
        right = null;
        back = null;
    }
    
    public ExprIF getReducedTree(){
        Node n = this;
        char z;
        String s = Main.chaine;
        String calculChain;
        boolean ok = true;
        while(n.left != null && n.right != null){
            while(ok){
                while (n.left != null){
                    n = n.left;
                }
                z = n.back.right.carac;
                if (z != '+' && z != '-' && z != '/' && z != '*'){
                    calculChain = (n.carac + "" + n.back.carac + "" + n.back.right.carac+ "" ); 
                    n.back.carac = calcul(calculChain);
                    n.back.left = null;
                    n.back.right = null;
                    ok = false;
                }
                else{
                    n = n.back.right;
                }
            }
            ok = true;
            n = this;
            s = s + (n.toString() + ' ');
        }
        Main.chaine = (s + "/n");
        return n;
    }

    public String toString(){
        return "lol";
    }
    
      public static char calcul (String s){
          String suice = "";
          try{
              ScriptEngineManager mgr = new ScriptEngineManager();
              ScriptEngine engine = mgr.getEngineByName("JavaScript");
              suice = "" + (engine.eval(s));
          }
          catch(Exception e){
              System.out.println("lol");
          }
          return suice.charAt(0);
    } 
}