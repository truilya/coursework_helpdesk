package coursework_helpdesk.config.converters;

import coursework_helpdesk.model.IssueStatus;
import coursework_helpdesk.repository.IssueStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("statusConverter")
public class StatusConverter implements Converter<String, IssueStatus> {

    @Autowired
    private IssueStatusRepository repository;

    @Override
    public IssueStatus convert(String s) {
        return repository.findOne(Integer.parseInt(s));
    }
}
