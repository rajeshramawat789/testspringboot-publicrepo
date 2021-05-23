package com.test.mapper;

import java.util.Collection;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
public interface GenericMapper<E, D> {

  E dtoToEntity(D dto);

  D entityToDto(E entity);

  Collection<E> dtoToEntity(Collection<D> dtos);
}
