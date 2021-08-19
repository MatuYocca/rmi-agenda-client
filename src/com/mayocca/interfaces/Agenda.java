package com.mayocca.interfaces;

import com.mayocca.clases.Contact;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Agenda extends Remote {

    public ArrayList<Contact> getContacts() throws RemoteException;

    public ArrayList<Contact> getContactsByName(String name) throws RemoteException;

    public Contact addContact(Contact c) throws RemoteException;

    public void clearContacts() throws RemoteException;

}
