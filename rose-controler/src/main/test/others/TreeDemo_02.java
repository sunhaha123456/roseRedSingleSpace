package others;

import com.rose.common.util.JsonUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class TreeDemo_02 {

    @Data
    public static class RecordMenu {
        private Long id;
        private Long pid;
        private String name;
        private List<RecordMenu> menus = new ArrayList<>();

        public RecordMenu(Long id, Long pid, String name) {
            this.id = id;
            this.pid = pid;
            this.name = name;
        }
    }

    /**
     * 功能：递归遍历 指定目录
     * @param menu  主目录元素
     * @param slist 主目录下所有目录元素无序list
     */
    public static void menuTree(RecordMenu menu, List<RecordMenu> slist) {
        for (RecordMenu re : slist) {
            if (re.getPid().equals(menu.getId())) {
                menu.getMenus().add(re);
                menuTree(re, slist);
            }
        }
    }

    public static void main(String[] args) {
        RecordMenu menu1 = new RecordMenu(1L, 0L, "第一级目录");
        RecordMenu menu2 = new RecordMenu(2L, 1L, "第二级目录1");
        RecordMenu menu3 = new RecordMenu(3L, 2L, "第三级目录1");
        RecordMenu menu4 = new RecordMenu(4L, 3L, "第四级目录");
        RecordMenu menu5 = new RecordMenu(5L, 4L, "第五级目录1");

        RecordMenu menu6 = new RecordMenu(6L, 1L, "第二级目录2");
        RecordMenu menu7 = new RecordMenu(7L, 2L, "第三级目录2");
        RecordMenu menu8 = new RecordMenu(8L, 2L, "第三级目录3");
        RecordMenu menu9 = new RecordMenu(9L, 4L, "第五级目录2");

        List<RecordMenu> slist = new ArrayList<>();
        slist.add(menu2);
        slist.add(menu1);
        slist.add(menu4);
        slist.add(menu5);
        slist.add(menu3);
        slist.add(menu6);
        slist.add(menu7);
        slist.add(menu8);
        slist.add(menu9);

        // 进行遍历
        menuTree(menu1, slist);

        // 打印输出结果树
        System.out.println(JsonUtil.objectToJson(menu1));
    }
}