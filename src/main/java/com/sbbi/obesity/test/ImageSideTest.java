package com.sbbi.obesity.test;

import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import SegmentationClusterSide.SegmentationSide;

/*
 * Test image side integration with MATLAB
 */

public class ImageSideTest {

	public static void main(String[] args) {
		
		try {
			SegmentationSide side = new SegmentationSide();
			Object segmentationClusterSide[] = side.SegmentationClusterSide(3, "/home/bsilva/Desktop/sbbi/obesityApp/images/5/1498078947301");
			
			Object objectSide1 = segmentationClusterSide[0];
			Object objectSide2 = segmentationClusterSide[1];
			Object objectSide3 = segmentationClusterSide[2];
			
			MWNumericArray arraySide1 = (MWNumericArray) objectSide1;
			MWNumericArray arraySide2 = (MWNumericArray) objectSide2;
			MWNumericArray arraySide3 = (MWNumericArray) objectSide3;
			
			double relationSide1 = (double) arraySide1.get(1);
			double relationSide2 = (double) arraySide2.get(1);
			double relationSide3 = (double) arraySide3.get(1);
			
			System.out.println(relationSide1);
			System.out.println(relationSide2);
			System.out.println(relationSide3);
			
		} catch (MWException e) {
			e.printStackTrace();
		}
		
	}
	
}
