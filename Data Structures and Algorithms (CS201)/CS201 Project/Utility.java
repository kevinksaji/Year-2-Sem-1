	import java.io.*;
	import java.util.*;
	
	public class Utility {
	
	    // Define a threshold for Quadtree decomposition (adjust as needed)
	    private static final int THRESHOLD = 13;
	
	    private static final double VARIANCE_THRESHOLD = 50.0;
	
	    public void Compress(int[][][] pixels, String outputFileName) throws IOException {
	        // Perform Quadtree compression on original pixels
	        QuadtreeNode root = compressQuadtree(pixels, 0, 0, pixels.length, pixels[0].length);
	
	        // Serialize and save the Quadtree structure to the output file
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFileName))) {
	            oos.writeObject(root);
	        }
	    }
	
	    public int[][][] Decompress(String inputFileName) throws IOException, ClassNotFoundException {
	        // Deserialize the Quadtree structure from the input file
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFileName))) {
	            QuadtreeNode root = (QuadtreeNode) ois.readObject();
	
	            // Reconstruct the image from the Quadtree structure
	            int[][][] decompressedImage = new int[root.width][root.height][3];
	            decompressQuadtree(root, decompressedImage, 0, 0, root.width, root.height);
	
	            return decompressedImage;
	        }
	    }
	
	    private QuadtreeNode compressQuadtree(int[][][] pixels, int x, int y, int width, int height) {
	        QuadtreeNode node = new QuadtreeNode(width, height);
	    
	        if (width == 1 && height == 1) {
	            node.color = pixels[x][y];
	        } else {
	            int[] averageColor = calculateAverageColor(pixels, x, y, width, height);
	            double variance = calculateVariance(pixels, averageColor, x, y, width, height);
	    
	            if (variance < VARIANCE_THRESHOLD || width <= THRESHOLD && height <= THRESHOLD) {
	                node.isLeaf = true;
	                node.color = averageColor;
	            } else {
	                int subWidth = width / 2;
	                int subHeight = height / 2;
	                node.children = new QuadtreeNode[4];
	    
	                node.children[0] = compressQuadtree(pixels, x, y, subWidth, subHeight);
	                node.children[1] = compressQuadtree(pixels, x + subWidth, y, width - subWidth, subHeight);
	                node.children[2] = compressQuadtree(pixels, x, y + subHeight, subWidth, height - subHeight);
	                node.children[3] = compressQuadtree(pixels, x + subWidth, y + subHeight, width - subWidth, height - subHeight);
	            }
	        }
	        return node;
	    }
	    private double calculateVariance(int[][][] pixels, int[] avgColor, int x, int y, int width, int height) {
	        double sum = 0;
	    
	        for (int i = x; i < x + width; i++) {
	            for (int j = y; j < y + height; j++) {
	                for (int k = 0; k < 3; k++) {
	                    sum += Math.pow(pixels[i][j][k] - avgColor[k], 2);
	                }
	            }
	        }
	    
	        double variance = sum / (3.0 * width * height); // We divide by 3 because there are 3 color channels
	        return variance;
	    }
	    
	
	    private void decompressQuadtree(QuadtreeNode node, int[][][] pixels, int x, int y, int width, int height) {
	        if (node.isLeaf) {
	            // Fill the pixels with the stored color
	            for (int i = x; i < x + width; i++) {
	                for (int j = y; j < y + height; j++) {
	                    pixels[i][j] = node.color;
	                }
	            }
	        } else {
	            int subWidth = width / 2;
	            int subHeight = height / 2;
	
	            // Recursively decompress the quadrants
	            decompressQuadtree(node.children[0], pixels, x, y, subWidth, subHeight);
	            decompressQuadtree(node.children[1], pixels, x + subWidth, y, width - subWidth, subHeight);
	            decompressQuadtree(node.children[2], pixels, x, y + subHeight, subWidth, height - subHeight);
	            decompressQuadtree(node.children[3], pixels, x + subWidth, y + subHeight, width - subWidth, height - subHeight);
	        }
	    }
	
	    private int[] calculateAverageColor(int[][][] pixels, int x, int y, int width, int height) {
	        int[] sum = new int[3];
	
	        for (int i = x; i < x + width; i++) {
	            for (int j = y; j < y + height; j++) {
	                for (int k = 0; k < 3; k++) {
	                    sum[k] += pixels[i][j][k];
	                }
	            }
	        }
	
	        int numPixels = width * height;
	        int[] averageColor = new int[3];
	        for (int k = 0; k < 3; k++) {
	            averageColor[k] = sum[k] / numPixels;
	        }
	
	        return averageColor;
	    }
	
	    private static class QuadtreeNode implements Serializable {
	        int width, height;
	        boolean isLeaf;
	        int[] color;
	        QuadtreeNode[] children;
	    
	        public QuadtreeNode(int width, int height) {
	            this.width = width;
	            this.height = height;
	            this.isLeaf = false;
	            this.color = null;
	            this.children = null;
	        }
	    }
	}