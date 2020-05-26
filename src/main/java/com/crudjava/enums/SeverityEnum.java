package com.crudjava.enums;

public enum SeverityEnum {

	ERROR(0), SUCCESS(1), INFO(2), WARN(3);
	
	private Integer codigo;
	
	private SeverityEnum(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public static SeverityEnum value(String key) {
		for (SeverityEnum severityEnum : values()) {
			if (severityEnum.getCodigo().toString().equals(key)) {
				return severityEnum;
			}
		}
		return null;
	}
}
