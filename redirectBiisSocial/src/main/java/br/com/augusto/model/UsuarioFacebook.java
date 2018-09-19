package br.com.augusto.model;

import org.json.JSONObject;

public class UsuarioFacebook {

	private Long id;
	private String firstName;
	private Integer timezone;
	private String email;
	private Boolean verified;
	private String middleName;
	private String gender;
	private String lastName;
	private String link;
	private String locale;
	private String name;
	private String updatedTime;

	public UsuarioFacebook(JSONObject jsonUsuario) {
		
		try {
			if (jsonUsuario != null) {
				
				if (!jsonUsuario.isNull("id")) {
					id = jsonUsuario.getLong("id");
				}
				
				if (!jsonUsuario.isNull("first_name")) {
					firstName = jsonUsuario.getString("first_name");
				}
				if (!jsonUsuario.isNull("timezone")) {
					timezone = jsonUsuario.getInt("timezone");
				}

				if (!jsonUsuario.isNull("email")) {
					email = jsonUsuario.getString("email");
				}

				if (!jsonUsuario.isNull("verified")) {
					verified = jsonUsuario.getBoolean("verified");
				}

				if (!jsonUsuario.isNull("middle_name")) {
					middleName = jsonUsuario.getString("middle_name");
				}

				if (!jsonUsuario.isNull("gender")) {
					gender = jsonUsuario.getString("gender");
				}

				if (!jsonUsuario.isNull("last_name")) {
					lastName = jsonUsuario.getString("last_name");
				}

				if (!jsonUsuario.isNull("link")) {
					link = jsonUsuario.getString("link");
				}

				if (!jsonUsuario.isNull("locale")) {
					locale = jsonUsuario.getString("locale");
				}
				if (!jsonUsuario.isNull("name")) {
					name = jsonUsuario.getString("name");
				}

				if (!jsonUsuario.isNull("updated_time")) {
					updatedTime = jsonUsuario.getString("updated_time");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "UsuarioFacebook [id=" + id + ", firstName=" + firstName + ", timezone=" + timezone + ", email=" + email
				+ ", verified=" + verified + ", middleName=" + middleName + ", gender=" + gender + ", lastName="
				+ lastName + ", link=" + link + ", locale=" + locale + ", name=" + name + ", updatedTime=" + updatedTime
				+ "]";
	}

}
