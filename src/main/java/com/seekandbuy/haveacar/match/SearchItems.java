package com.seekandbuy.haveacar.match;
import java.util.List;

public abstract class SearchItems<X, T> {
	
	public abstract List<T> ListAllProductsByUser(X x, List<T> t);
}
