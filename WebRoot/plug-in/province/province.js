/**
 * jQuery :  省市县联动插件
 * @author   kxt
 * @example  $("#test").province_city_district();
 */
$.fn.province_city_district = function(v_province,v_city,v_district){
    var _self = this;
    //插入3个空的下拉框
    //_self.append("<select id='province' name='province'></select>");
    //_self.append("<select id='city' name='city'></select>");
    //_self.append("<select id='district' name='district'></select>");
    _self.html("<select id='province' name='province' style='width: 100px' datatype='*' ></select>" +
            "<select id='city' name='city' style='width: 100px'></select>" +
            "<select id='district' name='district' style='width: 100px'></select>");
    //分别获取3个下拉框
    var sel1 = _self.find("select").eq(0);
    var sel2 = _self.find("select").eq(1);
    var sel3 = _self.find("select").eq(2);
     
    //定义3个默认值
    _self.data("province",["请选择", ""]);
    _self.data("city",["请选择", ""]);
    _self.data("district",["请选择", ""]);
    //默认省级下拉
    if(_self.data("province")){
        sel1.append("<option value='"+_self.data("province")[1]+"'>"+_self.data("province")[0]+"</option>");
    }
    //默认城市下拉
    if(_self.data("city")){
        sel2.append("<option value='"+_self.data("city")[1]+"'>"+_self.data("city")[0]+"</option>");
    }
    //默认县区下拉
    if(_self.data("district")){
        sel3.append("<option value='"+_self.data("district")[1]+"'>"+_self.data("district")[0]+"</option>");
    }
    $.get('context/province_city.xml', function(data){
        var arrList = [];
        $(data).find('province').each(function(){
            var $province = $(this);
            sel1.append("<option value='"+$province.attr('value')+"'>"+$province.attr('value')+"</option>");
        });
        if(typeof v_province != 'undefined'){
            sel1.val(v_province);
            sel1.change();
        }
    });
     
    //省级联动控制
    var index1 = "" ;
    var provinceValue = "";
    var cityValue = "";
    sel1.change(function(){
        //清空其它2个下拉框
        sel2[0].options.length=0;
        sel3[0].options.length=0;
        index1 = this.selectedIndex;
        if(index1 == 0){    //当选择的为 "请选择" 时
            if(_self.data("city")){
                sel2.append("<option value='"+_self.data("city")[1]+"'>"+_self.data("city")[0]+"</option>");
            }
            if(_self.data("district")){
                sel3.append("<option value='"+_self.data("district")[1]+"'>"+_self.data("district")[0]+"</option>");
            }
        } else{
            provinceValue = $('#province').val();
            $.get('context/province_city.xml', function(data){
                $(data).find("province[value='"+provinceValue+"'] > city").each(function(){
                    var $city = $(this);
                    sel2.append("<option value='"+$city.attr('value')+"'>"+$city.attr('value')+"</option>");
                });
                cityValue = $("#city").val();
                $(data).find("city[value='"+cityValue+"'] > district").each(function(){
                    var $district = $(this);
                    sel3.append("<option value='"+$district.attr('value')+"'>"+$district.attr('value')+"</option>");
                });
 
                if(typeof v_city != 'undefined'){
                    sel2.val(v_city);
                    sel2.change();
                }
 
                if(typeof v_district != 'undefined'){
                    sel3.val(v_district);
                }
            });
        }
    }).change();
    //城市联动控制
    sel2.change(function(){
        sel3[0].options.length=0;
        var cityValue2 = $('#city').val();
        $.get('context/province_city.xml', function(data){
            $(data).find("city[value='"+cityValue2+"'] > district").each(function(){
                var $district = $(this);
                sel3.append("<option value='"+$district.attr('value')+"'>"+$district.attr('value')+"</option>");
            });
            if(typeof v_district != 'undefined'){
                sel3.val(v_district);
            }
        });
    }).change();
    return _self;
};