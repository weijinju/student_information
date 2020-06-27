package com.wjj.program.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

//������֤����
public class CpachaUtil {
	
	// ��֤����Դ
	final private char[] code = {
		'2', '3', '4', '5', '6', '7', '8', '9',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
		'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 
		'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
		'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
		'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};
	//��������
	final private String[] fontNames = new String[]{
			"����", "����", "Courier", "Arial", 
			"Verdana", "Times", "Tahoma", "Georgia"};
	//������ʽ
	final private int[] fontStyles = new int[]{
			Font.BOLD, Font.ITALIC|Font.BOLD
	};
	
	//������֤��ĳ���
	private int vcodeLen = 4;
	//��֤��ͼ�������С��Ĭ��Ϊ17λ
	private int fontsize = 21;
	//��ʾ��֤��Ŀ��
	private int width = (fontsize+1)*vcodeLen+10;
	//��ʾ��֤��ĸ߶�
	private int height = fontsize+12;
	//���ø�����
	private int disturbline = 3;
	
	
	public CpachaUtil(){}
	
	//ָ����֤�볤��
	public CpachaUtil(int vcodeLen) {
		this.vcodeLen = vcodeLen;
		this.width = (fontsize+1)*vcodeLen+10;
	}
	//������֤��ͼƬ  
	public BufferedImage generatorVCodeImage(String vcode, boolean drawline){
		//������֤��ͼƬ
		BufferedImage vcodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = vcodeImage.getGraphics();
		//��䱳��ɫ
		g.setColor(new Color(246, 240, 250));
		g.fillRect(0, 0, width, height);
		if(drawline){
			drawDisturbLine(g);
		}
		//��������α�����
		Random ran = new Random();
		//��ͼƬ�ϻ���֤��
		for(int i = 0;i < vcode.length();i++){
			//��������
			g.setFont(new Font(fontNames[ran.nextInt(fontNames.length)], fontStyles[ran.nextInt(fontStyles.length)], fontsize));
			//���������ɫ
			g.setColor(getRandomColor());
			//����֤��
			g.drawString(vcode.charAt(i)+"", i*fontsize+10, fontsize+5);
		}
		//�ͷŴ�ͼ�ε��������Լ���ʹ�õ�����ϵͳ��Դ
		g.dispose();
		
		return vcodeImage;
	}
	//�����ת�������֤��ͼƬ
	public BufferedImage generatorRotateVCodeImage(String vcode, boolean drawline){
		//������֤��ͼƬ
		BufferedImage rotateVcodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = rotateVcodeImage.createGraphics();
		//��䱳��ɫ
		g2d.setColor(new Color(246, 240, 250));
		g2d.fillRect(0, 0, width, height);
		if(drawline){
			drawDisturbLine(g2d);
		}
		//��ͼƬ�ϻ���֤��
		for(int i = 0;i < vcode.length();i++){
			BufferedImage rotateImage = getRotateImage(vcode.charAt(i));
			g2d.drawImage(rotateImage, null, (int) (this.height * 0.7) * i, 0);
		}
		g2d.dispose();
		return rotateVcodeImage;
	}
	//������֤��
	public String generatorVCode(){
		int len = code.length;
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < vcodeLen;i++){
			int index = ran.nextInt(len);
			sb.append(code[index]);
		}
		return sb.toString();
	}
	//������֤��ͼƬ�ĸ�����
	private void drawDisturbLine(Graphics g){
		Random ran = new Random();
		for(int i = 0;i < disturbline;i++){
			int x1 = ran.nextInt(width);
			int y1 = ran.nextInt(height);
			int x2 = ran.nextInt(width);
			int y2 = ran.nextInt(height);
			g.setColor(getRandomColor());
			//��������
			g.drawLine(x1, y1, x2, y2);
		}
	}
	//��ȡһ����ת��ͼƬ
	private BufferedImage getRotateImage(char c){
		BufferedImage rotateImage = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotateImage.createGraphics();
		//����͸����Ϊ0
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, height, height);
		Random ran = new Random();
		g2d.setFont(new Font(fontNames[ran.nextInt(fontNames.length)], fontStyles[ran.nextInt(fontStyles.length)], fontsize));
		g2d.setColor(getRandomColor());
		double theta = getTheta();
		//��תͼƬ
		g2d.rotate(theta, height/2, height/2);
		g2d.drawString(Character.toString(c), (height-fontsize)/2, fontsize+5);
		g2d.dispose();
		
		return rotateImage;
	}
	//����һ�������ɫ
	private Color getRandomColor(){
		Random ran = new Random();
		return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220)); 
	}
	//���ò�ͬ�ĽǶ�
	private double getTheta(){
		return ((int) (Math.random()*1000) % 2 == 0 ? -1 : 1)*Math.random();
	}
	//��֤���ַ�����
	public int getVcodeLen() {
		return vcodeLen;
	}
	//������֤���ַ�����
	public void setVcodeLen(int vcodeLen) {
		this.width = (fontsize+3)*vcodeLen+10;
		this.vcodeLen = vcodeLen;
	}
	//��ȡ�����С
	public int getFontsize() {
		return fontsize;
	}
	//���������С
	public void setFontsize(int fontsize) {
		this.width = (fontsize+3)*vcodeLen+10;
		this.height = fontsize+15;
		this.fontsize = fontsize;
	}
	//ͼƬ���
	public int getWidth() {
		return width;
	}
	//����ͼƬ���
	public void setWidth(int width) {
		this.width = width;
	}
	// ͼƬ�߶�
	public int getHeight() {
		return height;
	}
	// height 
	 
	public void setHeight(int height) {
		this.height = height;
	}
	//����������
	public int getDisturbline() {
		return disturbline;
	}
	//���ø���������
	public void setDisturbline(int disturbline) {
		this.disturbline = disturbline;
	}
	
}
