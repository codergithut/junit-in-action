package tianjian.section02;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by tianjian on 2017/12/14.
 */
public class HamcrestTest {
    private List<String> values;

    @Before
    public void setUpList() {
        values = new ArrayList<String>();
        values.add("x");
        values.add("y");
        values.add("z");
    }

    @Test
    public void testWithoutHamcrest() {
        assertTrue(values.contains("one")
                || values.contains("two")
                || values.contains("three"));
    }

    @Test
    public void testWithHamcrest() {
        assertThat(values, hasItem(
                anyOf(equalTo("one"),
                        equalTo("two"),
                        equalTo("three"))));
    }
}
