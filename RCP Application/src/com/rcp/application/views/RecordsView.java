package com.rcp.application.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import com.rcp.application.OpenInEditor;
import com.rcp.application.Person;

public class RecordsView extends ViewPart {
	/** View's ID. */
	public static final String ID = "com.rcp.application.details.records";

	private TableViewer tableViewer;
	private ArrayList<Person> list = new ArrayList<Person>();
	
	private PropertyChangeListener listener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			tableViewer.update(evt.getSource(), null);
		}
	};

	public void createPartControl(Composite parent) {
		list = new ArrayList<Person>();

		/*
		 * Create table viewer to show list of persons.
		 */
		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.SINGLE);
		tableViewer.setContentProvider(new ArrayContentProvider());
		
		final Table table = tableViewer.getTable();
		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		table.setLayoutData(tableData);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		/*
		 * Create table's columns.
		 */
		createColumns();
		
		/*
		 * Set input to table viewer.
		 */
		tableViewer.setInput(list);

		/*
		 * Set table viewer as the selection provider for the site.
		 */
		getSite().setSelectionProvider(tableViewer);
		
		/*
		 * Add double click listener to open data in an editor.
		 */
		hookDoubleClickCommand();
		
		/*
		 * Add Toolbar action.
		 */
		fillToolbar();
	}

	private void fillToolbar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
		tbm.add(new AddNewRecordAction());
	}

	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

	private void createColumns() {
		createFirstNameColumn();
		createLastNameColumn();
		createAgeColumn();
		createGenderColumn();
		createProfessionColumn();
	}

	private void createFirstNameColumn() {
		TableViewerColumn firstName = new TableViewerColumn(tableViewer,
				SWT.NONE);
		TableColumn col = firstName.getColumn();
		col.setText("First Name");
		col.setWidth(100);
		firstName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Person) element).getFirstName();
			}
		});
	}

	public void createLastNameColumn() {
		TableViewerColumn lastName = new TableViewerColumn(tableViewer,
				SWT.NONE);
		TableColumn col = lastName.getColumn();
		col.setText("Last Name");
		col.setWidth(100);
		lastName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Person) element).getLastName();
			}
		});
	}

	public void createAgeColumn() {
		TableViewerColumn age = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn col = age.getColumn();
		col.setText("Age");
		col.setWidth(100);
		age.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return Integer.toString(((Person) element).getAge());
			}
		});
	}

	public void createGenderColumn() {
		TableViewerColumn gender = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn col = gender.getColumn();
		col.setText("Gender");
		col.setWidth(100);
		gender.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Person) element).getGender();
			}
		});
	}

	public void createProfessionColumn() {
		TableViewerColumn profession = new TableViewerColumn(tableViewer,
				SWT.NONE);
		TableColumn col = profession.getColumn();
		col.setText("Profession");
		col.setWidth(150);
		profession.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Person) element).getProfession();
			}
		});
	}

	private void hookDoubleClickCommand() {
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				IHandlerService handlerService = (IHandlerService) getSite()
						.getService(IHandlerService.class);

				try {
					ISelection s = tableViewer.getSelection();
					if (!(s instanceof IStructuredSelection))
						return;

					/*
					 * If selection is empty, simply return.
					 */
					IStructuredSelection ss = (IStructuredSelection) s;
					if (ss.isEmpty())
						return;

					/*
					 * Setup event parameters.
					 */
					Person person = (Person) ss.getFirstElement();
					Event ev = new Event();
					ev.data = person;
					ev.item = tableViewer.getTable();
					
					/*
					 * Add a call back to listen for changes.
					 */
					person.addPropertyChangeListener(listener);

					/*
					 * Run command to open editor.
					 */
					handlerService.executeCommand(OpenInEditor.ID, ev);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Action to add new record.
	 * 
	 * @author muahmad
	 *
	 */
	private class AddNewRecordAction extends Action {

		public AddNewRecordAction() {
			super("Add New");
			
			setId("Add_New");
			setToolTipText("Add new record.");
			setEnabled(true);
		}
		
		@Override
		public void run() {
			IHandlerService handlerService = (IHandlerService) getSite()
					.getService(IHandlerService.class);

			try {
				Person person = new Person(Person.count, "", "", 0, "", "");
				
				/*
				 * Add new record to the list.
				 */
				list.add(person);
				tableViewer.add(person);
				
				/*
				 * Add a call back to listen for changes.
				 */
				person.addPropertyChangeListener(listener);
				
				/*
				 * Setup event parameters.
				 */
				Event ev = new Event();
				ev.data = person;
				ev.item = tableViewer.getTable();

				/*
				 * Run command to open editor.
				 */
				handlerService.executeCommand(OpenInEditor.ID, ev);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}