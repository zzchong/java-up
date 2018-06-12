package base;

/**
 * Created by pc on 2018/5/18.
 */
public class Key {
    private String key;

    public Key(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key1 = (Key) o;

        return key != null ? key.equals(key1.key) : key1.key == null;
    }

    @Override
    public int hashCode() {
        return 10;
    }

    @Override
    public String toString() {
        return getKey();
    }
}
