package desafio.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import desafio.model.*;

public class PessoaSpecification implements Specification<Pessoa> {

  private static final long serialVersionUID = -7240410294574198143L;
  
  private SearchCriteria criteria;

  public PessoaSpecification(SearchCriteria criteria) {
    this.criteria = criteria;
  }

  public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

    if (criteria.getOperation().equalsIgnoreCase(">")) {

      return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());

    } else if (criteria.getOperation().equalsIgnoreCase("<")) {

      return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());

    } else if (criteria.getOperation().equalsIgnoreCase(":")) {

      if (root.get(criteria.getKey()).getJavaType() == String.class) {

        return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");

      } else {

        return builder.equal(root.get(criteria.getKey()), criteria.getValue());
      }
    }

    return null;
  }

}
