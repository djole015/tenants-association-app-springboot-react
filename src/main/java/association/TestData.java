package association;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import association.model.Flat;
import association.model.Message;
import association.services.FlatService;
import association.services.MessageService;

@Component
public class TestData {
	@Autowired
	private MessageService messageService;

	@Autowired
	private FlatService flatService;

	@PostConstruct
	public void init() throws ParseException {

		Flat f1 = new Flat();
		f1.setAddress("Prote Smiljanica 48");
		f1.setPresident("Mika Mikic");
		f1.setNoOfApartments(42);
		f1.setNoOfTenants(99);

		flatService.save(f1);

		Flat f2 = new Flat();
		f2.setAddress("Vlade Jovanovica 2");
		f2.setPresident("Djoka Djokic");
		f2.setNoOfApartments(21);
		f2.setNoOfTenants(55);

		flatService.save(f2);

		Flat f3 = new Flat();
		f3.setAddress("Cerska 3");
		f3.setPresident("Laza Lazic");
		f3.setNoOfApartments(8);
		f3.setNoOfTenants(15);

		flatService.save(f3);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd"); 

		Message a1 = new Message();
		a1.setTitle("finance");
		a1.setDescription("empty fund");
		a1.setType("note");
		a1.setPercentageRequired(12D);
		a1.setFlat(f1);
		a1.setCreatedAt(formatter.parse("2020-1-11"));

		messageService.save(a1);

		Message a2 = new Message();
		a2.setTitle("lock replacement");
		a2.setDescription("6/15 locksmith comming");
		a2.setType("note");
		a2.setPercentageRequired(12D);
		a2.setFlat(f1);
		a2.setCreatedAt(formatter.parse("2020-1-12"));

		messageService.save(a2);

		Message a3 = new Message();
		a3.setTitle("no water/low pressure");
		a3.setDescription("6/11 from 8am-10am");
		a3.setType("note");
		a3.setPercentageRequired(12D);
		a3.setFlat(f2);
		a3.setCreatedAt(formatter.parse("2020-2-13"));

		messageService.save(a3);

		Message a4 = new Message();
		a4.setTitle("facade painting");
		a4.setDescription("in favor or against");
		a4.setType("proposal");
		a4.setPercentageRequired(66D);
		a4.setFlat(f3);
		a4.setCreatedAt(formatter.parse("2020-3-14"));

		messageService.save(a4);

	}

}
