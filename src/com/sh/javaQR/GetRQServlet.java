package com.sh.javaQR;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetRQServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		try
		{
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String str = request.getParameter("str");
		if (str == null || str.length() == 0)
		{
			str = "wuhongbo";
		}
		BufferedImage image = RQUtil.getRQ(str, 300);
		
		try
		{
			ImageIO.write(image, "png", response.getOutputStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
