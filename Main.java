/*
 * Exemple de test sans lecture et écriture des fichiers
 */
 

public class Main {
    static String chaine;
    public static void main(String[] args) {
        TreeBuilderIF tb = new TreeBuilder("( ( 1 + 2 ) * 3 )");
        ExprIF t = tb.build();
        //... TO DO
    }
}
