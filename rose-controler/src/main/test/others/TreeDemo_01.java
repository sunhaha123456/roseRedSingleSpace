package others;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class TreeDemo_01 {
	
    @Data
    @AllArgsConstructor
    public static class RecordMenu {
        private Long id;
        private Long pid;
        private String name;
    }

    /**
     * 功能：递归 对主目录下菜单list 排序
	 * 备注：适用于逐次单目录结构
     * @param id     主目录id
     * @param slist  主目录id 对应目录下的 目录列表list，也就是要排序的主目录下的目录list
     * @param dlist  存储排序后主目录菜单
     */
    public static void menuTree(Long id, List<RecordMenu> slist, List<RecordMenu> dlist) {
        for (RecordMenu re : slist) {
            if (re.getPid().equals(id)) {
                dlist.add(re);
                menuTree(re.getId(), slist, dlist);
            }
        }
    }

    public static void main(String[] args) {
        RecordMenu menu1 = new RecordMenu(1L, 0L, "第一级目录");
        RecordMenu menu2 = new RecordMenu(2L, 1L, "第二级目录");
        RecordMenu menu3 = new RecordMenu(3L, 2L, "第三级目录");
        RecordMenu menu4 = new RecordMenu(4L, 3L, "第四级目录");
        RecordMenu menu5 = new RecordMenu(5L, 4L, "第五级目录");

        List<RecordMenu> slist = new ArrayList<>();
        slist.add(menu2);
        slist.add(menu1);
        slist.add(menu4);
        slist.add(menu5);
        slist.add(menu3);

        List<RecordMenu> dlist = new ArrayList<>();
        menuTree(menu1.getId(), slist, dlist);
    }
}