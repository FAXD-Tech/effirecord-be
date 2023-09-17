package com.faxd.effirecord.utils;

import com.faxd.effirecord.exception.BeanCopyNotSuccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class BeanHelper {

    private static final Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    public static void CopyDtoIntoEntity(Object entity, Object dto){
        Class<?> entityClass = entity.getClass();
        PropertyDescriptor[] entityPds = BeanUtils.getPropertyDescriptors(entityClass);
        for (PropertyDescriptor entityPd : entityPds) {
            if (entityPd.getWriteMethod() != null){
                PropertyDescriptor dtoPd = BeanUtils.getPropertyDescriptor(dto.getClass(), entityPd.getName());
                if(dtoPd != null && dtoPd.getReadMethod() != null){
                    Method dtoReadMethod = dtoPd.getReadMethod();
                    if(!Modifier.isPublic(dtoReadMethod.getDeclaringClass().getModifiers())){
                        dtoReadMethod.setAccessible(true);
                    }
                    try {
                        Object dtoValue = dtoReadMethod.invoke(dto);
                        if (dtoValue != null){
                            Method entityWriteMethod = entityPd.getWriteMethod();
                            if(!Modifier.isPublic(entityWriteMethod.getDeclaringClass().getModifiers())){
                                entityWriteMethod.setAccessible(true);
                            }
                            entityWriteMethod.invoke(entity, dtoValue);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        logger.error("entity field: %s, dto field: %s, message: %s",
                                entityPd.getName(), dtoPd.getName(), e.getMessage());
                        throw new BeanCopyNotSuccessException(e);
                    }
                }
            }
        }
    }
}
