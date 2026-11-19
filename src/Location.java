import java.util.ArrayList;
public class Location 
{
    private ArrayList<Character> spawnList;
    private ArrayList<Location> nextLocations;
    private String name;
    private String description;

    public Location(String name, String description)
    {
        setName(name);
        setDescription(description);
        spawnList = new ArrayList<Character>();
        nextLocations = new ArrayList<Location>();
    }

    public void addLocation(Location newLocation)
    {
        nextLocations.add(newLocation);
    }

    public ArrayList<Location> getLocations()
    {
        return nextLocations;
    }

    public void addSpawn(Character spawn)
    {
        spawnList.add(spawn);
    }

    public ArrayList<Character> getSpawns()
    {
        return spawnList;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;  
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
