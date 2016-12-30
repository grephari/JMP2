package com.epam.jmp2.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accident_severity")
public class AccidentSeverity implements Serializable {

	private static final long serialVersionUID = -8858117283638858596L;

	@Id
	private Integer code;

	@Column(name = "label", nullable = false)
	private String label;

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

}
