package database.datamapper;

import database.datasource.DataSource1;
import database.entity.User;

public class DataMapperTest
{
    public static void main(String[] args)
    {
        DataMapper dm = new DataMapper(new DataSource1().getDataSource());
        
        System.out.println("getTeams: " + dm.getTeams());
        System.out.println("getTeamMembers: " + dm.getTeamMembers(1));
        System.out.println("getTeamById: " + dm.getTeam(1));
        System.out.println("getTeamByName: " + dm.getTeam("A"));
        System.out.println("getUsersAll: " + dm.getUsers());
        System.out.println("getUsersByName: " + dm.getUsers("A"));
        System.out.println("getUserByName: " + dm.getUser("Anders And"));
        System.out.println("getUserById: " + dm.getUser(1));
        System.out.println("createUser: " + dm.createUser(new User("Chip", "1234", true)));
        System.out.println("updateUser: " + dm.updateUser(new User(9, "Chap", "1234", false)));
        System.out.println("deleteUser: " + dm.deleteUser(1));
        System.out.println("deleteUser: " + dm.validateUser("Chap", "1234"));
    }
}