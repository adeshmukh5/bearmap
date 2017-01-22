import java.io.File;

public class QuadNode implements Comparable<QuadNode> {
    private double ullon, ullat, lrlon, lrlat;
    private String fileName;
    private File file;

    public QuadNode(double ullon, double ullat, double lrlon, double lrlat, String fileName) {
        this.ullon = ullon;
        this.ullat = ullat;
        this.lrlon = lrlon;
        this.lrlat = lrlat;
        if (fileName.equals("root")) {
            this.fileName = "";
        } else {
            this.fileName = fileName;
        }
        file = new File("img/" + fileName + ".png");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuadNode node = (QuadNode) o;

        if (Double.compare(node.ullon, ullon) != 0) {
            return false;
        }
        if (Double.compare(node.ullat, ullat) != 0) {
            return false;
        }
        if (Double.compare(node.lrlon, lrlon) != 0) {
            return false;
        }
        if (Double.compare(node.lrlat, lrlat) != 0) {
            return false;
        }
        if (fileName != null ? !fileName.equals(node.fileName) : node.fileName != null) {
            return false;
        }
        return file != null ? file.equals(node.file) : node.file == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(ullon);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ullat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lrlon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lrlat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }

    public double getUllon() {
        return ullon;
    }

    public double getUllat() {
        return ullat;
    }

    public double getLrlon() {
        return lrlon;
    }

    public double getLrlat() {
        return lrlat;
    }

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }

    @Override
    public int compareTo(QuadNode other) {
        if (this.ullat > other.ullat) {
            return -1;
        } else if (this.ullat < other.ullat) {
            return 1;
        } else {
            if (this.ullon > other.ullon) {
                return 1;
            } else if (this.ullon < other.ullon) {
                return -1;
            } else {
                return 0;
            }
        }

    }
}
