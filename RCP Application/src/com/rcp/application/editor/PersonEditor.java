package com.rcp.application.editor;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.rcp.application.Person;

public class PersonEditor extends EditorPart {
	
	/** Editor's ID.*/
	public static final String ID = "com.rcp.application.editor";
	
	private Person person;
	private PersonEditorInput input;
	
	private Text firstName;
	private Text lastName;
	private Spinner age;
	private Combo gender;
	private Text profession;

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
		this.input = (PersonEditorInput) input;
		this.person = this.input.getPerson();
		
		String partName = person.getFirstName();
		if(partName == null || partName.isEmpty())
			partName = "New Record";
		
		this.setPartName(partName);
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		/*
		 * First name.
		 */
		new Label(composite, SWT.NONE).setText("First Name:*");
		
		firstName = new Text(composite, SWT.BORDER);
		firstName.setText(person.getFirstName());
		firstName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		firstName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				person.setFirstName(firstName.getText());
			}
		});
		
		/*
		 * Second name.
		 */
		new Label(composite, SWT.NONE).setText("Last Name:*");
		
		lastName = new Text(composite, SWT.BORDER);
		lastName.setText(person.getFirstName());
		lastName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lastName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				person.setLastName(lastName.getText());
			}
		});
		
		/*
		 * Age.
		 */
		new Label(composite, SWT.NONE).setText("Age:*");
		
		age = new Spinner(composite, SWT.BORDER);
		age.setMinimum(0);
		age.setMaximum(200);
		age.setSelection(person.getAge());
		age.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		age.addModifyListener(new ModifyListener() {			
			@Override
			public void modifyText(ModifyEvent e) {
				person.setAge(age.getSelection());
			}
		});
		
		/*
		 * Gender.
		 */
		new Label(composite, SWT.NONE).setText("Gender:*");
		
		gender = new Combo(composite, SWT.READ_ONLY);
		gender.setItems(new String[]{"Male", "Female"});
		gender.setText(person.getGender());
		gender.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		gender.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				person.setGender(gender.getText());
			}
		});
		
		/*
		 * Profession.
		 */
		new Label(composite, SWT.NONE).setText("Profession:*");
		
		profession = new Text(composite, SWT.BORDER);
		profession.setText(person.getProfession());
		profession.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		profession.addModifyListener(new ModifyListener() {			
			@Override
			public void modifyText(ModifyEvent e) {
				person.setProfession(profession.getText());
			}
		});
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		/*
		 * This editor will not be saving anything.
		 */
	}

	@Override
	public void doSaveAs() {
	}
	
	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void setFocus() {
		firstName.setFocus();
	}
}
