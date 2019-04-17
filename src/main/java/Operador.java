public class Operador {
    public static <T extends Comparable<T>> T[] ordenarElemento(T[] a) {

        T temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 1; j < a.length; j++) {
                if(a[j] != null && a[j-1] != null && (a[j].compareTo(a[j-1]) < 0)) {
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
        return a;
    }
}
