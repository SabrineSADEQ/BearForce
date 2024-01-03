package fr.isika.cda.javaee.viewModel;

import fr.isika.cda.javaee.entity.accounts.Goal;
import fr.isika.cda.javaee.entity.accounts.Role;

public class AccountViewModel {
	
	private Long id;
	private String email;
	private String password;
	private Role role;
	private Goal goal;
	private boolean connected;
	
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
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountViewModel [role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}
	
	

}
