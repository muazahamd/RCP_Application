package com.rcp.application.views;


import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.rcp.application.Person;

public class DetailsView extends ViewPart implements ISelectionListener {
	/** View's ID.*/
	public static final String ID = "com.rcp.application.details.view";
	
	private Label firstName;
	private Label lastName;
	private Label age;
	private Label gender;
	private Label profession;
	
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		new Label(composite, SWT.NONE).setText("First Name :");
		firstName = new Label(composite, SWT.NONE);
		
		new Label(composite, SWT.NONE).setText("Last Name :");
		lastName = new Label(composite, SWT.NONE);
		
		new Label(composite, SWT.NONE).setText("Age :");
		age = new Label(composite, SWT.NONE);
		
		new Label(composite, SWT.NONE).setText("Gender :");
		gender = new Label(composite, SWT.NONE);
		
		new Label(composite, SWT.NONE).setText("Profession :");
		profession = new Label(composite, SWT.NONE);
		
		/*
		 * Add selection change listener to workbench window so that we could
		 * listen to the selection of Records view.
		 */
		getViewSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);
	}

	private void showDetail(Person person){
		firstName.setText(person.getFirstName());
		firstName.pack();
		
		lastName.setText(person.getLastName());
		lastName.pack();
		
		age.setText("" + person.getAge());
		age.pack();
		
		gender.setText(person.getGender());
		gender.pack();
		
		profession.setText(person.getProfession());
		profession.pack();
	}
	
	public void setFocus() {
		firstName.getParent().setFocus();
	}
	
	@Override
	public void dispose() {
		getViewSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection.isEmpty())
			return;
		
		if (!(selection instanceof IStructuredSelection))
			return;
		
		Object obj = ((IStructuredSelection) selection).getFirstElement();
		if (!(obj instanceof Person))
			return;
		
		showDetail((Person) obj);
	}
}