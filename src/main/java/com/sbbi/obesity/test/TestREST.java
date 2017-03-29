package com.sbbi.obesity.test;

import org.springframework.web.client.RestTemplate;

public class TestREST {

	public static void main(String[] args) {
		
		RestTemplate temp = new RestTemplate();
		
		//String str = temp.getForObject("http://127.0.0.1:5000/knn", String.class);
		//System.out.println(str);
		
		String path = "/home/bsilva/Desktop/banana2.jpg";
		
		String s = temp.postForObject("http://127.0.0.1:5000/classify", path, String.class);
		String split[] = s.split("\n");
		
		for(int i = 6; i < 11; i++){
			String parsed = parse(split[i]);
			System.out.println(parsed);
		}
		
	}

	private static String parse(String string) {
		String s[] = string.split(" ");
		return s[4];
	}
	
}


//bazel-bin/tensorflow/examples/image_retraining/retrain --image_dir sbbi/datasets/segmentedMyFruit0210/train/
//bazel-bin/tensorflow/examples/label_image/label_image --graph=/tmp/output_graph.pb --labels=/tmp/output_labels.txt --output_layer=final_result --image=/home/bsilva/Desktop/rice.png
//bazel build --config opt -c opt --copt=-mavx --copt=-mavx2 --copt=-mfma --copt=-mfpmath=both --copt=-msse4.2 tensorflow/examples/image_retraining:retrain


//a = subprocess.check_output(['bazel-bin/tensorflow/examples/label_image/label_image --graph=/tmp/output_graph.pb --labels=/tmp/output_labels.txt --output_layer=final_result --image=/home/bsilva/Desktop/rice.png'])
//import commands
//commands.getstatusoutput('./call.sh')
