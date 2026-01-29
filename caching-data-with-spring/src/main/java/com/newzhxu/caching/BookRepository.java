package com.newzhxu.caching;

public interface BookRepository {

    Book getByIsbn(String isbn);

}