//打开的最大页签数量, 0：代表不限制
var MAX_TAB_SIZE = 20;
$(function(){
	 tabCloseEven();
	/* $('#tabs').tabs('add', {
	        title: '个人台账',
	        content: createFrame(ctx+'/home/index'),
	        closable: false
	    });*/
   
});
//选择项部1级分类菜单
function selectMenu(menuName, obj){
    $(".tophead_selected").removeClass("tophead_selected");
    $(obj).parent().addClass("tophead_selected");
	
    initLeftMenu(menus[menuName]);
}
//有节点的一级菜单
function getElement(title,url,icon){
	if($.trim(icon)!=""){
		icon = ctx+icon;
	}else{
		icon = ctx+"/static/portal/style/images/s.gif";
	}
	
	return'<li class="fr-tree-node">'+
		'<div url="'+url+'" title="'+title+'"class="clickDiv fr-tree-node-el fr-tree-node-el-accordion-root fr-tree-node-collapsed" unselectable="on">'+
			'<span class="fr-tree-node-right"></span><span'+
				' class="fr-tree-node-left"></span><span'+
				' class="fr-tree-node-indent"></span>'+
				'<img class="fr-tree-elbow-plus-accordion" style="float: left;"  src="'+icon+'">'+
			'<div class="fr-tree-node-textwrap">'+
				'<span title="'+title+'" unselectable="on"'+
					'class="fs-tree-node-content">'+title+'</span>'+
			'</div>'+
	'</div>';
'</li>';
}
//无节点的一级菜单
function getElement3(title,url,icon,i){
	var cl='clickDiv fr-tree-node-el fr-tree-node-cpt fr-tree-node-el-accordion fs-tree-node-el-odd';
	if(i%2==0){
		cl='clickDiv fr-tree-node-el fr-tree-node-cpt fr-tree-node-el-accordion fs-tree-node-el-even';
	}
	if($.trim(icon)!=""){
		icon = ctx+icon;
	}else{
		icon = ctx+"/static/portal/style/images/s.gif";
	}
	
	return'<li class="fr-tree-node">'+
			'<div url="'+url+'" title="'+title+'" class="'+cl+'" unselectable="on" id="bbtree1444817006742_105">'+
			'<span class="fr-tree-node-right"></span>'+
			'<span class="fr-tree-node-left"></span>'+
			'<span class="fr-tree-node-indent"></span>'+
			'<img class="fr-tree-elbow fr-tree-leaf-cpticon" style="float: left;" src="'+icon+'">'+
			'<div class="fr-tree-node-textwrap"><span title="'+title+'" unselectable="on" class="fs-tree-node-content">'+title+'</span>'+
			'</div></div>'+
'</li>';
}
//二级菜单
function getElement2(title,url,icon,i){
	var cl='clickDiv fr-tree-node-el fr-tree-node-cpt fr-tree-node-el-accordion fs-tree-node-el-odd';
	if(i%2==0){
		cl='clickDiv fr-tree-node-el fr-tree-node-cpt fr-tree-node-el-accordion fs-tree-node-el-even';
	}
	var imgurl = ctx+"/static/portal/style/images/s.gif";
	if($.trim(icon)!=""){
		icon = ctx+icon;
	}else{
		icon = ctx+"/static/portal/style/images/s.gif";
	}
	

	return'<li class="fr-tree-node">'+
		'<div url="'+url+'" title="'+title+'"class="'+cl+'" unselectable="on">'+
			'<span class="fr-tree-node-right"></span><span'+
				' class="fr-tree-node-left"></span><span'+
				' class="fr-tree-node-indent"><img class="fr-tree-icon" src="'+imgurl+'"></span><img'+
				' class="fr-tree-elbow fr-tree-leaf-cpticon"'+
				'style="float: left;" src="'+icon+'">'+
			'<div class="fr-tree-node-textwrap">'+
				'<span title="'+title+'" unselectable="on"'+
					'class="fs-tree-node-content">'+title+'</span>'+
			'</div>'+
	'</div>';
'</li>';
}
//两级菜单
function initLeftMenu(menus){
    var navigation$ = $("#navigationUl");
    navigation$.html("");
    
    $.each(menus, function(i, menu){
    	var m ;
    	if(menu.childs){
    		 m =  getElement(menu.name,"",menu.icon);	
    	}else{
    		m =  getElement3(menu.name,menu.src,menu.icon,i);
    	}
    
        var childesTr = '<ul style="display:'+(i==0?"block;":"none;")+'" class="fr-tree-node-ct">';
        if(menu.childs)
        $.each(menu.childs, function(i, child){
            childesTr +=  getElement2(child.name,child.src,child.icon,i);
        });
        navigation$.append('<li class="fr-tree-node">'+$(m).html()+childesTr+"</ul></li>");
    });
    $(".clickDiv").click(function(){
        var $this = $(this);
        var href = $this.attr('url');
        var title = $this.attr('title');
       
        if($this.parent().find("ul")){
        	if($this.parent().find("ul").css("display")!="block"){
        		$this.parent().find("ul").show(500);
        	}else{
        		$this.parent().find("ul").hide(100);
        	}
        }
        if(href!=""){
        	 addTab(title, href);
        	 $(".clickDiv").removeClass("fr-tree-node-el-accordion-selected-leaf");
        	 $(this).removeClass("fr-tree-node-accordion-over-root");
        	 $(this).addClass("fr-tree-node-el-accordion-selected-leaf");
        	
        }
    });
    
    $(".clickDiv").mouseover(function(){
    	if( !$(this).hasClass("fr-tree-node-el-accordion-selected-leaf"))
    	$(this).addClass("fr-tree-node-accordion-over-root");
    });
    $(".clickDiv").mouseout(function(){
    	$(this).removeClass("fr-tree-node-accordion-over-root");
    });
    if($(".clickDiv[url*='/']:eq(0)").attr("url")!='/pwf/judgeInfo/judgeinfoList'){
        $(".clickDiv[url*='/']:eq(0)").click();

    }
    
}


