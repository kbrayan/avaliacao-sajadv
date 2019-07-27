package desafio.criteria;
import lombok.Getter;
import lombok.Setter;

public class SearchCriteria {
    @Getter
    @Setter
    private String key;

    @Getter
    @Setter
    private String operation;

    @Getter
    @Setter
    private Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }


}

