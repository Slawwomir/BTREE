package tree;

import model.Record;

public class BTree implements Tree {

    private final String dataFile;
    private final Integer nodeDegree;
    private final Integer leafDegree;
    private final Integer root;

    public BTree(Integer nodeDegree, Integer leafDegree, String dataFile) {
        this(0, nodeDegree, leafDegree, dataFile);
    }

    public BTree(Integer root, Integer nodeDegree, Integer leafDegree, String dataFile) {
        this.root = root;
        this.nodeDegree = nodeDegree;
        this.leafDegree = leafDegree;
        this.dataFile = dataFile;
    }

    @Override
    public Boolean insert(Record record) {
/*
        Algorytm (schemat)
        1. Wyszukaj x wg algorytmu wyszukiwania klucza.
        2. Jeżeli znaleziony, to RETURN (Already_Exists).
        3. Sprawdź na bieżącej stronie, czy m < 2d. Jeżeli TAK,
                to wstaw parę (x,a) na tę stronę i RETURN (OK).
         PRZEPEŁNIENIE!
        4. Spróbuj wykonać kompensację (opis poniżej).
        5. Jeżeli kompensacja była możliwa, to RETURN (OK).
         KOMPENSACJA NIEMOŻLIWA!
        6. Dokonaj rozszczepienia przepełnionej strony (opis poniżej).
        7. Uczyń stronę przodka stroną bieżącą. Idź do kroku 3.

*/
        search(record.getKey());

        return null;
    }

    @Override
    public Boolean remove(Integer key) {
        return null;
    }

    @Override
    public Boolean update(Record record) {
        return null;
    }

    @Override
    public Record search(Integer key) {
        if (root == null)
            return null;


        return null;
    }

    public int getBlockSize() {
        return (2 + 4 * nodeDegree + 1) * Integer.BYTES;
    }
}
