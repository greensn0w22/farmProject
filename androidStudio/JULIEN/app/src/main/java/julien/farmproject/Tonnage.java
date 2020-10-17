package julien.farmproject;

public class Tonnage {

    private int _usagerID;
    private int _fieldID;
    private int _nbPounds;
    private String _dateEntry;

    Tonnage(int usagerID, int fieldID, int nbPounds, String dateEntry) {
        _usagerID = usagerID;
        _fieldID = fieldID;
        _nbPounds = nbPounds;
        _dateEntry = dateEntry;
    }
}
