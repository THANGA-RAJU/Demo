package com.app.Springboot.model;

public enum ConstantRole {
	
	PATIENT,SPL_DOCTOR,JR_DOCTOR,RECEIPTENSIT,ADMIN,SUPER_ADMIN;

	public static ConstantRole fromString(String text) {
		
		for(ConstantRole dr: ConstantRole.values()) {
			if(dr.name().equalsIgnoreCase(text)) {
				return dr;
			}
		}
		throw new IllegalArgumentException("No constant with text " + text + " found");
		
	}


}
