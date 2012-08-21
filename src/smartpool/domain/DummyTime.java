package smartpool.domain;

public class DummyTime {
    private int id;
    private String time;

    public DummyTime() {
    }

    public DummyTime(int id, String time) {
        this.id=id;
        this.time=time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
