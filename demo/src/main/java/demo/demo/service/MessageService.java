package demo.demo.service;


import demo.demo.entity.Message;
import demo.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface MessageService {

    Message save(Message message);

    List<Message> getAllByUserId(Long id);

}
