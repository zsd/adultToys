function PageAction(options){
    var me = this ;


    if(typeof(options)!= 'undefined' && typeof(options.view)!= 'undefined'){
        this.view = options.view ;
    }

    if(typeof(options)!= 'undefined' && typeof(options.PageRouter)!= 'undefined'){
        this.PageRouter = options.PageRouter ;
    }

    if(typeof(options)!= 'undefined' && typeof(options.template)!= 'undefined'){
        this.template = $("<div>"+ options.template+ "</div>") ;
    }

    var myDate = new Date();
    var time =  myDate.getTime( );
    this.spanId='span'+time
    this.buttonId = 'button'+  time ;
    this.inputId = 'input'+  time ;
    this.createHtmlElement() ;
    this.initEvent() ;
};
PageAction.prototype.view = '' ;
PageAction.prototype.PageRouter = '' ;
PageAction.prototype.tableTemplateHtml = '' ;
PageAction.prototype.createHtmlElement = function(){
    var me = this ;
    var spanId = me.spanId ;
    var buttonId = me.buttonId ;
    var  inputId = me.inputId ;
    var str = '<span id='+spanId+' > '+
    ' 到<input id="'+inputId+'"  value="'+me.view.model.currentPageNo+'" type="text" class="main_inputSty gd_paiInputSecond" style="width:30px !important;">页  '+
    ' <input type="button" class="xbt1" value="确定" id="'+buttonId+'" style="cursor:pointer;text-align:center ;width:40px;">   '+
    ' </span>   ' ;
    var htmlElement = $(str) ;
    this.template.contents().find(".icon36").remove() ;
    htmlElement.appendTo(this.template.contents().find(".sltgoPs"));
    this.tableTemplateHtml =  this.template.html() ;
    return htmlElement ;
} ;



PageAction.prototype.initEvent = function(){
    var me = this ;
  /*  $(".goPrePage").live("click",function(){
        if(me.view.model.currentPageNo == 1){
            return false;
        }
        var pageSize =  me.view.model.currentPageSize;
        var pageNo = parseInt(me.view.model.currentPageNo)- 1;
        me.PageRouter.navigate('getByPage/'+pageNo+'/'+pageSize, {trigger: true});
    }) ;*/


 /*   $(".goNextPage").live('click',function(){
        if(  me.view.model.currentPageNo ==    me.view.model.get("totalPages")){
            return false;
        }
        var pageSize =  me.view.model.currentPageSize;
        var pageNo = parseInt(me.view.model.currentPageNo) + 1;
        me.PageRouter.navigate('getByPage/'+pageNo+'/'+pageSize, {trigger: true});
    }) ;*/

    $("#"+me.buttonId).live('click',function(){
        var pageSize =  me.view.model.currentPageSize;
        var pageNo = $("#"+me.inputId).val() ;
        me.PageRouter.navigate('getByPage/'+pageNo+'/'+pageSize, {trigger: true});
    })  ;

    $("#"+me.inputId).live('keyup',function(){
        var totalPages =  me.view.model.attributes.totalPages;
          $(this).val(this.value.replace(/\D/g,'')) ;
        if($(this).val() < 1) {
            $(this).val(1)
        }
        if($(this).val() > totalPages){
            $(this).val(totalPages)
        }
    })  ;

    $("#"+me.inputId).live('afterpaste',function(){
        var totalPages =  me.view.model.attributes.totalPages;
          $(this).val(this.value.replace(/\D/g,'')) ;
        if($(this).val() < 1) {
            $(this).val(1)
        }
        if($(this).val() > totalPages){
            $(this).val(totalPages)
        }
    })  ;

   /* $("#"+me.inputId).live('blur',function(){
        debugger
        var totalPages =  me.view.model.attributes.totalPages;
        if($(this).val() > totalPages){
            $(this).val(totalPages) ;
        }
    })  ;*/
} ;



function PageAction2(options){
    var me = this ;


    if(typeof(options)!= 'undefined' && typeof(options.view)!= 'undefined'){
        this.view = options.view ;
    }

    if(typeof(options)!= 'undefined' && typeof(options.PageRouter)!= 'undefined'){
        this.PageRouter = options.PageRouter ;
    }

    if(typeof(options)!= 'undefined' && typeof(options.template)!= 'undefined'){
        this.template = $("<div>"+ options.template+ "</div>") ;
    }

    var myDate = new Date();
    var time =  myDate.getTime( );
    this.spanId='span'+time
    this.buttonId = 'button'+  time ;
    this.inputId = 'input'+  time ;
    this.createHtmlElement() ;
    this.initEvent() ;
};
PageAction2.prototype.view = '' ;
PageAction2.prototype.PageRouter = '' ;
PageAction2.prototype.tableTemplateHtml = '' ;
PageAction2.prototype.createHtmlElement = function(){
    var me = this ;
    var spanId = me.spanId ;
    var buttonId = me.buttonId ;
    var  inputId = me.inputId ;
    var str = '<span id='+spanId+' > '+
        ' 到<input id="'+inputId+'"  value="'+me.view.collection.pageNo+'" type="text" class="main_inputSty gd_paiInputSecond" style="width:30px !important;">页  '+
        ' <input type="button" class="xbt1" value="确定" id="'+buttonId+'" style="cursor:pointer;text-align:center ;width:40px;">   '+
        ' </span>   ' ;
    var htmlElement = $(str) ;
    this.template.contents().find(".icon36").remove() ;
    htmlElement.appendTo(this.template.contents().find(".sltgoPs"));
    this.tableTemplateHtml =  this.template.html() ;
    return htmlElement ;
} ;



PageAction2.prototype.initEvent = function(){
    var me = this ;
    $("#"+me.buttonId).live('click',function(){
        me.view.collection.pageNo = $("#"+me.inputId).val() ;
        me.PageRouter.navigate('page/'+me.view.collection.pageNo,{trigger: true});
    })  ;

    $("#"+me.inputId).live('keyup',function(){
        var totalPages = me.view.collection.totalPages;
        $(this).val(this.value.replace(/\D/g,'')) ;
        if($(this).val() < 1) {
            $(this).val(1)
        }
        if($(this).val() > totalPages){
            $(this).val(totalPages)
        }
    })  ;

    $("#"+me.inputId).live('afterpaste',function(){
        var totalPages = me.view.collection.totalPages;
        $(this).val(this.value.replace(/\D/g,'')) ;
        if($(this).val() < 1) {
            $(this).val(1)
        }
        if($(this).val() > totalPages){
            $(this).val(totalPages)
        }
    })  ;
} ;


