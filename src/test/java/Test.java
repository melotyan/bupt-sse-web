import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hao.yan on 2016/1/8.
 */
public class Test {
    static String str = "{\"D:/file/1452152894365.jpg\":\"logo.jpg\",\"D:/file/1452152894364.png\":\"404error.png\",\"D:/file/1452152894362.png\":\"button2.png\"}";

    public static void main(String[] args) {
        Gson gson = new Gson();

        Map<String, String> map = gson.fromJson(str, new TypeToken<Map<String, String>>(){}.getType());

        for (String key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }
}
