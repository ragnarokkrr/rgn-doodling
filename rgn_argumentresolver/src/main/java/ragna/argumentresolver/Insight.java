package ragna.argumentresolver;

import java.util.Objects;

public class Insight {
    private String bar;
    private String foo;

    public Insight(String bar, String foo) {
        this.bar = bar;
        this.foo = foo;
    }

    public Insight() {
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Override
    public String toString() {
        return "FooBar{" +
                "bar='" + bar + '\'' +
                ", foo='" + foo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Insight fooBar = (Insight) o;
        return Objects.equals(getBar(), fooBar.getBar()) &&
                Objects.equals(getFoo(), fooBar.getFoo());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getBar(), getFoo());
    }
}
