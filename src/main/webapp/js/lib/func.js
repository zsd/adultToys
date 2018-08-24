
//--------------------鼠标移到--------------------------//

/////////////鼠标移到1 显示2 

function tag_ms1(id1,id2){
$(id1).mouseover(function(){
$(id2).show();
});}

/////////////鼠标移到1 隐藏2 显示3
function tag_ms2(id1,id2,id3){
$(id1).mouseover(function(){
$(id2).hide();
$(id3).show();
});}

/////////////鼠标移到1 隐藏2 显示3
function tag_ms3(id1,id2){
$(id1).mouseover(function(){
$(id2).show();
});
$(id2).mouseover(function(){
$(id2).show();
$(id2).mouseout(function(){
$(id2).hide();
});
});}


/////////////鼠标移到1 切换样式1
function tag_ms4(id1,clson){
$(id1).mouseover(function(){
$(id1).attr("class",clson);
});}

/////////////鼠标移到1 切换样式1 显示2
function tag_ms5(id1,id2,clson){
$(id1).mouseover(function(){
$(id1).attr("class",clson);
$(id2).show();
});}

///////弹层提示 - 移到1显示2 最大值m 当前值n (点击隐藏)
 function tagtip_ms1(id1,id2,m,n){
 $(id1+n).mouseover(function(){
 tagtip1(id2,m,n);
 });
 $(document).click(function(event){
 var ckall=String(isParent(event.target,$(id1+n)[0],$(id2+n)[0]));
 if(ckall=="true"){
 $(id1+n).show();
 $(id2+n).show();
 }else{
 $(id2+n).hide();}
 //如果有多级那么只判断最后一级
 })
 }
 
 function tagtip1(id,m,n){
 for (i = 1; i <= m; i++) {
 $(id+i).hide();
 $(id+n).show();
 }}


///////弹层提示 - 移到1显示2,3 最大值m 当前值n (点击隐藏)
 function tagtip_ms2(id1,id2,id3,m,n){
 $(id1+n).mouseover(function(){
 tagtip2(id2,id3,m,n);
 });
 $(document).click(function(event){
 var ckall=String(isParent(event.target,$(id2+n)[0],$(id3+n)[0]));
 if(ckall=="true"){
 $(id2+n).show();
 $(id3+n).show();
 }else{
 $(id3+n).hide();}
 //如果有多级那么只判断最后一级
 })
 }
 
 function tagtip2(id1,id2,m,n){
 for (i = 1; i <= m; i++) {
 $(id1+i).hide();
 $(id1+n).show();
 $(id2+i).hide();
 $(id2+n).show();

 }}
 
 
 ///////弹层提示 - 移到1显示2 最大值m 当前值n (点击隐藏)
 function tagtip_ms3(id1,id2,id3,left1,left2,m,n){
 $(id1+n).mouseover(function(){
 tagtip3(id2,id3,left1,left2,m,n);
 });
 $(document).click(function(event){
 var ckall=String(isParent(event.target,$(id2+n)[0],$(id3+n)[0]));
 if(ckall=="true"){
 $(id2+n).show();
 $(id3+n).show();
 }else{
 $(id3+n).hide();}
 //如果有多级那么只判断最后一级
 })
 }
 
 function tagtip3(id1,id2,left1,left2,m,n){
 for (i = 1; i <= m; i++) {

 $(id1+i).hide();
 $(id1+n).show();
 $(id2+i).hide();
 $(id2+n).show();
 $(id1+i).css({left:left1},"slow");
 $(id2+i).css({left:left2},"slow");

 }}


//--------------------鼠标移到 属性切换--------------------------//

/////////////鼠标移到1 替换2的样式或属性atx为pthx
function tagto_ms1(id1,id2,atx,pthx){
$(id1).mouseover(function(){
$(id2).attr(atx,pthx);
});}

/////////////鼠标移到1 替换2的样式或属性atx为pthx ，隐藏3
function tagto_ms2(id1,id2,id3,atx,pthx){
$(id1).mouseover(function(){
$(id3).hide();
$(id2).attr(atx,pthx);
});}

//--------------------鼠标移开--------------------------//

/////////////鼠标移开1 隐藏2
function tag_mso1(id1,id2){
$(id1).mouseout(function(){
$(id2).hide();
});}

/////////////鼠标移开1 隐藏2 显示3
function tag_mso2(id1,id2,id3){
$(id1).mouseout(function(){
$(id2).hide();
$(id3).show();
});}

/////////////鼠标移到1 切换样式1
function tag_mso3(id1,clson){
$(id1).mouseover(function(){
$(id1).attr("class",clson);
});}

/////////////鼠标移到1 切换样式1 显示2
function tag_mso4(id1,id2,clson){
$(id1).mouseover(function(){
$(id1).attr("class",clson);
$(id2).show();
});}

