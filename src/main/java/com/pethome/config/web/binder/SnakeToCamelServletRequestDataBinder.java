package com.pethome.config.web.binder;

import com.pethome.util.StringUtil;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 5月 15 10:30
 */


public class SnakeToCamelServletRequestDataBinder extends ExtendedServletRequestDataBinder {

    public SnakeToCamelServletRequestDataBinder(Object target) {
        super(target);
    }

    public SnakeToCamelServletRequestDataBinder(Object target, String objectName) {
        super(target, objectName);
    }

    @Override
    protected void addBindValues(@NonNull MutablePropertyValues mpvs, @NonNull ServletRequest request) {
        super.addBindValues(mpvs, request);
        // 避免向正在遍历的列表中添加元素，因此先将转换后的属性值添加到新列表中，再将新列表中的元素添加到原列表中
        List<PropertyValue> newPropertyValueList = new ArrayList<>();
        for (PropertyValue propertyValue : mpvs.getPropertyValueList()) {
            String name = propertyValue.getName();
            if(StringUtil.isSnake(name)){
                String camelName = StringUtil.convertSnakeToCamel(name);
                if(!mpvs.contains(camelName)){
                    newPropertyValueList.add(new PropertyValue(camelName, propertyValue.getValue()));
                }
            }
        }
        for (PropertyValue propertyValue : newPropertyValueList){
            mpvs.add(propertyValue.getName(), propertyValue.getValue());
        }
    }
}