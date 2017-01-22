import java.util.ArrayList;


public class QuadTree {
    private QuadNode root;
    private QuadTree q1;
    private QuadTree q2;
    private QuadTree q3;
    private QuadTree q4;

    public QuadTree(double ullon, double ullat, double lrlon, double lrlat, String fileName) {
        root = new QuadNode(ullon, ullat, lrlon, lrlat, fileName);
        if (root.getFileName().length() >= 7) {
            q1 = null;
            q2 = null;
            q3 = null;
            q4 = null;
            return;
        }
        double lat = (ullat + lrlat) / 2;
        double lon = (ullon + lrlon) / 2;
        q1 = new QuadTree(ullon, ullat, lon, lat, root.getFileName() + "1");
        q2 = new QuadTree(lon, ullat, lrlon, lat, root.getFileName() + "2");
        q3 = new QuadTree(ullon, lat, lon, lrlat, root.getFileName() + "3");
        q4 = new QuadTree(lon, lat, lrlon, lrlat, root.getFileName() + "4");
    }

    public int getDepth(double resolution) {
        int depth = 7;
        for (int i = 7; i >= 0; i--) {
            if ((49.41 / (Math.pow(2, i - 1))) / 288200 <= resolution) {
                depth = i;
            }
        }
        return depth;
    }

    public boolean intersects(double ulon, double ulat, double llon, double llat) {
        boolean one = (root.getUllat() <= llat);
        boolean two = (root.getLrlat() >= ulat);
        boolean three = (root.getUllon() >= llon);
        boolean four = (root.getLrlon() <= ulon);
        return !(one || two || three || four);
    }

    public ArrayList<QuadNode> raster(double r, double uo, double ua, double lo, double la) {
        ArrayList<QuadNode> imgs = new ArrayList<QuadNode>();
        int depth = getDepth(r);
        boolean intersects = intersects(uo, ua, lo, la);
        if (root == null) {
            int sdkfj = 0;
        } else if ((root.getFileName().length() == depth) && intersects) {
            imgs.add(root);
        } else if (intersects) {
            ArrayList<QuadNode> img1 = q1.raster(r, uo, ua, lo, la);
            ArrayList<QuadNode> img2 = q2.raster(r, uo, ua, lo, la);
            ArrayList<QuadNode> img3 = q3.raster(r, uo, ua, lo, la);
            ArrayList<QuadNode> img4 = q4.raster(r, uo, ua, lo, la);
            for (QuadNode n : img1) {
                imgs.add(n);
            }
            for (QuadNode n : img2) {
                imgs.add(n);
            }
            for (QuadNode n : img3) {
                imgs.add(n);
            }
            for (QuadNode n : img4) {
                imgs.add(n);
            }
        }
        return imgs;
    }
}
