package com.epam.jmp2.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accident_severity")
public class AccidentSeverity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer code;

    @Column(name ="label", nullable = false)
    private String label;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy="accidentSeverity")
    private List<Accident> accidents;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Accident> getAccidents() {
		return accidents;
	}

	public void setAccidents(List<Accident> accidents) {
		this.accidents = accidents;
	}
    
    
    
    
}
