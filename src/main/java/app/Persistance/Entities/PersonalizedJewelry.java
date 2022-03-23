package app.Persistance.Entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonalizedJewelry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;
	private String text;
	private Double price;
	private Boolean withCristals;
	private String imageName;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@OneToMany (mappedBy = "personalizedJewelry", orphanRemoval = true,
			cascade = CascadeType.ALL)
	private Set<CartPers> cartPers;
}
