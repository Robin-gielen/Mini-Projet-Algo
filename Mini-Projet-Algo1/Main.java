

public class Main {
    static String chaine;
    public static void main(String[] args) {
        TreeBuilderIF tb = new TreeBuilder("((1+2)*3)");
        ExprIF t = tb.build();
        t.getReducedTree();
        System.out.println(chaine);
    }
}