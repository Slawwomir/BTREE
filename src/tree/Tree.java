package tree;

import model.Record;

public interface Tree {

    public abstract Boolean insert(Record record);

    public abstract Boolean remove(Integer key);

    public abstract Boolean update(Record record);

    public abstract Record search(Integer key);
}
