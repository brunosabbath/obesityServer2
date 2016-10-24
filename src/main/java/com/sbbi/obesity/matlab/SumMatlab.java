package com.sbbi.obesity.matlab;




import com.mathworks.toolbox.javabuilder.MWException;

import SegmentationPipeline.Segmentation;
import test.Class1;

public class SumMatlab {
	
	public static void segmentation(){
		try {
			Segmentation s = new Segmentation();
			s.SegmentationPipeline();
		} catch (MWException e) {
			e.printStackTrace();
		}
	}
	
	public static String getFood(){
		
		Object result[] = null;
		
		try {
			Class1 go = new Class1();
			result = go.test(1);
		} catch (MWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result[0].toString();
	}
	
	/*public static int goMAtlab(){
		
		Object[] result = null;
		//MWNumericArray n = null;
		
		Class1 soma = null;
			try {
				
			    //n = new MWNumericArray(Double.valueOf(5),MWClassID.DOUBLE);
				soma = new Class1();
				result = soma.somar(1, 2, 3);
				
			} catch (MWException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				//MWArray.disposeArray(n);
				//MWArray.disposeArray(result);
				soma.dispose();
			}
			System.out.println(result[0]);
			MWNumericArray r = (MWNumericArray) result[0];
			return (int) r.get(1);
		
	}*/
	
}
