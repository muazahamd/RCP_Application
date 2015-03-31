package com.rcp.application.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rcp.application.Person;

/**
 * Editor input for Person's object.
 * 
 * @author muahmad
 * 
 */
public class PersonEditorInput implements IEditorInput {
	
	private Person person;
	
	public PersonEditorInput(Person person){
		this.person = person;
	}
	
	public Person getPerson(){
		return this.person;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return person.toString();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Display a person";
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + person.getID();
        return result;
    }
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonEditorInput other = (PersonEditorInput) obj;
        if (person.getID() != other.getPerson().getID())
            return false;
        return true;
    }
}
