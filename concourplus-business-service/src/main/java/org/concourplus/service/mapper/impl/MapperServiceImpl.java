package org.concourplus.service.mapper.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.concourplus.service.mapper.MapperService;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service("mapperService")
@Lazy(false)
public class MapperServiceImpl implements MapperService {

	@Autowired
	private Mapper mapper;

	@Override
	public void map(Object source, Object destination, String mapId) throws MappingException {
		mapper.map(source, destination, mapId);
	}

	@Override
	public <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException {
		return mapper.map(source, destinationClass, mapId);
	}

	@Override
	public <U, T> Collection<T> mapCollection(Collection<U> source, Class<T> destinationClass, String mapId)
			throws MappingException {
		Collection<T> destination = Collections.emptyList();
		if (source != null && !source.isEmpty()) {
			destination = new ArrayList<T>(source.size());
			for (U o : source) {
				destination.add(map(o, destinationClass, mapId));
			}
		}
		return destination;
	}

}
