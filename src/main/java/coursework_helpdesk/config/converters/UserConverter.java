package coursework_helpdesk.config.converters;

import coursework_helpdesk.model.User;
import coursework_helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter implements Converter<String, User> {

    @Autowired
    private UserRepository repository;

    @Override
    public User convert(String s) {
        return repository.findOne(Integer.parseInt(s));
    }
}
