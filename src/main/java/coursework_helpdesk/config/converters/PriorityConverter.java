package coursework_helpdesk.config.converters;

import coursework_helpdesk.model.IssuePriority;
import coursework_helpdesk.repository.IssuePriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("priorityConverter")
public class PriorityConverter implements Converter<String,IssuePriority> {

    @Autowired
    private IssuePriorityRepository repository;

    @Override
    public IssuePriority convert(String s) {
        return repository.findOne(Integer.parseInt(s));
    }
}
