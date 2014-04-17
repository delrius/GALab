package ga.lab.operators;

public abstract class BinaryOperator<T> implements IOperator {

    protected T val1;
    protected T val2;
    public BinaryOperator(T val1, T val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
    public T[] perform() {
        return doPerform();
    }

    protected abstract T[] doPerform();
}
