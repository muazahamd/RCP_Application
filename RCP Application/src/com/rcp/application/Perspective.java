package com.rcp.application;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	/** Perspective ID.*/
	public static final String ID = "com.rcp.application.perspective";
	
	public void createInitialLayout(IPageLayout layout) {
		layout.setFixed(true);
	}
}
