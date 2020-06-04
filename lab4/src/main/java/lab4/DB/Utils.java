package lab4.DB;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {
    public static final Map<String, Method> handlers = new HashMap<String, Method>() {
        {
            try {
                put("add", DBClass.class.getDeclaredMethod("add", Scanner.class));
                put("delete", DBClass.class.getDeclaredMethod("delete", Scanner.class));
                put("show_all", DBClass.class.getDeclaredMethod("show_all", Scanner.class));
                put("price", DBClass.class.getDeclaredMethod("price", Scanner.class));
                put("change_price", DBClass.class.getDeclaredMethod("change_price", Scanner.class));
                put("filter_by_price", DBClass.class.getDeclaredMethod("filter_by_price", Scanner.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static void initialize(DBClass db, int n) {
        db.create();
        db.clear();
        Scanner line;
        for (int i = 0; i < n; i++) {
            int price = 100 + (int)(Math.random() * 101);
            line = new Scanner("prod" + i + " " + price);
            db.add(line);
        }
    }

    public static void execute(DBClass db, String cmd) {
        if (cmd.charAt(0) != '/') {
            System.out.println("Invalid syntax.");
            return;
        }
        Scanner line = new Scanner(cmd.substring(1));

        if (line.hasNext()) {
            String key = line.next();
            if (!Utils.handlers.containsKey(key)) {
                System.out.println("Unknown command.");
                return;
            }

            try {
                Utils.handlers.get(key).invoke(db, line);
            } catch (Exception e) {
                System.out.println("Exception occurred in main thread! Stack trace:");
                e.printStackTrace();
            }
        }
    }
}
