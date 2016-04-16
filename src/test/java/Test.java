import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by hao.yan on 2016/1/8.
 */
class PrivateClass {
    private int i = 3;

    public int getI() {
        return i;
    }
    private String privateMethod(String name, Integer year) {//一定要用Integer, 不可以用int
        return "hello " + name + " is " + year + " years old";
    }
}
public class Test {
    private int i;
    public static void main(String[] args) throws Exception {
        PrivateClass p = new PrivateClass();
        Class<?> classClass = p.getClass();
        Field field = classClass.getDeclaredField("i");
        Method method = classClass.getDeclaredMethod("privateMethod", new Class[] {String.class, Integer.class});
        field.setAccessible(true);
        method.setAccessible(true);
        field.set(p, 10);
        System.out.println(method.invoke(p, new Object[] {"yanhao", 20}));
        System.out.println(p.getI());
    }

}
