package acme.entities.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 100)
	protected String name;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@NotBlank
	@Length(max = 100)
	protected String technology;

	@NotBlank
	@Length(max = 255)
	protected String description;

	// The annotation @PositiveOrZero does not apply
	// here since we have a single data type. We will
	// learn how to implement a custom restriction.

	@NotNull
	protected Money retailPrice;

	@URL
	protected String info;

	@NotNull
	protected ItemType type;

	protected boolean draftMode;

	// Derived attributes ----------------------------------------------------

	// Relationships ----------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor inventor;
}
