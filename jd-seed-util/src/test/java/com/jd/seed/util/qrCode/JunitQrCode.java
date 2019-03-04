package com.jd.seed.util.qrCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * <pre>
 * 二维码
 * 
 * </pre>
 * 
 * @author mecarlen 2019年1月23日 上午10:46:14
 */
public class JunitQrCode {
	@Test
	public void create() throws Exception{
		String contents = "安安妈妈"; // 二维码内容 
        int width = 430; // 二维码图片宽度 300
        int height = 430; // 二维码图片高度300
        String format = "png";// 二维码的图片格式 gif  
        
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
         // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        // 内容所使用字符集编码  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数  
        
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,//要编码的内容  
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,  
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,  
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION  
                BarcodeFormat.QR_CODE,  
                width, //条形码的宽度  
                height, //条形码的高度  
                hints);//生成条形码时的一些配置,此项可选  
          
        // 生成二维码  
        File outputFile = new File("d:" + File.separator + "information" + File.separator + "baby-mom.png");//指定输出路径  
          
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        
	}
	
	@Test
	public void scan() throws Exception{
		String filePath="d:" + File.separator + "information" + File.separator + "baby.png";
		BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
        hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
        String retStr = result.getText();
        System.out.println("--------------"+retStr);
	}
}
