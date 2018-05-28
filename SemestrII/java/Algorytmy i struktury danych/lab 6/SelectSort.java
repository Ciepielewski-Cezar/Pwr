
public class SelectSort{
    private final Comparator comparator1;
    
    public SelectSort(Comparator comp){
        comparator1 = comp;
    }
    
    public Object[] sort(Object[] tab){
        int size = tab.length;
        for (int slot = 0; slot < size - 1; ++slot) {
            int smallest = slot;
            for (int check = slot + 1; check < size; ++check)
                if (comparator1.compare(tab[check], tab[smallest]) < 0)
                    smallest = check;
            swap(tab, smallest, slot);
        }
        return tab;
    }
    
    public void swap(Object[] tab, int left, int right){
        if (left != right) {
            Object temp = tab[left];
            tab[left] = tab[right];
            tab[right] = temp;
        }
    }
}
