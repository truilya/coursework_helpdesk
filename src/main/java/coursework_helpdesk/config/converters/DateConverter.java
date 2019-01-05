package coursework_helpdesk.config.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component("dateConverter")
public class DateConverter implements Converter<String, LocalDateTime> {


    @Override
    public LocalDateTime convert(String s)  {
        DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm");
        return LocalDateTime.parse(s,format);
    }
}
