package be.abis.exercise.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class CancelConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean){
        return aBoolean ? "C" : null;
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return s != null && s.equalsIgnoreCase("c");
    }

}
