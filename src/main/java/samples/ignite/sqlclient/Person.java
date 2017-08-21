package samples.ignite.sqlclient;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class Person {
    @QuerySqlField(index = true) private Integer id;
    @QuerySqlField(index = true) private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
