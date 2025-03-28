package hezix.org.converter;

import hezix.org.entity.Birthday;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;
import java.util.Optional;

@Converter(autoApply = true)
public class BirthDayConverter implements AttributeConverter<Birthday, Date> {
    @Override
    public Date convertToDatabaseColumn(Birthday birthday) {
        return Optional.ofNullable(birthday)
                .map(Birthday::birthdate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .map(Birthday::new)
                .orElse(null);
    }
}
