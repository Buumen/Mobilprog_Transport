package hu.uni.miskolc.transport;

public class TourModal {

    // túranevünk változói,
    // leírás, számok és időtartam, id.
    private String tourName;
    private String tourLength;
    private String tourTracks;
    private String tourDescription;
    private int id;

    // getter és setter metódusok létrehozása
    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourLength() {
        return tourLength;
    }

    public void setTourLength(String tourLength) {
        this.tourLength = tourLength;
    }

    public String getTourTracks() {
        return tourTracks;
    }

    public void setTourTracks(String tourTracks) {
        this.tourTracks = tourTracks;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // konstruktor
    public TourModal(String tourName, String tourLength, String tourTracks, String tourDescription) {
        this.tourName = tourName;
        this.tourLength = tourLength;
        this.tourTracks = tourTracks;
        this.tourDescription = tourDescription;
    }
}

