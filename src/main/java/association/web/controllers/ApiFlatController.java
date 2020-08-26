package association.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import association.model.Flat;
import association.model.Message;
import association.services.FlatService;
import association.services.MessageService;


@RestController
@RequestMapping("/api/flats")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiFlatController {
	
	@Autowired
	FlatService flatService;
	
	@Autowired
	private MessageService  messageService;

	@GetMapping
	public ResponseEntity<List<Flat>> getFlats(){
		List<Flat> flats = null;

		flats = flatService.findAll();

		return new ResponseEntity<>(flats, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Flat> get(@PathVariable Long id) {

		Flat flat = flatService.findOne(id);

		if (flat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(flat, HttpStatus.OK);
	}

	@GetMapping("/{id}/messages")
	ResponseEntity<List<Message>> getMessages(@PathVariable("id") Long flatId) {

		List<Message> messages = messageService.findByFlatId(flatId);

		if (messages == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Flat> add(@Valid @RequestBody Flat newFlat) {

		Flat savedFlat = flatService.save(newFlat);

		return new ResponseEntity<>(savedFlat, HttpStatus.CREATED);
	}
}
