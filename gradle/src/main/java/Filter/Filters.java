package Filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import object.Scan;

public interface Filters extends Serializable {
public boolean comper(Scan scan);
}
