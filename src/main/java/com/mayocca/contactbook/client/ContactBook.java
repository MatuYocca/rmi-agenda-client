package com.mayocca.contactbook.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ContactBook extends Remote {

    public ArrayList<Contact> getContacts() throws RemoteException;

    public ArrayList<Contact> getContactsByName(String name) throws RemoteException;

    public Contact addContact(Contact c) throws RemoteException;

    public void clearContacts() throws RemoteException;

}
