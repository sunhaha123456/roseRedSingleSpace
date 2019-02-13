$(function () {
    //nav初始化
	$('#mainMenuNav').tree({
		// url : '',
		lines : true,
		onClick : function (node) {
			if (node.url) {
				if ($('#tabs').tabs('exists', node.text)) {
					$('#tabs').tabs('select', node.text);
				} else {
                    var content = '<div style="padding:0px;overflow:hidden;width:100%;height:100%;"><iframe scrolling="auto" frameborder="0" src="' + node.url + '.html" style="width:100%;height:100%;"></iframe></div>';
					$('#tabs').tabs('add', {
						title : node.text,
						iconCls : node.iconCls,
						closable : true,
                        content : content,
                    });
					tabClose();
				}
			} else {
                $(this).tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);
            }
		},
        data :
            [
                {
                    "id":1,
                    "text":"系统模块",
                    "iconCls":"icon-system",
                    "children" : [
                        {
                            "text" : "管理员管理",
                            "iconCls":"icon-manager",
                            "url":"manager"
                        }
                    ]
                }, {
                "id":2,
                "text":"会员模块",
                "iconCls":"icon-user",
                "state":"closed",
                "children":[
                    {
                        "text":"会员管理",
                        "iconCls":"icon-group",
                        "url":"user"
                    }
                ]
            }
        ]
	});
	
	$('#tabs').tabs({
		fit : true,
		border : false
	});
	
	tabClose();
	tabCloseEven();
});

//tab close
function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children("span").text();
		$('#tabs').tabs('close',subtitle);
	})

	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY,
		});
		
		var subtitle =$(this).children("span").text();
		$('#mm').data("currtab",subtitle);
		
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven() {
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});	
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!=currtab_title)
				$('#tabs').tabs('close',t);
		});	
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}