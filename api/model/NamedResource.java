package cat.politecnicllevant.api.model;

public class NamedResource {

    private String name;
    private String url;

    public NamedResource() {
    }

    public NamedResource(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
