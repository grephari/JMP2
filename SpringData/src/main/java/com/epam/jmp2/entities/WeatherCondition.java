package com.epam.jmp2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="weather_conditions")
public class WeatherCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer code;

    @Column(name ="label", nullable = false)
    private String label;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accidentIndex")
    private Accident accident;*/

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
