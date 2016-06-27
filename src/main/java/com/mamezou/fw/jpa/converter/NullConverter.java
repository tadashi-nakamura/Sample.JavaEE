package com.mamezou.fw.jpa.converter;

import javax.persistence.AttributeConverter;

public interface NullConverter<F, C> extends AttributeConverter<F, C> {

	@Override
	public abstract C convertToDatabaseColumn(F attribute);

	@Override
	public abstract F convertToEntityAttribute(C dbData);

}
