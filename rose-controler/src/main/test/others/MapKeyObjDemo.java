package others;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能：map将比对hashcode 和 equals，作为key是否重复的判断
 * @author sunpeng
 * @date 2018
 */
public class MapKeyObjDemo extends Object {

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    public static void main(String[] args) {
        Map<Object, String> map = new HashMap<>();
        MapKeyObjDemo a1 = new MapKeyObjDemo();
        MapKeyObjDemo a2 = new MapKeyObjDemo();
        map.put(a1, "1");
        map.put(a2, "2");
        System.out.println(map.size());
        System.out.println(map.get(a1));
        System.out.println(map.get(a2));
        System.out.println("11111111111111111111");
        System.out.println(a1.equals(a2));
    }
}