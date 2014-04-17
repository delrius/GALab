package ga.lab.operators;

public abstract class UnaryOperator<T> implements IOperator {
    protected T val;

    public UnaryOperator(T val) {
        this.val = val;
    }

    public T perform() {
        return doPerform();
    }

    protected abstract T doPerform();
}
