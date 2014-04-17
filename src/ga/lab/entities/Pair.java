package ga.lab.entities;

public class Pair<T1, T2> {
    private T1 val1;
    private T2 val2;

    public Pair(T1 val1, T2 val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public T1 getVal1() {
        return val1;
    }

    public void setVal1(T1 val1) {
        this.val1 = val1;
    }

    public T2 getVal2() {
        return val2;
    }

    public void setVal2(T2 val2) {
        this.val2 = val2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (!val1.equals(pair.val1)) return false;
        if (!val2.equals(pair.val2)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = val1.hashCode();
        result = 31 * result + val2.hashCode();
        return result;
    }
}
