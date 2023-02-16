import java.util.*;

public class NdArray<E> extends AbstractCollection<E> {
    //private ArrayList<E> list;
    private Object[] list;
    private ArrayList<Integer> dims = new ArrayList<>();
    private int size;

    public NdArray(Integer firstDimLen, Integer ...furtherDimLens) throws NegativeArraySizeException{
        if(firstDimLen < 0) {
            throw new NegativeArraySizeException(String.format("Illegal dimension size %d.",firstDimLen));
        }
        this.dims.add(firstDimLen);
        for(Integer dim : furtherDimLens) {
            if(dim < 0) {
                throw new NegativeArraySizeException(String.format("Illegal dimension size %d.",dim));
            }
            this.dims.add(dim);
        }
        //this.list = new ArrayList<>(dims.stream().reduce(1, (a, b) -> a * b));
        this.size = dims.stream().reduce(1, (a, b) -> a * b);
        this.list = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public E get(int... indices) {
        //return list.get(getIndex(indices));
        return (E) list[getIndex(indices)];
    }

    public void set(E item, int... indices) {
        //list.set(getIndex(indices), item);
        list[getIndex(indices)] = item;
    }

    public int[] getDimensions() {
        int[] dimArray = new int[dims.size()];
        for(int i=0; i<dims.size(); i++) {
            dimArray[i] = dims.get(i);
        }
        return dimArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new NAIterator();
    }

    private class NAIterator implements Iterator<E> {
        private int pos = 0;        // Iteroinnin nykyindeksi (liittyen taulukkoon vals).

        @Override
        public boolean hasNext() {
            return pos < size;        // Onko nykykohta vielä taulukon sisällä?
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if(pos >= size) {         // Heitetään poikkeus, ellei seuraavaa alkiota ole.
                throw new NoSuchElementException("No more values in the array!");
            }
            return (E) list[pos++];   // Palautetaan iteroitavan indeksin alkio taulukosta vals.
        }                           // ja kasvatetaan iteroitavaa indeksiä askel eteenpäin.
    }


    @Override
    public int size() {
        return dims.stream().reduce(1, (a,b) -> a*b);
    }

    private int getIndex(int[] ints) {
        if (ints.length != dims.size()) {
            throw new IllegalArgumentException(String.format("The array has %d dimensions but %d indices were given.",
                    dims.size(), ints.length));
        }

        for (int i=0; i<ints.length; i++) {
            if (ints[i] < 0 || ints[i] > dims.get(i)-1) {
                throw new IndexOutOfBoundsException(String.format("Illegal index %d for dimension %d of length %d.",
                        ints[i], i+1, dims.get(i)));
            }
        }
        if (ints.length == 1) {
            return 0;
        }

        int index = ints[1] + dims.get(1) * ints[0]; //Indeksi 2D-tasolla
        if (ints.length > 2) { //Haetaan indeksi jos suurempi kuin 2D
            for (int i=2; i<ints.length; i++) {
                index = ints[i] + dims.get(i) * index;
            }
        }
        return index;
    }
}
