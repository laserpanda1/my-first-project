package sia.tacocloud.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import sia.tacocloud.*;
import java.util.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
@Component
@ConfigurationProperties(prefix="taco.orders")
public class OrderProps {

    @Min(value = 5, message = "must be between 5 and 25")
    @Max(value = 25, message = "must be between 5 and 25")
    private int pageSize = 20;
}
