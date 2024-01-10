package fr.isika.cda.javaee.viewModel;

import fr.isika.cda.javaee.entity.accounts.Goal;
import fr.isika.cda.javaee.entity.accounts.Profile;
import fr.isika.cda.javaee.entity.accounts.Role;

public class AccountViewModel {

	private Long id;
	private String email;
	private String password;
	private Role role;
	private Goal goal = new Goal();
	private boolean connected;

	 private int wizardStep;
	 private Profile profile = new Profile();
	private Long gymId;

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

	
	 public int getWizardStep() {
	        return wizardStep;
	    }

	    public void setWizardStep(int wizardStep) {
	        this.wizardStep = wizardStep;
	    }
		public Profile getProfile() {
			return profile;
		}
		public void setProfile(Profile profile) {
			this.profile = profile;
		}
		
		
		public Long getGymId() {
			return gymId;
		}
		public void setGymId(Long gymId) {
			this.gymId = gymId;
		}

	
	





}
