package association.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import association.dto.MessageDTO;
import association.model.Message;
import association.services.MessageService;
import association.support.MessageDTOToMessage;
import association.support.MessageToMessageDTO;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiMessageController {
	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageToMessageDTO toDTO;

	@Autowired
	MessageDTOToMessage toMessage;

	@GetMapping()
	ResponseEntity<List<MessageDTO>> getAllMessages(@RequestParam(required = false) String title,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long flatId,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Message> messagesPage;

		if (title != null || type != null || flatId != null) {
			messagesPage = messageService.search(title, type, flatId, pageNum);
		} else {

			messagesPage = messageService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(messagesPage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(messagesPage.getContent()), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<MessageDTO> getMessageById(@PathVariable Long id) {
		Message message = messageService.findOne(id);
		if (message == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(message), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<MessageDTO> deleteMessage(@PathVariable Long id) {
		Message deleted = messageService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<MessageDTO> addMassage(@Valid @RequestBody MessageDTO newMessageDTO) {

		Message savedMessage = messageService.save(toMessage.convert(newMessageDTO));

		return new ResponseEntity<>(toDTO.convert(savedMessage), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<MessageDTO> updateMessage(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Long id) {

		if (!id.equals(messageDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Message persisted = messageService.save(toMessage.convert(messageDTO));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
