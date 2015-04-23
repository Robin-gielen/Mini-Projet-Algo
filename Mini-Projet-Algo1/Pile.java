

public class Pile{
    
    private Node head;
    private Node tempon;
    
    public Pile(){
        head = null;
    }

    public void push(Node n){
        if (isEmpty()){
            head = n;
        }
        else{
            n.right = head;
            head.back = n;
            head = n;
        }
    }
    
    public Node pop(){
        tempon = null;
        if (!isEmpty()){
            if (head.right != null){
                tempon = head;
                head = head.right;
                head.back = null;
            }
            else{
                tempon = head;
                head = null;
			}
        }
			
		return tempon;
	}
	
	public boolean isEmpty(){
		return (head == null);
	}
}