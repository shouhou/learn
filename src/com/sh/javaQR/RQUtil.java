package com.sh.javaQR;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.common.HybridBinarizer;

public class RQUtil
{
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 生成二维码
	 * 
	 * @author wuhongbo
	 * @param str
	 *            内容
	 * @param height
	 *            高度（px）
	 * 
	 */
	public static BufferedImage getRQ(String str, Integer height)
	{
		if (height == null || height < 100)
		{
			height = 200;
		}

		try
		{

			ByteMatrix byteMatrix = new MultiFormatWriter().encode(str,
					BarcodeFormat.QR_CODE, height, height);

			return toBufferedImage(byteMatrix);

			// 输出方式
			// 网页
			// ImageIO.write(image, "png", response.getOutputStream());

			// 文件
			// ImageIO.write(image, "png", file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 写到文件中
	 * 
	 * @author wuhongbo
	 * @param str
	 * @param height
	 * @param file
	 * @throws IOException
	 */
	public static void getRQwriteFile(String str, Integer height, File file)
			throws IOException
	{
		BufferedImage image = getRQ(str, height);
		ImageIO.write(image, "png", file);
	}

	/**
	 * 转换成图片
	 * 
	 * @author wuhongbo
	 * @param matrix
	 * @return
	 */
	private static BufferedImage toBufferedImage(ByteMatrix matrix)
	{
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				image.setRGB(x, y, matrix.get(x, y) == 0 ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 解码 各类型条码
	 */
	public static String decodeRQ(File file)
	{

		BufferedImage image;
		try
		{
			if (file == null || file.exists() == false)
			{
				throw new Exception(" File not found:" + file.getPath());
			}

			image = ImageIO.read(file);

			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Result result;

			// 解码设置编码方式为：utf-8，
			Hashtable hints = new Hashtable();
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

			result = new MultiFormatReader().decode(bitmap, hints);

			return result.getText();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws Exception
	{
		File file = new File("c://1.png");
		RQUtil.getRQwriteFile("吴宏波中华人民共和国", 200, file);

		System.out.println("-----成生成功----");
		System.out.println();

		String s = RQUtil.decodeRQ(file);

		System.out.println("-----解析成功----");
		System.out.println(s);
	}

}
