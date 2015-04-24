

public class Main {
    static String chaine = "";
    public static void main(String[] args){
        String[] tab = stringExtractor.stringExtracteur("exprs.txt");
        for(int i = 0; i < tab.length; i++){
            TreeBuilderIF tb = new TreeBuilder(tab[i]);
            ExprIF t = tb.build();
            t.getReducedTree();
            System.out.println(chaine);
            chaine = "";
        }
    }
}
