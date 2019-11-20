package gui;

import javafx.beans.property.SimpleStringProperty;

public class Person
{
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty age;
    private SimpleStringProperty email;

    public Person(String id, String name, String age, String email)
    {
        this.id    = new SimpleStringProperty(id);
        this.name  = new SimpleStringProperty(name);
        this.age   = new SimpleStringProperty(age);
        this.email = new SimpleStringProperty(email);
    }

    public String getId()
    {
        return this.id.get();
    }

    public String getName()
    {
        return this.name.get();
    }

    public String getAge()
    {
        return this.age.get();
    }

    public String getEmail()
    {
        return this.email.get();
    }
}