//--------------------鼠标点击--------------------------//

/////////////判断鼠标点击返回值true或false
 function isParent(cks,thistag1,thistag2){
 while (cks != undefined && cks != null && cks.tagName.toUpperCase() != 'BODY'){
 if (cks == thistag1 || cks == thistag2){return true;}
 cks = cks.parentNode;}
 return false;}

/////////////鼠标点击1 显示2.....
function tag_ckg1(id1,id2){
 $(document).click(function(event){
 var ckall=String(isParent(event.target,$(id2)[0],$(id1)[0]));
 if(ckall=="true"){
 $(id1).show();
 $(id2).toggle();
 }else{
 $(id2).hide();}
 //如果有多级那么只判断最后一级
 });}


/////////////鼠标点击1 隐藏2 显示3
function tag_ck1(id1,id2){
$(id1).click(function(){
$(id2).show();
});}

/////////////鼠标点击1 隐藏2 显示3
function tag_ck2(id1,id2,id3){
$(id1).click(function(){
$(id2).hide();
$(id3).show();
});}

/////////////鼠标点击1 隐藏2 隐藏3 显示4
function tag_ck3(id1,id2,id3,id4){
$(id1).click(function(){
$(id2).hide();
$(id3).hide();
$(id4).show();
});}

/////////////鼠标点击1 隐藏2 隐藏3 隐藏4 显示5 
function tag_ck4(id1,id2,id3,id4,id5){
$(id1).click(function(){
$(id2).hide();
$(id3).hide();
$(id4).hide();
$(id5).show();
});}

/////////////鼠标点击1 隐藏2 隐藏3 隐藏4 隐藏5 显示5
function tag_ck5(id1,id2,id3,id4,id5,id6){
$(id1).click(function(){
$(id2).hide();
$(id3).hide();
$(id4).hide();
$(id5).hide();
$(id6).show();
});}

//--------------------鼠标点击 样式切换--------------------------//

/////////////鼠标点击1 替换2的样式或属性atx为pthx
function tagto_ck1(id1,id2,atx,pthx){
$(id1).click(function(){
$(id2).attr(atx,pthx);
});}

/////////////鼠标点击1 替换3的样式或属性atx为pthx ，隐藏2 
function tagto_ck2(id1,id2,id3,atx,pthx){
$(id1).click(function(){
$(id2).hide();
$(id3).attr(atx,pthx);
});}



//鼠标点击 样式切换反复***
function tagto_ckg1(id1,css1,id2){
$(id1).click(function(){
$(this).toggleClass(css1);
$(id2).toggle();
});
}


//鼠标点击 样式切换反复***只做菜单收缩
function tagto_ckframe(id1,css1,id2,frameid,rowcol,val1,val2){
$(id1).click(function(){
var $idx = $(window.parent.document).find(frameid);
var cols=$idx.attr(rowcol)
if (cols==val1){$idx.attr(rowcol,val2)}
if (cols==val2){$idx.attr(rowcol,val1)}
$(this).toggleClass(css1);
$(id2).toggle();
});
}


//--------------------鼠标点击 关闭层或隐藏层--------------------------//


/////////////鼠标点击1 隐藏1
function close_ckchild(id,cc){
$(id).click(function(){
$(this).children(cc).hide();
});}

/////////////鼠标点击1 隐藏父标签
function close_ckparent(id,pp){
$(id).click(function(){
$(this).parent(pp).hide();
});}

/////////////鼠标点击1 隐藏2 
function close_ck1(id1,id2){
$(id1).click(function(){
$(id2).hide();
});}

/////////////鼠标点击1 隐藏2 隐藏3
function close_ck2(id1,id2,id3){
$(id1).click(function(){
$(id2).hide();
$(id3).hide();
});}


//--------------------鼠标移到和移开 --------------------//
///////////////判断当前点击事件样式替换
function tag_mst1(tag,cssoff,csson){
$(tag).hover(function(){
$(this).attr("class",csson);
}, function(){
$(this).attr("class",cssoff);
});
}

///////////////判断当前点击事件显示和隐藏 - find用法class tag(这个必须是标签而且还是父子关系,两个样式也可以)，(class两个样式组合时只要一个)
function tag_mst2(tag,tagson){
$(tag).hover(function(){
$(this).find(tagson).show();
}, function() {
$(this).find(tagson).hide();
});
}

//--------------------鼠标点击前和点击后 --------------------//
///////////////判断当前点击事件样式替换
function tag_ckt1(tag,csst,cssoff,csson){
var divs=document.getElementsByTagName(tag);
for(var i=0;i<divs.length;++i){
if(divs[i].className==cssoff||divs[i].className==csson){
divs[i].onclick=function(){
$(csst).attr("class",cssoff);
$(this).attr("class",csson);
}}};}

