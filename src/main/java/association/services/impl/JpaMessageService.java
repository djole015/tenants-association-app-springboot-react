package association.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import association.model.Message;
import association.repositories.MessageRepository;
import association.services.MessageService;

@Service
public class JpaMessageService implements MessageService {
	
	@Autowired
	MessageRepository messageRepository;

	@Override
	public Page<Message> findAll(int pageNum) {
		return messageRepository.findAll(PageRequest.of(pageNum, 5));
	}

	@Override
	public Message findOne(Long id) {
		return messageRepository.findById(id).get();
	}

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public Message delete(Long id) {
		Message message = messageRepository.getOne(id);
		
		if (message != null) {
			messageRepository.deleteById(id);;
		}
		
		return message;
	}

	@Override
	public List<Message> findByFlatId(Long flatId) {

		return messageRepository.findByFlatId(flatId);
	}

	@Override
	public Page<Message> search(String title, String type, Long flatId, int pageNum) {
		if (title != null) {
			title = "%" + title + "%";
		}

		if (type != null) {
			type = "%" + type + "%";
		}
		return messageRepository.search(title, type, flatId, PageRequest.of(pageNum, 5));
	}

}
