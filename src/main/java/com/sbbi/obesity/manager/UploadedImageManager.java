package com.sbbi.obesity.manager;

import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sbbi.obesity.model.Pixels;

import SegmentationCluster.SegmentationTop;
import SegmentationClusterSide.SegmentationSide;

public class UploadedImageManager {

	public Pixels getRelationTopImage(String path) {

		try {
			
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

			Pixels pixels = new Pixels();
			pixels.setRelationTopLeft(relationLeft).setRelationTopRight(relationRight).setRelationTopBottom(relationBottom);

			return pixels;
		} catch (MWException e) {
			e.printStackTrace();
		}

		return new Pixels();
	}

	public Pixels getRelationSideImage(String path, Pixels pixels) {

		// path = "/home/bsilva/Desktop/sbbi/obesityApp/images/5/1498078947301"
		try {
			SegmentationSide side = new SegmentationSide();
			Object segmentationClusterSide[] = side.SegmentationClusterSide(3, path);

			Object objectSide1 = segmentationClusterSide[0];
			Object objectSide2 = segmentationClusterSide[1];
			Object objectSide3 = segmentationClusterSide[2];

			MWNumericArray arraySide1 = (MWNumericArray) objectSide1;
			MWNumericArray arraySide2 = (MWNumericArray) objectSide2;
			MWNumericArray arraySide3 = (MWNumericArray) objectSide3;

			double relationSide1 = (double) arraySide1.get(1);
			double relationSide2 = (double) arraySide2.get(1);
			double relationSide3 = (double) arraySide3.get(1);

			pixels.setRelationSide1(relationSide1).setRelationSide2(relationSide2).setRelationSide3(relationSide3);
			
		} catch (MWException e) {
			e.printStackTrace();
		}

		return pixels;
	}

}
