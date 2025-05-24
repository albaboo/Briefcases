package gestormusica;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;
import java.io.*;

@XmlRootElement(name = "playlist")
@XmlAccessorType(XmlAccessType.FIELD) 
public class PlayList  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Expose
	@XmlElement(required = true)
	private String nom;
	
	@Expose
	@XmlElementWrapper(name = "llista")
    @XmlElement(name = "canso")
	private List<Canso> llista;
	
	public PlayList() {
		llista = new ArrayList<>();
	}
	public PlayList(String nom) {
		this.nom = nom;
		llista = new ArrayList<>();
	}
	
	public PlayList(String nom, List<Canso> llista) {
		this.nom = nom;
		this.llista = llista;
	}

	public List<Canso> getLlista() {
		return llista;
	}

	public void setLlista(List<Canso> llista) {
		this.llista = llista;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
    public void afegirCanso(Canso canso) {
        llista.add(canso);
    }
    
    public void eliminarCanso(Canso canso) {
    	llista.remove(canso);
    }

}
