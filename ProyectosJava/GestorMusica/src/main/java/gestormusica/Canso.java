package gestormusica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe que emmagatzema les dades d'una canço individual
 * 
 */
@XmlRootElement(name = "canso")
@XmlAccessorType(XmlAccessType.FIELD) 
public class Canso {
	
	@Expose
	@XmlElement(required = true)
	private String titol;
	
	@Expose
	@XmlElement(required = true)
	private String autor;
	
	@Expose
	@XmlElement(required = true)
	private String arxiu;
	
	@Expose
	@XmlElement
	private String arxiuLletra;
	
	@Expose
	@XmlElementWrapper(name = "lletra")
    @XmlElement(name = "linea")
	private List<String> lletra;
	
	@Expose
	@XmlElementWrapper(name = "temps")
    @XmlElement(name = "tempsExacte")
	private List<Integer> tempsLletra;

	/**
	 * Constructor de la classe
	 * @param autor
	 * @param titol
	 * @param arxiu 
	 */
	public Canso(String autor, String titol, String arxiu) {
		this.titol = titol;
		this.autor = autor;
		this.arxiu = arxiu;
	}
	
	public Canso() {
	}

	// Getters i setters
	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getArxiu() {
		return arxiu;
	}

	public void setArxiu(String arxiu) {
		this.arxiu = arxiu;
	}

	public String getArxiuLletra() {
		return arxiuLletra;
	}

	public void setArxiuLletra(String arxiuLletra) {
		this.arxiuLletra = arxiuLletra;
	}

	public List<String> getLletra() {
		return lletra;
	}

	public void setLletra(List<String> lletra) {
		this.lletra = lletra;
	}

	public List<Integer> getTempsLletra() {
		return tempsLletra;
	}

	public void setTempsLletra(List<Integer> tempsLletra) {
		this.tempsLletra = tempsLletra;
	}

	public boolean coincideixAmb(String criteri) {
		return autor.contains(criteri) || titol.contains(criteri);
	}
	
	public void cargarLletra() {
		lletra = new ArrayList<>();
		tempsLletra = new ArrayList<>();
		try {
			List<String> lineas = Files.readAllLines(Paths.get(arxiuLletra));
			for (String linea : lineas) {
				if (linea.matches("\\[\\d+\\].+")) {
					String[] parts = linea.split("]", 2);
					int temps = Integer.parseInt(parts[0].substring(1));
					tempsLletra.add(temps);
					lletra.add(parts[1]);
				} else {
					tempsLletra.add(0);
					lletra.add(linea);
				}
			}
			Utils.mostraMissatge("Lletra afegida per: " + this.getTitol());
		} catch (IOException e) {
			Utils.mostraError("no s'ha pogut llegir de l'arxiu " + arxiuLletra);
		}
	}

	@Override
	public String toString() {
		return autor + " - " + titol;
	}
}
