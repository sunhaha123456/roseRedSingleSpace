    thymeleaf 相关
1、thymeleaf模板html中注释：
        单行注释    <!--/* xxx */-->
        多行注释    <!--/* xxx xxx */-->
2、引入css、js demo：
    css：<link rel="stylesheet" th:href="@{/static/res/easyui/themes/icon.css}" />
    js ：<script th:src="@{/static/res/easyui/jquery.min.js}" />
3、thymeleaf前端获取后端传入数据：
    3.1、后台情形：request.setAttribute("keyName", "value");
        a、js脚本中获取：var key = [[${keyName}]];
    3.2、获取后台session中数据：
        <div th: obj ect = "${session.user}">
         <p>Name:< span th: text = "*{firstName}"></span></p>
         <p>Lastname:<span th: text = " *{lastName}"></span></p>
        </div>
4、thymeleaf 标签获取 contextPath：
<script type="text/javascript" th:inline="javascript">
    /*<![CDATA[*/
    contextPath = [[@{/}]];
    /*]]>*/
</script>
js中添加，否则引号或者"<"">"等不能用
5、字符串拼接：
    <td th:text="${info}+'str'"></td>
6、ajax中，url拼接方式：
    /*<![CDATA[*/
    $.ajax({
        async: false,
        url: [[@{/login/verify}]],
        type: "POST",
        ...
     /*]]>*/

================================================================================
1、菜单url前，不能带 /，即：user/menu/toUserManage 而非 /user/menu/toUserManage
2、对应关系：
    <link rel="stylesheet" th:href="@{/static/res/easyui/themes/easy.common.css}" />
        <link rel="stylesheet" th:href="@{/static/res/easyui/themes/default/easyui.css}" />
        <link rel="stylesheet" th:href="@{/static/res/easyui/themes/icon.css}" />

    <script th:src="@{/static/res/easyui/easy.common.js}" />
        <script th:src="@{/static/res/easyui/jquery.min.js}" />
        <script th:src="@{/static/res/easyui/jquery.easyui.min.js}" />
        <script th:src="@{/static/res/easyui/locale/easyui-lang-zh_CN.js}" />