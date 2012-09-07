package smartpool.util.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static java.lang.String.format;

public class ContainsMatcher extends BaseMatcher<String> {
    private final String subString;
    private String fullString;

    public ContainsMatcher(String subString) {
        this.subString = subString;
    }

    @Override
    public boolean matches(Object o) {
        if (o == null) return false;
        fullString = o.toString();
        return fullString.contains(subString);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("%s does not contain %s", fullString, subString));
    }

    public static ContainsMatcher contains(String subString) {
        return new ContainsMatcher(subString);
    }
}
