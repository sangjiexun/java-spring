package rsys.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Table(name="users")
@Entity
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "password")
	private String password;

	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Email
	@Size(min = 2, max = 30)
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "section")
	@Enumerated(EnumType.STRING)
	private SectionName sectionName;

	@NotNull
	@Min(value=150000)
	private Long salary;

	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public String toCsvString() {
		return id + "," + firstName + "," + lastName + "," + email + "," + password + "," + sectionName + "," + salary + "," + roleName;
	}

	public static User of(Integer id, @NotNull @Size(min = 2, max = 30) String firstName,
			@NotNull @Size(min = 2, max = 30) String lastName,
			@NotNull @Email @Size(min = 2, max = 30) String email,
			@NotNull @Size(min = 2, max = 30) String password,
			@NotNull SectionName sectionName,
			@NotNull @Min(150000) Long salary, RoleName roleName)  {
		return new User(id, firstName, lastName, email, password, sectionName, salary, roleName);
	}

	public User() {}

	public User(Integer id, @NotNull @Size(min = 2, max = 30) String firstName,
			@NotNull @Size(min = 2, max = 30) String lastName,
			@NotNull @Email @Size(min = 2, max = 30) String email,
			@NotNull @Size(min = 2, max = 30) String password,
			@NotNull SectionName sectionName,
			@NotNull @Min(150000) Long salary, RoleName roleName) {
		super();
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.sectionName = sectionName;
		this.salary = salary;
		this.roleName = roleName;
	}
}
