package com.zycus.bankapp.dao;

import java.util.*;

public interface DAO<T> {
	void create(T object);
	List<T> findAll();
	T findById(int i);
}
