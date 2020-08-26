package association.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String title;
	@Column
	private String type;
	@Column
	private Double percentageRequired;
	@Column
	private boolean accepted = false;
	@Column(nullable = false)
	private String description;
	@Column
	private Date createdAt;

	@ManyToOne
	private Flat flat;

	public Message() {
		super();
	}

	public Message(Long id, String title, String type, Double percentageRequired, boolean accepted, String description,
			Date createdAt, Flat flat) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.percentageRequired = percentageRequired;
		this.accepted = accepted;
		this.description = description;
		this.createdAt = createdAt;
		this.flat = flat;
	}

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

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
		/*
		 * if (flat != null && !flat.getMessages().contains(this)) {
		 * flat.getMessages().add(this); }
		 */
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
