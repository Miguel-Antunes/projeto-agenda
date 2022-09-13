package io.github.miguelantunes.agendaapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 150)
	private String nome;
	
	@Column(name = "email", nullable = false, length = 150)
	private String email;
	
	@Column	
	private Boolean favorito;
	
	

}
