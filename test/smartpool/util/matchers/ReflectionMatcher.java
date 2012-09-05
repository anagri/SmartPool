package smartpool.util.matchers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ReflectionMatcher<T> extends BaseMatcher<T> {
    private T expected;
    private Object actual;

    public ReflectionMatcher(T o) {
        expected = o;
    }

    @Override
    public boolean matches(Object actual) {
        this.actual = actual;
        return EqualsBuilder.reflectionEquals(expected, this.actual);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(ToStringBuilder.reflectionToString(expected) + " is not equal to " + ToStringBuilder.reflectionToString(actual));
    }

    public static<T> ReflectionMatcher<T> reflectionEquals(T o) {
        return new ReflectionMatcher<T>(o);
    }
}