function addTab(title, url){
    if ($('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('select', title);//选中并刷新
        var currTab = $('#tabs').tabs('getSelected');
        //var url = $(currTab.panel('options').content).attr('src');
        if (url != undefined && currTab.panel('options').title != 'Home') {
            $('#tabs').tabs('update', {
                tab: currTab,
                options: {
                    content: createFrame(ctx+url)
                }
            })
        }
    }
    else {
        if (MAX_TAB_SIZE && $("#tabs").tabs("tabs").length > MAX_TAB_SIZE) {
            $.messager.alert('提示信息', '您当前打开了太多的页面，如果继续打开，会造成程序运行缓慢，无法流畅操作！', 'error');
            return;
        }
        var content = createFrame(ctx+url);
        $('#tabs').tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    }
    tabClose();
}

function createFrame(url){
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose(){
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close', subtitle);
    })
	
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind('contextmenu', function(e){
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        
        var subtitle = $(this).children(".tabs-closable").text();        
        $('#mm').data("currtab", subtitle);
        $('#tabs').tabs('select', subtitle);
		
        return false;
    });
}

//绑定右键菜单事件
function tabCloseEven(){
    //刷新
    $('#mm-tabupdate').click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        if (url != undefined && currTab.panel('options').title != 'Home') {
            $('#tabs').tabs('update', {
                tab: currTab,
                options: {
                    content: createFrame(url)
                }
            })
        }
    })
    //关闭当前
    $('#mm-tabclose').click(function(){
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    })
    //全部关闭
    $('#mm-tabcloseall').click(function(){
        $('.tabs-inner span').each(function(i, n){
            var t = $(n).text();
            if (t != 'Home' && t!='个人台账') {
                $('#tabs').tabs('close', t);
            }
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function(){
        var prevall = $('.tabs-selected').prevAll();
        var nextall = $('.tabs-selected').nextAll();
        if (prevall.length > 0) {
            prevall.each(function(i, n){
                var t = $('a:eq(0) span', $(n)).text();
                if (t != 'Home' && t!='个人台账') {
                    $('#tabs').tabs('close', t);
                }
            });
        }
        if (nextall.length > 0) {
            nextall.each(function(i, n){
                var t = $('a:eq(0) span', $(n)).text();
                if (t != 'Home' && t!='个人台账') {
                    $('#tabs').tabs('close', t);
                }
            });
        }
        return false;
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function(){
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            //msgShow('系统提示','后边没有啦~~','error');
            alert('后边没有啦~~');
            return false;
        }
        nextall.each(function(i, n){
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function(){
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function(i, n){
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });
    
    //退出
    $("#mm-exit").click(function(){
        $('#mm').menu('hide');
    })
}

//关闭当前页，子页调用此方法以关闭页面
function closeCurrentTab(){
    var currTab = $('#tabs').tabs('getSelected');
    var currtab_index = $('#tabs').tabs('getTabIndex', currTab);
    
    $('#tabs').tabs('close', currtab_index);
}

//换肤
function replaceTheme(){
    var themes = {
        'gray': '../plugins/easyui/themes/gray/easyui.css',
        'black': '../plugins/easyui/themes/black/easyui.css',
        'bootstrap': '../plugins/easyui/themes/bootstrap/easyui.css',
        'default': '../plugins/easyui/themes/default/easyui.css',
        'metro': '../plugins/easyui/themes/metro/easyui.css',
        'pepper-grinder': '../plugins/easyui/themes/pepper-grinder/easyui.css',
        'blue': '../plugins/easyui/themes/default/easyui.css',
        'cupertino': '../plugins/easyui/themes/cupertino/easyui.css',
        'dark-hive': '../plugins/easyui/themes/dark-hive/easyui.css',
        'sunny': '../plugins/easyui/themes/sunny/easyui.css'
    };
    
    var skins = $('.li-skinitem span').click(function(){
        var $this = $(this);
        if ($this.hasClass('cs-skin-on')) 
            return;
        skins.removeClass('cs-skin-on');
        $this.addClass('cs-skin-on');
        var skin = $this.attr('rel');
        $('#swicth-style').attr('href', themes[skin]);
        setCookie('cs-skin', skin);
        skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
    });
    
    if (getCookie('cs-skin')) {
        var skin = getCookie('cs-skin');
        $('#swicth-style').attr('href', themes[skin]);
        $this = $('.li-skinitem span[rel=' + skin + ']');
        $this.addClass('cs-skin-on');
        skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
    }
}

function setCookie(name, value){//两个参数，一个是cookie的名子，一个是值
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date(); //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

function getCookie(name){//取cookies函数        
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) 
        return unescape(arr[2]);
    return null;
}