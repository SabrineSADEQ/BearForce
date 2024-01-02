package fr.isika.cda.javaee.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


	@Entity
	public class Account {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column(unique = true)
		private String email;
		private String password;
		@Enumerated(EnumType.STRING)
		private Role role;
		private Goal goal;

		public Account(String email, String password) {
			this.email = email;
			this.password = password;
		}

		public Account() {
		}

		public Account(Long id, String email, String password) {
			this.id = id;
			this.email = email;
			this.password = password;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public Goal getGoal() {
			return goal;
		}

		public void setGoal(Goal goal) {
			this.goal = goal;
		}

}
