package com.rcp.application;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rcp.application.editor.PersonEditorInput;
import com.rcp.application.editor.PersonEditor;

public class OpenInEditor extends AbstractHandler {

	/** Handler's ID. */
	public static final String ID = "com.rcp.application.open_editor";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		Event ev = (Event) event.getTrigger();
		
		if (ev.data instanceof Person ) {
			Person person = (Person) ev.data;
			PersonEditorInput input = new PersonEditorInput(person);
			try {
				page.openEditor(input, PersonEditor.ID);

			}
			catch (PartInitException e) {
				throw new RuntimeException(e);
			}
		}
		
		return null;
	}
}
