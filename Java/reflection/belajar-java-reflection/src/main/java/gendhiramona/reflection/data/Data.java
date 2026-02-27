package gendhiramona.reflection.data;

public class Data<T extends AutoCloseable> {

    private T data;

    public Data() {
    }

    public Data(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
