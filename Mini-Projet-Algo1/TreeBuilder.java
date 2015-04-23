 

public class TreeBuilder implements TreeBuilderIF{
    
    public String elem;
    
    public TreeBuilder(String elem){
        this.elem = elem;
    }
            
    public ExprIF build(){
        Pile s = new Pile();
        Node tempon = new Node(' ');
        Node n = new Node(' ');
        Node n1 = new Node(' ');
        Node n2 = new Node(' ');
        char carac;
        for (int i = 0; i < (elem.length()-1); i++){
            carac = elem.charAt(i);
            if (carac != '(' && carac !=')'){
                tempon.carac = carac;
                s.push(tempon);
            }
            else if (carac == ')'){
                n2 = s.pop();
                n = s.pop();
                n1 = s.pop();
                n1.back = n;
                n2.back = n;
                n.left = n1;
                n.right = n2;
                s.push(n);
            }
        }
        return s.pop();
    }
}