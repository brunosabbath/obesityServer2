package com.sbbi.obesity.matlab;

import com.mathworks.toolbox.javabuilder.MWArray;



import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import somar.Class1;

public class SumMatlab {

	public static int goMAtlab(){
		
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
		
	}
	
}
