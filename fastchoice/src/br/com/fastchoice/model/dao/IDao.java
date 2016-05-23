package br.com.fastchoice.model.dao;

import java.util.ArrayList;

public interface IDao<T> {
	
	public void create(T obj);
	public ArrayList<T> read(String obj);
	public Object readWithId(String obj, long id);
	public ArrayList<T> readQuery(String obj, String query);
	public void update(T obj);
	public void delete(T obj);

}
