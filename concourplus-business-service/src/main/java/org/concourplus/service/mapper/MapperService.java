package org.concourplus.service.mapper;

import java.util.Collection;

import org.dozer.MappingException;

public interface MapperService {

	public void map(Object source, Object destination, String mapId) throws MappingException;

	public <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException;
	
    public <U, T> Collection<T> mapCollection(Collection<U> source, Class<T> destinationClass, String mapId) throws MappingException;

}
