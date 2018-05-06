package database.entity;

import database.datamapper.DataMapper;
import database.datasource.DataSource1;
import java.util.ArrayList;

public class Team
{
    private int id;
    private String name;

    public Team()
    {
    }

    public Team(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Team{" + "id=" + id + ", name=" + name + '}';
    }

    public ArrayList<User> getMembers()
    {
        return new DataMapper(new DataSource1().getDataSource()).getTeamMembers(id);
    }
}
