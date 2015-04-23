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
        String s = Main.chaine;
        String calculChain;
        boolean ok = true;
        while(n.left != null && n.right != null){
            while(ok){
                while (n.left != null){
                    n = n.left;
                }
                
                if (n.back.right.left == null){
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
        
        Node n = this;
        
        String stringTreated = "";
        boolean littleTreeUnfinished = true;
        boolean fromLeft = true;
        boolean beOnRight = false;
        int rightCounter = 0;
        
        while (n.left != null && n.right != null)
        {
            while (littleTreeUnfinished)
            {
                while (n.left != null)
                {
                    beOnRight = false;
                    n = n.left;
                }
                if (!beOnRight)
                {
                    stringTreated = "(" + n.carac + "" + n.back.carac + stringTreated;
                    n = n.back.right;
                }
                if (beOnRight)
                {
                    rightCounter = 0;
                    stringTreated = "(" + stringTreated;
                    stringTreated = stringTreated + "" + n.back.carac + "" + n.carac +")";
                    n = n.back.back;
                    littleTreeUnfinished = false;
                }
                if (n.left == null && !beOnRight)
                {
                    stringTreated = stringTreated + "" + n.carac + ")";
                    n = n.back; 
                    littleTreeUnfinished = false;
                }
                else if (!beOnRight)
                {
                    rightCounter++;
                }
            }
            for (int i = 0; i < rightCounter + 1; i++)
            {
                stringTreated = stringTreated + ")";
                n = n.back;
            }
            n = n.right;
            beOnRight = true;
            littleTreeUnfinished = true;
        }
        
        return stringTreated;
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