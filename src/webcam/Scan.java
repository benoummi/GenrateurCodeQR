package webcam;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * 
 * @author SBENOUMMI
 *
 */
public class Scan {

 @SuppressWarnings("unchecked")
public static String ReadQR(BufferedImage image) throws Exception{
 
  @SuppressWarnings("rawtypes")
  Hashtable hintMap = new Hashtable();
  hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
  
  BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource( image)));
  Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
     hintMap);
  
  return  qrCodeResult.getText();
 }
}