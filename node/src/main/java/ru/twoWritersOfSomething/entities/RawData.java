package ru.twoWritersOfSomething.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "raw_data")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Update event;

    public RawData(Long id, Update event) {
        this.id = id;
        this.event = event;
    }

    public RawData() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Update getEvent() {
        return event;
    }

    public void setEvent(Update event) {
        this.event = event;
    }

    // В методах hashcode и equals не нужно использовать мутабельнный Long тк
    // при сохранении сущности id = null, а только в бд назначается id и потом сетится
    // в entity, что может повлечь за собой проблему потери объекта

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawData rawData = (RawData) o;
        return Objects.equals(event, rawData.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event);
    }
}
