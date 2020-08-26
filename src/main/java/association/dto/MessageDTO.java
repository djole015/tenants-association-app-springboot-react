package association.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageDTO {

	private Long id;
	@NotBlank(message = "Message title is required")
	private String title;
	@Pattern(regexp = "NOTE|PROPOSAL", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String type;
	@Min(0)
	@Max(100)
	private Double percentageRequired;
	private boolean accepted = false;
	@Size(min = 10)
	@NotBlank(message = "Message Description is required")
	private String description;
	@JsonFormat(pattern = "yyyy-mm-dd")
	//@NotBlank(message = "Date is required")
	private Date createdAt;

	private Long flatId;
	private String flatAddress;
	private Integer flatNoOfTenants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPercentageRequired() {
		return percentageRequired;
	}

	public void setPercentageRequired(Double percentageRequired) {
		this.percentageRequired = percentageRequired;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getFlatId() {
		return flatId;
	}

	public void setFlatId(Long flatId) {
		this.flatId = flatId;
	}

	public String getFlatAddress() {
		return flatAddress;
	}

	public void setFlatAddress(String flatAddress) {
		this.flatAddress = flatAddress;
	}

	public Integer getFlatNoOfTenants() {
		return flatNoOfTenants;
	}

	public void setFlatNoOfTenants(Integer flatNoOfTenants) {
		this.flatNoOfTenants = flatNoOfTenants;
	}

}
