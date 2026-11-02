import java.util.*;

public class LinkedListWithCursor<T> {
    //Importante: No cambiar ni los nombres ni la visibilidad
    Node<T> left;
    Node<T> right;
    int sizeLeft;
    int sizeRight;

    public LinkedListWithCursor() {
        left = null;
        right = null;
        sizeLeft = 0;
        sizeRight = 0;
    }


    public LinkedListWithCursor(Collection<T> col, int index) {
        // TODO Ejercicio 1
        if (col == null || index < 0 || index > col.size()) {
            throw new IndexOutOfBoundsException();
        }
        List<T> todos = new ArrayList<>(col);
        for (int i = 0; i < index; i++) {
            left = new Node<>(todos.get(i), left);
        }
        sizeLeft = index;

        for (int i = todos.size() - 1; i >= index; i--) {
            right = new Node<>(todos.get(i), right);
        }
        sizeRight = todos.size() - index;
    }


    public int index() {
        // TODO Ejercicio 2
        return sizeLeft;
    }


    public int size() {
        // TODO Ejercicio 3
        return sizeLeft + sizeRight;
    }


    public T current() {
        // TODO Ejercicio 4
        if (right == null) {
            return null;
        }
        return right.data;
    }


    public void add(T item) {
        // TODO Ejercicio 5
        left = new Node<>(item, left);
        sizeLeft++;
    }


    public T remove() {
        // TODO Ejercicio 6
        if (right == null) {
            throw new NoSuchElementException();
        }
        T elem = right.data;
        right = right.next;
        sizeRight--;
        return elem;
    }


    public T next() {
        // TODO Ejercicio 7
        if (right == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = right;
        right = right.next;
        node.next = left;
        left = node;
        sizeRight--;
        sizeLeft++;

        return node.data;
    }


    public T previous() {
        // TODO Ejercicio 8
        if (left == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = left;
        left = left.next;
        node.next = right;
        right = node;
        sizeLeft--;
        sizeRight++;

        return node.data;
    }


    public T moveTo(int index) {
        // TODO Ejercicio 9
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        while (sizeLeft < index) {
            next();
        }
        while (sizeLeft > index) {
            previous();
        }

        return current();
    }

    /**
     * Devuelve la lista con el formato [izquierda][*derecha], por ejemplo
     * "[l, u][*n, a]", "[][*s, o]", "[s, o][*]" o "[][*]".
     *  NO LO MODIFIQUES.
     */
    @Override
    public String toString() {
        // La cadena izquierda está en orden inverso: se copia a un array al revés
        Object[] leftItems = new Object[sizeLeft];
        Node<T> n = left;
        for (int i = sizeLeft - 1; i >= 0 && n != null; i--) {
            leftItems[i] = n.data;
            n = n.next;
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < leftItems.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(leftItems[i]);
        }
        sb.append("][*");
        n = right;
        boolean first = true;
        while (n != null) {
            if (!first) sb.append(", ");
            sb.append(n.data);
            first = false;
            n = n.next;
        }
        return sb.append("]").toString();
    }
}
