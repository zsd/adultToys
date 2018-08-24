/*
 * 提供jquery插件的形式调用，如:$.DialogTool() ;
 * */
(function ($) {
	$.extend({DialogTool:function(){return new DialogTool();}});
})(jQuery);

/*
 * 弹出工具类;
 * */
function DialogTool(){
	
	var _dialog = null;
	var _dialog2 = null;
	
	/*
	 * 产生html<私有方法>
	 * title 弹出框名称
	 * message 弹出框信息
	 * type 弹出框类型 1:confirm,2:alert,3:success,4:error
	 * */
	function _createHtml(title,message,message2,type,callback){
		var icon = '';
		switch(type){
			case 1:
				icon = 'icon49';
				break;
			case 2:
				icon = 'icon49';
				break;
			case 3:
				icon = 'icon47';
				break;
			case 4:
				icon = 'icon48';
				break;
			default:
				icon = '';
				break;
		}
        var html = '<div class="promptDialog_box pd_width428">'+
                            '<div class="x1 pdboxHead">'+
                                '<h3 class="pdboxTit">'+ title +'</h3>'+
                                '<a href="javascript:void(0);" class="icon67 pdboxClo close"></a>'+
                            '</div>'+
                            '<div class="pdboxCont">'+
                                '<div class="tipMainWrap">'+
                                        '<div class="'+ icon +' tipsicon"></div>'+
                                        '<div class="tipswords">'+
                                            '<h3>'+ message +'</h3>'+
                                            '<p>'+ message2 +'</p>'+
                                        '</div>'+
                                '</div>'+
                            '</div>'+
                            '<div class="pdboxToolbar">'+
                                '<div class="pdboxBtns">'+
                                    '<input type="button" class="xbt1 btw75 confirm" value="确定" />'+
                                    (type==1?'<input type="button" class="xbt5 btw75 cancel" value="取消" />' : '')+
                                '</div>'+
                            '</div>'+
                        '</div>';
		var htmlObj = $(html);
		htmlObj.find('.close').on('click',function(){
			_dialog2.close();
		});
		htmlObj.find('.cancel').on('click',function(){
			_dialog2.close();
		});
		htmlObj.find('.confirm').on('click',function(){
			_dialog2.close();
			if(typeof(callback) == 'function'){
				callback();
			}
		});
        //回车事件
        htmlObj.find('.confirm').on('keydown',function(e){
            if (e.keyCode == 13) {
                $(e.target).trigger('click');
            }
        });
		return htmlObj;
	};
	
	/* 
	 * 弹出窗口 用法：如:$.DialogTool().open()
	 * html 字符串或者DOM对象
	 * 返回弹出框对象
	 * */
	this.open = function(html){
		_dialog = art.dialog({
			content:$(html)[0],
			title:false,
			opacity:0.1,
			button:null,
			lock:true,
			padding:0,
			resize:false,
			zIndex:1024 
		});
		return _dialog;
	};
	
	/* 
	 * 弹出窗口 用法<没有指定zIndex属性>：如:$.DialogTool().open2()
	 * html 字符串或者DOM对象
	 * 返回弹出框对象
	 * */
	this.open2 = function(html){
		_dialog2 = art.dialog({
			content:$(html)[0],
			title:false,
			opacity:0.1,
			button:null,
			lock:true,
			padding:0,
			resize:false
		});
		return _dialog2;
	};
	
	/* 
	 * 关闭窗口 用法：如:$.DialogTool().close()
	 * */
	this.close = function(){
		_dialog.close();
	};
	
	/* 
	 * 弹出方法<内嵌iframe的方式> 用法：如:$.DialogTool().iframe()
	 * url 
	 * 返回弹出框对象
	 * */
	this.iframe = function(url){
		_dialog = art.dialog.open(url,{
			title:false,
			opacity:0.1,
			button:null,
			lock:true,
			padding:0,
			resize:false,
			init:function(){

			}
		},false);
		return _dialog;
	};
	
	/*
	 * 确认提示框 用法：如:$.DialogTool().confirm()
	 * title 标题
	 * message 内容信息
	 * callback[可选]
	 * */
	this.confirm = function(title,message,callback){
		var contentObj = _createHtml(title,message,'',1,callback);
		this.open2(contentObj);
	};
	
	/*
	 * 警告提示框 用法：如:$.DialogTool().alert()
	 * title 标题
	 * message 内容信息
	 * callback[可选]
	 * */
	this.alert = function(title,message,callback){
		var contentObj = _createHtml(title,message,'',2,callback);
		this.open2(contentObj);
	};
	
	/*
	 * 警告提示框<带副本内容信息> 用法：如:$.DialogTool().alert2()
	 * title 标题
	 * message 内容信息
	 * message2 副本内容信息
	 * callback[可选]
	 * */
	this.alert2 = function(title,message,message2,callback){
		var contentObj = _createHtml(title,message,message2,2,callback);
		this.open2(contentObj);
	};
	
	/*
	 * 成功提示框 用法：如:$.DialogTool().success()
	 * title 标题
	 * message 内容信息
	 * callback[可选]
	 * */
	this.success = function(title,message,callback){
		var contentObj = _createHtml(title,message,'',3,callback);
		this.open2(contentObj);
	};
	
	/*
	 * 错误提示框 用法：如:$.DialogTool().error()
	 * title 标题
	 * message 内容信息
	 * callback[可选]
	 * */
	this.error = function(title,message,callback){
		var contentObj = _createHtml(title,message,'',4,callback);
		this.open2(contentObj);
	};
	
	return this;
}

