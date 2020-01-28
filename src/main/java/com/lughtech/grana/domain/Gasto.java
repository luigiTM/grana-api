package com.lughtech.grana.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Gasto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGasto;
	@ManyToOne
	@JoinColumn(name = "id_grana")
	private Grana idGrana;
	private String tipo;
	private Float gasto;
	private Date dataGasto;

	@ManyToMany
	@JoinTable(name = "Gasto_Pessoa", joinColumns = @JoinColumn(name = "gasto_id"), inverseJoinColumns = @JoinColumn(name = "pessoa_id"))
	private List<Pessoa> pessoas = new ArrayList<>();

	public Gasto() {
	}

	public Gasto(Integer idGasto, Grana idGrana, String tipo, Float gasto, Date dataGasto) {
		this.idGrana = idGrana;
		this.tipo = tipo;
		this.gasto = gasto;
		this.dataGasto = dataGasto;
	}

	public Integer getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(Integer idGasto) {
		this.idGasto = idGasto;
	}

	public Grana getIdGrana() {
		return idGrana;
	}

	public void setIdGrana(Grana idGrana) {
		this.idGrana = idGrana;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Float getGasto() {
		return gasto;
	}

	public void setGasto(Float gasto) {
		this.gasto = gasto;
	}

	public Date getDataGasto() {
		return dataGasto;
	}

	public void setDataGasto(Date dataGasto) {
		this.dataGasto = dataGasto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGasto == null) ? 0 : idGasto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gasto other = (Gasto) obj;
		if (idGasto == null) {
			if (other.idGasto != null)
				return false;
		} else if (!idGasto.equals(other.idGasto))
			return false;
		return true;
	}

}
