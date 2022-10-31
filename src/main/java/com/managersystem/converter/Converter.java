package com.managersystem.converter;

/**
 * Converter for Data Access Object and Data Transport Object
 *
 * @param <T> Data Access Object
 * @param <E> Data Transport Object
 */
public interface Converter<T, E> {

    /**
     * DAO to DTO
     *
     * @param dao
     * @return dto
     */
    E convertTo(T dao);

    /**
     * DAO from DTO
     *
     * @param dto
     * @return dao
     */
    T convertFrom(E dto);
}
