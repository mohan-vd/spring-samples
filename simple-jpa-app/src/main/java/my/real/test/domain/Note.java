package my.real.test.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String contents;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(final String contents) {
        this.contents = contents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Note)) return false;

        final Note note = (Note) o;

        return new EqualsBuilder().append(getId(), note.getId()).append(getName(), note.getName()).append(getContents(),
                note.getContents()).append(getEmail(), note.getEmail()).isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contents, email);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Id", id).append("name", name)
                .append("contents", contents).append("email", email).toString();
    }
}
