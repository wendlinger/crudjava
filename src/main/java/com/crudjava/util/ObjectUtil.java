package com.crudjava.util;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ObjectUtil {

	public static String[] getNullPropertyNames(Object source){
	
		final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
		return Stream.of(wrappedSource.getPropertyDescriptors())
				.map(FeatureDescriptor::getName)
				.filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
				.toArray(String[]::new);
	}
	
	public static void copyNonNullProperties (Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getGenericIdValue(Object source) {
		return (T) new BeanWrapperImpl(source).getPropertyValue("id");
	}
}
