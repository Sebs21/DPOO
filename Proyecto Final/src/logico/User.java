package logico;

public class User {
	private String usuario;
	private String pass;
	private String tipo;

	public User(String usuario, String pass, String tipo) {
		super();
		this.usuario = usuario;
		this.pass = pass;
		this.tipo = tipo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
