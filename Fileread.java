package daire_data_upload;

import java.util.regex.Pattern;
import java.io.*;
//import java.lang.String;

class Fileread {
	Double fileread() throws Exception {
		//ファイル読み込み設定
		File file=new File("C:\\Users\\b1016103\\Desktop\\imuz-log.txt");
		FileInputStream input=new FileInputStream(file);
		InputStreamReader stream=new InputStreamReader(input,"Shift_JIS");
		BufferedReader buffer=new BufferedReader(stream);

		String line;
		double final_data=1.0;

		while((line=buffer.readLine())!=null) {
			//text読み込み設定し「,」で区切る
			byte[] b=line.getBytes();
			line=new String(b,"UTF-8");
			String[] value=line.split(",",0);
			double[] data=new double[value.length];

			//正規表現によって数値だけとりだしdataにぶっこむ
			for(int i=0;i<data.length;i++) {
				if(Pattern.matches("^-?[0-9]*.?[0-9]+$", value[i])){
					data[i]=Double.parseDouble(value[i]);
				}
			}
			//角度1番でかくなる加速度抽出
			if(Math.abs(data[3])<30&&Math.abs(data[4])<30&&Math.abs(data[5])<30) {
				if(data[0]!=0.0) {
					if(data[0]<final_data) {
						final_data=data[0];
					}
				}
			}
		}
		//角度計算して最大値表示
		Angle_Calculation ac=new Angle_Calculation();
		double max_angle=ac.Angle_Caluculation(final_data);;
		input.close();
		stream.close();
		buffer.close();
		return max_angle;
	}
}
