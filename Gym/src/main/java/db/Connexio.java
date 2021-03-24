package db;

public interface Connexio<T> {
    public T start();
    public void close();
}
