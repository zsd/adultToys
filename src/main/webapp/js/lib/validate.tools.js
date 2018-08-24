/*
 * 提供jquery插件的形式调用，如:$.Validator() ;
 * */
(function ($) {
    $.extend({Validator:function(){return new Validator();}});
})(jQuery);

/*
 * 数据校验工具类;
 * */
function Validator(){
    /**
     *邮政编码验证
     *请正确填写您的邮政编码
     * @param value 校验值
     * @returns {boolean|*}
     */
    this.zipCode = function(value){
        var tel = /^[0-9]{6}$/;
        return tel.test(value);
    };

    /**
     *联系电话(手机/电话皆可)验证
     *请正确填写您的联系电话
     * @param value 校验值
     * @returns {boolean|*}
     */
    this.phone = function(value){
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return  tel.test(value) || mobile.test(value);
    };

    /**
     * 电话号码验证，电话号码格式010-12345678
     * 请正确填写您的电话号码
     * @param value 校验值
     * @returns {boolean|*}
     */
    this.tel = function(value){
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return tel.test(value);
    };

    /**
     * 手机号码验证
     * 请正确填写您的手机号码
     * @param value 校验值
     * @returns {boolean|*}
     */
    this.mobile = function(value){
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        return length == 11 && mobile.test(value);
    };

    /**
     * 身份证号码验证
     * 请正确输入您的身份证号码
     * @param value
     * @returns {boolean|*}
     */
    this.idNumber = function(value){
        return /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/.test(value);
    };

    /**
     * 字符最小长度验证（一个中文字符长度为2）
     * 长度不能小于{param}
     * @param value 校验值
     * @param param 最小长度
     * @returns {boolean}
     */
    this.stringMinLength = function(value , param){
        var length = value.length;
        for ( var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return length >= param;
    };

    /**
     * 字符最大长度验证（一个中文字符长度为2）
     * 长度不能大于{param}
     * @param value 校验值
     * @param param 最大长度
     * @returns {boolean}
     */
    this.stringMaxLength = function(value , param){
        var length = value.length;
        for ( var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return length <= param;
    };

    /**
     * 字符验证
     * 只能包括中文字、英文字母、数字和下划线
     * @param value 校验值
     * @returns {boolean|*}
     */
    this.stringCheck = function(value){
        return /^[\u0391-\uFFE5\w]+$/.test(value);
    };

    /**
     * 中文字两个字节
     * 请确保输入的值在{param[0]}-{param[1]}个字节之间(一个中文字算2个字节)
     * @param value 校验值
     * @param param 区间数组
     * @returns {boolean}
     */
    this.byteRangeLength = function(value, param){
        var length = value.length;
        for(var i = 0; i < value.length; i++){
            if(value.charCodeAt(i) > 127){
                length++;
            }
        }
        return length >= param[0] && length <= param[1];
    };

    /**
     * 字符验证
     * 不允许包含特殊符号
     * @param value 校验值
     * @returns {boolean|*}
     */
    this.string = function(value){
        return /^[\u0391-\uFFE5\w]+$/.test(value);
    };

    /**
     * 必须以特定字符串开头验证
     * 必须以 {param} 开头
     * @param value 校验值
     * @param param 特定字符串
     * @returns {boolean|*}
     */
    this.begin = function(value, param){
        var begin = new RegExp("^" + param);
        return begin.test(value);
    };

    /**
     * 验证两次输入值是否不相同
     * 两次输入不能相同
     * @param value 校验值
     * @param param 比较值的选择器字符串，如：'#selector'
     * @returns {boolean}
     */
    this.notEqualTo = function(value, param){
        return value != $(param).val();
    };

    /**
     * 验证值不允许与特定值等于
     * 输入值不允许为{param}
     * @param value 验证值
     * @param param 特定值
     * @returns {boolean}
     */
    this.notEqual = function(value, param){
        return value != param;
    };

    /**
     * 验证值必须大于特定值(不能等于)
     * 输入值必须大于{param}
     * @param value 验证值
     * @param param 特定值
     * @returns {boolean}
     */
    this.gt = function(value, param){
        return value > param;
    };

    /**
     * 验证值小数位数不能超过两位
     * 小数位数不能超过两位
     * @param value 验证值
     * @returns {boolean|*}
     */
    this.decimal = function(value){
        var decimal = /^-?\d+(\.\d{1,2})?$/;
        return decimal.test(value);
    };

    /**
     * 字母数字
     * 只能包括英文字母和数字
     * @param value 验证值
     * @returns {boolean|*}
     */
    this.alnum = function(value){
        return /^[a-zA-Z0-9]+$/.test(value);
    };

    /**
     * 汉字
     * 只能输入汉字
     * @param value 验证值
     * @returns {boolean|*}
     */
    this.chCharacter = function(value){
        var tel = /^[\u4e00-\u9fa5]+$/;
        return tel.test(value);
    };

    /**
     * IP地址检验
     * Ip地址格式错误
     * @param value 验证值
     * @returns {boolean|*}
     */
    this.ip = function(value){
        var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
        return ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256);
    };

    /**
     *验证整数值
     * 调用名：vSInteger(pNum,[pIntegerDigit])
     * @param pNum 数值
     * @param pIntegerDigit 整数位数(可选)，这里的整数位数是指整数宽度从0到给定的宽度
     * @returns {boolean} 正确 返回 true;否则 返回 false
     */
    this.vInteger = function(pNum,pIntegerDigit){
        if((typeof pIntegerDigit).toLowerCase() == 'undefined')
        {
            if(! /^[ ]*[0-9]{0,}[ ]*$/.test(pNum) || $.trim(pNum).length==0){
                return false; 	//Error Integer format
            }
        }else{
            var _RegExp = new RegExp();
            _RegExp.compile('^[ ]*[0-9]{0,'+ pIntegerDigit +'}[ ]*$');
            if(!_RegExp.test(pNum) || $.trim(pNum).length==0){
                return false; 	//Error Integer format
            }
        }
        return true;
    };

    /**
     * 验证数字
     * 请输入数字
     * @returns {boolean|*} 正确 返回 true;否则 返回 false
     */
    this.number = function(value){
        var number = /^[0-9]*$/;
        return number.test(value);
    };

    /**
     * 验证浮点数
     * 请输入数字
     * @returns {boolean|*} 正确 返回 true;否则 返回 false
     */
    this.floatNum = function(value){
        var number = /^\-?[0-9]{0,}\.?[0-9]{0,}$/;
        return number.test(value);
    };

    return this;
}