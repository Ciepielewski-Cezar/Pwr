/**
 * Created by Czarek on 16.05.2017.
 */
public class Drzewo {
    private Wezel root;
    //private final Comparator comparator;

    public void insert(int x){
        root = insert(x, root);
    }

    protected Wezel insert(int x, Wezel t){
        if ( t == null){
            t = new Wezel(x);
        } else {
            int cmp = t.value - x;

            if (cmp > 0) t.left = insert(x, t.left);
            else if (cmp < 0) t.right = insert(x, t.right);
            else throw new IllegalArgumentException();
        }
        return t;
    }

    
    public Object find(int x){
        Wezel t = search(x);
        return t != null ? t.value : null;
    }

    private Wezel search(int value){
        Wezel wezel = root;
        int cmp = 0;
        while (wezel != null &&(cmp = (value - wezel.value)) !=0)
            wezel = cmp < 0 ? wezel.left : wezel.right;
        return wezel;
    }

    
    public int min(){
        Wezel wezel = root;
        while(wezel.left != null){
            wezel = wezel.left;
        }
        return wezel.value;
    }

    public int max(){
        Wezel wezel = root;
        while(wezel.right != null){
            wezel = wezel.right;
        }
        return wezel.value;
    }

    
    public int wysokosc(){
        if (root == null)
            return -1;
        else{
            return wysokosc(root);
        }
    }
    
    private int wysokosc(Wezel x){
        if (x == null) {
            return -1;
        }
    
        int leftH = wysokosc(x.left);
        int rightH = wysokosc(x.right);

        if (leftH > rightH) {
            return leftH + 1;
        } else {
            return rightH + 1;
        }
    }
    
    
    public int liczbaWezlow(){
        if (root == null)
            return -1;
        else{
            return liczbaWezlow(root);
        }
    }
    
    private int liczbaWezlow(Wezel x){
        if (x == null){
            return 0;
        }
        
        return 1 + liczbaWezlow(x.left) +  liczbaWezlow(x.right);
    }
    
    
    public int liczbaLisci(){
        if (root == null)
            return -1;
        else{
            return liczbaLisci(root);
        }
    }
    
    private int liczbaLisci(Wezel x){
        if (x == null){
            return 0;
        }
        if (x.left == null && x.right == null) {
            return 1;
        }

        return liczbaLisci(x.left) +  liczbaLisci(x.right);
    }
    
    
    public int liczbaWew(){
        if (root == null)
            return -1;
        else{
            return liczbaWew(root);
        }
    }
    
    private int liczbaWew(Wezel x){
        if (x == null){
            return 0;
        }
        if (x.left != null || x.right != null) {
            return 1 + liczbaWew(x.left) + liczbaWew(x.right);
        } else{
            return 0;
        }
    }
    
        public void poziomami(){
        if (root == null)
            return ;
        else{
            int h = wysokosc()+1;
            for (int i = 1; i <= h; i++){
                poziomami(root, i);
                System.out.println();
            }
        }
    }
    
    private void poziomami(Wezel x, int poziom){
        if (x == null){
            return ;
        }
        
        if (poziom == 1){
            System.out.print( x.value + ", ");
        }
        else if( poziom > 1){
            poziomami(x.left, poziom - 1);
            poziomami(x.right, poziom - 1);
            
        }
    }


    public void preOrder(Wezel x){
        if(x != null){
            System.out.println(x.value);
            preOrder(x.left);
            preOrder(x.right);
        }
    }

    public void preOrder(){
        if(root != null){
            System.out.println(root.value);
            preOrder(root.left);
            preOrder(root.right);
        }
    }



    public void inOrder(Wezel x){
        if(x != null){
            inOrder(x.left);
            System.out.println(x.value);
            inOrder(x.right);
        }
    }

    public void inOrder(){
        if(root != null){
            inOrder(root.left);
            System.out.println(root.value);
            inOrder(root.right);
        }
    }



    public void postOrder(Wezel x){
        if(x != null){
            postOrder(x.left);
            postOrder(x.right);
            System.out.println(x.value);
        }
    }

    public void postOrder(){
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.value);
        }
    }


    public Wezel getRoot(){ return root;}




}