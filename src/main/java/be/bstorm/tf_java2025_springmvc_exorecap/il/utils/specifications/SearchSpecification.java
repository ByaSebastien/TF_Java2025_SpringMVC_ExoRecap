package be.bstorm.tf_java2025_springmvc_exorecap.il.utils.specifications;

import be.bstorm.tf_java2025_springmvc_exorecap.il.utils.request.SearchParam;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public interface SearchSpecification {

    static <T> Specification<T> search(SearchParam<T> searchParam){
        return (root, query, cb) -> switch (searchParam.op()) {
            case EQ -> cb.equal(root.get(searchParam.field()),searchParam.value());
            case NE -> cb.notEqual(root.get(searchParam.field()),searchParam.value());
            case GT -> {
                try {
                    Number number = new BigDecimal(searchParam.value().toString());
                    yield cb.gt(root.get(searchParam.field()), number);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Value must be a number");
                }
            }
            case GTE -> {
                try {
                    Number number = new BigDecimal(searchParam.value().toString());
                    yield cb.ge(root.get(searchParam.field()), number);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Value must be a number");
                }
            }
            case LT -> {
                try {
                    Number number = new BigDecimal(searchParam.value().toString());
                    yield cb.lt(root.get(searchParam.field()), number);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Value must be a number");
                }
            }
            case LTE -> {
                try {
                    Number number = new BigDecimal(searchParam.value().toString());
                    yield cb.le(root.get(searchParam.field()), number);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Value must be a number");
                }
            }
            case START -> cb.like(root.get(searchParam.field()),searchParam.value() + "%");
            case END -> cb.like(root.get(searchParam.field()),"%" + searchParam.value());
            case CONTAINS -> cb.like(root.get(searchParam.field()),"%" + searchParam.value() + "%");
            case IN -> root.get(searchParam.field()).in(((String)searchParam.value()).split(","));
            case NIN -> cb.not(root.get(searchParam.field())).in(((String)searchParam.value()).split(","));
        };
    }
}
