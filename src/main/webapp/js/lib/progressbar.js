/**
 * Created by Administrator on 2014/6/18.
 */

(function ($) {
    $.extend({ProgressBar:function(){return new ProgressBar();}});
})(jQuery);

/*
 * 进度条工具类;
 * */
function ProgressBar(){

    var progressBar = null;

    /*
     * 产生html<私有方法>
     * top 偏离顶部的距离，默认值200，单位是px，用法：如:_createHtml(200)
     * */
    function _createHtml(top){
        top = (top || 200) + 'px';
        var picURL = window.ctx + '/skin/new/images/jindutiao.gif';
        var html = '<div class="ProgressBar" style="top:' + top +';">'+
                            '<div class="txt">正在加载..</div>'+
                            '<img src="'+ picURL +'" />'+
                        '</div>';
        return $(html);
    };

    /**
     * 显示进度条 用法：如:$.ProgressBar().show()
     */
    this.show = function(top){
        progressBar =  _createHtml(top);
        $('body').prepend(progressBar);
    };

    /**
     * 隐藏进度条 用法：如:$.ProgressBar().hide()
     */
    this.hide = function(){
        $('.ProgressBar').remove();
    };

    return this;
}