///////////////判断当前点击事件样式替换
function tag_ckt2(tag,csst,cssoff,csson,tagson){
var divs=document.getElementsByTagName(tag);
for(var i=0;i<divs.length;++i){
if(divs[i].className==cssoff||divs[i].className==csson){
divs[i].onclick=function(){
$(csst).attr("class",cssoff);
$(this).attr("class",csson);
$(this).find(tagson).show();

}}};}


///////////////判断当前点击事件样式替换
function tag_ckt3(tag,csst,cssoff,csson,tagson1,tagson2){
var divs=document.getElementsByTagName(tag);
for(var i=0;i<divs.length;++i){
if(divs[i].className==cssoff||divs[i].className==csson){
divs[i].onclick=function(){
$(csst).attr("class",cssoff);
//$(csst).find(tagson1).hide();
//$(csst).find(tagson2).hide();
$(this).attr("class",csson);
$(this).find(tagson1).show();
$(this).find(tagson2).show();
}}};}



///////////////判断当前点击事件样式替换(用于左菜单)===自定函数不做为公共调用
function tag_ckt4(tag,cssn,chlid,chlidcss,pareid,parechlid){
var divs=document.getElementsByTagName(tag);
for(var i=0;i<divs.length;++i){
if(divs[i].className==cssn){
divs[i].onclick=function(){
$(this).children(chlid).toggleClass(chlidcss);
$(this).parent(pareid).children(parechlid).slideToggle();
}}};}

//--------------------鼠标点击 把文字带到另一层替换位置--------------------//

/////////////鼠标移开1 对应显示文字2
function tag_txt1(id1,id2){
 $(id1).mouseover(function(){

 var txt2=$(id2).text();
 $(id1).each(function(){
 
 this.onclick=function(){
  var txt1=$(this).text();
 $(this).html(txt2)
 if (txt1 !== ""){
 $(id2).html(txt1);
}}
});});}



//--------------------鼠标双击--------------------//

/////////////鼠标移到1显示2
function tag_dbl1(id1,id2){
$(id1).dblclick(function(){
$(id2).show();
});}


//--------------------弹出对话框--------------------------//


/////////////鼠标点击1 弹出2
function tag_dialog1(id1,id2){
$(id1).click(function(){
var thistop=$(this).offset().top;//获取当前位置的top
//var maskHeight = $(document).height();//文档的总高度
var maskWidth = $(window).width();//窗口的宽度
var dialogTop =  thistop - ($(id2).height()/2);
var dialogLeft = (maskWidth/2) - ($(id2).width()/2);
$(id2).css({top:dialogTop,left:dialogLeft}).show();
});}


//--------------------点击切换卡--------------------------//

  /////////////////切换卡（点击1 显示2 按钮样式2 最大值m 当前值n）常用 
 function tabid(id1,id2,clsoff,clson,m,n,times){
 $(id1+n).click(function(){
 tabname(id1,id2,clsoff,clson,m,n,times);
 });}
 function tabname(id1,id2,clsoff,clson,m,n,times){
 for (i = 1; i <= m; i++){
 $(id1+i).attr("class",clsoff);
 $(id1+n).attr("class",clson);
 $(id2+i).hide();
 $(id2+n).slideDown(times);}
 }
 
  /////////////////切换卡（点击1 按钮样式2 显示3 最大值m 当前值n）常用于选项按钮（id1一种*多种样式）
 function tab_id2(id1,id2,id3,clsoff,clson,m,n){
 $(id1+n).click(function(){
 tab_name2(id2,id3,clsoff,clson,m,n);
 });}

 function tab_name2(id1,id2,clsoff,clson,m,n){
 for (i = 1; i <= m; i++) {
 $(id1+i).hide();
 $(id1+n).show();
 $(id2+i).attr("class",clsoff+i);
 $(id2+n).attr("class",clson+n);
 }}
 
 /////////////////切换卡（点击1 显示2 最大值m 当前值n）常用于选项按钮（id1两种*多种样式，自定义）
 function tab_id3(id1,id2,clsoff1,clson1,chltag,clsoff2,clson2,m,n){
 $(id1+n).click(function(){
 tab_name3(id2,m,n);
 tab_namemore3(id1,clsoff1,clson1,chltag,clsoff2,clson2,m,n)
 });}
 
 function tab_name3(id,m,n){
 for (i = 1; i <= m; i++) {
 $(id+i).hide();
 $(id+n).show();
 }}

 function tab_namemore3(id,clsoff1,clson1,chltag,clsoff2,clson2,m,n){
 for (i = 1; i <= m; i++) {
 $(id+i).attr("class",clsoff1);
 $(id+n).attr("class",clson1);
 $(id+i).children("dd").attr("class",clsoff2+i);
 $(id+n).children("dd").attr("class",clson2+n);
 }}
 
 