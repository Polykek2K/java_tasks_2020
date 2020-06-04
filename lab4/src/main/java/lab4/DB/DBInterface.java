package lab4.DB;

import java.util.Scanner;

public interface DBInterface {
    public abstract void create();
    public abstract void clear();
    public abstract void disconnect();

    public abstract void add(Scanner s);
    public abstract void delete(Scanner s);
    public abstract void show_all(Scanner s);
    public abstract void price(Scanner s);
    public abstract void change_price(Scanner s);
    public abstract void filter_by_price(Scanner s);
}
