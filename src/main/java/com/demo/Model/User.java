package com.demo.Model;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import com.demo.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String gender;
	private String emailId;
	private String phoneNumber;
	private String userType=Constant.USER_TYPE.NORMAL;
	private String password;
	private Boolean isActive = true;
	private Integer loginCount=0;
	private String ssoType;
	private LocalDateTime loginAt;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@PrePersist
	public void onSave() {
		// LocalDateTime currentDateTime = new LocalDateTime();

		this.createdAt = LocalDateTime.now();

		this.updatedAt = LocalDateTime.now();
	}

	@PostPersist
	public void onUpdate() {
		// LocalDateTime currentDateTime = new LocalDateTime();

		this.updatedAt = LocalDateTime.now();
		
	}

}
