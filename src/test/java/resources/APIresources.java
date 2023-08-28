package resources;

public enum APIresources {
    AddPlaceAPI("maps/api/place/add/json"),
    getPlaceAPI("maps/api/place/get/json"),
    deletePlaceAPI("maps/api/place/delete/json"),
    updateplaceAPI("/maps/api/place/update/json");
    private String  resource;

    APIresources(String resource) {
        this.resource= resource;
    }
    public String getResource(){
        return resource;
    }
}
