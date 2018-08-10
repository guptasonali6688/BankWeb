package com.zycus.bankWebApp;

import java.util.*;

public interface DAO<T> {
	void create(T object);
	List<T> findAll();
	T findById(int i);
}
