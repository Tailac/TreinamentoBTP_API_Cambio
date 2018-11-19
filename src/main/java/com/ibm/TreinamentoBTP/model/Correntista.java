package com.ibm.TreinamentoBTP.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Correntista {
	
	
	@Id
	@GeneratedValue(
			generator="seq_correntista",
			strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(
			allocationSize = 1,
			name="seq_correntista",
			sequenceName = "seq_correnstista")
	private Long id;
	
	@NotNull
	private String nome;
	
	private String cpf;

    @OneToOne
	private Conta conta;

}
