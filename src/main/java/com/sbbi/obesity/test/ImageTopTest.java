package com.sbbi.obesity.test;

import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.manager.UploadedImageManager;
import com.sbbi.obesity.model.Pixels;

import SegmentationCluster.SegmentationTop;
import SegmentationClusterSide.SegmentationSide;

/*
 * Test image top integration with MATLAB
 */

public class ImageTopTest {

	public static void main(String[] args) {

		String path = "/home/bsilva/Desktop/sbbi/obesityApp/images/5/1498078947301/1.jpg";
		path = "/home/bsilva/Desktop/pow/1.jpg";
		UploadedImageManager manager = new UploadedImageManager();
		Pixels pixels = manager.getRelationTopImage(path);
		System.out.println("done");
		
		
		/*try {
			SegmentationTop segmentationTop = new SegmentationTop();
			Object[] segmentationCluster = segmentationTop.SegmentationCluster(3, path);
			
			Object objectLeft = segmentationCluster[0];
			Object objectRight = segmentationCluster[1];
			Object objectBottom = segmentationCluster[2];
			
			MWNumericArray arrayLeft = (MWNumericArray) objectLeft;
			MWNumericArray arrayRight = (MWNumericArray) objectRight;
			MWNumericArray arrayBottom = (MWNumericArray) objectBottom;
			
			double relationLeft = (double) arrayLeft.get(1);
			double relationRight = (double) arrayRight.get(1);
			double relationBottom = (double) arrayBottom.get(1);
			
			System.out.println(relationLeft);
			System.out.println(relationRight);
			System.out.println(relationBottom);
			
			Pixels pixels = new Pixels();
			pixels.setRelationTopLeft(relationLeft).setRelationTopRight(relationRight).setRelationTopBottom(relationBottom);


		} catch (MWException e) {
			e.printStackTrace();
		}*/
				
	}

}
