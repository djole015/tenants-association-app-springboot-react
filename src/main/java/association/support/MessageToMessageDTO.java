package association.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import association.dto.MessageDTO;
import association.model.Message;

@Component
public class MessageToMessageDTO implements Converter<Message, MessageDTO> {

	@Override
	public MessageDTO convert(Message source) {
		MessageDTO dto = new MessageDTO();

		dto.setId(source.getId());
		dto.setTitle(source.getTitle());
		dto.setDescription(source.getDescription());
		dto.setAccepted(source.isAccepted());
		dto.setType(source.getType());
		dto.setPercentageRequired(source.getPercentageRequired());
		dto.setCreatedAt(source.getCreatedAt());

		dto.setFlatId(source.getFlat().getId());
		dto.setFlatAddress(source.getFlat().getAddress());
		dto.setFlatNoOfTenants(source.getFlat().getNoOfTenants());
		
		return dto;
	}

	public List<MessageDTO> convert(List<Message> sourceList) {
		List<MessageDTO> dtoList = new ArrayList<>();

		for (Message source : sourceList) {
			dtoList.add(convert(source));
		}

		return dtoList;
	}

}
